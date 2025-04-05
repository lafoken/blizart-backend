-- liquibase formatted sql

-- changeset inkronsane:7 context:prod splitStatements:true endDelimiter:;
-- Додавання тестових вакансій для діагностики

INSERT INTO vacancies (id, title, short_description, requirements) VALUES (
    'test-vacancy-1',
    'Тестова Вакансія 1 (Майстер Йода)',
    'Шукаємо досвідченого джедая для навчання падаванів.',
    'Володіння Силою;Зелений колір шкіри (бажано);Досвід > 800 років'
) ON CONFLICT (id) DO NOTHING;

INSERT INTO vacancies (id, title, short_description, requirements) VALUES (
    'test-vacancy-2',
    'Тестова Вакансія 2 (Оператор)',
    'Оператор виробничої лінії лиж.',
    'Уважність;Відповідальність;Готовність навчатись'
) ON CONFLICT (id) DO NOTHING;

-- rollback DELETE FROM vacancies WHERE id IN ('test-vacancy-1', 'test-vacancy-2');
