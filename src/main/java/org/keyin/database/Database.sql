CREATE TABLE users (
    userId SERIAL PRIMARY KEY,
    userName TEXT,
    userEmail TEXT,
    userPhoneNumber TEXT,
    userAddress TEXT,
    userPassword TEXT,
    userRole TEXT
);

CREATE TABLE memberships (
    membershipId SERIAL PRIMARY KEY,
    membershipType TEXT,
    membershipCost INT,
    membershipStatus TEXT,
    userId INT REFERENCES users(userId)
);

CREATE TABLE workoutClasses (
    workoutClassId SERIAL PRIMARY KEY,
    workoutClassName TEXT,
    trainerId REFERENCES users(userId)
);

INSERT INTO users (userName, userEmail, userPhoneNumber, userAddress, userPassword, userRole) 
VALUES 
('Jimbo Gym', 'thegymman@gmail.com', '1234567890', '123 Gym St.', 'testpassword1', 'Admin'),
('John Workout', 'johnworks@outlook.com', '0987654321', '456 Place Rd.', 'testpassword2', 'Trainer'),
('Veronica Gym', 'thegymwoman@outlook.com', '1122334455', '789 Avenue Ave.', 'testpassword3', 'Member'),
('Bobby Gains', 'bobbygains123@gmail.com', '2233445566', '987 Swole St.', 'testpassword4', 'Member'),
('Training Jane', 'janetrains@gmail.com', '3344556677', '654 Workout Dr.', 'testpassword5', 'Trainer');

INSERT INTO memberships (membershipType, membershipCost, membershipStatus, userId) 
VALUES 
('Standard', 50, 'Active', 2),
('Premium', 100, 'Active', 3),
('Standard', 50, 'Active', 4),
('Premium', 100, 'Inactive', 5);

INSERT INTO workoutClasses (workoutClassName, trainerId) 
VALUES 
('Yoga for Beginners', 2),
('HIIT (High-Intensity Interval Training)', 2),
('Advanced Pilates', 5),
('Strength Training', 5);