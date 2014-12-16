package main.java.FHJ.shelf.model.repos;

import main.java.FHJ.shelf.model.AbstractElement;


/**
 * Contract to be supported by elements repositories(CD, Book, DVD and their Collections) 
 * 
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
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
