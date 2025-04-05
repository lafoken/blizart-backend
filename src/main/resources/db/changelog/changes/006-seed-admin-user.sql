-- liquibase formatted sql

-- changeset inkronsane:6 context:prod splitStatements:true endDelimiter:;
-- Пароль 'admin', хешований BCrypt
INSERT INTO users (id, username, password, role) VALUES (
                                                            1,
                                                            'admin',
                                                            '$2a$10$IXeUUsKFCfRV4R/0ae6esOrZ8R/X5xiV4l53mItGpPsbZPEtnRSeK',
                                                            'ADMIN'
                                                        ) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password, role = EXCLUDED.role;

-- rollback DELETE FROM users WHERE username = 'admin';