package es.unileon.springapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.client.Client;

@Repository(value = "loanDao")
public class JPALoanDao implements LoanDao {

	private EntityManager em = null;

	/*
	 * Sets the entity manager.
	 */
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Loan> getLoanList() {
		return em.createQuery("select p from Loan p order by p.idLoan")
				.getResultList();
	}

	@Transactional(readOnly = false)
	public void addLoan(Loan loan) {
		em.merge(loan);
	}

	public Client getClient(String dni) {
		Query query = em.createQuery("select c from Client c where c.id= :dni");
		query.setParameter("dni", dni);

		return (Client) query.getSingleResult();
	}

	public Account getAccount(String accountNumber) {
		Query query = em
				.createQuery("select a from Account a where a.id= :accountNumber");
		query.setParameter("accountNumber", accountNumber);
		return (Account) query.getSingleResult();
	}

}