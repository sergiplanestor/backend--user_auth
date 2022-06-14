-- AUTHORITY_ROLE
INSERT INTO
    tbl_authority_role(role, level)
VALUES
    ('ANONYMOUS', 1),
    ('CUSTOMER', 2),
    ('TESTER', 3),
    ('DEVELOPER', 4),
    ('ADMIN', 5 )
;

-- CUSTOMER
INSERT INTO
    tbl_user_data(email, alias, first_name, last_name)
VALUES
    ('test@xyz.com', 'Testy', 'Tester', 'Dev'),
    ('splanes@protonmail.com', 'CoolDaddy', 'Sergi', 'Planes')
;

-- CUSTOMER IDENTITY
INSERT INTO
    tbl_credentials(uuid, pwd)
VALUES
    ((FIND_USR_UUID_BY_EMAIL('test@xyz.com')), 'Test1234!'),
    ((FIND_USR_UUID_BY_EMAIL('splanes@protonmail.com')), '96ed7h83')
;

-- CUSTOMER IDENTITY ROLES
INSERT INTO
    tbl_user_authority_roles(user_uuid, role_uuid)
VALUES
    (
        (FIND_USR_UUID_BY_EMAIL('test@xyz.com')),
        (SELECT r.uuid FROM tbl_authority_role r WHERE r.role = 'CUSTOMER')
    ),
    (
        (FIND_USR_UUID_BY_EMAIL('splanes@protonmail.com')),
        (SELECT r.uuid FROM tbl_authority_role r WHERE r.role = 'CUSTOMER')
    ),
    (
        (FIND_USR_UUID_BY_EMAIL('test@xyz.com')),
        (SELECT r.uuid FROM tbl_authority_role r WHERE r.role = 'TESTER')
    ),
    (
        (FIND_USR_UUID_BY_EMAIL('splanes@protonmail.com')),
        (SELECT r.uuid FROM tbl_authority_role r WHERE r.role = 'DEVELOPER')
    ),
    (
        (FIND_USR_UUID_BY_EMAIL('splanes@protonmail.com')),
        (SELECT r.uuid FROM tbl_authority_role r WHERE r.role = 'ADMIN')
    )
;