CREATE TABLE IF NOT EXISTS sql_injection_user_info
(
    id bigserial NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    address text NOT NULL,
    phone text NOT NULL,
    email text NOT NULL,
    card_number text,
    username text NOT NULL,
    password text NOT null,
    PRIMARY KEY(id)
    );

CREATE TABLE IF NOT EXISTS sql_injection_movies
(
    id bigserial NOT NULL,
    title text NOT NULL,
    director text NOT NULL,
    year integer NOT NULL,
    genre text NOT NULL,
    PRIMARY KEY(id)
    );
ALTER TABLE sql_injection_movies
    OWNER to "cySec";

INSERT INTO sql_injection_user_info(first_name, last_name, address, phone, email, card_number, username, password)
VALUES ('John', 'Doe', 'USA', '123456789', 'john.doe@unknow.com', '5545454565', 'johnny', 'jo1234'),
       ('Steve', 'Clark', 'Canada', '9876543210', 'steve.clark@sss.ca', null, 'steeve', 'st4444'),
       ('Angel', 'Karmazinski', 'Russia', '33333333333', 'angel@mail.ru', null, 'angelo', 'angelo89'),
       ('Mike', 'George', 'UK', '444444444444','mike.@mail.co.uk', '5265455997', 'Michelangelo', 'mmmm44');

INSERT INTO sql_injection_movies(title, director, year, genre)
VALUES('The Godfather', 'Francis Ford Coppola', 1972, 'Crime, Drama'),
       ('12 Angry Men', 'Sidney Lumet', 1957, 'Crime, Drama'),
       ('The Matrix', 'Lana Wachowski and Lilly Wachowski', 1999, 'Action, Sci-FI')