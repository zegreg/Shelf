package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import fhj.shelf.utils.repos.AbstractShelf;
import fhj.shelf.utils.repos.ShelfRepository;

/**
 * Class whose instances represent the command that gets a shelf from the shelf
 * repository
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetOneShelf implements Callable<AbstractShelf> {

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Shelf identification number
	 */
	private Long shelfID;

	/**
	 * Creates a command instance with the given repository and shelf's
	 * identification number wanted
	 * 
	 * @param shelfRepo
	 * @param shelfID
	 */
	public GetOneShelf(ShelfRepository shelfRepo, Long shelfID) {
		this.shelfRepository = shelfRepo;
		this.shelfID = shelfID;
	}

	/**
	 * 
	 * @return shelf with the identification number given
	 */
	@Override
	public AbstractShelf call() throws Exception {

		return shelfRepository.getShelfById(shelfID);
	}

}
