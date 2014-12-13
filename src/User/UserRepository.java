/**
 * 
 */
package User;

import Database.Repository;



/**
 * @author amiguinhos do Maia
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
	
	
}
