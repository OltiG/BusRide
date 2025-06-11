-- Insert sample cities
INSERT INTO cities (name) VALUES
('Pristina'),
('Prizren'),
('Peja'),
('Gjakova'),
('Gjilan'),
('Ferizaj'),
('Mitrovica');

-- Insert sample bus agencies
INSERT INTO bus_agencies (name, contact_number) VALUES
('Kosovo Bus', '+383 44 123 456'),
('Pristina Express', '+383 44 234 567'),
('Kosovo Travel', '+383 44 345 678');

-- Insert sample routes
INSERT INTO routes (departure_city_id, destination_city_id, distance_km, base_price) VALUES
(1, 2, 85.5, 5.00),  -- Pristina to Prizren
(1, 3, 85.0, 5.00),  -- Pristina to Peja
(1, 4, 80.0, 4.50),  -- Pristina to Gjakova
(1, 5, 45.0, 3.00),  -- Pristina to Gjilan
(1, 6, 35.0, 2.50),  -- Pristina to Ferizaj
(1, 7, 40.0, 3.00);  -- Pristina to Mitrovica

-- Insert sample schedules
INSERT INTO schedules (route_id, agency_id, departure_time, arrival_time, bus_number, available_seats, price, is_active) VALUES
(1, 1, '08:00', '09:30', 'KB001', 45, 5.00, true),  -- Pristina to Prizren
(1, 2, '10:00', '11:30', 'PE001', 40, 5.50, true),
(1, 3, '14:00', '15:30', 'KT001', 35, 5.00, true),
(2, 1, '09:00', '10:30', 'KB002', 42, 5.00, true),  -- Pristina to Peja
(2, 2, '13:00', '14:30', 'PE002', 38, 5.50, true),
(3, 1, '07:00', '08:30', 'KB003', 45, 4.50, true);  -- Pristina to Gjakova 