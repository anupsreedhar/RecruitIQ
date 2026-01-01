-- Users table
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    status VARCHAR(50),
    created_at TIMESTAMP
);

-- Vendors table
CREATE TABLE IF NOT EXISTS vendors (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    status VARCHAR(50),
    created_at TIMESTAMP
);

-- Candidates table
CREATE TABLE IF NOT EXISTS candidates (
    id UUID PRIMARY KEY,
    candidate_code VARCHAR(255),
    total_experience_years INTEGER,
    primary_skills JSON,
    location VARCHAR(255),
    created_at TIMESTAMP
);

-- Candidate PII table
CREATE TABLE IF NOT EXISTS candidate_pii (
    id UUID PRIMARY KEY,
    candidate_id UUID UNIQUE,
    full_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    linkedin_url VARCHAR(255),
    CONSTRAINT fk_candidate_pii_candidate FOREIGN KEY (candidate_id) REFERENCES candidates(id)
);

-- Resume Files table
CREATE TABLE IF NOT EXISTS resume_files (
    id UUID PRIMARY KEY,
    candidate_id UUID,
    file_type VARCHAR(50),
    storage_key VARCHAR(255),
    mime_type VARCHAR(100),
    file_size BIGINT,
    checksum VARCHAR(255),
    created_at TIMESTAMP,
    CONSTRAINT fk_resume_candidate FOREIGN KEY (candidate_id) REFERENCES candidates(id)
);

-- Positions table
CREATE TABLE IF NOT EXISTS positions (
    id UUID PRIMARY KEY,
    vendor_id UUID,
    title VARCHAR(255),
    jd_text TEXT,
    jd_structured JSON,
    status VARCHAR(50),
    created_at TIMESTAMP,
    CONSTRAINT fk_position_vendor FOREIGN KEY (vendor_id) REFERENCES vendors(id)
);

-- Candidate Position Match table
CREATE TABLE IF NOT EXISTS candidate_position_match (
    id UUID PRIMARY KEY,
    candidate_id UUID,
    position_id UUID,
    alignment_score INTEGER,
    strengths JSON,
    gaps JSON,
    interview_focus JSON,
    internal_notes TEXT,
    created_at TIMESTAMP,
    CONSTRAINT fk_cpm_candidate FOREIGN KEY (candidate_id) REFERENCES candidates(id),
    CONSTRAINT fk_cpm_position FOREIGN KEY (position_id) REFERENCES positions(id)
);

-- Interview Requests table
CREATE TABLE IF NOT EXISTS interview_requests (
    id UUID PRIMARY KEY,
    vendor_id UUID,
    candidate_id UUID,
    position_id UUID,
    status VARCHAR(50),
    requested_at TIMESTAMP,
    CONSTRAINT fk_ir_vendor FOREIGN KEY (vendor_id) REFERENCES vendors(id),
    CONSTRAINT fk_ir_candidate FOREIGN KEY (candidate_id) REFERENCES candidates(id),
    CONSTRAINT fk_ir_position FOREIGN KEY (position_id) REFERENCES positions(id)
);

-- Vendor Activity Logs table
CREATE TABLE IF NOT EXISTS vendor_activity_logs (
    id UUID PRIMARY KEY,
    vendor_id UUID,
    candidate_id UUID,
    action VARCHAR(50),
    created_at TIMESTAMP,
    CONSTRAINT fk_val_vendor FOREIGN KEY (vendor_id) REFERENCES vendors(id),
    CONSTRAINT fk_val_candidate FOREIGN KEY (candidate_id) REFERENCES candidates(id)
);

-- Vendor Candidate Visibility table
CREATE TABLE IF NOT EXISTS vendor_candidate_visibility (
    id UUID PRIMARY KEY,
    vendor_id UUID,
    candidate_id UUID,
    position_id UUID,
    published_by UUID,
    published_at TIMESTAMP,
    CONSTRAINT fk_vcv_vendor FOREIGN KEY (vendor_id) REFERENCES vendors(id),
    CONSTRAINT fk_vcv_candidate FOREIGN KEY (candidate_id) REFERENCES candidates(id),
    CONSTRAINT fk_vcv_position FOREIGN KEY (position_id) REFERENCES positions(id),
    CONSTRAINT fk_vcv_user FOREIGN KEY (published_by) REFERENCES users(id)
);

-- Vendor Users table
CREATE TABLE IF NOT EXISTS vendor_users (
    id UUID PRIMARY KEY,
    vendor_id UUID,
    user_id UUID UNIQUE,
    CONSTRAINT fk_vu_vendor FOREIGN KEY (vendor_id) REFERENCES vendors(id),
    CONSTRAINT fk_vu_user FOREIGN KEY (user_id) REFERENCES users(id)
);
