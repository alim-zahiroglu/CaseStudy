INSERT INTO users (insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, user_name, role, pass_word)
VALUES
    ('2024-02-22 00:00:00', 1, false, '2024-02-22 00:00:00', 1, 'Alim@admin.com', 'ADMIN', 'Abc1'),
    ('2024-02-22 00:00:00', 1, false, '2024-02-22 00:00:00', 1, 'Manager@manager.com', 'MANAGER', 'Abc1'),
    ('2024-02-22 00:00:00', 1, false, '2024-02-22 00:00:00', 1, 'User@user.com', 'USER', 'Abc1');

-- Base Data for Projects (Huawei Product Lines)
INSERT INTO projects (id, name, config_type, insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted)
VALUES
    (1, 'Huawei P Series', 'FIXED', NOW(), 1, NOW(), 1, false),
    (2, 'Huawei Mate Series', 'MONTHLY', NOW(), 1, NOW(), 1, false),
    (3, 'Huawei Nova Series', 'WEEKLY', NOW(), 1, NOW(), 1, false);

-- Base Data for Models (Phone Models with Percentages)
INSERT INTO model (id, name, is_active, project_id, fixed_percentage, monthly_percentage, weekly_percentage, insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted)
VALUES
    (1, 'Huawei P60', true, 1, 50, NULL, NULL, NOW(), 1, NOW(), 1, false),  -- Fixed config
    (2, 'Huawei Mate 50', true, 2, NULL, 30, NULL, NOW(), 1, NOW(), 1, false), -- Monthly config
    (3, 'Huawei Nova 11', true, 3, NULL, NULL, 20, NOW(), 1, NOW(), 1, false); -- Weekly config

-- Base Data for Parts (Components for Phones or Cars)
INSERT INTO parts (id, name, quantity, model_id, insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted)
VALUES
    (1, 'Kirin 9000 Chipset', 1, 1, NOW(), 1, NOW(), 1, false),
    (2, 'OLED Display', 1, 2, NOW(), 1, NOW(), 1, false),
    (3, 'Triple Camera Setup', 1, 3, NOW(), 1, NOW(), 1, false);

-- Base Data for Monthly Targets (Production Goals)
INSERT INTO monthly_target (id, month, target, project_id, insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, is_deleted)
VALUES
    (1, 'JANUARY', 5000, 1, NOW(), 1, NOW(), 1, false),
    (2, 'FEBRUARY', 7000, 1, NOW(), 1, NOW(), 1, false),
    (3, 'MARCH', 6000, 2, NOW(), 1, NOW(), 1, false),
    (4, 'APRIL', 8000, 2, NOW(), 1, NOW(), 1, false),
    (5, 'MAY', 5500, 3, NOW(), 1, NOW(), 1, false),
    (6, 'JUNE', 7500, 3, NOW(), 1, NOW(), 1, false);
