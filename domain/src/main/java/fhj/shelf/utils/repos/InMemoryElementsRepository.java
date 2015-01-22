package fhj.shelf.utils.repos;


import java.util.Map;
import java.util.TreeMap;

import fhj.shelf.utils.AbstractElement;
import fhj.shelf.utils.AbstractShelf;



/**
 * 
 * This class implements the contract in the {@link ElementsRepository}.
 *
 *@author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public class InMemoryElementsRepository extends InMemoryRepo<AbstractElement> implements ElementsRepository{

	private Map<Long, AbstractElement> elementsContainer;
	
	public InMemoryElementsRepository()
	{
		elementsContainer = new TreeMap<Long, AbstractElement>();

	}
/**
 * This method  search an element ID by implemented the {@code Iterable} in {@link InMemoryRepo}
 */
	@Override
	public AbstractElement getElementById(long eid) {
		for (AbstractElement element : super.getDatabaseElements())
			if (element.getId() == eid)
				return element;
	return null;
}

	@Override
	public void remove(AbstractElement t) {
		for (AbstractElement element : super.getDatabaseElements())
			if (element.equals(t))
				super.remove(t);
	}
	
	@Override
	public boolean add(AbstractElement element)  {
		
		if ( !elementsContainer.containsKey(element.getId())) {
			elementsContainer.put( element.getId(), element);
			return true;
		}
		return false;
	}
}


