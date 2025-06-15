-- Create database if it doesn't exist
CREATE DATABASE warmhouse;

-- Connect to the warmhouse database
\c warmhouse;

-- User table
CREATE TABLE IF NOT EXISTS _user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL
);

-- Location table
CREATE TABLE IF NOT EXISTS location (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    timezone VARCHAR(6) NOT NULL,
    user_id BIGINT REFERENCES _user(id) ON DELETE CASCADE
);

-- Device Group table (minimal)
CREATE TABLE IF NOT EXISTS device_group (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location_id BIGINT REFERENCES location(id) ON DELETE CASCADE
);

-- Device Type table
CREATE TABLE IF NOT EXISTS device_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    unit_type VARCHAR(100) NOT NULL
);

-- Device table
CREATE TABLE IF NOT EXISTS device (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    connected BOOLEAN NOT NULL,
    active BOOLEAN NOT NULL,
    target_value VARCHAR(50),
    actual_value VARCHAR(50),
    date_time_updated TIMESTAMPTZ,
    type_id BIGINT REFERENCES device_type(id) ON DELETE SET NULL,
    user_id BIGINT REFERENCES _user(id) ON DELETE SET NULL,
    device_group_id BIGINT REFERENCES device_group(id) ON DELETE SET NULL
);

-- Telemetry data table
CREATE TABLE IF NOT EXISTS telemetry_data (
    id SERIAL PRIMARY KEY,
    date_time TIMESTAMPTZ NOT NULL,
    active BOOLEAN NOT NULL,
    value VARCHAR(50) NOT NULL,
    unit_type VARCHAR(100) NOT NULL,
    device_id BIGINT REFERENCES device(id) ON DELETE NO ACTION
);