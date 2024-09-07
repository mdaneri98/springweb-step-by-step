CREATE TABLE IF NOT EXISTS users (
    userid SERIAL PRIMARY KEY,
    username varchar(255) UNIQUE NOT NULL,
    password varchar(255) NOT NULL
);
