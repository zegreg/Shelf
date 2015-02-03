package fhj.shelf.utils.repos;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class implements the contract in the {@link ShelfRepository}.
 *
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public class InMemoryShelfRepository extends InMemoryRepo<AbstractShelf>
		implements ShelfRepository {
	
	
	/**
     * Holds the shelfves
     */
	private Map<Long, AbstractShelf> shelfsContainer;
    
   
	private static AtomicInteger uniqueId = new AtomicInteger();
	private long id;

	
	public InMemoryShelfRepository() {
		this.id = uniqueId.getAndIncrement();
		shelfsContainer = Collections.synchronizedMap(new TreeMap<Long, AbstractShelf>());
	}

	/**
	 * This method search an shelf ID by implemented the {@code Iterable} in
	 * {@link InMemoryRepo}
	 */
	@Override
	public AbstractShelf getShelfById(long sid) {
		return shelfsContainer.get(sid);
	}

//	@Override
//	public boolean add(AbstractShelf shelf) {
//
//		if (!shelfsContainer.containsKey(shelf.getId())) {
//			shelfsContainer.put(shelf.getId(), shelf);
//			return true;
//		}
//		return false;
//	}

	public Map<Long, AbstractShelf> getShelfs() {
		return shelfsContainer;
	}
	
	@Override
	public boolean remove(AbstractShelf shelf) {
		
		if (shelf != null && shelfsContainer.containsKey(getId())) {
			shelfsContainer.remove(getId());
			return true;
		}
		return false;
	}

	 /**
     * {@see Repository#insert(ProductCreationDescriptor<?>)}
     */    
    @Override
    public long add(AbstractShelf shelf)
    {
        long newID = getId();
    	shelfsContainer.put(newID, shelf);
    	return newID;
    }

//    /**
//     * {@see Repository#insert(ProductMutationDescriptor<?>)}
//     */    
//	@Override
//	public Product update(AbstractShelf shelf) 
//	{
//		// TODO
//		throw new UnsupportedOperationException();
//	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}
	

}
