package es.unileon.ulebank.assets;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.financialproducts.InterestRate;
import es.unileon.ulebank.assets.handler.Handler;
import es.unileon.ulebank.assets.handler.LoanIdentificationNumberCode;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.support.PaymentPeriod;


public class VariableLoan extends Loan{

	private static Client client;

	public VariableLoan(LoanIdentificationNumberCode idLoan, double initialCapital, double interest,
			PaymentPeriod paymentPeriod, int amortizationTime, Account account,
			InterestRate interestRate, PaymentPeriod recalcOfInterset)
			throws LoanException {
		super(idLoan, initialCapital, interest, paymentPeriod, amortizationTime,
				account, client);
		
	}

}
