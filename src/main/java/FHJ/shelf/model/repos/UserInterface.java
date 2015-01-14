package main.java.FHJ.shelf.model.repos;






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
	 * @change the username
	 */
	public void setLoginName(String new_user);

	/**
	 * @change the username
	 */
	public void setLoginPassword(String new_user);
	
	
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
