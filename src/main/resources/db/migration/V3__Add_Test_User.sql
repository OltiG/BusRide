-- Insert test user (password is 'password123')
INSERT INTO users (username, email, password, first_name, last_name, role)
VALUES ('testuser', 'test@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'Test', 'User', 'USER'); 