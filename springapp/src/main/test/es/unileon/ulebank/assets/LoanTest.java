package es.unileon.ulebank.assets;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.FinancialProductHandler;
import es.unileon.ulebank.assets.handler.exceptions.LINCMalformedException;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.history.GenericTransaction;
import es.unileon.ulebank.history.Transaction;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.support.PaymentPeriod;
import es.unileon.ulebank.transacionManager.TransactionManager;

public class LoanTest {

	private Loan loanWrongInterest;
	private Loan loanCancel;
	private Loan loanAmortize;

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
		this.bank = new Bank(this.manager, new GenericHandler("1234"));
		this.office = new Office(new GenericHandler("1234"), bank);

		this.commercialAccount1 = new Account(this.office, this.bank,
				"0000000000");
		this.commercialAccount2 = new Account(this.office, this.bank,
				"0000000001");

		this.authorizedHandler1 = new GenericHandler("Miguel");
		this.authorized1 = new Client(this.authorizedHandler1);

		this.authorizedHandler2 = new GenericHandler("Antonio");
		this.authorized2 = new Client(this.authorizedHandler2);

		// we do a transaction of 40000 euros to the account1
		Transaction transaction1 = new GenericTransaction(40000, new Date(),
				"Salary");
		transaction1.setEffectiveDate(new Date());
		this.commercialAccount1.doTransaction(transaction1);

		// we do a transaction of 200000 euros to the account2
		Transaction transaction2 = new GenericTransaction(200000, new Date(),
				"Salary");
		transaction2.setEffectiveDate(new Date());
		this.commercialAccount2.doTransaction(transaction2);

		// we create a loan of 100000 euros linked to the account
		this.loanCancel = new Loan(new FinancialProductHandler("LN", "ES"),
				100000, 0.15, PaymentPeriod.MONTHLY, 23,
				this.commercialAccount1);

		this.loanAmortize = new Loan(new FinancialProductHandler("LN", "ES"),
				168000, 0.20, PaymentPeriod.ANNUAL, 72, this.commercialAccount2);
	}

	@Test(expected = LoanException.class)
	public void testLoanWrongInterest() throws LoanException,
			LINCMalformedException, MalformedHandlerException {
		this.loanWrongInterest = new Loan(new FinancialProductHandler("LN", "ES"), 50000, 20,
				PaymentPeriod.MONTHLY, 23, this.commercialAccount1);
	}

	@Test(expected = LoanException.class)
	public void testCancelLoanNotEnoughMoneyToPay() throws LoanException {
		this.loanCancel.cancelLoan();
	}

	@Test(expected = LoanException.class)
	public void testLiquidateLoanNotEnoughMoneyToPay() throws LoanException {
		this.loanCancel.amortize(50000);
	}

	@Test
	public void testCancelLoan() throws LoanException, TransactionException {
		Transaction transaction = new GenericTransaction(100000, new Date(),
				"Salary");
		transaction.setEffectiveDate(new Date());
		this.commercialAccount1.doTransaction(transaction);
		assertEquals(100000, this.loanCancel.getDebt(), 0);
		this.loanCancel.cancelLoan();
		assertEquals(0, this.loanCancel.getDebt(), 0);
	}

	@Test
	public void testAmortizeQuantityToTheLoan() throws LoanException {
		assertEquals(168000, this.loanAmortize.getDebt(), 0);
		this.loanAmortize.amortize(50000);
		assertEquals(118000, this.loanAmortize.getDebt(), 0);
		this.loanAmortize.amortize(118000);
		assertEquals(0, this.loanAmortize.getDebt(), 0);
	}

}
