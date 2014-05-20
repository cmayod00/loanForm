package es.unileon.ulebank.assets.command;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.Handler;
import es.unileon.ulebank.assets.handler.LoanIdentificationNumberCode;
import es.unileon.ulebank.assets.strategy.commission.PercentCommission;
import es.unileon.ulebank.assets.strategy.commission.StrategyCommission;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.support.LoanList;
import es.unileon.ulebank.support.PaymentPeriod;



public class CreateLoanCommand implements Command {

	private Handler idCommand;
	private LoanIdentificationNumberCode idLoan;
	private StrategyCommission cancelCommission;
	private StrategyCommission studyCommission;
	private StrategyCommission modifyCommission;
	private StrategyCommission openningCommission;
	private double initialCapital;
	private double interest;
	private PaymentPeriod paymentPeriod;
	private int amortizationTime;
	private Account account;
	private Client client;
	private Loan loan;

	private LoanList<Loan> loanList;

	public CreateLoanCommand(Handler idCommand, Handler idLoan,
			double initialCapital, double interest,
			PaymentPeriod paymentPeriod, int amortizationTime,
			LoanList<Loan> loanList) {

		this.idCommand = idCommand;
		this.idLoan = (LoanIdentificationNumberCode) idLoan;
		this.cancelCommission = new PercentCommission();
		this.studyCommission = new PercentCommission();
		this.modifyCommission = new PercentCommission();
		this.openningCommission = new PercentCommission();
		this.initialCapital = initialCapital;
		this.interest = interest;
		this.paymentPeriod = paymentPeriod;
		this.amortizationTime = amortizationTime;
		this.loanList = loanList;
		this.client = client;
	}

	@Override
	public void execute() {
		try {
			this.loan = new Loan(this.idLoan, this.initialCapital,
					this.interest, this.paymentPeriod, this.amortizationTime,
					this.account,this.client);
		} catch (LoanException e) {
		}

		this.loanList.addLoan(this.loan);

	}

	@Override
	public void undo() {
		this.loanList.removeLoan(this.loan);
	}

	@Override
	public void redo() {
		this.loanList.addLoan(this.loan);
	}

	@Override
	public Handler getId() {
		return this.idCommand;
	}

}
