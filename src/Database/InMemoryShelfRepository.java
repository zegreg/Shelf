package Database;

import afterSOLIDrevisionEHL.model.AbstractShelf;



/**
 * Class that implements an in-memory product repository (i.e. an ephemeral
 * TravelAgency catalog implementation)
 */
public class InMemoryShelfRepository extends InMemoryRepo<AbstractShelf> implements
		ShelfRepository
{
	/**
	 * 
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
	

	
