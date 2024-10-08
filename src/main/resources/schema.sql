--  This code is used to create the tables in the database
-- This code will generate when the application is started

DROP TABLE IF EXISTS code;

CREATE TABLE IF NOT EXISTS code (
  id SERIAL PRIMARY KEY,
  tech_stack VARCHAR(255) NOT NULL,
  duration INT NOT NULL,
  description VARCHAR(255) NOT NULL,
  location VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);