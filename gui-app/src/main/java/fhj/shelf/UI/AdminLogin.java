package fhj.shelf.UI;

import fhj.shelf.utils.User;



public class AdminLogin extends User  {
	 
	String username;
	String password;
	String email;
	String fullname;
	
    public AdminLogin(String username, String password, String email,
			String fullName) throws IllegalArgumentException {
    	super(username, password, email, fullName);
    	
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullName;
	}


    
	public  boolean authenticate(String username, String password) {
       
        if (username.equals(this.username) && password.equals(this.password)) {
            return true;
        }
        return false;
    }
}
