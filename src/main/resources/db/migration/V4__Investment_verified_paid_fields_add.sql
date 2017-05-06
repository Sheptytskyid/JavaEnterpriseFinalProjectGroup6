ALTER TABLE startup_platform.investments ADD COLUMN verified BOOLEAN NULL;
ALTER TABLE startup_platform.investments ADD COLUMN paid BOOLEAN NULL;
ALTER TABLE startup_platform.investments ADD COLUMN admin_verify_id BIGINT NULL;
ALTER TABLE startup_platform.investments ADD COLUMN verify_date TIMESTAMP WITHOUT TIME ZONE NULL;
--  ----------------------------------------------------
-- Table startup_platform.investment_pay_request for admins
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS startup_platform.investment_payment_request (
  id BIGINT NOT NULL,
  investment_id BIGINT NOT NULL,
  admin_id BIGINT NULL,
  payment_date TIMESTAMP WITHOUT TIME ZONE NULL,
  payment_details VARCHAR(255) NULL,
  sum NUMERIC NULL,
  payment_submit_date TIMESTAMP WITHOUT TIME ZONE NULL,
  PRIMARY KEY (id),
  CONSTRAINT ADMIN_PAYMENT_SUBMIT_FK
  FOREIGN KEY (admin_id)
  REFERENCES startup_platform.users (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,
  CONSTRAINT INVESTMENT_PAYMENT_SUBMIT_FK
  FOREIGN KEY (investment_id)
  REFERENCES startup_platform.investments (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE);