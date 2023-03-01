INSERT INTO crypto_schema.crypto_wallet(balance)
VALUES (10000),
       (1000000);

INSERT INTO crypto_schema.user(id, user_name, password, email, crypto_wallet_id, role)
VALUES ('e4849d3b-625f-4a37-b860-e70415ae8dce', 'user',
        '$2a$16$rI5Fff7PGf3c55nnPNq3teLPaqrfQ3VGPCQOovi/u0Y4cZQ30Wysm', 'testmail@gmail.ru', 1, 'USER'),
       ('3fd610fa-315c-4813-9a64-6eb453b44f88', 'admin',
        '$2a$16$e2FlDEum1FFH2AnZYpgSg.Ak7/Cc4jAQZBfqpFWn6EOwDgQGicHUm', 'admin@gmail.ru', 2, 'ADMIN');

INSERT INTO crypto_schema.cryptocurrency(abbreviation, value)
VALUES ('BTC', 1800000),
       ('ETH', 122000),
       ('USDT', 75000),
       ('XMR', 11300);

INSERT INTO crypto_schema.crypto_count(crypto_id, count, crypto_wallet_id)
VALUES (1, 0.0001, 1),
       (1, 0.0002, 2),
       (2, 0.003, 2);

INSERT INTO log_schema.operation_detail(operation_type, balance_after_operation, callback_time, operation_performer_id)
VALUES ('Buy Crypto Currency', 1200000, '2021-02-28', '3fd610fa-315c-4813-9a64-6eb453b44f88'),
       ('Buy Crypto Currency', 1000000, '2021-03-01', '3fd610fa-315c-4813-9a64-6eb453b44f88')