package es.unileon.ulebank.assets;

import es.unileon.ulebank.assets.handler.Handler;
import es.unileon.ulebank.assets.handler.LoanIdentificationNumberCode;

public interface FinancialProduct {
	public void update();
	public Handler getId();
}
