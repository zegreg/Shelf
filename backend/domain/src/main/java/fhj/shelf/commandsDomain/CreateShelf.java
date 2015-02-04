package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.repos.ShelfRepository;

/**
 * Class whose instances represent the command that creates a shelf
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class CreateShelf implements Callable<String> {

	
	private long id;
	/**
	 * Holds the shelf repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Shelf capacity
	 */
	private int nbElements;

	/**
	 * Creates a command instance with the given repository and the capacity of
	 * the shelf
	 * 
	 * @param shelfRepo
	 * @param nbElements
	 */
	public CreateShelf(ShelfRepository shelfRepo, int nbElements) {
		this.shelfRepository = shelfRepo;
		this.nbElements = nbElements;
		
	}

	/**
	 * This method creates shelf with the given capacity and returns the shelf
	 * id if the shelf was successful created, else returns a message of error.
	 * was successful or not.
	 * 
	 * @return a string with information about the success of the insertion of
	 *         an user in an user repository
	 * @throws Exception
	 */
	@Override
	public String call() throws Exception {

		Shelf shelf = new Shelf(nbElements);

		if (shelfRepository.add(shelf)) {

			return new StringBuilder("ShelfId: ").append(shelf.getId()).toString();
		}

		return "Unable to add shelf to Database";
		
	}
}
