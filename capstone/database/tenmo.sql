BEGIN TRANSACTION;

DROP TABLE IF EXISTS transfers;
DROP TABLE IF EXISTS user_account;
DROP TABLE IF EXISTS tenmo_user;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) UNIQUE NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(20),
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

CREATE TABLE user_account (
    account_id serial NOT NULL,
    balance DECIMAL(13, 2) DEFAULT(1000),
    user_id int NOT NULL,
    CONSTRAINT PK_user_account PRIMARY KEY (account_id),
    CONSTRAINT FK_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user(user_id)
);

CREATE TABLE transfers (
    transfer_id serial NOT NULL,
    amount DECIMAL(13,2) NOT NULL,
    transfer_to int NOT NULL,
    transfer_from int NOT NULL,
    CONSTRAINT PK_transfer PRIMARY KEY (transfer_id),
    CONSTRAINT FK_transfer_from FOREIGN KEY (transfer_from) REFERENCES user_account(account_id),
    CONSTRAINT FK_transfer_to FOREIGN KEY (transfer_to) REFERENCES user_account(account_id)
);

--  rollback;

COMMIT;