package fhj.shelf.utils.repos;

import java.util.Map;
import java.util.TreeMap;

import fhj.shelf.utils.AbstractShelf;

/**
 * This class implements the contract in the {@link ShelfRepository}.
 *
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public class InMemoryShelfRepository extends InMemoryRepo<AbstractShelf>
		implements ShelfRepository {
	
	private Map<Long, AbstractShelf> shelfsContainer;

	public InMemoryShelfRepository() {
		shelfsContainer = new TreeMap<Long, AbstractShelf>();
	}

	/**
	 * This method search an shelf ID by implemented the {@code Iterable} in
	 * {@link InMemoryRepo}
	 */
	@Override
	public AbstractShelf getShelfById(long sid) {
		return shelfsContainer.get(sid);
	}

	@Override
	public boolean add(AbstractShelf shelf) {

		if (!shelfsContainer.containsKey(shelf.getId())) {
			shelfsContainer.put(shelf.getId(), shelf);
			return true;
		}
		return false;
	}

	public Map<Long, AbstractShelf> getShelfs() {
		return shelfsContainer;
	}
	
	@Override
	public boolean remove(AbstractShelf shelf) {
		
		if (shelf != null && shelfsContainer.containsKey(shelf.getId())) {
			shelfsContainer.remove(shelf.getId());
			return true;
		}
		return false;
	}


}
