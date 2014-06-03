package es.unileon.springapp.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.unileon.ulebank.assets.Loan;

public class JPALoanDaoTests {

	private ApplicationContext context;
	private LoanDao loanDao;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				"classpath:test-context.xml");
		loanDao = (LoanDao) context.getBean("loanDao");
	}

	@Test
	public void testGetProductList() {
		List<Loan> loan = loanDao.getLoanList();
		assertEquals(loan.size(), 1, 0);
	}

	@Test
	public void testSaveProduct() {
		List<Loan> loans = loanDao.getLoanList();

		Loan l = loans.get(0);
		Double amountOfMoney = l.getAmountOfMoney();
		l.setAmountOfMoney(500000);
		loanDao.addLoan(l);

		List<Loan> updatedProducts = loanDao.getLoanList();
		Loan l2 = updatedProducts.get(0);
		assertEquals(l2.getAmountOfMoney(), 500000, 0);

		l2.setAmountOfMoney(amountOfMoney);
		loanDao.addLoan(l2);

	}
}
