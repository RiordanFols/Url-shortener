CREATE SEQUENCE usr_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE usr
(
    id       BIGINT             NOT NULL DEFAULT nextval('usr_sequence'::regclass),
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(100)       NOT NULL,
    active   BOOLEAN            NOT NULL,
    token    VARCHAR(36) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    user_id BIGINT      NOT NULL,
    role    VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, role)
);

ALTER TABLE IF EXISTS user_role
    ADD CONSTRAINT fk_user_role_usr FOREIGN KEY (user_id) REFERENCES usr;