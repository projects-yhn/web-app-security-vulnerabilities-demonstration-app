INSERT INTO xsrf_bank_users(email, names, username)
VALUES ('vulnerable.user@test.test', 'vulnerable user John', 'vulnerableJohn'),
       ('evil.user@hacker.hack.me', 'Attacker Evil', 'evilAttacker'),
       ('standard.user@mail.me', 'Standard user Yalco', 'standardUser');

INSERT INTO xsrf_bank_user_accounts(type, currency, user_id, amount, iban)
VALUES ('CHECKING_ACCOUNT', 'BGN', (SELECT id from xsrf_bank_users where username = 'vulnerableJohn'), 15000, 'BG645SSDF6656664844312'),
       ('SAVINGS_ACCOUNT', 'BGN', (SELECT id from xsrf_bank_users where username = 'vulnerableJohn'), 20000, 'BG645MMT45313516531807'),
       ('CHECKING_ACCOUNT', 'BGN', (SELECT id from xsrf_bank_users where username = 'standardUser'), 7000, 'BG645SSDT4531351653180'),
       ('CHECKING_ACCOUNT', 'BGN', (SELECT id from xsrf_bank_users where username = 'evilAttacker'), 1000, 'BG645EVIL1234567890127');

