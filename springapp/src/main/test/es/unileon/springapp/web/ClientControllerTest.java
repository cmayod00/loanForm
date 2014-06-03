package es.unileon.springapp.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.springapp.repository.InMemoryLoanDao;
import es.unileon.springapp.service.SimpleLoanManager;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.exceptions.LINCMalformedException;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.support.PaymentPeriod;
import es.unileon.ulebank.transacionManager.TransactionManager;

public class ClientControllerTest {

	private Loan loan;
	private Client client;
	private Account account;
	private Office office;
	private Bank bank;

	@Before
	public void setUp() throws MalformedHandlerException, LoanException,
			LINCMalformedException {
		bank = new Bank(new TransactionManager(),"1111");
		office = new Office("1234", bank);
		account = new Account("123412341234456789");
		client = new Client("71560136Y");
		loan = new Loan("MG-5-2014-FR-KMW2C-7", 10000,
				0.05, PaymentPeriod.MONTHLY.toString(), 12, account, client);
		client.setName("Carlos");
		client.setSurname("Mayo");
		client.addLoan(loan);
	}

	@Test
	public void testHandleRequestView() throws Exception {
		ClientController controller = new ClientController(loan, account,
				client);
//		controller.setClient(client);
		SimpleLoanManager slm= new SimpleLoanManager();
		slm.setLoanDao(new InMemoryLoanDao(new ArrayList<Loan>()));
		controller.setLoanManager(slm);
		ModelAndView modelAndView = controller.handleRequest(null, null);
		assertEquals("client", modelAndView.getViewName());
		assertNotNull(modelAndView.getModel());
		@SuppressWarnings("unchecked")
		Map<String, Object> modelMap = (Map<String, Object>) modelAndView
				.getModel().get("model");
		Client clientReceived = (Client) modelMap.get("client");
		List<Loan> loansList = (List<Loan>) modelMap.get("loans");
		assertEquals("Carlos", clientReceived.getName());
		assertEquals("Mayo", clientReceived.getSurname());
		assertEquals(1,loansList.size());
		assertEquals(10000, loansList.get(0).getAmountOfMoney(), 0.01);
	}
}
