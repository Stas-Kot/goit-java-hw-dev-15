CREATE TABLE users (
--    id IDENTITY PRIMARY KEY,
    name VARCHAR(200) PRIMARY KEY,
    password VARCHAR(100),
    role enum('ROLE_USER', 'ROLE_ADMIN') NOT NULL
);

CREATE TABLE notes (
    note_id IDENTITY PRIMARY KEY,
    user_name VARCHAR(200) NOT NULL,
    title VARCHAR(500),
    content VARCHAR(2000),
    FOREIGN KEY(user_name) REFERENCES users(name) ON DELETE CASCADE
);