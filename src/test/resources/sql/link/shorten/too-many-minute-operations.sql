UPDATE user_level
SET minute_max = 1
WHERE name = 'NONE';

INSERT INTO token(value, user_id, name, created_at, status)
VALUES ('92f76c0c-2de1-4ad8-8116-10075572d564', 9000000000, 'test_token', now() at time zone 'utc', 'ACTIVE');

INSERT INTO operation(created_at, type, token)
VALUES (now() at time zone 'utc', 'SHORTEN', '92f76c0c-2de1-4ad8-8116-10075572d564');