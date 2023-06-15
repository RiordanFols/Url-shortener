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

INSERT INTO setting(name, type, value)
VALUES ('linkTtl', 'DURATION', 'PT24h'),
       ('lang', 'STRING', 'en');

INSERT INTO usr(id, username, password, registered_at, status, user_level)
VALUES (9000000000, 'TEST_USER', 'PASSWORD', now(), 'ACTIVE', 'NONE')