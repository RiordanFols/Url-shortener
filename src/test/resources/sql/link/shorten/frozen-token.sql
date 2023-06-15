INSERT INTO usr(id, username, password, status, user_level)
VALUES (9000000100, 'test-username-for-getting', '12345', 'ACTIVE', 'NONE');

INSERT INTO token(value, user_id, name, created_at, status)
VALUES ('92f76c0c-2de1-4ad8-8116-10075572d564', 9000000100, 'test_token', now() at time zone 'utc', 'FROZEN');