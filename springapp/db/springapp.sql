CREATE DATABASE springapp;

GRANT ALL ON springapp.* TO springappuser@'%' IDENTIFIED BY 'pspringappuser';
GRANT ALL ON springapp.* TO springappuser@localhost IDENTIFIED BY 'pspringappuser';

USE springapp;
CREATE TABLE loans(
	idLoan varchar(30) PRIMARY KEY,
	idClient varchar(9) REFERENCES client(idClient),
	accountNumber varchar(15) REFERENCES account(pk_account),
	paymentPeriod varchar(15),
	initialCapital DOUBLE,
	amortizationTime DOUBLE,
	interest DOUBLE,
	description varchar(160)
);

CREATE TABLE payments(
	paymentId varchar(15) PRIMARY KEY,
	idloan varchar(30) REFERENCES loans(idLoan),
	dateForPay date,
	dateLimit date,
	amountOfMoney DOUBLE,
	amortization DOUBLE,
	interest DOUBLE,
	pendingCapital DOUBLE,
	isPaid boolean
)

CREATE TABLE client(
	idClient varchar(9) PRIMARY KEY,
	name varchar(15),
	surname varchar(30)
);

CREATE TABLE account(
	accountNumber varchar(15) PRIMARY KEY,
	officeId varchar(4),
	bankId varchar(4),
	CONSTRAINT pk_account PRIMARY KEY (officeId, bankId, accountNumber)
);

CREATE TABLE bank(
	bankId varchar(4) PRIMARY KEY
)
CREATE TABLE office(
	officeId varchar(4) PRIMARY KEY,
	bankId REFERENCES bank(bankId)
)


CREATE INDEX loans_description ON loans(description);