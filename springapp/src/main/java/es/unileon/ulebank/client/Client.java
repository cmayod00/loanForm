/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.client;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import es.unileon.ulebank.assets.Loan;

/**
 *Class tha provides the basic gestion data of a client in a bank
 * 
 * @author Gonzalo Nicolas Barreales
 */
@Entity
@Table(name="client") 
public class Client {
    
    /**
     * Name of the Client
     */
	@Column(name = "name")
	private String name;
	/**
	 * Surname of he client
	 */
	@Column(name = "surname")
	private String surname;
	
	
	/**
     * Identifier of the client
     */
	@Id
    @Column(name = "idClient")
    private String id;
    
    /**
     * Accounts where the client appear
     */
   // private ArrayList<Account> accounts;
    
//    @Column
//    @ElementCollection (targetClass=Loan.class)
//	//@OneToMany (cascade = CascadeType.ALL, targetEntity = Loan.class)
//    private List<Loan> loans;
    
    public Client(){
    	//loans= new ArrayList<Loan>();
        
    }
    /**
     * Constructor of client. Receive the id and initilize the list of accounts
     * 
     * @param clientHandler 
     */
    public Client(String clientHandler){
//        accounts = new ArrayList<>();
        // TODO !!!!Mal!!!
        this.id = clientHandler;
        //loans= new ArrayList<Loan>();
    }
    
    /**
     * Adds an account to the list of clients. If the account exists, it won't be added
     * 
     * @param account 
     */
//    public void add(Account account){
//        if(!accounts.contains(account)){
//            accounts.add(account);
//        }
//    }
    
//    public void addLoan(Loan loan){
//
//		//        Iterator<Account> iterator = accounts.iterator();
////        while(iterator.hasNext()){
////        	Iterator<Client> titular = iterator.next().getTitulars().iterator();
////        	while (titular.hasNext()) {
////        		if(this.id.toString().equals(titular.next().getId().toString())){
////            		
////    			}
////			}
////        }
//    	boolean found=false;
//    	for(int i=0; i < loans.size() && !found; i++) {
//    		if(loans.get(i).getIdLoan().equals(loan.getIdLoan())){
//    			found = true;
//    		}
//    	}
////    	if(!loans.contains(loan)){
//    	if(found) {
//            loans.add(loan);
//        }
//    	
//    }
//    
//    public List<Loan> getLoans() {
//		return loans;
//	}
//    

	/**
     * Remove the account identified with acountHandler
     * 
     * @param accountHandler
     * @return true if account is deleted, false if account doesn't exists
     */
//    public boolean removeAccount(String accountHandler){
//        boolean result = false;
//        Iterator<Account> iterator = accounts.iterator();
//        while(iterator.hasNext()){
//            Account account = iterator.next();
//            if(account.getID().compareTo(accountHandler)==0){
//                result = accounts.remove(account);
//            }
//        }
//        return result;
//    }
    
    /**
     * Check if the account idientified with account Handler exists
     * @param accountHandler
     * @return true if the account exists, false if it doesn't exists
     */
//    public boolean existsAccount(String accountHandler){
//        boolean result = false;
//        Iterator<Account> iterator = accounts.iterator();
//        while(iterator.hasNext()){
//            Account account = iterator.next();
//            if(account.getID().compareTo(accountHandler)==0){
//                result = true;
//            }
//        }
//        return result;
//    }

    /**
     * @return id of the client
     */
    public String getId() {
        return id.toString();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
 
    
}
