/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.client;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.Handler;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *Class tha provides the basic gestion data of a client in a bank
 * 
 * @author Gonzalo Nicolas Barreales
 */
public class Client {
    
    /**
     * Name of the Client
     */
	private String name;
	/**
	 * Surname of he client
	 */
	private String surname;
	
	
	/**
     * Identifier of the client
     */
    private DNIHandler id;
    
    /**
     * Accounts where the client appear
     */
    private ArrayList<Account> accounts;
    
    /**
     * Constructor of client. Receive the id and initilize the list of accounts
     * 
     * @param clientHandler 
     */
    public Client(Handler clientHandler){
        accounts = new ArrayList<>();
        this.id=(DNIHandler) clientHandler;
    }
    
    /**
     * Adds an account to the list of clients. If the account exists, it won't be added
     * 
     * @param account 
     */
    public void add(Account account){
        if(!accounts.contains(account)){
            accounts.add(account);
        }
    }
    
    /**
     * Remove the account identified with acountHandler
     * 
     * @param accountHandler
     * @return true if account is deleted, false if account doesn't exists
     */
    public boolean removeAccount(Handler accountHandler){
        boolean result = false;
        Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext()){
            Account account = iterator.next();
            if(account.getID().compareTo(accountHandler)==0){
                result = accounts.remove(account);
            }
        }
        return result;
    }
    
    /**
     * Check if the account idientified with account Handler exists
     * @param accountHandler
     * @return true if the account exists, false if it doesn't exists
     */
    public boolean existsAccount(Handler accountHandler){
        boolean result = false;
        Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext()){
            Account account = iterator.next();
            if(account.getID().compareTo(accountHandler)==0){
                result = true;
            }
        }
        return result;
    }

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
