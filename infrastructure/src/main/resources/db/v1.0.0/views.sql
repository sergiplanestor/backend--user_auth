CREATE OR REPLACE SQL SECURITY INVOKER VIEW view_credentials AS
SELECT
    u.email as 'Email',
    u.alias as 'Alias',
    SECURITY_DECRYPT(c.pwd) as 'Password',
    c.date_creation as 'Created On',
    c.date_expiration as 'Expires On',
    c.refreshed_count as 'Refreshed Times'
FROM tbl_credentials c
INNER JOIN tbl_user_data u ON u.uuid = c.uuid;


CREATE OR REPLACE SQL SECURITY INVOKER VIEW view_full_customer AS
SELECT DISTINCT
    u.uuid as 'UUID',
    u.email as 'Email',
    u.first_name as 'Name',
    u.last_name as 'Last Name',
    u.alias as 'Alias',
    SECURITY_DECRYPT(c.pwd) as 'Password',
    (SELECT r.role FROM tbl_authority_role r WHERE r.level = FIND_USR_MAX_ROLE_LEVEL(u.uuid)) as 'Highest Role',
    (SELECT r.role FROM tbl_authority_role r WHERE r.level = FIND_USR_MIN_ROLE_LEVEL(u.uuid)) as 'Lowest Role',
    c.date_creation as 'Password Creation',
    c.date_expiration as 'Password Expiration',
    c.refreshed_count as 'Refreshed Times',
    u.date_creation as 'User Creation',
    u.date_last_login as 'Last Login'
FROM tbl_user_data u
INNER JOIN tbl_credentials as c ON u.uuid = c.uuid
ORDER BY u.first_name;





