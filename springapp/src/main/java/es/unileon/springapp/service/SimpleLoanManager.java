package es.unileon.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.unileon.springapp.repository.LoanDao;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.client.Client;

@Component
public class SimpleLoanManager implements LoanManager {

    private static final long serialVersionUID = 1L;

    @Autowired
    private LoanDao loanDao;

    public void setLoanDao(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    /**
     * Obtain the loans stored in the database
     * @return
     */
    public List<Loan> getLoans() {
        return loanDao.getLoanList();
    }

    /**
     * Store the loan in the db
     */
	@Override
	public void addLoan(Loan loan) {
		loanDao.addLoan(loan);
	}

	@Override
	public Client getClient(String dni) {
		return loanDao.getClient(dni);
	}

	@Override
	public Account getAccount(String accountNumber) {
		return loanDao.getAccount(accountNumber);
	}

    
}