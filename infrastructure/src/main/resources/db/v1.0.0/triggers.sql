CREATE OR REPLACE TRIGGER TRIGGER_BEFORE_INSERT_USER_DATA
BEFORE INSERT ON tbl_user_data FOR EACH ROW
    BEGIN
        SET NEW.uuid = USR_UUID();

        IF NEW.email IS NOT NULL THEN
            SET NEW.email = TRIM(NEW.email);
        END IF;

        IF NEW.first_name IS NOT NULL THEN
            SET NEW.first_name = TRIM(NEW.first_name);
        END IF;

        IF NEW.last_name IS NOT NULL THEN
            SET NEW.last_name = TRIM(NEW.last_name);
        END IF;

        IF NEW.alias IS NOT NULL THEN
            SET NEW.alias = TRIM(NEW.alias);
        ELSEIF NEW.first_name IS NOT NULL THEN
            SET NEW.alias = TRIM(NEW.first_name);
        END IF;
    END; //
    
CREATE OR REPLACE TRIGGER TRIGGER_BEFORE_INSERT_AUTH_ROLE
BEFORE INSERT ON tbl_authority_role FOR EACH ROW
    BEGIN
        SET NEW.uuid = USR_UUID();
    END; //
-- delimiter ;
-- delimiter //
CREATE OR REPLACE TRIGGER TRIGGER_BEFORE_INSERT_CREDENTIALS
BEFORE INSERT ON tbl_credentials FOR EACH ROW
    BEGIN
        SET NEW.pwd = SECURITY_ENCRYPT(NEW.pwd);
        SET NEW.date_expiration = PWD_EXPIRATION();
    END; //
-- delimiter ;
-- delimiter //
CREATE OR REPLACE TRIGGER TRIGGER_BEFORE_UPDATE_CREDENTIALS
BEFORE UPDATE ON tbl_credentials FOR EACH ROW
    BEGIN
        IF NEW.pwd != SECURITY_DECRYPT(OLD.pwd) THEN
            SET NEW.pwd = SECURITY_ENCRYPT(NEW.pwd);
        END IF;
        SET NEW.date_creation = CURRENT_DATE;
        SET NEW.date_expiration = PWD_EXPIRATION();
        SET NEW.refreshed_count = OLD.refreshed_count + 1;
    END; //