package Database;

import afterSOLIDrevisionEHL.model.AbstractElement;


/**
 * Contract to be supported by product repositories (i.e. TravelAgency catalog
 * implementations) 
 */

	public interface ElementsRepository extends Repository<AbstractElement> {

		/**
		 * Gets the product with the given id, or {@code null} if none exists
		 *  
		 * @param id the product identifier
		 * @return the instance with the given identifier
		 */
		public AbstractElement getElementById(long eid);

		
	}
