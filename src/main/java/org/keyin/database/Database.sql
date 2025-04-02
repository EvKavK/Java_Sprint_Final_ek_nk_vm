CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    user_name TEXT,
    user_email TEXT,
    user_phoneNumber TEXT,
    user_address TEXT,
    user_password TEXT,
    user_role TEXT
);

CREATE TABLE memberships (
    membership_id SERIAL PRIMARY KEY,
    membership_type TEXT,
    membership_cost INT,
    membership_status TEXT,
    member_id INT REFERENCES users(user_id)
);

CREATE TABLE workoutClasses (
    workoutClass_id SERIAL PRIMARY KEY,
    workoutClass_name TEXT,
    trainer_id REFERENCES users(user_id)
);

INSERT INTO users (user_name, user_email, user_phoneNumber, user_address, user_password, user_role) 
VALUES 
('Jimbo Gym', 'thegymman@gmail.com', '1234567890', '123 Gym St.', 'testpassword1', 'Admin'),
('John Workout', 'johnworks@outlook.com', '0987654321', '456 Place Rd.', 'testpassword2', 'Trainer'),
('Veronica Gym', 'thegymwoman@outlook.com', '1122334455', '789 Avenue Ave.', 'testpassword3', 'Member'),
('Bobby Gains', 'bobbygains123@gmail.com', '2233445566', '987 Swole St.', 'testpassword4', 'Member'),
('Training Jane', 'janetrains@gmail.com', '3344556677', '654 Workout Dr.', 'testpassword5', 'Trainer');

INSERT INTO memberships (membership_type, membership_cost, membership_status, member_id) 
VALUES 
('Standard', 50, 'Active', 2),
('Premium', 100, 'Active', 3),
('Standard', 50, 'Active', 4),
('Premium', 100, 'Inactive', 5);

INSERT INTO workoutClasses (workoutClass_name, trainer_id) 
VALUES 
('Yoga for Beginners', 2),
('HIIT (High-Intensity Interval Training)', 2),
('Advanced Pilates', 5),
('Strength Training', 5);