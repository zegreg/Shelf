package test.java.FHJ.shelf.model;


import main.java.FHJ.shelf.model.repos.DatabaseElements;




/**
 * This interface sets the contract for the UserInterface, with the given methods.
 * @authors Hugo Leal, José Oliveira, Filipa Estiveira
 */
public interface UserInterface extends DatabaseElements
{
	/**
	 * @return the username
	 */
	public String getLoginName();
	

	/**
	 * @return the password
	 */
	public String getLoginPassword();


	/**
	 * avoids duplicate user
	 */
	boolean equals(UserInterface user);


	/**
	 * @return the email
	 */
	String getEmail();


	/**
	 * @return the fullname
	 */
	String getFullName();
	

}
