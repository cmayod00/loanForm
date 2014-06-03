package es.unileon.springapp.repository;

import java.util.List;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.client.Client;

public interface LoanDao {

    public List<Loan> getLoanList();

    public void addLoan(Loan loan);
    
    public Client getClient(String dni);
    
    public Account getAccount(String accountNumber);
    

}
