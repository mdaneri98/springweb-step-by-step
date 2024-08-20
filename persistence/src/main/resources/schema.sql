CREATE TABLE IF NOT EXISTS users (
  userId SERIAL PRIMARY KEY,
  username varchar(255) UNIQUE NOT NULL
);

INSERT INTO users (username) VALUES ('new_user');
