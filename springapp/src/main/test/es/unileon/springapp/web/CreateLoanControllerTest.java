package es.unileon.springapp.web;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import es.unileon.ulebank.assets.KindOfMethod;
import es.unileon.ulebank.support.PaymentPeriod;

public class CreateLoanControllerTest {

	private CreateLoanController controller;

	@Before
	public void setUp() throws Exception {
		this.controller = new CreateLoanController();
	}

	@Test
	public void testInit() {
		ModelMap map = new ModelMap();
		String result = controller.init(map);
		assertEquals("createloan",result);
	}
	
	@Test
	public void testPopulatePaymentPeriod(){
		Map<String, String> paymentPeriod = controller.populatePaymentPeriod();
		assertTrue(!paymentPeriod.isEmpty());
		assertTrue(paymentPeriod.containsValue(PaymentPeriod.MONTHLY.toString()));
		assertTrue(paymentPeriod.containsValue(PaymentPeriod.BIANNUAL.toString()));
		assertTrue(paymentPeriod.containsValue(PaymentPeriod.ANNUAL.toString()));
		assertTrue(paymentPeriod.containsValue(PaymentPeriod.QUARTERLY.toString()));
		assertTrue(paymentPeriod.containsValue(PaymentPeriod.TWICEMONTHLY.toString()));
		
	}
	
	@Test
	public void testPopulateLoanType(){
		Map<String, String> loanType = controller.populateLoanType();
		assertTrue(!loanType.isEmpty());
		assertTrue(loanType.containsValue(KindOfMethod.American.toString()));
		assertTrue(loanType.containsValue(KindOfMethod.French.toString()));
		assertTrue(loanType.containsValue(KindOfMethod.German.toString()));
		assertTrue(loanType.containsValue(KindOfMethod.Italian.toString()));
		assertTrue(loanType.containsValue(KindOfMethod.Progressive.toString()));
	}

}
