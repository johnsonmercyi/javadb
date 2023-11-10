CREATE DATABASE fintech;

USE fintech;

# Testing UUID data type and It worked
# create table demo (idd  UUID);
# insert into demo values(uuid());
# select * from demo;

CREATE TABLE customer (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    firstname           VARCHAR(100) DEFAULT NULL,
    lastname            VARCHAR(100) DEFAULT NULL,
    address             TEXT DEFAULT NULL,
    created             DATETIME NOT NULL,
    updated             DATETIME DEFAULT NULL
);

# Test
# INSERT INTO  customer (firstname, lastname, address)
# VALUES ('John', 'Doe', 'New Address');
#
# SELECT * FROM customer;

CREATE TABLE user (
    id 					INT AUTO_INCREMENT PRIMARY KEY,
    customer_id			INT NOT NULL,
    username			VARCHAR(50) NOT NULL UNIQUE,
    password			VARCHAR(50) NOT NULL,
    email				VARCHAR(100) NOT NULL UNIQUE,
    created				DATETIME NOT NULL,
    updated				DATETIME DEFAULT NULL,
    
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON UPDATE CASCADE
);

CREATE TABLE account_type (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    type                VARCHAR(50) NOT NULL,
    created				DATETIME NOT NULL,
    updated				DATETIME DEFAULT NULL
);

CREATE TABLE account (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    customer_id			INT NOT NULL,
    account_type_id		INT NOT NULL,
    balance             DECIMAL(18, 2),
    pin                 INT DEFAULT NULL,
    account_no          INT NOT NULL,
    created				DATETIME NOT NULL,
    updated				DATETIME DEFAULT NULL,

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
# DROP DATABASE fintech;