package fhj.shelf.inMemoryRepositories;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

import model.fhj.shelf.model.Shelf;
import model.fhj.shelf.model.mutations.ShelfCreationDescriptor;


/**
 * This class implements the contract in the {@link ShelfRepository}.
 *
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 */
public class InMemoryShelfRepository extends InMemoryRepo<Shelf> implements
		ShelfRepository {

	/**
	 * Holds the shelfs
	 */
	private Map<Long, Shelf> shelfsContainer;

	/**
	 * Holds the last used identifier
	 */
	private static final AtomicLong lastId = new AtomicLong(0);

	public InMemoryShelfRepository() {

		shelfsContainer = Collections
				.synchronizedMap(new TreeMap<Long, Shelf>());
	}

	/**
	 * This method search an shelf ID by implemented the {@code Iterable} in
	 * {@link InMemoryRepo}
	 */
	@Override
	public Shelf getShelfById(long sid) {
		return shelfsContainer.get(sid);
	}

	@Override
	public long add(ShelfCreationDescriptor creationDescriptor) {

		long newID = lastId.incrementAndGet();
		shelfsContainer.put(newID, creationDescriptor.build(newID));
		return newID;
	}

	public Map<Long, Shelf> getShelfs() {
		return shelfsContainer;
	}

	@Override
	public boolean remove(Shelf shelf) {

		if (shelf != null && shelfsContainer.containsKey(shelf.getId())) {
			shelfsContainer.remove(shelf.getId());
			return true;
		}
		return false;
	}

}
