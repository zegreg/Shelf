package fhj.shelf.commandsDomain;

import java.util.Map;
import java.util.concurrent.Callable;

import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.repos.ShelfRepository;

public class GetAllShelfs implements Callable<Map<Long, AbstractShelf>> {

	/**
	 * Holds the associated shelf repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated shelf repository
	 */
	public GetAllShelfs(ShelfRepository shelfRepo) {
		this.shelfRepository = shelfRepo;
	}

	/**
	 * 
	 * @return the repository with all the shelfs
	 * @throws Exception
	 */
	@Override
	public Map<Long, AbstractShelf> call() throws Exception {
		return shelfRepository.getShelfs();
	}
}


