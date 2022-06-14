
CREATE OR REPLACE FUNCTION FIND_USR_MAX_ROLE_LEVEL (uuid VARCHAR(32)) RETURNS INT
    BEGIN
        DECLARE max_level INT;

        SELECT role.level INTO max_level FROM tbl_authority_role as role
            INNER JOIN tbl_user_authority_roles as user_roles ON user_roles.role_uuid = role.uuid
        WHERE user_roles.user_uuid = uuid
        ORDER BY role.level DESC
        LIMIT 1;

        RETURN max_level;
    END //

CREATE OR REPLACE FUNCTION FIND_USR_MIN_ROLE_LEVEL(uuid VARCHAR(32)) RETURNS INT
    BEGIN
        DECLARE max_level INT;

        SELECT role.level INTO max_level FROM tbl_authority_role role
        INNER JOIN tbl_user_authority_roles user_roles ON user_roles.role_uuid = role.uuid
        WHERE user_roles.user_uuid = uuid
        ORDER BY role.level
        LIMIT 1;

        RETURN max_level;
    END; //
-- delimiter ;
-- delimiter //
CREATE OR REPLACE FUNCTION USR_UUID() RETURNS VARCHAR(32)
    BEGIN
        RETURN UPPER(SYS_GUID());
    END; //

CREATE OR REPLACE FUNCTION FIND_USR_UUID_BY_EMAIL(email VARCHAR(100)) RETURNS VARCHAR(32)
    BEGIN
        DECLARE uuid_found VARCHAR(32);
        SELECT t.uuid INTO uuid_found FROM tbl_user_data t WHERE t.email = email;
        RETURN uuid_found;
    END; //
-- delimiter ;
-- delimiter //
CREATE OR REPLACE FUNCTION PWD_EXPIRATION() RETURNS DATE
    BEGIN
        RETURN ADD_MONTHS(CURRENT_DATE, 6);
    END; //
-- delimiter ;
-- delimiter //
CREATE OR REPLACE FUNCTION ROLE_UUID(role_name VARCHAR(15)) RETURNS VARCHAR(32)
    BEGIN
        DECLARE uuid_found VARCHAR(32);
        SELECT t.uuid INTO uuid_found FROM tbl_authority_role t WHERE t.role = role_name;
        RETURN uuid_found;
    END; //
-- delimiter ;
-- delimiter //
CREATE OR REPLACE FUNCTION SECURITY_ENCRYPT(plain VARCHAR(256)) RETURNS VARCHAR(256)
    BEGIN
        RETURN TO_BASE64(AES_ENCRYPT(plain, SECRET()));
    END; //
-- delimiter ;
-- delimiter //
CREATE OR REPLACE FUNCTION SECURITY_DECRYPT(pwd VARCHAR(256)) RETURNS VARCHAR(256)
    BEGIN
        RETURN AES_DECRYPT(BINARY(FROM_BASE64(pwd)), SECRET());
    END; //
-- delimiter ;
-- delimiter //
CREATE OR REPLACE FUNCTION SECRET() RETURNS VARCHAR(256)
    BEGIN
        RETURN SHA2('Yarx229A63qkwwNEAwGSbf4cpFknWbrh', 512);
    END; //