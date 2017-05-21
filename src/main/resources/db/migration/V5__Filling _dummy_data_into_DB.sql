--   ----------------------------------------------------------------------------------------------
-- Clear yours DB
--   ----------------------------------------------------------------------------------------------
TRUNCATE TABLE startup_platform.users CASCADE;
TRUNCATE TABLE startup_platform.user_types CASCADE;
TRUNCATE TABLE startup_platform.roles CASCADE;
TRUNCATE TABLE startup_platform.categories CASCADE;
CREATE TABLE IF NOT EXISTS startup_platform.event (
  id INTEGER NOT NULL PRIMARY KEY
);
TRUNCATE TABLE startup_platform.event CASCADE;

DROP TABLE startup_platform.projects CASCADE;
DROP TABLE startup_platform.investments CASCADE;

--   ----------------------------------------------------------------------------------------------
-- Create projects and investments tables
--   ----------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.projects (
  id          BIGINT      NOT NULL,
  name        VARCHAR(60) NULL,
  owner_id    BIGINT      NULL,
  category_id INT         NULL,
  cost        DECIMAL     NULL,
  date_added  TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
  date_start  TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
  min_invest  DECIMAL     NULL,
  description VARCHAR     NULL,
  goal        VARCHAR     NULL,
  other       VARCHAR     NULL,
  logo_url    VARCHAR     NULL,
  verified    BOOLEAN     NULL,
  PRIMARY KEY (id),
  CONSTRAINT OWNER_FK
  FOREIGN KEY (owner_id)
  REFERENCES startup_platform.users (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  CONSTRAINT CATEGORY_FK
  FOREIGN KEY (category_id)
  REFERENCES startup_platform.categories (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE
);

ALTER TABLE startup_platform.projects
  ADD COLUMN stage VARCHAR(35) NULL;

ALTER TABLE startup_platform.projects
  ADD COLUMN photo OID NULL;

CREATE TABLE IF NOT EXISTS startup_platform.investments (
  id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  project_id BIGINT NOT NULL,
  date TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
  sum DECIMAL NULL,
  PRIMARY KEY (id),
  CONSTRAINT INVESTOR_FK
  FOREIGN KEY (user_id)
  REFERENCES startup_platform.users (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  CONSTRAINT INVEST_PROJECT_FK
  FOREIGN KEY (project_id)
  REFERENCES startup_platform.projects (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE);

ALTER TABLE startup_platform.investments ADD COLUMN verified BOOLEAN NULL;
ALTER TABLE startup_platform.investments ADD COLUMN paid BOOLEAN NULL;



--   ----------------------------------------------------------------------------------------------
-- Fill user_types table
--   ----------------------------------------------------------------------------------------------
INSERT INTO startup_platform.user_types (id, name) VALUES
  (1, 'INVESTOR'),
  (2, 'ENTREPRENEUR'),
  (3, 'CONSULTANT');

--   ----------------------------------------------------------------------------------------------
-- Fill roles table
--   ----------------------------------------------------------------------------------------------
INSERT INTO startup_platform.roles (id, name) VALUES
  (1, 'ROLE_USER'),
  (2, 'ROLE_ADMIN');

--   ----------------------------------------------------------------------------------------------
-- Fill users table with user PASSWORDS = {admin, useruser}
--   ----------------------------------------------------------------------------------------------
INSERT INTO startup_platform.users (id, type_id, name, email, password) VALUES
  (1, 3, 'Admin', 'admin@admin.admin', '$2a$10$3NAgFi3tu7iO76jnzKdxg.dT0YbiDpoyPbcKPtIC7iupjmc3ziFiu'),
  (2, 3, 'User', 'user@user.user', '$2a$10$DDb4kmE5su.kXx7ku11slubKGBoQfEaCdSxIGokN7QUa46ync48o2');

--   ----------------------------------------------------------------------------------------------
-- Fill users_roles table with user PASSWORD = admin
--   ----------------------------------------------------------------------------------------------
INSERT INTO startup_platform.users_roles (user_id, role_id) VALUES
  (1, 2),
  (2, 1);

--   ----------------------------------------------------------------------------------------------
--  Fill categories table
--   ----------------------------------------------------------------------------------------------
INSERT INTO startup_platform.categories (id, name) VALUES
  (1, 'CATEGORY 1'),
  (2, 'CATEGORY 2'),
  (3, 'CATEGORY 3');

--   ----------------------------------------------------------------------------------------------
--  Fill investment_interest table
--   ----------------------------------------------------------------------------------------------
INSERT INTO startup_platform.investment_interests (id, category_id, sum, description, goal, user_id) VALUES
  (1, 1, 500, 'Description for FIRST InvInterest', 'Goal for FIRST InvInterest', 1),
  (2, 2, 500, 'Description for SECOND InvInterest', 'Goal for SECOND InvInterest', 2),
  (3, 3, 500, 'Description for THIRD InvInterest', 'Goal for THIRD InvInterest', 1);

--   ----------------------------------------------------------------------------------------------
--  Fill projects table
--   ----------------------------------------------------------------------------------------------
INSERT INTO startup_platform.projects (id, name, owner_id, category_id, cost, date_added, date_start, min_invest, description, goal, stage)
VALUES
  (1, 'Raccoon.World - gadgets for hands', 1, 1, 400000, '20.05.2017 14:30', '20.05.2017 15:30', 50000, 'Raccoon.World is a company that creates gadgets for hands, based on spatialization, tracking, reproduction of fine motor skills and tactile perception.
Raccoon.world is an international company incorporated in Delaware, USA with R&D Center in Ukraine.', 'goal', 'start'),
  (2, 'Integrating Virtuality into Reality, Game', 1, 1, 150000, '20.05.2017 14:30', '20.05.2017 15:30', 50000,
      'Mobile MMO RPG (midcore), with Step-by-step combat system, and focus on multiplayer real-time PvP.', 'goal',
      'start'),
  (3, 'Wood Pellets Storage', 1, 2, '1800000', '20.05.2017 14:30', '20.05.2017 15:30', 60000, 'European winter storage
All math and figures are taken from personal experience obtained during last year
Pellets sales market- Europe', 'goal', 'start'),
  (4, 'Virtual Insurance Agents (chatbots) for insurers', 1, 2, 400000, '20.05.2017 14:30', '20.05.2017 15:30', 20000,
      'We are building Virtual Insurance Agents (chatbots) to help insurers to engage their customers via Facebook Messengers.',
      'goal', 'middle'),
  (5, 'Appio', 1, 3, 500000, '20.05.2017 14:30', '20.05.2017 15:30', 25000,
      'We transformed huge, long, expensive and crowded of people process to really simple idea, full-stack turnkey solution less than a week and almost free.',
      'goal', 'middle'),
  (6, 'Coliving Hub', 2, 1, 450000, '20.05.2017 14:30', '20.05.2017 15:30', 100000,
      'Affordable housing in San Francisco for digital nomads', 'goal', 'middle'),
  (7, 'Service for converting audio format to text format', 2, 1, 300000, '20.05.2017 14:30', '20.05.2017 15:30', 30000,
      'Transcribe multiple voices from any meeting, interview, conference or other voice negotiations. Ability to complete a text search from transcribed files.',
      'goal', 'middle'),
  (8, 'global marketplace for tutoring', 2, 2, 5000000, '20.05.2017 14:30', '20.05.2017 15:30', 500000,
      'a global marketplace for tutoring that is launched in 5 countries.', 'goal', 'finish'),
  (9, 'Kevuru Games', 2, 1, 100000, '20.05.2017 14:30', '20.05.2017 15:30', 10000,
      'Mobile games development with focus on strategy, match 3 and slots games categories', 'goal', 'finish'),
  (10, 'ebooks', 2, 2, 5000, '20.05.2017 14:30', '20.05.2017 15:30', 2500,
       'Production of the cheap eBook and introduction them to the markets as a substitute of classic paper books. The buyer must understand that By purchasing an e-book he refuses from classic paper books and newspapers, leading to lower demand for these books and thus reduce the cost of their production resources in the form of paper.',
       'goal', 'finish');

--   ----------------------------------------------------------------------------------------------
--  Fill investments table
--   ----------------------------------------------------------------------------------------------
INSERT INTO startup_platform.investments (id, user_id, project_id, date, sum, verified, paid) VALUES
  (1, 1, 1, '20.05.2017 15:30', 50000, TRUE, TRUE),
  (2, 1, 2, '20.05.2017 15:30', 50000, TRUE, TRUE),
  (3, 1, 3, '20.05.2017 15:30', 60000, TRUE, TRUE),
  (4, 1, 4, '20.05.2017 15:30', 20000, TRUE, TRUE),
  (5, 1, 5, '20.05.2017 15:30', 25000, TRUE, TRUE),
  (6, 2, 6, '20.05.2017 15:30', 100000, TRUE, TRUE),
  (7, 2, 7, '20.05.2017 15:30', 30000, TRUE, TRUE),
  (8, 2, 8, '20.05.2017 15:30', 500000, TRUE, TRUE),
  (9, 2, 9, '20.05.2017 15:30', 10000, TRUE, TRUE),
  (10, 2, 10, '20.05.2017 15:30', 2500, TRUE, TRUE);

--   ----------------------------------------------------------------------------------------------
--  Fill events table
--   ----------------------------------------------------------------------------------------------
INSERT INTO startup_platform.event (id) VALUES
  (1),
  (2);