package es.unileon.springapp.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.springapp.beans.LoanBean;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.support.PaymentPeriod;

@Controller
public class ClientController {

	protected final Log logger = LogFactory.getLog(getClass());

	private Loan loan;
	private Client client;
	private Account account;
	@Autowired
	private Office office;
	@Autowired
	private Bank bank;

	@Autowired
	public ClientController(Loan loan, Account account, Client client) {
		this.loan = loan;
		this.account = account;
		this.client = client;
		this.account.addTitular(client);
		this.client.addLoan(loan);
	}

	/**
	 * Sirve para enviar los datos a la vista y que esta las muestre.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/client.htm")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// LoanBean bean = new LoanBean();

		Map<String, Object> myModel = new HashMap<String, Object>();

		List<Loan> loans = client.getLoans();

		myModel.put("client", this.client);
		myModel.put("loans", loans);

		return new ModelAndView("client", "model", myModel);
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}
}
