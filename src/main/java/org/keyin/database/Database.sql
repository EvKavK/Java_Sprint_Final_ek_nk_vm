CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username TEXT,
    password TEXT,
    email TEXT,
    phone TEXT,
    address TEXT,
    city TEXT,
    province TEXT,
    postalCode TEXT,
    role TEXT
);

CREATE TABLE memberships (
    id SERIAL PRIMARY KEY,
    userID INT REFERENCES users(id) ON DELETE CASCADE,
    type TEXT,
    startDate DATE,
    endDate DATE,
    status TEXT,
    price DECIMAL(10, 2),
    durMonths INT
);

CREATE TABLE workoutClasses (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    targetArea TEXT,
    reps TEXT,
    sets TEXT,
    setDuration TEXT,
    notes TEXT,
    trainerID INT REFERENCES users(id)
);

INSERT INTO users (username, password, email, phone, address, city, province, postalCode, role) 
VALUES 
('testuser1', '$2a$12$AbfVv/fQHJAqpivWedlbm.g6sMWML.O2fVakdzjulZGu9FEkuA1AC', 'testemail1@gmail.com', '7091123344', '1 Test Ave.', 'Testville', 'NL', 'A1B 2C3', 'Admin'),
('testuser2', '$2a$12$aA4T9Hvl9NKxopta3vf.Je8zcXUQ1v3KO/kYMHYQqQ4lgf3lwQseS', 'testemail2@gmail.com', '7095567788', '2 Testing Rd.', 'Test City', 'NL', 'X8Y 9Z0', 'Member'),
('testuser3', '$2a$12$O1iMB3LndLgSJ0fp/Vo3s.RH1MmdWEx316f/rxvLlCH3FJGgwmQ8C', 'testemail3@gmail.com', '7099901122', '3 Alpha St.', 'Alpha Alpines', 'NL', 'X0X 0X0', 'Trainer'),
('testuser4', '$2a$12$BTn2z5bSZD3WPvQ.pBcVbOcIQEubFgh3LB5QkVinKONuS.7Qa6r1.', 'testemail4@gmail.com', '7093345566', '4 Beta Cv.', 'Beta Beach', 'NL', 'E7N 4V3', 'Trainer'),
('testuser5', '$2a$12$RHhYxejWd5yvVkfm/VAAA.rKoW171xbMKh/y9vhQv70o/FoWXN61G', 'testemail5@gmail.com', '7097789900', '5 Sigma Trl.', 'Sigma Shores', 'NL', 'J3S 4E2', 'Member');

INSERT INTO memberships (userID, type, startDate, endDate, status, price, durMonths) 
VALUES 
(2, 'Standard', '2025-01-01', '2025-12-31', 'Active', 50.00, 12),
(2, 'Premium', '2025-01-01', '2025-12-31', 'Active', 100.00, 12),
(3, 'Student', '2025-01-01', '2025-08-31', 'Active', 40.00, 7),
(4, 'Senior', '2024-12-01', '2025-11-30', 'Inactive', 45.00, 12),
(2, 'Premium', '2024-01-01', '2024-12-31', 'Expired', 100.00, 12);

INSERT INTO workoutClasses (name, description, targetArea, reps, sets, setDuration, notes, trainerID) -- filler data done with chatgpt
VALUES 
('Yoga for Beginners', 'Introduction to basic yoga poses and breathing techniques', 'Full Body', 'Hold poses', '1', '60 minutes', 'Bring yoga mat and water', 3),
('HIIT Training', 'High-intensity intervals with cardio and bodyweight exercises', 'Full Body', '20 reps', '4', '45 minutes', 'Suitable for intermediate fitness levels', 3),
('Core Power', 'Focus on abdominal and lower back strengthening', 'Core', '15 reps', '3', '30 minutes', 'Mat work, no equipment needed', 4),
('Power Lifting', 'Heavy weight training focusing on major compound movements', 'Full Body', '5 reps', '5', '75 minutes', 'Experience required, max weight limits apply', 4),
('Spin Class', 'Indoor cycling with intervals and hill climbs', 'Lower Body', '30-60 seconds', '6', '45 minutes', 'Bring towel and water bottle', 3),
('Flexibility Flow', 'Dynamic stretching and mobility work', 'Full Body', '10-15 reps', '2', '40 minutes', 'Great for recovery days', 4),
('Boxing Fundamentals', 'Learn basic boxing techniques and combinations', 'Upper Body', '3 minute rounds', '12', '60 minutes', 'Hand wraps required', 3);