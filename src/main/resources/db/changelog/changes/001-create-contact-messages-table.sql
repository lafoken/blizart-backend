-- liquibase formatted sql

-- changeset inkronsane:1 context:prod splitStatements:true endDelimiter:;
CREATE TABLE contact_messages (
                                  id BIGSERIAL PRIMARY KEY,
                                  name VARCHAR(100) NOT NULL,
                                  email VARCHAR(255) NOT NULL,
                                  phone VARCHAR(20),
                                  subject VARCHAR(255) NOT NULL,
                                  message TEXT NOT NULL,
                                  received_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()
);

-- rollback DROP TABLE contact_messages;