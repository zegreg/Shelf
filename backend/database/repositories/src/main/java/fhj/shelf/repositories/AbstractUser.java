package fhj.shelf.repositories;

/**
 * This class sets the operations for all type of users 
 * 
 * @authors Hugo Leal, José Oliveira, Filipa Estiveira
 */
public abstract class AbstractUser implements DatabaseElements {
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
	public abstract void setLoginName(String newUsername);

	/**
	 * @change the password
	 */
	public abstract void setLoginPassword(String newPassword);

	/**
	 * avoids duplicate user
	 */
	@Override
	public abstract boolean equals(Object user);

	/**
	 * 
	 * @return
	 */
	@Override
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
