package es.unileon.ulebank.assets;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.exceptions.LINCMalformedException;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.transacionManager.TransactionManager;

public class LoanTest {

	private Loan loanWrongInterest;
	private Loan loanCancel;
	private Loan loanAmortize;
	private Loan loanNotAllowed;

	private TransactionManager manager;
	private Bank bank;
	private Office office;
	private Account commercialAccount1, commercialAccount2;
	private GenericHandler authorizedHandler1, authorizedHandler2;
	private Client authorized1, authorized2;
	

	@Before
	public void setUp() throws MalformedHandlerException, TransactionException,
			LoanException, LINCMalformedException {
		this.manager = new TransactionManager();
		this.bank = new Bank(this.manager,"1234");
		this.office = new Office("1234", bank);

		this.commercialAccount1 = new Account("123412340000000000");
		this.commercialAccount2 = new Account("123412340000000001");

		this.authorizedHandler1 = new GenericHandler("Miguel");
		this.authorized1 = new Client("45678999A");

		this.authorizedHandler2 = new GenericHandler("Antonio");
		this.authorized2 = new Client("13423112A");

		
		this.loanCancel = new Loan("MG-5-2014-FR-KMW2C-7",
				100000, 0.15, PaymentPeriod.MONTHLY.toString(), 23,
				this.commercialAccount1,authorized1);

		this.loanAmortize = new Loan("MG-5-2015-ES-KMW2C-7",
				168000, 0.20, PaymentPeriod.ANNUAL.toString(), 72, this.commercialAccount2,authorized2);
	}
	
	@Test(expected = LoanException.class)
	public void testLoanNotAllowed() throws LoanException,
			LINCMalformedException, MalformedHandlerException {
		this.loanNotAllowed = new Loan("MG-5-2016-FR-KMW2C-7", 100000000, 0.01,
				PaymentPeriod.MONTHLY.toString(), 30, this.commercialAccount1,authorized1);
	}

	@Test(expected = LoanException.class)
	public void testLoanWrongInterest() throws LoanException,
			LINCMalformedException, MalformedHandlerException {
		this.loanWrongInterest = new Loan("MG-5-2017-ES-KMW2C-7", 50000, -9,
				PaymentPeriod.MONTHLY.toString(), 23, this.commercialAccount1,authorized1);
	}




}
