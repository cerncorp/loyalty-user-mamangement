CREATE TABLE IF NOT EXISTS Users(
    id SERIAL PRIMARY KEY,                  -- For the 'Long' type SERIAL  BIGINT
    user_name VARCHAR(255), -- NOT NULL,         -- For 'userName'
    first_name VARCHAR(255), -- NOT NULL,        -- For 'firstName'
    last_name VARCHAR(255), -- NOT NULL,         -- For 'lastName'
    first_name_khr VARCHAR(255),            -- For 'firstNameKhr'
    last_name_khr VARCHAR(255),             -- For 'lastNameKhr'
    identify_type VARCHAR(50),              -- For 'identifyType'
    id_number VARCHAR(50),                  -- For 'idNumber'
    dob TIMESTAMP,                           -- For 'dob' as a timestamp
    gender VARCHAR(10),                     -- For 'gender'
    phone_number VARCHAR(20),               -- For 'phoneNumber'
    address TEXT,                            -- For 'address'
    email VARCHAR(255) UNIQUE               -- For 'email', ensuring it's unique
);
-- DELETE FROM Users;
