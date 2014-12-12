package Database;

import afterSOLIDrevisionEHL.model.AbstractElement;


public class InMemoryElementsRepository extends InMemoryRepo<AbstractElement> implements ElementsRepository{

/**
 * 
 */
	@Override
	public AbstractElement getElementById(long eid) {
		for (AbstractElement element : super.getDatabaseElements())
			if (element.getId() == eid)
				return element;
	return null;
}
}


