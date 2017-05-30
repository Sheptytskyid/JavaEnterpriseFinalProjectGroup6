--   ----------------------------------------------------------------------------------------------
-- Fill user_types table
--   ----------------------------------------------------------------------------------------------
INSERT INTO user_types (id, name) VALUES(1, 'INVESTOR');
-- (2, 'ENTREPRENEUR'),(3, 'CONSULTANT');

-- --   ----------------------------------------------------------------------------------------------
-- -- Fill roles table
-- --   ----------------------------------------------------------------------------------------------
INSERT INTO roles (id, name) VALUES(1, 'ROLE_USER'),(2, 'ROLE_ADMIN');

-- --   ----------------------------------------------------------------------------------------------
-- -- Fill users table with user PASSWORDS = {admin, useruser}
-- --   ----------------------------------------------------------------------------------------------
INSERT INTO users (id, type_id, name, email, password) VALUES(1, 1, 'Admin', 'admin@admin.admin', '$2a$10$3NAgFi3tu7iO76jnzKdxg.dT0YbiDpoyPbcKPtIC7iupjmc3ziFiu');
-- (2, 3, 'User', 'user@user.user', '$2a$10$DDb4kmE5su.kXx7ku11slubKGBoQfEaCdSxIGokN7QUa46ync48o2');

-- --   ----------------------------------------------------------------------------------------------
-- -- Fill users_roles table with user PASSWORD = admin
-- --   ----------------------------------------------------------------------------------------------
INSERT INTO users_roles (user_id, role_id) VALUES(1, 2);
-- (2, 1);

-- --   ----------------------------------------------------------------------------------------------
-- --  Fill categories table
-- --   ----------------------------------------------------------------------------------------------
INSERT INTO categories (id, name) VALUES(1, 'CATEGORY 1');
-- (2, 'CATEGORY 2'),(3, 'CATEGORY 3');

-- --   ----------------------------------------------------------------------------------------------
-- --  Fill investment_interest table
-- --   ----------------------------------------------------------------------------------------------
INSERT INTO investment_interests (id, category_id, sum, description, goal, user_id) VALUES(1, 1, 500, 'Description for FIRST InvInterest', 'Goal for FIRST InvInterest', 1);
-- (2, 2, 500, 'Description for SECOND InvInterest', 'Goal for SECOND InvInterest', 2),(3, 3, 500, 'Description for THIRD InvInterest', 'Goal for THIRD InvInterest', 1);
--
-- --   ----------------------------------------------------------------------------------------------
-- --  Fill projects table
-- --   ----------------------------------------------------------------------------------------------
INSERT INTO projects (id, name, owner_id, category_id, cost, date_added, date_start, min_invest, description, goal, stage) VALUES(1, 'Raccoon.World - gadgets for hands', 1, 1, 400000, '2017-05-20 14:30:30.069', '2017.05.20 15:30:30.069', 50000, 'tratata', 'goal', 'start');
-- (2, 'Integrating Virtuality into Reality, Game', 1, 1, 150000, '2017-05-20 14:30:30.069', '2017-05-20 15:30:30.069', 50000,
--       'Mobile MMO RPG (midcore), with Step-by-step combat system, and focus on multiplayer real-time PvP.', 'goal',
--       'start'),(3, 'Wood Pellets Storage', 1, 2, '1800000', '2017-05-20 14:30:30.069', '2017-05-20 15:30:30.069', 60000, 'European winter storage
-- All math and figures are taken from personal experience obtained during last year
-- Pellets sales market- Europe', 'goal','start'),(4, 'Virtual Insurance Agents (chatbots) for insurers', 1, 2, 400000, '2017-05-20 14:30:30.069', '2017-05-20 15:30:30.069', 20000,
--       'We are building Virtual Insurance Agents (chatbots) to help insurers to engage their customers via Facebook Messengers.',
--       'goal', 'middle'),(5, 'Appio', 1, 3, 500000, '2017-05-20 14:30:30.069', '2017-05-20 15:30:30.069', 25000,
--       'We transformed huge, long, expensive and crowded of people process to really simple idea, full-stack turnkey solution less than a week and almost free.',
--       'goal', 'middle'),(6, 'Coliving Hub', 2, 1, 450000, '2017-05-20 14:30:30.069', '2017-05-20 15:30:30.069', 100000,
--       'Affordable housing in San Francisco for digital nomads', 'goal', 'middle'),(7, 'Service for converting audio format to text format', 2, 1, 300000, '2017-05-20 14:30:30.069', '2017-05-20 15:30:30.069', 30000,
--       'Transcribe multiple voices from any meeting, interview, conference or other voice negotiations. Ability to complete a text search from transcribed files.',
--       'goal', 'middle'),(8, 'global marketplace for tutoring', 2, 2, 5000000, '2017-05-20 14:30:30.069', '2017-05-20 15:30:30.069', 500000,
--       'a global marketplace for tutoring that is launched in 5 countries.', 'goal', 'finish'),(9, 'Kevuru Games', 2, 1, 100000, '2017-05-20 14:30:30.069', '2017-05-20 15:30:30.069', 10000,
--       'Mobile games development with focus on strategy, match 3 and slots games categories', 'goal', 'finish'),(10, 'ebooks', 2, 2, 5000, '2017-05-20 14:30:30.069', '2017-05-20 15:30:30.069', 2500,
--        'Production of the cheap eBook and introduction them to the markets as a substitute of classic paper books. The buyer must understand that By purchasing an e-book he refuses from classic paper books and newspapers, leading to lower demand for these books and thus reduce the cost of their production resources in the form of paper.',
--        'goal', 'finish');

-- --   ----------------------------------------------------------------------------------------------
-- --  Fill investments table
-- --   ----------------------------------------------------------------------------------------------
-- INSERT INTO investments (id, user_id, project_id, date, sum, verified, paid) VALUES(1, 1, 1, '2017-05-20 15:30:30.069', 50000, TRUE, TRUE);
-- (2, 1, 2, '2017-05-20 15:30:30.069', 50000, TRUE, TRUE),(3, 1, 3, '2017-05-20 15:30:30.069', 60000, TRUE, TRUE),(4, 1, 4, '2017-05-20 15:30:30.069', 20000, TRUE, TRUE),(5, 1, 5, '2017-05-20 15:30:30.069', 25000, TRUE, TRUE),(6, 2, 6, '2017-05-20 15:30:30.069', 100000, TRUE, TRUE),(7, 2, 7, '2017-05-20 15:30:30.069', 30000, TRUE, TRUE),(8, 2, 8, '2017-05-20 15:30:30.069', 500000, TRUE, TRUE),(9, 2, 9, '2017-05-20 15:30:30.069', 10000, TRUE, TRUE),(10, 2, 10, '2017-05-20 15:30:30.069', 2500, TRUE, TRUE);

--   ----------------------------------------------------------------------------------------------
--  Fill events table
--   ----------------------------------------------------------------------------------------------
INSERT INTO event (id) VALUES(1);
-- (2);