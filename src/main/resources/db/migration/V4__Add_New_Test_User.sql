-- Insert new test user (password is 'test123')
INSERT INTO users (email, password, first_name, last_name, role, created_at)
VALUES (
    'test@example.com',
    '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', -- This is the BCrypt hash for 'test123'
    'Test',
    'User',
    'USER',
    CURRENT_TIMESTAMP
); 