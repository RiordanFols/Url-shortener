DELETE
FROM token;

DELETE
FROM user_role;

DELETE
FROM usr;

DELETE
FROM operation;

DELETE
FROM setting;

DELETE
FROM user_level;

INSERT INTO setting(name, type, value)
VALUES ('linkTtl', 'DURATION', 'PT24h'),
       ('lang', 'STRING', 'en');

INSERT INTO user_level (name, minute_max, month_max, price)
VALUES ('NONE', 6, 1000, 0.00),
       ('BRONZE', 60, 1000000, 100.00),
       ('SILVER', 600, 10000000, 250.00),
       ('GOLDEN', 6000, 100000000, 1000.00);

INSERT INTO usr(id, username, password, registered_at, status, user_level)
VALUES (9000000000, 'TEST_USER', '$2a$08$IRcWo0LmOXvolKAVn0x9FeUEcXkRvsMoBxzAdOfiF2wR6iQBLvHi.',
        now() at time zone 'utc', 'ACTIVE', 'NONE');