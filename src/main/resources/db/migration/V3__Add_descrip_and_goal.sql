ALTER TABLE startup_platform.investment_interests ADD COLUMN description VARCHAR NOT NULL;
ALTER TABLE startup_platform.investment_interests ADD COLUMN goal VARCHAR NOT NULL;
ALTER TABLE startup_platform.investment_interests DROP COLUMN user_id;
ALTER TABLE startup_platform.investment_interests ADD COLUMN user_id BIGINT NULL;
ALTER TABLE startup_platform.investment_interests ADD CONSTRAINT INVESTOR_INTEREST_FK FOREIGN KEY (user_id)
  REFERENCES startup_platform.users (id) ON DELETE CASCADE
ON UPDATE CASCADE;
INSERT INTO startup_platform.roles VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');
