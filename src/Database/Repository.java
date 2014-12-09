/**
 * 
 */
package Database;

import java.util.function.Predicate;


/**
 * @author amiguinhos do Maia
 *
 */
public interface Repository<T extends DatabaseElements>
{

	/**
	 * Gets all the DatabaseElements in the repository
	 * 
	 * @return The repository DatabaseElements
	 */
	public Iterable<T> getDatabaseElements();

	/**
	 * Gets all the DatabaseElements that pass to the given filtering criteria
	 * 
	 * @param criteria
	 *            The filtering criteria to be used
	 * @return The repository DatabaseElements that are within the specified filtering
	 *         criteria
	 */
	public Iterable<T> getDatabaseElements(Predicate<T> criteria);

	/**
	 * Adds the given DatabaseElements to the Database
	 * 
	 * @param product
	 *            The DatabaseElements to add to the Database
	 * @throws IllegalArgumentException
	 *             if the received DatabaseElements is {@code null}
	 */
	public void insert(T t);

}
