package Database;

import afterSOLIDrevisionEHL.model.AbstractShelf;




/**
 * Contract to be supported by product repositories (i.e. TravelAgency catalog
 * implementations) 
 */
public interface ShelfRepository extends Repository<AbstractShelf>
{
	/**
	 * Gets the product with the given id, or {@code null} if none exists
	 *  
	 * @param id the product identifier
	 * @return the instance with the given identifier
	 */
	public AbstractShelf getProductById(long id);
}
