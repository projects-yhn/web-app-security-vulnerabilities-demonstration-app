CREATE TABLE IF NOT EXISTS xsrf_bank_user_accounts
(
    id bigserial NOT NULL,
    type text NOT NULL,
    currency text NOT NULL,
    user_id bigint REFERENCES xsrf_bank_users(id) NOT NULL,
    amount numeric NOT NULL,
    iban text NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE xsrf_bank_user_accounts
    OWNER to "cySec";