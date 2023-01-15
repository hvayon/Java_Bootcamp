CREATE SCHEMA IF NOT EXISTS repo;

DROP TABLE IF EXISTS repo.user;

CREATE TABLE repo.user (
                           id        SERIAL PRIMARY KEY,
                           username  text,
                           password  text
);