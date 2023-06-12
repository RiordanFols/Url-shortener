INSERT INTO usr(id, username, password, active)
VALUES (9000000100, 'test-username', '12345', true);

INSERT INTO token(value, user_id, name, created_at)
VALUES ('92f76c0c-2de1-4ad8-8116-10075572d564', 9000000100, 'test_token', now());