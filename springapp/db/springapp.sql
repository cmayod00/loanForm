CREATE DATABASE ulebank;

GRANT ALL ON ulebank.* TO springappuser@'%' IDENTIFIED BY 'pspringappuser';
GRANT ALL ON ulebank.* TO springappuser@localhost IDENTIFIED BY 'pspringappuser';

USE ulebank;
CREATE TABLE loans(
	idLoan varchar(30) PRIMARY KEY,
	idClient varchar(9) REFERENCES client(idClient),
	accountNumber varchar(18) REFERENCES account(accountNumber),
	paymentPeriod varchar(15),
	initialCapital DOUBLE,
	amortizationTime DOUBLE,
	interest DOUBLE,
	creationDate datetime,
	description varchar(160)
);

CREATE TABLE payments(
	paymentId varchar(15) PRIMARY KEY,
	idLoan varchar(30) REFERENCES loans(idLoan),
	dateForPay date,
	dateLimit date,
	amountOfMoney DOUBLE,
	amortization DOUBLE,
	interest DOUBLE,
	pendingCapital DOUBLE,
	isPaid boolean
);

CREATE TABLE client(
	idClient varchar(9) PRIMARY KEY,
	name varchar(15),
	surname varchar(30)
);

CREATE TABLE account(
	accountNumber varchar(18) PRIMARY KEY 
);

CREATE TABLE bank(
	bankId varchar(4) PRIMARY KEY
);
CREATE TABLE office(
	officeId varchar(4) PRIMARY KEY,
	bankId varchar(4) REFERENCES bank(bankIdentificator)
);


CREATE INDEX loans_description ON loans(description);