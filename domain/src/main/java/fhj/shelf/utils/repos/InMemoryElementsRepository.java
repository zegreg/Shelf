package fhj.shelf.utils.repos;


import fhj.shelf.utils.AbstractElement;

/**
 * 
 * This class implements the contract in the {@link ElementsRepository}.
 *
 *@author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public class InMemoryElementsRepository extends InMemoryRepo<AbstractElement> implements ElementsRepository{

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
}


