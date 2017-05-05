ALTER TABLE startup_platform.investments ADD COLUMN verified BOOLEAN NULL;
ALTER TABLE startup_platform.investments ADD COLUMN paid BOOLEAN NULL;
-- -----------------------------------------------------
-- Table startup_platform.investment_verify_request for admins
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.investment_verify_request (
  id BIGINT NOT NULL,
  investment_id BIGINT NOT NULL,
  user_id BIGINT NULL,
  verify_date TIMESTAMP WITHOUT TIME ZONE NULL,
  PRIMARY KEY (id),
  CONSTRAINT ADMIN_VERIFICATION_FK
  FOREIGN KEY (user_id)
  REFERENCES startup_platform.users (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,
  CONSTRAINT INVESTMENT_VERIFICATION_FK
  FOREIGN KEY (investment_id)
  REFERENCES startup_platform.investments (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE);
--  ----------------------------------------------------
-- Table startup_platform.investment_pay_request for admins
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.investment_payment_request (
  id BIGINT NOT NULL,
  investment_id BIGINT NOT NULL,
  user_id BIGINT NULL,
  payment_date TIMESTAMP WITHOUT TIME ZONE NULL,
  payment_details VARCHAR(255) NULL,
  sum NUMERIC NULL,
  payment_submit_date TIMESTAMP WITHOUT TIME ZONE NULL,
  PRIMARY KEY (id),
  CONSTRAINT ADMIN_PAYMENT_SUBMIT_FK
  FOREIGN KEY (user_id)
  REFERENCES startup_platform.users (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,
  CONSTRAINT INVESTMENT_PAYMENT_SUBMIT_FK
  FOREIGN KEY (investment_id)
  REFERENCES startup_platform.investments (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE);