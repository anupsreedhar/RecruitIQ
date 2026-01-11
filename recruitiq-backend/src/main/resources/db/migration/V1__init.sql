-- Enable UUID generation
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ========================
-- Common timestamp triggers
-- ========================
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at := now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION prevent_created_at_update()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.created_at <> OLD.created_at THEN
        RAISE EXCEPTION 'created_at is immutable';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- ========================
-- USERS
-- ========================
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    role VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TRIGGER trg_users_updated_at
BEFORE UPDATE ON users
FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_users_created_guard
BEFORE UPDATE ON users
FOR EACH ROW EXECUTE FUNCTION prevent_created_at_update();

-- ========================
-- VENDORS
-- ========================
CREATE TABLE vendors (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TRIGGER trg_vendors_updated_at
BEFORE UPDATE ON vendors
FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_vendors_created_guard
BEFORE UPDATE ON vendors
FOR EACH ROW EXECUTE FUNCTION prevent_created_at_update();

-- ========================
-- CANDIDATES
-- ========================
CREATE SEQUENCE candidate_code_seq START 1;

CREATE TABLE candidates (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    candidate_code VARCHAR(20) UNIQUE,
    total_experience_years INTEGER,
    primary_skills JSONB,
    location VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE OR REPLACE FUNCTION generate_candidate_code()
RETURNS TRIGGER AS $$
BEGIN
    NEW.candidate_code :=
        'CAND-' || LPAD(nextval('candidate_code_seq')::text, 6, '0');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_candidate_code
BEFORE INSERT ON candidates
FOR EACH ROW
WHEN (NEW.candidate_code IS NULL)
EXECUTE FUNCTION generate_candidate_code();

CREATE TRIGGER trg_candidates_updated_at
BEFORE UPDATE ON candidates
FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_candidates_created_guard
BEFORE UPDATE ON candidates
FOR EACH ROW EXECUTE FUNCTION prevent_created_at_update();

-- ========================
-- CANDIDATE PII
-- ========================
CREATE TABLE candidate_pii (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    candidate_id UUID UNIQUE NOT NULL REFERENCES candidates(id) ON DELETE CASCADE,
    full_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    linkedin_url VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

-- ========================
-- RESUME FILES (MinIO metadata)
-- ========================
CREATE TABLE resume_files (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    candidate_id UUID NOT NULL REFERENCES candidates(id) ON DELETE CASCADE,
    file_type VARCHAR(50),
    storage_key VARCHAR(500) NOT NULL,
    mime_type VARCHAR(100),
    file_size BIGINT,
    checksum VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

-- ========================
-- POSITIONS
-- ========================
CREATE TABLE positions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    vendor_id UUID NOT NULL REFERENCES vendors(id),
    title VARCHAR(255) NOT NULL,
    jd_text TEXT,
    jd_structured JSONB,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TRIGGER trg_positions_updated_at
BEFORE UPDATE ON positions
FOR EACH ROW EXECUTE FUNCTION set_updated_at();

-- ========================
-- CANDIDATE POSITION MATCH
-- ========================
CREATE TABLE candidate_position_match (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    candidate_id UUID NOT NULL REFERENCES candidates(id),
    position_id UUID NOT NULL REFERENCES positions(id),
    alignment_score INTEGER,
    strengths JSONB,
    gaps JSONB,
    interview_focus JSONB,
    internal_notes TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

-- ========================
-- INTERVIEW REQUESTS
-- ========================
CREATE TABLE interview_requests (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    vendor_id UUID NOT NULL REFERENCES vendors(id),
    candidate_id UUID NOT NULL REFERENCES candidates(id),
    position_id UUID NOT NULL REFERENCES positions(id),
    status VARCHAR(50) NOT NULL,
    requested_at TIMESTAMP NOT NULL DEFAULT now()
);

-- ========================
-- VENDOR ACTIVITY LOGS
-- ========================
CREATE TABLE vendor_activity_logs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    vendor_id UUID NOT NULL REFERENCES vendors(id),
    candidate_id UUID NOT NULL REFERENCES candidates(id),
    action VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

-- ========================
-- VENDOR CANDIDATE VISIBILITY
-- ========================
CREATE TABLE vendor_candidate_visibility (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    vendor_id UUID NOT NULL REFERENCES vendors(id),
    candidate_id UUID NOT NULL REFERENCES candidates(id),
    position_id UUID NOT NULL REFERENCES positions(id),
    published_by UUID NOT NULL REFERENCES users(id),
    published_at TIMESTAMP NOT NULL DEFAULT now()
);

-- ========================
-- VENDOR USERS
-- ========================
CREATE TABLE vendor_users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    vendor_id UUID NOT NULL REFERENCES vendors(id),
    user_id UUID UNIQUE NOT NULL REFERENCES users(id)
);
