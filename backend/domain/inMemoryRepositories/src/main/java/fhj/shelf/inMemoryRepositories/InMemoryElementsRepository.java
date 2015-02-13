package fhj.shelf.inMemoryRepositories;


import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

import model.fhj.shelf.model.Element;
import model.fhj.shelf.model.mutations.ElementCreationDescriptor;

/**
 * 
 * This class implements the contract in the {@link ElementsRepository}.
 *
 *@author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public class InMemoryElementsRepository extends InMemoryRepo<Element> implements ElementsRepository{
	
	
	/**
     * Holds the shelfves
     */
	private Map<Long, Element> elementsContainer;
    
    /**
     * Holds the last used identifier
     */
    private static final AtomicLong lastId = new AtomicLong(0);

	
	public InMemoryElementsRepository()
	{
		
		elementsContainer = Collections.synchronizedMap(new TreeMap<Long, Element>());

	}
/**
 * This method  search an element ID by implemented the {@code Iterable} in {@link InMemoryRepo}
 */
	@Override
	public Element getDatabaseElementById(long eid) {
		return elementsContainer.get(eid);
}

	@Override
	public boolean remove(Element element) {
		
		if (element != null && elementsContainer.containsKey(element.getId())) {
			elementsContainer.remove(element.getId());
			return true;
		}
		return false;
	}
	
	
	@Override
	public long add(ElementCreationDescriptor<?> creationDescriptor)  {
		
		long newID = lastId.incrementAndGet();
		elementsContainer.put(newID, creationDescriptor.build(newID));
    	return newID;
	}
	    

}


