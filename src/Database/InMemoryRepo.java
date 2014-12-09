package Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

/**
 * @author amiguinhos do Maia
 * @param <T>
 *
 */
public abstract class InMemoryRepo<T extends DatabaseElements> implements
		Repository<T>
{
	/**
	 * Holds the database.
	 */
	private final Collection<T> database = new ArrayList<>();

	/**
	 * {@see ProductRepository#getProducts()}
	 */
	@Override
	public Iterable<T> getDatabaseElements()
	{
		return Collections.unmodifiableCollection(database);
	}

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Iterable<T> getDatabaseElements(Predicate<T> criteria)
	{
		ArrayList<T> results = new ArrayList<>();
		for (T databaseElement : database)
			if (criteria.test(databaseElement))
				results.add(databaseElement);

		return results;
	}

	/**
	 * Adds the given DatabaseElement to the Database
	 * 
	 * @param DatabaseElement
	 *            The DatabaseElement to add to the catalog
	 * @throws IllegalArgumentException
	 *             if the received DatabaseElement is {@code null}
	 */
	@Override
	public void insert(T DatabaseElement)
	{
		if (DatabaseElement == null)
			throw new IllegalArgumentException();

		database.add(DatabaseElement);
	}

}
