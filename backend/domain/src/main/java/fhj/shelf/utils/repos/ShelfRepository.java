package fhj.shelf.utils.repos;

import java.util.Map;




/**
 * Contract to be supported by shelfs repositories. An Shelf could contain elements (CD, Book, DVD and their Collections) 
 * 
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public interface ShelfRepository extends Repository<AbstractShelf>
{
	/**
	 * Gets the product with the given id, or {@code null} if none exists
	 *  
	 * @param id the product identifier
	 * @return the instance with the given identifier
	 */
	public abstract AbstractShelf getShelfById(long id);
	
	
	
	public abstract Map<Long, AbstractShelf> getShelfs();
	
	public abstract boolean add(AbstractShelf shelf);
	
	public abstract boolean remove(AbstractShelf data);
}
