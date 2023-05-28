CREATE SEQUENCE operation_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE operation
(
    id         BIGINT       NOT NULL DEFAULT nextval('operation_sequence'::regclass),
    created_at TIMESTAMP    NOT NULL,
    type       VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);