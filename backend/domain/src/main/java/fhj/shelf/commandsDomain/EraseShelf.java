package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import fhj.shelf.repos.AbstractShelf;
import fhj.shelf.repos.ShelfRepository;

/**
 * Class whose instances represent the command that eliminates a shelf from a
 * shelf repository
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class EraseShelf implements Callable<String> {

	/**
	 * Holds the shelf repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Identification number of the shelf that will be eliminated
	 */
	private long shelfID;

	/**
	 * Creates a command instance with the given shelf repository, and shelf's
	 * identification number
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public EraseShelf(ShelfRepository shelfRepo, long shelfID) {
		this.shelfRepository = shelfRepo;

		this.shelfID = shelfID;
	}

	/**
	 * This method gets the shelf with the identification number given, remove
	 * all elements that are in it, and when the shelf is empty, removes the
	 * shelf from the shelf repository
	 * 
	 * @return a string with information about the success of the elimination of
	 *         the shelf in shelf repository
	 */
	@Override
	public String call() throws Exception {

		AbstractShelf shelf = shelfRepository.getShelfById(shelfID);

		shelf.removeAllElements();

		if (shelf.getFreeSpace() == shelf.getCapacity()) {
			shelfRepository.remove(shelf);
			return "Shelf " + shelfID + " removed successfuly";
		}

		return "Unable to add shelf to Database";
	}
}
