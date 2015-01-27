package fhj.shelf.UI;

import fhj.shelf.utils.User;


/**
 * Class whose instance represent an Manager Login, to be used in the UI
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 *
 */
public class AdminLogin extends User  {
	 /**
	  * Attributes
	  */
	private String username;
	private String password;
	private String email;
	private String fullname;
	
	
	
	/**
	 * Constructor
	 * @param username
	 * @param password
	 * @param email
	 * @param fullName
	 * @throws IllegalArgumentException
	 */
    public AdminLogin(String username, String password, String email,
			String fullName) throws IllegalArgumentException {
    	super(username, password, email, fullName);
    	
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullName;
	}


    /**
     * Method to be call by Login class
     * @param username
     * @param password
     * @return
     */
	public  boolean loginAuthentication(String username, String password) {
       
        if (username.equals(this.username) && password.equals(this.password)) {
            return true;
        }
        return false;
    }
}
