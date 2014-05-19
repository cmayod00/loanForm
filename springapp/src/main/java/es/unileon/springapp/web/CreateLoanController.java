package es.unileon.springapp.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

import es.unileon.springapp.beans.LoanBean;
import es.unileon.springapp.service.PriceIncrease;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.assets.KindOfMethod;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.Handler;
import es.unileon.ulebank.assets.handler.LoanIdentificationNumberCode;
import es.unileon.ulebank.assets.handler.exceptions.LINCMalformedException;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.support.PaymentPeriod;


@Controller
@RequestMapping(value="/createloan.htm")
public class CreateLoanController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private Client client;
    @Autowired
    private Office office;
    @Autowired
    private Bank bank;
    @Autowired
    private Account account;
    

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@Valid LoanBean loanBean, BindingResult result) throws LINCMalformedException, LoanException
    {
    	 System.out.println(loanBean.getAmortizationTime());
        
         System.out.println(loanBean.getInitialCapital());
         System.out.println(loanBean.getInterest());
         System.out.println(loanBean.getLoanType());
         System.out.println(loanBean.getPaymentPeriod());
        if (result.hasErrors()) {
           // return new ModelAndView("OKJ");
        }
	
        Handler idLoan = new LoanIdentificationNumberCode("MG", "ES");
        loanBean.setId(idLoan.toString());
        PaymentPeriod period = getPaymentPeriod(loanBean.getPaymentPeriod());
        Loan loan = new Loan(idLoan, loanBean.getInitialCapital(), loanBean.getInterest(), period, loanBean.getAmortizationTime(), account);
        
        Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("client", this.client);
		myModel.put("createloan", loanBean);

        return new ModelAndView("client","model",myModel);
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public String onSubmit(@Valid LoanBean loanBean, BindingResult result) throws LINCMalformedException, LoanException
//    {
//        if (result.hasErrors()) {
//            //return new ModelAndView("createloan");
//        	//return "error";
//        }
//	
//        LoanIdentificationNumberCode idLoan = new LoanIdentificationNumberCode("MG", "ES");
//        //Loan loan = new Loan(idLoan, loanBean.getInitialCapital(), loanBean.getInterest(), loanBean.getPaymentPeriod(), loanBean.getAmortizationTime(), account);
//
//       // loanBean.setId(idLoan.toString());
//        return "redirect:/client.htm";
//        //return new ModelAndView("client","model",loanBean);
//    }

    @RequestMapping(method = RequestMethod.GET)
    protected String init(ModelMap model) {
        LoanBean bean = new LoanBean();
        
        model.addAttribute("createloan", bean);
    	return "createloan";
    }
    
//    @RequestMapping(method = RequestMethod.GET)
//    protected LoanBean init(ModelMap model) {
//        LoanBean bean = new LoanBean();
//        
//       // model.addAttribute("createloan", bean);
//    	return bean;
//    }
    
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
    
    
    @ModelAttribute("paymentPeriod")
    public Map<String, String> populatePaymentPeriod(){
		Map<String, String> paymentPeriod = new LinkedHashMap<String, String>();
		paymentPeriod.put("Annual", PaymentPeriod.ANNUAL.toString());
		paymentPeriod.put("Biannual", PaymentPeriod.BIANNUAL.toString());
		paymentPeriod.put("Monthly", PaymentPeriod.MONTHLY.toString());
		paymentPeriod.put("Quarterly", PaymentPeriod.QUARTERLY.toString());
		paymentPeriod.put("Twicemonthly", PaymentPeriod.TWICEMONTHLY.toString());
    	return paymentPeriod;
    	
    }
    @ModelAttribute("loanType")
    public Map<String, String> populateLoanType(){
    	Map<String, String> loanType = new LinkedHashMap<String, String>();
    	loanType.put("American Method", KindOfMethod.American.toString());
    	loanType.put("French Method", KindOfMethod.French.toString());
    	loanType.put("German Method", KindOfMethod.German.toString());
    	loanType.put("Italian Method", KindOfMethod.Italian.toString());
    	loanType.put("Progressive Method", KindOfMethod.Progressive.toString().toString());
    	return loanType;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
   

}