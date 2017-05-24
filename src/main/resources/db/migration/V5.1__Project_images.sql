ALTER TABLE startup_platform.projects DROP COLUMN IF EXISTS photo;
ALTER TABLE startup_platform.projects ADD COLUMN IF NOT EXISTS image OID NULL;