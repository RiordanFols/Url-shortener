CREATE TABLE user_level
(
    name       VARCHAR(30) UNIQUE NOT NULL,
    minute_max BIGINT             NOT NULL,
    month_max  BIGINT             NOT NULL,
    price      NUMERIC            NOT NULL,
    PRIMARY KEY (name)
);

INSERT INTO user_level (name, minute_max, month_max, price)
VALUES ('NONE', 6, 1000, 0.00),
       ('BRONZE', 60, 1000000, 100.00),
       ('SILVER', 600, 10000000, 250.00),
       ('GOLDEN', 6000, 100000000, 1000.00);

ALTER TABLE usr
    ADD COLUMN user_level VARCHAR(30) NOT NULL DEFAULT 'NONE';
