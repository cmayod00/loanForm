package es.unileon.springapp.beans;


public class LoanBean {
	private double initialCapital;
	private double interest;
	private int amortizationTime;
	private String paymentPeriod;
	private String loanType;
	private String id;
	public double getInitialCapital() {
		return initialCapital;
	}
	public void setInitialCapital(double initialCapital) {
		this.initialCapital = initialCapital;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public int getAmortizationTime() {
		return amortizationTime;
	}
	public void setAmortizationTime(int amortizationTime) {
		this.amortizationTime = amortizationTime;
	}
	public String getPaymentPeriod() {
		return paymentPeriod;
	}
	public void setPaymentPeriod(String paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}
	
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
