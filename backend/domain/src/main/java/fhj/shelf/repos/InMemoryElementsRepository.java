package fhj.shelf.repos;


import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 
 * This class implements the contract in the {@link ElementsRepository}.
 *
 *@author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public class InMemoryElementsRepository extends InMemoryRepo<AbstractElement> implements ElementsRepository{
	
	
	/**
     * Holds the shelfves
     */
	private Map<Long, AbstractElement> elementsContainer;
    
   
	private static AtomicInteger uniqueId = new AtomicInteger();
	private long id;

	
	public InMemoryElementsRepository()
	{
		this.id = uniqueId.getAndIncrement();
		elementsContainer = Collections.synchronizedMap(new TreeMap<Long, AbstractElement>());

	}
/**
 * This method  search an element ID by implemented the {@code Iterable} in {@link InMemoryRepo}
 */
	@Override
	public AbstractElement getDatabaseElementById(long eid) {
		return elementsContainer.get(eid);
}

	@Override
	public boolean remove(AbstractElement element) {
		
		if (element != null && elementsContainer.containsKey(element.getId())) {
			elementsContainer.remove(element.getId());
			return true;
		}
		return false;
	}
	
	
//	@Override
//	public boolean add(AbstractElement element)  {
//		
//		if ( !elementsContainer.containsKey(element.getId())) {
//			elementsContainer.put( element.getId(), element);
//			return true;
//		}
//		return false;
//	}
	
	 /**
     * {@see Repository#insert(ProductCreationDescriptor<?>)}
     */    
    @Override
    public long add(AbstractElement element)
    {
        long newID = getId();
    	elementsContainer.put(newID, element);
    	return newID;
    }
    

    @Override
    public long getId() {
		return id;
	}
    public void setId(long id) {
		this.id = id;
	}
}


