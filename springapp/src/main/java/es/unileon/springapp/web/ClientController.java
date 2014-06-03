package es.unileon.springapp.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.springapp.service.LoanManager;
import es.unileon.springapp.service.SimpleLoanManager;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.office.Office;

@Controller
public class ClientController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private LoanManager simpleLoanManager;
	
	
	

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

		List<Loan> loans = simpleLoanManager.getLoans();

		myModel.put("client", this.simpleLoanManager.getClient("71560136Y"));
		myModel.put("loans", loans);

		return new ModelAndView("client", "model", myModel);
	}

	
	public void setLoanManager(SimpleLoanManager slm) {
		this.simpleLoanManager = slm;
		
	}
}
