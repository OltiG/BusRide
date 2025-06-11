-- Insert new admin user (password is 'admin123')
INSERT INTO users (username, email, password, first_name, last_name, role)
VALUES ('superadmin', 'superadmin@admin.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'Super', 'Admin', 'ADMIN'); 