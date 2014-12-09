package Database;

import afterSOLIDrevisionEHL.model.Shelf;

import afterSOLIDrevisionEHL.model.Shelf;



/**
 * Contract to be supported by product repositories (i.e. TravelAgency catalog
 * implementations) 
 */
public interface ShelfRepository extends Repository<Shelf>
{
	/**
	 * Gets the product with the given id, or {@code null} if none exists
	 *  
	 * @param id the product identifier
	 * @return the instance with the given identifier
	 */
	public Shelf getProductById(long id);
}
