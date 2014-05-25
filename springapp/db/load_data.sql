INSERT INTO bank (bankId) VALUES ('1234');
INSERT INTO office (officeId, bankId) VALUES ('1234','1234');
INSERT INTO client (clienId, name, surname) VALUES ('71560136Y', 'Carlos', 'Mayo');
INSERT INTO accounts (officeId, bankId, accountNumber) VALUES ('1234','1234','0000000000');


INSERT INTO loans (idLoan, idClient, accountNumber, paymentPeriod, initialCapital, amortizationTime, interest, description) values(1, 'Lamp', 5.78);