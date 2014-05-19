package es.unileon.springapp.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.support.PaymentPeriod;

@Controller
public class ClientController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private Client client;
	@Autowired
	private Office office;
	@Autowired
	private Bank bank;
	@Autowired
	private Account account;

	@RequestMapping(value = "/client.htm")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LoanBean bean = new LoanBean();
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("client", this.client);
		myModel.put("createloan", bean);

		return new ModelAndView("client", "model", myModel);
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}
}
