CREATE DATABASE fintech;

USE fintech;


# Testing UUID data type and It worked
# create table demo (idd  UUID);
# insert into demo values(uuid());
# select * from demo;


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
SELECT * FROM customer;


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
    account_no      INT NOT NULL,
    created				  DATETIME NOT NULL,
    updated				  DATETIME DEFAULT NULL,

    FOREIGN KEY (customer_id) REFERENCES customer (id) ON UPDATE CASCADE,
    FOREIGN KEY (account_type_id) REFERENCES account_type (id) ON UPDATE CASCADE
);



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

# TEST
-- USE mysql;
-- DROP DATABASE fintech;