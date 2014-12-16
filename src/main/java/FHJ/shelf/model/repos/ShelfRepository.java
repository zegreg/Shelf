package main.java.FHJ.shelf.model.repos;

import main.java.FHJ.shelf.model.AbstractShelf;




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
	public AbstractShelf getShelfById(long id);
	
	
	
	
}
