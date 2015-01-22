package fhj.shelf.utils.repos;


/**
 * This interface sets the contract for the UserInterface, with the given methods.
 * @authors Hugo Leal, José Oliveira, Filipa Estiveira
 */
public abstract class AbstractUser implements DatabaseElements
{
	/**
	 * @return the username
	 */
	public abstract String getLoginName();
	

	/**
	 * @return the password
	 */
	public abstract String getLoginPassword();

	/**
	 * @change the username
	 */
	public abstract void setLoginName(String new_user);

	/**
	 * @change the username
	 */
	public abstract void setLoginPassword(String new_user);
	
	
	/**
	 * avoids duplicate user
	 */
	
	public abstract boolean equals(AbstractUser user);

	/**
	 * 
	 * @return
	 */
	public abstract int hashCode();

	/**
	 * @return the email
	 */
	public abstract String getEmail();


	/**
	 * @return the fullname
	 */
	public abstract String getFullName();
	

}
