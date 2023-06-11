CREATE TABLE IF NOT EXISTS xsrf_wire_transfer_transactions
(
    id bigserial NOT NULL,
    transaction_id text NOT NULL,
    sender_id bigint REFERENCES xsrf_bank_users(id) NOT NULL,
    sender_account_id bigint REFERENCES xsrf_bank_user_accounts(id) NOT NULL,
    amount numeric NOT NULL,
    amount_currency text NOT NULL,
    receiver_names text NOT NULL,
    receiver_iban text NOT NULL,
    description text NOT NULL,
    creation_ts timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE xsrf_wire_transfer_transactions
    OWNER to "cySec";