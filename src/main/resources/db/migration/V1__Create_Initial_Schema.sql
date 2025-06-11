-- Create users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username NVARCHAR(250) NOT NULL UNIQUE,
    email NVARCHAR(250) NOT NULL UNIQUE,
    password NVARCHAR(250) NOT NULL,
    first_name NVARCHAR(250) NOT NULL,
    last_name NVARCHAR(250) NOT NULL,
    role NVARCHAR(250) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create cities table
CREATE TABLE cities (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Create bus_agencies table
CREATE TABLE bus_agencies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact_number VARCHAR(20)
);

-- Create routes table
CREATE TABLE routes (
    id BIGSERIAL PRIMARY KEY,
    departure_city_id BIGINT REFERENCES cities(id),
    destination_city_id BIGINT REFERENCES cities(id),
    distance_km DECIMAL(10,2),
    base_price DECIMAL(10,2) NOT NULL,
    UNIQUE(departure_city_id, destination_city_id)
);

-- Create schedules table
CREATE TABLE schedules (
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT REFERENCES routes(id),
    agency_id BIGINT REFERENCES bus_agencies(id),
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    bus_number VARCHAR(20) NOT NULL,
    available_seats INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    is_active BOOLEAN DEFAULT true
); 