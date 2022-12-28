CREATE TABLE IF NOT EXISTS sql_injection_user_info
(
    id
    bigserial
    NOT
    NULL,
    first_name
    text
    NOT
    NULL,
    last_name
    text
    NOT
    NULL,
    address
    text
    NOT
    NULL,
    phone
    text
    NOT
    NULL,
    email
    text
    NOT
    NULL,
    card_number
    text,
    username
    text
    NOT
    NULL,
    PRIMARY
    KEY
(
    id
)
    );

ALTER TABLE sql_injection_user_info
    OWNER to "cySec";

INSERT INTO sql_injection_user_info(first_name, last_name, address, phone, email, card_number, username)
VALUES ('John', 'Doe', 'USA', '123456789', 'john.doe@unknow.com', '5545454565', 'johnny'),
       ('Steve', 'Clark', 'Canada', '9876543210', 'steve.clark@sss.ca', null, 'steeve'),
       ('Angel', 'Karmazinski', 'Russia', '33333333333', 'angel@mail.ru', null, 'angelo'),
       ('Mike', 'George', 'UK', '444444444444','mike.@mail.co.uk', '5265455997', 'Michelangelo');