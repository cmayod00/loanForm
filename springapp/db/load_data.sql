INSERT INTO bank (bankId) VALUES ('1234');
INSERT INTO office (officeId, bankId) VALUES ('1234','1234');
INSERT INTO client (idClient, name, surname) VALUES ('71560136Y', 'Carlos', 'Mayo');
INSERT INTO account (accountNumber) VALUES ('123412340000000000');


INSERT INTO loans (idLoan, idClient, accountNumber, paymentPeriod, initialCapital, amortizationTime, interest, creationDate, description) values('MG-4-2014-ES-KMW2C-7','71560136Y', '123412340000000000', 'Monthly', 10000 , 12, 0.05, now(), 'Prestamo moto' );