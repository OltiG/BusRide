-- Insert Admin User (password hashed as 'adminpass' with BCrypt encoded value for simplicity)
-- Use your own hashed password here
INSERT INTO users (id, username, email, password, role)
VALUES (
           1,
           'adminuser',
           'admin@example.com',
           '$2a$10$DOWSDubVp9RSjKzQor2cWuC6UzMvHZHOA9WxoQi38vWAK0C8g7klK',
           'ADMIN'
       )
ON CONFLICT (id) DO NOTHING;


-- Insert Agency
INSERT INTO agencies (id, agency_name)
VALUES
    (1, 'Prime Transit')
    ON CONFLICT (id) DO NOTHING;

-- Insert Bus linked to Agency
INSERT INTO bus (id, bus_brand, licence_plate, agency_id)
VALUES
    (1, 'Volvo', 'AB123CD', 1)
    ON CONFLICT (id) DO NOTHING;

-- Insert Route linked to Bus
INSERT INTO route (id, departure_city, destination_city, price_per_ticket, departure_time, bus_id)
VALUES
    (1, 'New York', 'Boston', 49.99, '08:00:00', 1)
    ON CONFLICT (id) DO NOTHING;