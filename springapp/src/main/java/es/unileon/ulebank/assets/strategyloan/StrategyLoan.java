package es.unileon.ulebank.assets.strategyloan;

import java.util.ArrayList;


/**
 * Interface for implement the strategy pattern over method of of calculate interest.
 * 
 * 
 * v1.0 Initial version
 */
public interface StrategyLoan {
	
	public ArrayList<ScheduledPayment> doCalculationOfPayments();
}
