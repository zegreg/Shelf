package fhj.shelf.inMemoryRepositories;

import java.util.Map;

import fhj.shelf.repositories.Repository;
import model.fhj.shelf.model.Shelf;
import model.fhj.shelf.model.mutations.ShelfCreationDescriptor;

/**
 * Contract to be supported by shelfs repositories. An Shelf could contain
 * elements (CD, Book, DVD and their Collections)
 * 
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public interface ShelfRepository extends Repository<Shelf> {
	/**
	 * Gets the product with the given id, or {@code null} if none exists
	 * 
	 * @param id
	 *            the product identifier
	 * @return the instance with the given identifier
	 */
	public abstract Shelf getShelfById(long id);

	/**
	 * 
	 * @return a map with all shelfs in the repository, the key is the id of the
	 *         shelf and the value is the shelf
	 */
	public abstract Map<Long, Shelf> getShelfs();

	/**
	 * Add a {@link Shelf} to the repository
	 * 
	 * @param creationDescriptor
	 *            mutable shelf
	 * @return shelf's id
	 */
	public abstract long add(ShelfCreationDescriptor creationDescriptor);

	/**
	 * Remove shelf of the repository
	 * 
	 * @param shelf
	 *            to be removed
	 * @return true if success else returns false
	 */
	public abstract boolean remove(Shelf shelf);
}
