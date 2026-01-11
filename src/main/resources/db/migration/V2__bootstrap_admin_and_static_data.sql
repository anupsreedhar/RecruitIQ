-- =====================================
-- BOOTSTRAP AGENCY ADMIN USER
-- =====================================
INSERT INTO users (
    email,
    password_hash,
    role,
    status
)
VALUES (
    'admin@recruitiq.com',
    crypt('recruitiq', gen_salt('bf', 10)),
    'AGENCY_ADMIN',
    'ACTIVE'
)
ON CONFLICT (email) DO NOTHING;

-- =====================================
-- OPTIONAL: STATIC ROLES / LOOKUPS
-- =====================================
-- Example:
-- INSERT INTO roles (code, description) VALUES (...)
