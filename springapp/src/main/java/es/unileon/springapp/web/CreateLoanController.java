package es.unileon.springapp.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.springapp.service.LoanBean;
import es.unileon.springapp.service.LoanManager;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.KindOfMethod;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.PaymentPeriod;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.FinancialProductHandler;
import es.unileon.ulebank.assets.handler.exceptions.LINCMalformedException;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.handler.MalformedHandlerException;

@Controller
@RequestMapping(value = "/createloan.htm")
public class CreateLoanController {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	
	@Autowired
	private LoanManager simpleLoanManager;

	/**
	 * Recibe los parametros que le pasa la vista y realiza la funcion de crear
	 * un prestamo.
	 * 
	 * @param loanBean
	 * @param result
	 * @return
	 * @throws LINCMalformedException
	 * @throws LoanException
	 * @throws MalformedHandlerException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(@Valid LoanBean loanBean, BindingResult result)
			throws LINCMalformedException, LoanException, MalformedHandlerException {
		if (result.hasErrors()) {
			return new ModelAndView("The page has been falled");
		}

		Client client = simpleLoanManager.getClient("71560136Y");
		Account account = simpleLoanManager.getAccount("123412340000000000");
		String idLoan = new FinancialProductHandler("MG", "ES").toString();
		loanBean.setId(idLoan);
		String period = loanBean.getPaymentPeriod();
		Loan loan = new Loan(idLoan, loanBean.getInitialCapital(),
				loanBean.getInterest(), period, loanBean.getAmortizationTime(),
				account, client);
		simpleLoanManager.addLoan(loan);
		//client.addLoan(loan);
		Map<String, Object> myModel = new HashMap<String, Object>();
		//List<Loan> loans = client.getLoans();
		myModel.put("client", client);
		myModel.put("loans", simpleLoanManager.getLoans());

		return new ModelAndView("client", "model", myModel);
	}

	/**
	 * Recibe peticiones GET de la vista y devuleve los atributos que se le
	 * piden
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected String init(ModelMap model) {
		LoanBean bean = new LoanBean();

		model.addAttribute("createloan", bean);
		return "createloan";
	}

	/**
	 * Metodo para obtener el string the un paymentPeriod para poder mostrarlo
	 * en el formulario
	 * 
	 * @param paymentPeriod
	 * @return
	 */
	private PaymentPeriod getPaymentPeriod(String paymentPeriod) {
		if (paymentPeriod.equalsIgnoreCase("ANNUAL")) {
			return PaymentPeriod.ANNUAL;
		} else if (paymentPeriod.equalsIgnoreCase("BIANNUAL")) {
			return PaymentPeriod.BIANNUAL;
		} else if (paymentPeriod.equalsIgnoreCase("MONTHLY")) {
			return PaymentPeriod.MONTHLY;
		} else if (paymentPeriod.equalsIgnoreCase("QUARTERLY")) {
			return PaymentPeriod.QUARTERLY;
		} else if (paymentPeriod.equalsIgnoreCase("TWICEMONTHLY")) {
			return PaymentPeriod.TWICEMONTHLY;
		}

		return PaymentPeriod.MONTHLY;
	}

	/**
	 * Sirve para mandar los paymentPeriod a la vista
	 * 
	 * @return
	 */
	@ModelAttribute("paymentPeriod")
	public Map<String, String> populatePaymentPeriod() {
		Map<String, String> paymentPeriod = new LinkedHashMap<String, String>();
		paymentPeriod.put("Annual", PaymentPeriod.ANNUAL.toString());
		paymentPeriod.put("Biannual", PaymentPeriod.BIANNUAL.toString());
		paymentPeriod.put("Monthly", PaymentPeriod.MONTHLY.toString());
		paymentPeriod.put("Quarterly", PaymentPeriod.QUARTERLY.toString());
		paymentPeriod
				.put("Twicemonthly", PaymentPeriod.TWICEMONTHLY.toString());
		return paymentPeriod;

	}

	/**
	 * Metodo que sirve para mandar los tipos de pago posibles de cuotas a la
	 * vista
	 * 
	 * @return
	 */
	@ModelAttribute("loanType")
	public Map<String, String> populateLoanType() {
		Map<String, String> loanType = new LinkedHashMap<String, String>();
		loanType.put("American Method", KindOfMethod.American.toString());
		loanType.put("French Method", KindOfMethod.French.toString());
		loanType.put("German Method", KindOfMethod.German.toString());
		loanType.put("Italian Method", KindOfMethod.Italian.toString());
		loanType.put("Progressive Method", KindOfMethod.Progressive.toString()
				.toString());
		return loanType;
	}


}