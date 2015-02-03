package fhj.shelf.utils.repos;





/**
 * Contract to be supported by elements repositories(CD, Book, DVD and their Collections) 
 * 
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 */

	public interface ElementsRepository extends Repository<AbstractElement> {

		
		public abstract long add(AbstractElement element);
		
		
		public abstract boolean remove(AbstractElement element);
		
		
		/**
		 * Gets the product with the given id, or {@code null} if none exists
		 *  
		 * @param id the product identifier
		 * @return the instance with the given identifier
		 */
		public abstract AbstractElement getDatabaseElementById(long eid);
		
		public abstract long getId();

	}

