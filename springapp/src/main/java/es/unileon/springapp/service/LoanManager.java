package es.unileon.springapp.service;

import java.util.List;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.client.Client;

public interface LoanManager {

	public List<Loan> getLoans();
	
	public void addLoan(Loan loan);

	public Client getClient(String dni);
	
	public Account getAccount(String accountNumber);
	
}
