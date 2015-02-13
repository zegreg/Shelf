package fhj.shelf.inMemoryRepositories;

import model.fhj.shelf.model.Element;
import model.fhj.shelf.model.mutations.ElementCreationDescriptor;
import fhj.shelf.repositories.Repository;


/**
 * Contract to be supported by elements repositories(CD, Book, DVD and their Collections) 
 * 
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 */

	public interface ElementsRepository extends Repository<Element> {

		
		public abstract long add(ElementCreationDescriptor<?> creationDescriptor);
		
		
		public abstract boolean remove(Element element);
		
		
		/**
		 * Gets the product with the given id, or {@code null} if none exists
		 *  
		 * @param id the product identifier
		 * @return the instance with the given identifier
		 */
		public abstract Element getDatabaseElementById(long eid);
		
		

	}

