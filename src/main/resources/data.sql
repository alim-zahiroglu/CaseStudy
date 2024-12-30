INSERT INTO users (insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, user_name, role, pass_word)
VALUES
    ('2024-02-22 00:00:00', 1, false, '2024-02-22 00:00:00', 1, 'Alim@admin.com', 'ADMIN', 'Abc1'),
    ('2024-02-22 00:00:00', 1, false, '2024-02-22 00:00:00', 1, 'Manager@manager.com', 'MANAGER', 'Abc1'),
    ('2024-02-22 00:00:00', 1, false, '2024-02-22 00:00:00', 1, 'User@user.com', 'USER', 'Abc1');

-- Base Data for Projects (Huawei Product Lines)
INSERT INTO projects (name, config_type, insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted)
VALUES
    ( 'Huawei P Series', 'FIXED', NOW(), 1, NOW(), 1, false),
    ( 'Huawei Mate Series', 'MONTHLY', NOW(), 1, NOW(), 1, false),
    ( 'Huawei Nova Series', 'WEEKLY', NOW(), 1, NOW(), 1, false);

-- Base Data for Models (Phone Models with Percentages)
INSERT INTO model (name, is_active, project_id, fixed_percentage, monthly_percentage, weekly_percentage, insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted)
VALUES
    ( 'Huawei P60', true, 1, 50, NULL, NULL, NOW(), 1, NOW(), 1, false),  -- Fixed config
    ( 'Huawei Mate 50', true, 2, NULL, 30, NULL, NOW(), 1, NOW(), 1, false), -- Monthly config
    ('Huawei Nova 11', true, 3, NULL, NULL, 20, NOW(), 1, NOW(), 1, false); -- Weekly config

-- Base Data for Parts (Components for Phones or Cars)
INSERT INTO parts (name, quantity, model_id, insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted)
VALUES
    ('Kirin 9000 Chipset', 1, 1, NOW(), 1, NOW(), 1, false),
    ('OLED Display', 1, 2, NOW(), 1, NOW(), 1, false),
    ('Triple Camera Setup', 1, 3, NOW(), 1, NOW(), 1, false);

-- Base Data for Monthly Targets (Production Goals)
INSERT INTO monthly_target (month, target, project_id, insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted)
VALUES
    ( 'DECEMBER', 3500, 1, NOW(), 1, NOW(), 1, false),
    ('JANUARY', 5000, 1, NOW(), 1, NOW(), 1, false),
    ('FEBRUARY', 7000, 1, NOW(), 1, NOW(), 1, false),
    ( 'MARCH', 6000, 2, NOW(), 1, NOW(), 1, false),
    ( 'APRIL', 8000, 2, NOW(), 1, NOW(), 1, false),
    ('MAY', 5500, 3, NOW(), 1, NOW(), 1, false),
    ('JUNE', 7500, 3, NOW(), 1, NOW(), 1, false);
