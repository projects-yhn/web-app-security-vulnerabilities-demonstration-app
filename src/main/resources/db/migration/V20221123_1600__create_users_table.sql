CREATE TABLE IF NOT EXISTS users
(
    id bigserial NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    creation_ts timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
    );

ALTER TABLE users
    OWNER to "cySec";