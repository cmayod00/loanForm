package es.unileon.springapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.unileon.springapp.repository.InMemoryLoanDao;
import es.unileon.springapp.repository.LoanDao;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.client.Client;

public class SimpleLoanDaoTest {

	private List<Loan> loans;
	private SimpleLoanManager loanManager;
	private Loan loan;
	private Account account;
	private Client client;

	@Before
	public void setUp() throws Exception {

		loanManager = new SimpleLoanManager();
		loans = new ArrayList<Loan>();

		account = new Account("123412340000000000");
		client = new Client("71560136Y");
		loan = new Loan("MG-5-2014-FR-KMW2C-7", 10000, 0.02, "MONTHLY", 12,
				account, client);
		loans.add(loan);

		LoanDao loanDao = new InMemoryLoanDao(loans);
		loanManager.setLoanDao(loanDao);

	}

	@Test
	public void testGetLoansWithNoLoans() {
		loanManager = new SimpleLoanManager();
		loanManager.setLoanDao(new InMemoryLoanDao(null));
		assertNull(loanManager.getLoans());
	}

	@Test
	public void testGetLoans() {

		List<Loan> loans = loanManager.getLoans();
		assertNotNull(loans);
		assertEquals(1, loanManager.getLoans().size());

		Loan loan = loans.get(0);
		assertEquals(12, loan.getAmortizationTime());
		assertEquals(10000, loan.getAmountOfMoney(), 0);
		assertEquals("MG-5-2014-FR-KMW2C-7", loan.getIdLoan());
		assertEquals(0.02, loan.getInterest(), 0);
		assertEquals("MONTHLY", loan.getPaymentPeriodString());
		assertEquals("123412340000000000", loan.getAccountNumber());
	}

	@Test
	public void testAddLoanWithNullListOfLoans() {
		try {
			loanManager = new SimpleLoanManager();
			loanManager.setLoanDao(new InMemoryLoanDao(null));
			loanManager.addLoan(loan);
		} catch (NullPointerException ex) {
			fail("Loan list is null.");
		}
	}

	@Test
	public void testAddLoanToTheListOfLoans() {

		loanManager = new SimpleLoanManager();
		loanManager.setLoanDao(new InMemoryLoanDao(new ArrayList<Loan>()));
		loanManager.addLoan(loan);
		assertEquals(1, loanManager.getLoans().size());

	}

}
