-- Insert cities
INSERT INTO cities (name) VALUES 
('Prishtina'),
('Prizren'),
('Peja'),
('Gjakova'),
('Gjilan'),
('Ferizaj'),
('Mitrovica');

-- Insert bus agencies
INSERT INTO bus_agencies (name, contact_number, email) VALUES 
('Kosovo Bus Lines', '+383 44 123 456', 'info@kosovobus.com'),
('Express Travel', '+383 44 789 012', 'contact@expresstravel.com'),
('City Transport', '+383 44 345 678', 'support@citytransport.com');

-- Insert routes
INSERT INTO routes (departure_city_id, destination_city_id, distance_km, base_price) VALUES 
((SELECT id FROM cities WHERE name = 'Prishtina'), (SELECT id FROM cities WHERE name = 'Prizren'), 85.0, 5.00),
((SELECT id FROM cities WHERE name = 'Prishtina'), (SELECT id FROM cities WHERE name = 'Peja'), 80.0, 4.50),
((SELECT id FROM cities WHERE name = 'Prishtina'), (SELECT id FROM cities WHERE name = 'Gjakova'), 95.0, 6.00),
((SELECT id FROM cities WHERE name = 'Prizren'), (SELECT id FROM cities WHERE name = 'Prishtina'), 85.0, 5.00),
((SELECT id FROM cities WHERE name = 'Peja'), (SELECT id FROM cities WHERE name = 'Prishtina'), 80.0, 4.50),
((SELECT id FROM cities WHERE name = 'Gjakova'), (SELECT id FROM cities WHERE name = 'Prishtina'), 95.0, 6.00);

-- Insert schedules
INSERT INTO schedules (route_id, agency_id, departure_time, arrival_time, bus_number, available_seats, price, is_active) VALUES 
-- Prishtina to Prizren
((SELECT id FROM routes WHERE departure_city_id = (SELECT id FROM cities WHERE name = 'Prishtina') AND destination_city_id = (SELECT id FROM cities WHERE name = 'Prizren')),
 (SELECT id FROM bus_agencies WHERE name = 'Kosovo Bus Lines'),
 '08:00:00', '09:30:00', 'BUS001', 45, 5.00, true),
((SELECT id FROM routes WHERE departure_city_id = (SELECT id FROM cities WHERE name = 'Prishtina') AND destination_city_id = (SELECT id FROM cities WHERE name = 'Prizren')),
 (SELECT id FROM bus_agencies WHERE name = 'Express Travel'),
 '10:00:00', '11:30:00', 'BUS002', 40, 5.50, true),
((SELECT id FROM routes WHERE departure_city_id = (SELECT id FROM cities WHERE name = 'Prishtina') AND destination_city_id = (SELECT id FROM cities WHERE name = 'Prizren')),
 (SELECT id FROM bus_agencies WHERE name = 'City Transport'),
 '14:00:00', '15:30:00', 'BUS003', 35, 5.00, true),

-- Prishtina to Peja
((SELECT id FROM routes WHERE departure_city_id = (SELECT id FROM cities WHERE name = 'Prishtina') AND destination_city_id = (SELECT id FROM cities WHERE name = 'Peja')),
 (SELECT id FROM bus_agencies WHERE name = 'Kosovo Bus Lines'),
 '09:00:00', '10:25:00', 'BUS004', 42, 4.50, true),
((SELECT id FROM routes WHERE departure_city_id = (SELECT id FROM cities WHERE name = 'Prishtina') AND destination_city_id = (SELECT id FROM cities WHERE name = 'Peja')),
 (SELECT id FROM bus_agencies WHERE name = 'Express Travel'),
 '13:00:00', '14:25:00', 'BUS005', 38, 5.00, true),

-- Prishtina to Gjakova
((SELECT id FROM routes WHERE departure_city_id = (SELECT id FROM cities WHERE name = 'Prishtina') AND destination_city_id = (SELECT id FROM cities WHERE name = 'Gjakova')),
 (SELECT id FROM bus_agencies WHERE name = 'Kosovo Bus Lines'),
 '07:00:00', '08:40:00', 'BUS006', 44, 6.00, true),
((SELECT id FROM routes WHERE departure_city_id = (SELECT id FROM cities WHERE name = 'Prishtina') AND destination_city_id = (SELECT id FROM cities WHERE name = 'Gjakova')),
 (SELECT id FROM bus_agencies WHERE name = 'City Transport'),
 '15:00:00', '16:40:00', 'BUS007', 36, 5.50, true); 