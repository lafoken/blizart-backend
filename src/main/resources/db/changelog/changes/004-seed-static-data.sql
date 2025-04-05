-- liquibase formatted sql

-- changeset inkronsane:4 context:prod splitStatements:true endDelimiter:;
INSERT INTO company_details (
    id, full_name, edrpou, legal_address, director_name, founding_date, history_summary, status,
    parent_company_name, parent_company_address, parent_company_website_url
) VALUES (
             1,
             'Товариство з обмеженою відповідальністю "Бліцарт Продукціон"',
             '34154698',
             '89502, Закарпатська область, Ужгородський район, м. Чоп, вул. Загородня, буд. 4а',
             'Костур Максим Михайлович',
             '27 лютого 2006 року',
             'Засновано 27.02.2006 як дочірнє підприємство австрійської Blizzard Sport GmbH зі 100% іноземними інвестиціями для виробництва високоякісних лиж на експорт.',
             'Зареєстровано, не перебуває в процесі припинення',
             'Blizzard Sport GmbH',
             'A-5730 Mittersill, Klausgasse 32, Австрія',
             'https://www.blizzard.ski/'
         ) ON CONFLICT (id) DO NOTHING;

INSERT INTO production_info (
    id, main_activity_description, export_percentage, export_markets, facility_location,
    facility_advantages, production_shifts, technology_standards, supply_chain_notes
) VALUES (
             1,
             'Основна діяльність ТОВ "Бліцарт Продукціон" (КВЕД 32.30) - виробництво спортивних товарів, зокрема, високоякісних гірських лиж.',
             100,
             'Австрія та країни Європейського Союзу',
             'м. Чоп, Закарпатська область',
             'Близькість до кордонів ЄС;Зручна логістика;Доступ до кваліфікованої робочої сили', -- Example delimiter: ;
             'Двозмінний режим по 8 годин',
             'Використання передових технологій та високих стандартів якості Blizzard Sport GmbH.',
             'Імпорт сировини та компонентів з країн ЄС. Співпраця з українськими та європейськими постачальниками.'
         ) ON CONFLICT (id) DO NOTHING;

INSERT INTO career_environment (id, description) VALUES (
                                                            1,
                                                            'ТОВ "Бліцарт Продукціон" пропонує стабільне робоче середовище з офіційним працевлаштуванням та прагне створити комфортні умови для роботи та розвитку.'
                                                        ) ON CONFLICT (id) DO NOTHING;

INSERT INTO career_benefits (career_environment_id, icon, title, details) VALUES
                                                                              (1, 'bus', 'Безкоштовний транспорт', 'Маршрути: Ужгород–Чоп, Чинадійово–Мукачево–Чоп'),
                                                                              (1, 'coffee', 'Субсидоване харчування', 'Доступне харчування в їдальні підприємства'),
                                                                              (1, 'clock', 'Двозмінний режим', '8-годинні зміни без понаднормових'),
                                                                              (1, 'award', 'Кар''єрне зростання', 'Можливості для професійного розвитку')
    ON CONFLICT DO NOTHING;

INSERT INTO social_responsibility_info (
    id, introduction, local_impact_description, local_impact_key_points,
    economic_contribution_description, economic_contribution_areas,
    employee_support_description, employee_support_programs
) VALUES (
             1,
             'ТОВ "Бліцарт Продукціон" усвідомлює свою роль у суспільстві та прагне позитивно впливати на місцеву громаду, економіку регіону та життя наших працівників.',
             'ТОВ "Бліцарт Продукціон" є значним роботодавцем у місті Чоп та Закарпатській області, створюючи сотні робочих місць та позитивно впливаючи на соціально-економічну ситуацію в регіоні.',
             'Створення сотень робочих місць;Сприяння економічному розвитку регіону;Підтримка місцевих постачальників;Розвиток інфраструктури',
             'Наша діяльність здійснює значний позитивний вплив на місцеву економіку через виробництво, експорт та сплату податків.',
             '[{"title":"Виробництво","description":"Збільшення промислового потенціалу регіону, сприяння технологічному прогресу."}, {"title":"Експорт","description":"Позитивний вплив на торговельний баланс України, приток іноземної валюти."}, {"title":"Податки","description":"Надходження до місцевого та державного бюджетів для розвитку інфраструктури та соціальних програм."}]', -- Example storing as JSON string
             'Ми приділяємо велику увагу підтримці своїх працівників, адже наш успіх залежить від їх добробуту.',
             'Безкоштовний транспорт для співробітників;Субсидоване харчування;Офіційне працевлаштування з усіма перевагами;Можливості навчання та професійного розвитку'
         ) ON CONFLICT (id) DO NOTHING;

-- No initial vacancies seeded

-- rollback DELETE FROM social_responsibility_info WHERE id = 1; DELETE FROM career_benefits WHERE career_environment_id = 1; DELETE FROM career_environment WHERE id = 1; DELETE FROM production_info WHERE id = 1; DELETE FROM company_details WHERE id = 1;