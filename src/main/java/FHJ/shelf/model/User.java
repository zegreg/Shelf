package main.java.FHJ.shelf.model;

import main.java.FHJ.shelf.model.repos.UserInterface;




/**
 * This class defines the User, it has all the properties of the UserInterface.
 *
 *
 * @authors Hugo Leal, José Oliveira, Filipa Estiveira 
 *
 */
public class User implements UserInterface
{


	
	private final String email;
	private final String fullName;
	private String username;
	private String password;



	/**
	 * This is the constructor of the class, it defines the user.
	 *
	 */
	public User(String username, String password, String email, String fullName)
	{
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
	}


 	
	/**
	 * This is an override method the base class, it delivers the  
	 * login of the user.
	 */
	@Override
	public String getLoginName() {
		// TODO Auto-generated method stub
		return username;
	}
	
	

	/**
	 * This is an override method of the base class, it delivers 
	 * the user's password.
	 */
	@Override
	public String getLoginPassword() {
		// TODO Auto-generated method stub
		return password;
	}


	/**
	 * This is an override method of the base class, it delivers the user's email.
	 */
	@Override
	public String getEmail()
	{
		return email;
	}

	
	/**
	 * This is an override method of the base class, it delivers the user's full name.
	 */
	@Override
	public String getFullName()
	{
		return fullName;
	}

	
	/**
	 * This is an override method of the base class, it delivers the user's 
	 * hashcode.
	 *
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/**
	 * This is an overrride method of the base class, it verifies if the user
	 * is unique.
	 *
	 */	
	@Override
	public boolean equals(UserInterface user) {
		if (this == user) {
			return true;
		}
		if (user == null) {
			return false;
		}
		
		if( !getClass().equals( user.getClass() ) )
			return false;
		
		if( !this.username.equals( ((User)user).username ) )
			return false;
		if( !this.password.equals( ((User)user).password ) )
			return false;
		if( !this.fullName.equals( ((User)user).fullName ) )
			return false;
		if( !this.email.equals( ((User)user).email ) )
			return false;
		
		return true;
	}

	
	/**
	 * This is an override method of the base class, it delivers the string  
	 * that contains all of the users.
	 *
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("USER Content\n\n\n");

		return builder.append("\nLoginName : ").append(this.username).
				append("\nLoginPassword : ").append(this.password).
				append("\nemail : ").append(this.email).
				append("\nFullName : ").append(this.fullName).
				append("\n").toString();

	}



	@Override
	public void setLoginName(String new_user) {
		this.username = new_user;
		
	}



	@Override
	public void setLoginPassword(String new_user) {
		this.password = new_user;
		
	}




}