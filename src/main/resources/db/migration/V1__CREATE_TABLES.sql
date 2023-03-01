CREATE SCHEMA crypto_schema;
CREATE SCHEMA log_schema;

CREATE TABLE crypto_schema.cryptocurrency
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    abbreviation VARCHAR(6),
    value        NUMERIC(18, 10)
);

CREATE TABLE crypto_schema.crypto_wallet
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    balance NUMERIC(18, 10) NOT NULL DEFAULT 0.00
);

CREATE TABLE crypto_schema.crypto_count
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    crypto_id        BIGINT REFERENCES crypto_schema.cryptocurrency (id),
    count            NUMERIC(18, 10) NOT NULL,
    crypto_wallet_id BIGINT REFERENCES crypto_schema.crypto_wallet (id)
);

CREATE TABLE crypto_schema.user
(
    id               UUID PRIMARY KEY,
    user_name        VARCHAR(24)  NOT NULL,
    email            VARCHAR(24)  NOT NULL,
    password         VARCHAR(256) NOT NULL,
    crypto_wallet_id BIGINT REFERENCES crypto_schema.crypto_wallet (id),
    role             VARCHAR(24) DEFAULT 'USER'
);

CREATE TABLE log_schema.operation_detail
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation_performer_id  UUID      NOT NULL,
    operation_type          VARCHAR   NOT NULL,
    balance_after_operation NUMERIC(18, 10),
    callback_time           TIMESTAMP NOT NULL
);



