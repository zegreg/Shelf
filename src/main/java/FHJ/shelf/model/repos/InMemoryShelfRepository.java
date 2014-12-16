package main.java.FHJ.shelf.model.repos;

import main.java.FHJ.shelf.model.AbstractShelf;



/**
 * This class implements the contract in the {@link ShelfRepository}.
 *
 *@author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public class InMemoryShelfRepository extends InMemoryRepo<AbstractShelf> implements
		ShelfRepository
{
	/**
	 *  This method  search an shelf ID by implemented the {@code Iterable} in {@link InMemoryRepo} 
	 */
	@Override
	public AbstractShelf getShelfById(long sid)
	{
		for (AbstractShelf shelf : super.getDatabaseElements())
			if (shelf.getId() == sid)
				return shelf;

		return null;
	}
	
}
	

	
