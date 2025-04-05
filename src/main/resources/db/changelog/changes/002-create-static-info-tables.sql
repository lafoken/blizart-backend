-- liquibase formatted sql

-- changeset inkronsane:2 context:prod splitStatements:true endDelimiter:;
CREATE TABLE company_details (
                                 id BIGSERIAL PRIMARY KEY,
                                 full_name VARCHAR(255) NOT NULL,
                                 edrpou VARCHAR(10) NOT NULL UNIQUE,
                                 legal_address VARCHAR(255) NOT NULL,
                                 director_name VARCHAR(100),
                                 founding_date VARCHAR(50),
                                 history_summary TEXT,
                                 status VARCHAR(100),
                                 parent_company_name VARCHAR(255),
                                 parent_company_address VARCHAR(255),
                                 parent_company_website_url VARCHAR(255)
);

CREATE TABLE production_info (
                                 id BIGSERIAL PRIMARY KEY,
                                 main_activity_description TEXT,
                                 export_percentage INT,
                                 export_markets VARCHAR(255),
                                 facility_location VARCHAR(255),
                                 facility_advantages TEXT, -- Simple storage for list as text
                                 production_shifts VARCHAR(100),
                                 technology_standards TEXT,
                                 supply_chain_notes TEXT
);

CREATE TABLE career_environment (
                                    id BIGSERIAL PRIMARY KEY,
                                    description TEXT
);

CREATE TABLE social_responsibility_info (
                                            id BIGSERIAL PRIMARY KEY,
                                            introduction TEXT,
                                            local_impact_description TEXT,
                                            local_impact_key_points TEXT, -- Simple storage for list as text
                                            economic_contribution_description TEXT,
                                            economic_contribution_areas TEXT, -- Store JSON or delimited text
                                            employee_support_description TEXT,
                                            employee_support_programs TEXT -- Simple storage for list as text
);

-- rollback DROP TABLE social_responsibility_info; DROP TABLE career_environment; DROP TABLE production_info; DROP TABLE company_details;