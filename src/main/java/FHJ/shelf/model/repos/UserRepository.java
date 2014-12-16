/**
 * 
 */
package main.java.FHJ.shelf.model.repos;

import test.java.FHJ.shelf.model.UserInterface;



/**
 * This interface sets the contract for the User Repository, with the given methods.
 * @authors Hugo Leal, José Oliveira, Filipa Estiveira
 *
 */
public interface UserRepository extends Repository<UserInterface>
{
	/**
	 * Gets the user with the given loginName, or {@code null} if none exists
	 *  
	 * @param loginName the user identifier
	 * @return the instance with the given identifier
	 */
	public UserInterface getUserName(String name);
	
	/**
	 * This method verifies if the user was already added.
	 *
	 */
	public boolean add(UserInterface user);
	
	/**
	 * This method verifies if username and password are valid.
	 *
	 */
	public boolean validatePassword(String username, String password);

	/**
	 * This method delivers the size of the user.
	 *
	 */
	public int getSize();
	
	
}