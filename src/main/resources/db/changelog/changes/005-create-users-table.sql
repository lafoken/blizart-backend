-- liquibase formatted sql

-- changeset inkronsane:5 context:prod splitStatements:true endDelimiter:;
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL
);

-- rollback DROP TABLE users;