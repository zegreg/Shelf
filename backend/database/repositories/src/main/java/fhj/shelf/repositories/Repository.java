package fhj.shelf.repositories;

import java.util.function.Predicate;

/**
 * @author Grupo dos Formandos do programa Reprograma a tua Carreira
 *
 */
public interface Repository<T extends DatabaseElements> {

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
	 * @return The repository DatabaseElements that are within the specified
	 *         filtering criteria
	 */
	public Iterable<T> getDatabaseElements(Predicate<T> criteria);

}
