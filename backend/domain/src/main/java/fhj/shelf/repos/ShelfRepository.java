package fhj.shelf.repos;

import java.util.Map;

import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.mutation.ShelfCreationDescriptor;


/**
 * Contract to be supported by shelfs repositories. An Shelf could contain elements (CD, Book, DVD and their Collections) 
 * 
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public interface ShelfRepository extends Repository<Shelf>
{
	/**
	 * Gets the product with the given id, or {@code null} if none exists
	 *  
	 * @param id the product identifier
	 * @return the instance with the given identifier
	 */
	public abstract Shelf getShelfById(long id);
	
	
	
	public abstract Map<Long, Shelf> getShelfs();
	
	public abstract long add(ShelfCreationDescriptor creationDescriptor);
	
	public abstract boolean remove(Shelf shelf);
}
