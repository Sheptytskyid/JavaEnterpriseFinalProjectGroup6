-- DROP SCHEMA IF EXISTS startup_platform CASCADE;
-- CREATE SCHEMA IF NOT EXISTS startup_platform;

-- -----------------------------------------------------
-- Table user_types
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user_types (
  id INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
  id BIGINT NOT NULL,
  type_id INT NULL,
  name VARCHAR(60) NOT NULL,
  password VARCHAR(60) NOT NULL,
  phone VARCHAR(24) NULL,
  address VARCHAR NULL,
  email VARCHAR(45) NOT NULL,
  last_name VARCHAR NULL,
  photo OID NULL,
  PRIMARY KEY (id),
  CONSTRAINT USER_TYPE_FK
  FOREIGN KEY (type_id)
  REFERENCES user_types (id)
    ON DELETE SET NULL
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table categories
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS categories (
  id INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table projects
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS projects (
  id          BIGINT      NOT NULL,
  name        VARCHAR(60) NULL,
  owner_id    BIGINT      NULL,
  category_id INT         NULL,
  cost        DECIMAL     NULL,
  date_added  TIMESTAMP(0)   NULL,
  date_start  TIMESTAMP(0)   NULL,
  min_invest  DECIMAL     NULL,
  description VARCHAR     NULL,
  goal        VARCHAR     NULL,
  other       VARCHAR     NULL,
  stage       VARCHAR(35) NULL,
  logo_url    VARCHAR     NULL,
  verified    BOOLEAN     NULL,
  image       OID         NULL,
  PRIMARY KEY (id),
  CONSTRAINT OWNER_FK
  FOREIGN KEY (owner_id)
  REFERENCES users (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  CONSTRAINT CATEGORY_FK
  FOREIGN KEY (category_id)
  REFERENCES categories (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table roles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS roles (
  id INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table users_roles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users_roles (
  user_id BIGINT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT ROLE_FK
  FOREIGN KEY (role_id)
  REFERENCES roles (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT USER_ROLE_FK
  FOREIGN KEY (user_id)
  REFERENCES users (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table investment_interests
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS investment_interests (
  id          INT     NOT NULL,
  user_id   BIGINT    NULL,
  category_id INT     NULL,
  description VARCHAR NOT NULL,
  goal VARCHAR NOT NULL,
  sum         DECIMAL NULL,
  PRIMARY KEY (id),
  CONSTRAINT INVESTOR_INTEREST_FK
  FOREIGN KEY (user_id)
  REFERENCES users (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  CONSTRAINT INVEST_CATEGORY_FK
  FOREIGN KEY (category_id)
  REFERENCES categories (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table investments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS investments (
  id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  project_id BIGINT NOT NULL,
  date TIMESTAMP(0) NULL,
  sum DECIMAL NULL,
  verified BOOLEAN NULL,
  paid BOOLEAN NULL,
  PRIMARY KEY (id),
  CONSTRAINT INVESTOR_FK
  FOREIGN KEY (user_id)
  REFERENCES users (id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT INVEST_PROJECT_FK
  FOREIGN KEY (project_id)
  REFERENCES projects (id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table events
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS event (
  id INTEGER NOT NULL PRIMARY KEY
);