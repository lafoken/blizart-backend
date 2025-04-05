-- liquibase formatted sql

-- changeset inkronsane:3 context:prod splitStatements:true endDelimiter:;
CREATE TABLE career_benefits (
                                 id BIGSERIAL PRIMARY KEY,
                                 career_environment_id BIGINT NOT NULL,
                                 icon VARCHAR(50),
                                 title VARCHAR(100) NOT NULL,
                                 details VARCHAR(255),
                                 CONSTRAINT fk_career_environment
                                     FOREIGN KEY(career_environment_id)
                                         REFERENCES career_environment(id)
                                         ON DELETE CASCADE
);

CREATE TABLE vacancies (
                           id VARCHAR(50) PRIMARY KEY, -- Assuming ID might be non-numeric later
                           title VARCHAR(255) NOT NULL,
                           short_description TEXT,
                           requirements TEXT -- Simple storage for list as text
);

-- rollback DROP TABLE vacancies; DROP TABLE career_benefits;