package fhj.shelf.repositories;

import java.util.Map;

/**
 * This interface sets the contract for all Users Repository, with the given
 * methods.
 * 
 * @authors Hugo Leal, José Oliveira, Filipa Estiveira
 *
 */
public interface UserRepository extends Repository<AbstractUser> {
	/**
	 * Gets the user with the given loginName, or {@code null} if none exists
	 * 
	 * @param loginName
	 *            the user identifier
	 * @return the instance with the given identifier
	 */
	public AbstractUser getUserName(String name);

	/**
	 * This method adds a new user to database.
	 *
	 */
	public boolean add(AbstractUser user);

	/**
	 * This method removes a existing user off database.
	 *
	 */
	public boolean remove(AbstractUser user);

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

	/**
	 * This method gets all Users in the repository
	 * @return
	 */
	public Map<String, AbstractUser> getUsers();

}