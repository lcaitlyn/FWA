CREATE SCHEMA IF NOT EXISTS cinema;

CREATE TABLE IF NOT EXISTS cinema.users (
    ID SERIAL PRIMARY KEY ,
    FirstName varchar(256) NOT NULL ,
    SecondName varchar(256),
    Password varchar(256) NOT NULL ,
    Email varchar(256) NOT NULL
);