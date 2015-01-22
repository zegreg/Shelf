package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.repos.ShelfRepository;

public class CreateShelf implements Callable<String> {

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Shelf capacity
	 */
	private int nbElements;

	/**
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public CreateShelf(ShelfRepository shelfRepo, int nbElements) {
		this.shelfRepository = shelfRepo;
		this.nbElements = nbElements;
	}

	/**
	 * This method creates a user, adds the user to an user repository and
	 * returns a String with a message if insertion in the repository
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

			return new StringBuilder("ShelfId: ").append(shelf.getId())
					.toString();
		}

		return "Unable to add shelf to Database";
	}
}
