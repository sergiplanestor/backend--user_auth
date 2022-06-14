DROP TABLE IF EXISTS tbl_user_authority_roles;
DROP TABLE IF EXISTS tbl_credentials;
DROP TABLE IF EXISTS tbl_authority_role;
DROP TABLE IF EXISTS tbl_user_data;
-- ·················································································································· --
-- CUSTOMER
-- ·················································································································· --
CREATE OR REPLACE TABLE tbl_user_data(
    _id INT NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(32) NOT NULL,
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) CHARACTER SET utf8 NOT NULL,
    last_name VARCHAR(50) CHARACTER SET utf8 DEFAULT NULL,
    alias VARCHAR(50) CHARACTER SET utf8 DEFAULT NULL,
    date_creation DATE NOT NULL DEFAULT CURRENT_DATE,
    date_last_login TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (_id),
    UNIQUE U_tbl_user_data_uuid (uuid),
    UNIQUE U_tbl_user_data_email (email),
    CONSTRAINT CHCK_tbl_user_data_email CHECK (email LIKE '%___@___%.__%'),
    CONSTRAINT CHCK_tbl_user_data_first_name CHECK (LENGTH(TRIM(first_name)) > 3)
);

-- ·················································································································· --
-- CUSTOMER IDENTITY
-- ·················································································································· --
CREATE OR REPLACE TABLE tbl_credentials(
    _id INT NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(32) NOT NULL,
    pwd VARCHAR(256) NOT NULL,
    date_creation DATE NOT NULL DEFAULT CURRENT_DATE,
    date_expiration DATE NOT NULL,
    refreshed_count INT NOT NULL DEFAULT 0,

    PRIMARY KEY (_id),
    UNIQUE U_tbl_credentials_uuid (uuid),
    CONSTRAINT FK_user_uuid_credentials_uuid FOREIGN KEY (uuid) REFERENCES tbl_user_data(uuid)
);

CREATE OR REPLACE TABLE tbl_authority_role(
    _id INT NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(32) NOT NULL,
    role VARCHAR(15) NOT NULL,
    level INT NOT NULL,

    PRIMARY KEY (_id),
    UNIQUE U_tbl_authority_role_uuid (uuid)
);

CREATE OR REPLACE TABLE tbl_user_authority_roles(
    user_uuid VARCHAR(32) NOT NULL,
    role_uuid VARCHAR(32) NOT NULL,

    PRIMARY KEY (user_uuid, role_uuid),
    FOREIGN KEY (user_uuid) REFERENCES tbl_user_data(uuid),
    FOREIGN KEY (role_uuid) REFERENCES tbl_authority_role(uuid)
);