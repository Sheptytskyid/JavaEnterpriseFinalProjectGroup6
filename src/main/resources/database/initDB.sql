CREATE SCHEMA startup_platform;

-- -----------------------------------------------------
-- Table startup_platform.user_types
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.user_types (
  id INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table startup_platform.users
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.users (
  id BIGINT NOT NULL,
  type_id INT NULL,
  name VARCHAR(60) NOT NULL,
  password VARCHAR(60) NOT NULL,
  phone VARCHAR(24) NULL,
  address VARCHAR NULL,
  email VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT USER_TYPE_FK
  FOREIGN KEY (type_id)
  REFERENCES startup_platform.user_types (id)
    ON DELETE SET NULL
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table startup_platform.categories
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.categories (
  id INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table startup_platform.projects
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.projects (
  id BIGINT NOT NULL,
  name VARCHAR(60) NULL,
  owner_id BIGINT NULL,
  category_id INT NULL,
  cost DECIMAL NULL,
  date_added TIMESTAMP NULL,
  date_start TIMESTAMP NULL,
  min_invest DECIMAL NULL,
  description VARCHAR NULL,
  goal VARCHAR NULL,
  other VARCHAR NULL,
  logo_url VARCHAR NULL,
  verified BOOLEAN NULL,
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
  ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table startup_platform.roles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.roles (
  id INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table startup_platform.users_roles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.users_roles (
  user_id BIGINT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT ROLE_FK
  FOREIGN KEY (role_id)
  REFERENCES startup_platform.roles (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT USER_ROLE_FK
  FOREIGN KEY (user_id)
  REFERENCES startup_platform.users (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table startup_platform.investment_interests
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.investment_interests (
  id INT NOT NULL,
  user_id BIGINT NOT NULL,
  category_id INT NULL,
  sum DECIMAL NULL,
  PRIMARY KEY (id),
  CONSTRAINT INVESTOR_INTEREST_FK
  FOREIGN KEY (user_id)
  REFERENCES startup_platform.users (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  CONSTRAINT INVEST_CATEGORY_FK
  FOREIGN KEY (category_id)
  REFERENCES startup_platform.categories (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table startup_platform.investments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.investments (
  id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  project_id BIGINT NOT NULL,
  date TIMESTAMP NULL,
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