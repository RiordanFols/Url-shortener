CREATE TABLE token
(
    value      VARCHAR(36) UNIQUE NOT NULL,
    user_id    BIGINT             NOT NULL,
    name       VARCHAR(50)        NOT NULL,
    created_at TIMESTAMP          NOT NULL,
    PRIMARY KEY (value)
);

ALTER TABLE IF EXISTS token
    ADD CONSTRAINT fk_token_usr FOREIGN KEY (user_id) REFERENCES usr;