package es.unileon.springapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.client.Client;

public class InMemoryLoanDao implements LoanDao {
	private List<Loan> loansList;

	public InMemoryLoanDao(List<Loan> loansList) {
		this.loansList = loansList;
	}

	@Override
	public List<Loan> getLoanList() {
		return loansList;
	}

	@Override
	public void addLoan(Loan loan) {
		loansList.add(loan);

	}

	@Override
	public Client getClient(String dni) {
		return null;
	}

	@Override
	public Account getAccount(String accountNumber) {

		return null;
	}
}
