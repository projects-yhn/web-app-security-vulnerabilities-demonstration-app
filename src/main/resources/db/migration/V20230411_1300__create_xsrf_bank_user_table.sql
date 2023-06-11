CREATE TABLE IF NOT EXISTS xsrf_bank_users
(
    id bigserial NOT NULL,
    email text NOT NULL,
    names text NOT NULL,
    username text NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE xsrf_bank_users
    OWNER to "cySec";