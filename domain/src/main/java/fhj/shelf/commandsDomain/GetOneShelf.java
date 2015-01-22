package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.repos.ShelfRepository;


public class GetOneShelf implements Callable<AbstractShelf>{

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;

	private Long shelfID;

	/**
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public GetOneShelf(ShelfRepository shelfRepo, Long shelfID) {
		this.shelfRepository = shelfRepo;
		this.shelfID = shelfID;
	}

	/**
	 * 
	 * @return the repository with all the users
	 * @throws Exception
	 */
	@Override
	public AbstractShelf call() throws Exception {

		return shelfRepository.getShelfById(shelfID);
	}

}

