-- liquibase formatted sql

-- changeset inkronsane:8 context:prod splitStatements:true endDelimiter:;
-- Додавання статусу та метаданих до вакансій

ALTER TABLE vacancies
    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
ADD COLUMN assigned_to VARCHAR(100) NULL,
ADD COLUMN filled_date DATE NULL,
ADD COLUMN archived_date DATE NULL;

-- Додаємо індекс для пошуку за статусом (опціонально, але корисно)
CREATE INDEX idx_vacancies_status ON vacancies(status);

-- Оновлюємо статус існуючих тестових вакансій (якщо вони є) на ACTIVE
UPDATE vacancies SET status = 'ACTIVE' WHERE id LIKE 'test-vacancy-%';


-- rollback ALTER TABLE vacancies DROP COLUMN archived_date;
-- rollback ALTER TABLE vacancies DROP COLUMN filled_date;
-- rollback ALTER TABLE vacancies DROP COLUMN assigned_to;
-- rollback ALTER TABLE vacancies DROP COLUMN status;
-- rollback DROP INDEX IF EXISTS idx_vacancies_status;