ALTER TABLE xsrf_wire_transfer_transactions
    ADD COLUMN transaction_status text NOT NULL DEFAULT 'PENDING';