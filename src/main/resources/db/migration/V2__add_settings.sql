CREATE TABLE setting
(
    name  VARCHAR(255) NOT NULL,
    type  VARCHAR(30)  NOT NULL,
    value VARCHAR(255) NOT NULL,
    PRIMARY KEY (name)
);

INSERT INTO setting(name, type, value)
VALUES (1, 'DURATION', '1D');