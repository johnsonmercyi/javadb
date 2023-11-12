CREATE DATABASE fintech;

USE fintech;


-- # Testing UUID data type and It worked
-- # create table demo (idd  UUID);
-- # insert into demo values(uuid());
-- # select * from demo;


CREATE TABLE customer (
    id                  UUID PRIMARY KEY,
    firstname           VARCHAR(100) DEFAULT NULL,
    lastname            VARCHAR(100) DEFAULT NULL,
    address             TEXT DEFAULT NULL,
    created             DATETIME NOT NULL,
    updated             DATETIME DEFAULT NULL
);

# Test
-- INSERT INTO  customer (id, firstname, lastname, address)
-- VALUES (UUID(), 'John', 'Doe', 'New Address');
#
-- SELECT * FROM customer;



CREATE TABLE user (
    id 					    UUID  PRIMARY KEY,
    customer_id		  UUID NOT NULL,
    username			  VARCHAR(50) NOT NULL UNIQUE,
    password			  VARCHAR(50) NOT NULL,
    email				    VARCHAR(100) NOT NULL UNIQUE,
    created				  DATETIME NOT NULL,
    updated				  DATETIME DEFAULT NULL,
    
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON UPDATE CASCADE
);
-- SELECT * FROM user;

CREATE TABLE account_type (
    id              UUID PRIMARY KEY,
    type            VARCHAR(50) NOT NULL,
    created				  DATETIME NOT NULL,
    updated				  DATETIME DEFAULT NULL
);

CREATE TABLE account (
    id              UUID PRIMARY KEY,
    customer_id			UUID NOT NULL,
    account_type_id	UUID NOT NULL,
    balance         DECIMAL(18, 2),
    pin             INT DEFAULT NULL,
    account_no      BIGINT NOT NULL,
    created				  DATETIME NOT NULL,
    updated				  DATETIME DEFAULT NULL,

    FOREIGN KEY (customer_id) REFERENCES customer (id) ON UPDATE CASCADE,
    FOREIGN KEY (account_type_id) REFERENCES account_type (id) ON UPDATE CASCADE
);

-- DROP TABLE `account`;

CREATE TABLE transaction (
    id                UUID PRIMARY KEY,
    account_id	      UUID NOT NULL,
    type              VARCHAR(50) NOT NULL, -- CREDIT, DEBIT
    type_description  VARCHAR(50) NOT NULL, -- DEPOSIT, WITHDRAWAL, BILLS
    beneficiary       VARCHAR(50) NOT NULL,
    amount            DECIMAL(18,2),
    description       TEXT DEFAULT NULL,
    created DATETIME NOT NULL,
    updated DATETIME DEFAULT NULL,

    FOREIGN KEY (account_id) REFERENCES account (id) ON UPDATE CASCADE
);

-- DROP TABLE transaction;


# CREATE TRIGGERS
CREATE TRIGGER customerCreated
BEFORE INSERT ON customer
FOR EACH ROW
    BEGIN
        SET NEW.created = NOW();
    END;

CREATE TRIGGER customerUpdated
BEFORE UPDATE ON customer
FOR EACH ROW
    BEGIN
        SET NEW.updated = NOW();
    END;


CREATE TRIGGER userCreated
BEFORE INSERT ON user
FOR EACH ROW
    BEGIN
        SET NEW.created = NOW();
    END;

CREATE TRIGGER userUpdated
BEFORE UPDATE ON user
FOR EACH ROW
    BEGIN
        SET NEW.updated = NOW();
    END;


CREATE TRIGGER accountTypeCreated
BEFORE INSERT ON account_type
FOR EACH ROW
    BEGIN
        SET NEW.created = NOW();
    END;

CREATE TRIGGER accountTypeUpdated
BEFORE UPDATE ON account_type
FOR EACH ROW
    BEGIN
        SET NEW.updated = NOW();
    END;


CREATE TRIGGER accountCreated
BEFORE INSERT ON account
FOR EACH ROW
    BEGIN
        SET NEW.created = NOW();
    END;

CREATE TRIGGER accountUpdated
BEFORE UPDATE ON account
FOR EACH ROW
    BEGIN
        SET NEW.updated = NOW();
    END;

CREATE TRIGGER transactionCreated 
BEFORE INSERT ON transaction 
FOR EACH ROW 
  BEGIN
	  SET NEW.created = NOW();
	END;


CREATE TRIGGER transactionUpdated 
BEFORE UPDATE ON transaction 
FOR EACH ROW 
  BEGIN 
	  SET NEW.updated = NOW();
	END;


# TEST
-- USE mysql;
-- DROP DATABASE fintech;

-- drop table mysql.innodb_table_stats;
-- CREATE TABLE
--     innodb_table_stats (
--         database_name VARCHAR(64),
--         table_name VARCHAR(64),
--         last_update TIMESTAMP,
--         n_rows BIGINT,
--         clustered_index_size BIGINT,
--         sum_of_other_index_sizes BIGINT
--     );

-- drop table mysql.innodb_index_stats;
-- CREATE TABLE
--     innodb_index_stats (
--         database_name VARCHAR(64) COLLATE utf8_general_ci NOT NULL,
--         table_name VARCHAR(64) COLLATE utf8_general_ci NOT NULL,
--         index_name VARCHAR(64) COLLATE utf8_general_ci NOT NULL,
--         last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--         stat_name VARCHAR(64) COLLATE utf8_general_ci NOT NULL,
--         stat_value BIGINT UNSIGNED NOT NULL,
--         sample_size BIGINT UNSIGNED DEFAULT NULL,
--         stat_description VARCHAR(1024) COLLATE utf8_general_ci NOT NULL,
--         PRIMARY KEY (
--             database_name,
--             table_name,
--             index_name,
--             stat_name
--         )
--     ) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;
