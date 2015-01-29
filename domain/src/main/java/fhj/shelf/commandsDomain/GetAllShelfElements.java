package fhj.shelf.commandsDomain;

import java.util.Iterator;
import java.util.concurrent.Callable;

import fhj.shelf.utils.Element;
import fhj.shelf.utils.repos.AbstractShelf;
import fhj.shelf.utils.repos.ShelfRepository;

/**
 * Class whose instances represent the command that gets all elements that are
 * in a shelf
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetAllShelfElements implements Callable<Iterator<Element>> {

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Shelf identification number
	 */
	private long shelfID;

	/**
	 * Creates a command instance with the given shelf repository and shelf
	 * identification number
	 * 
	 * @param shelfRepo
	 * @param shelfID
	 */
	public GetAllShelfElements(ShelfRepository shelfRepo, Long shelfID) {
		this.shelfRepository = shelfRepo;
		this.shelfID = shelfID;
	}

	/**
	 * This method gets a shelf of the shelf repository by the identification
	 * number and returns an iterator with all elements that are in the shelf
	 * 
	 * @return the repository with all the users
	 */
	@Override
	public Iterator<Element> call() throws Exception  {

		AbstractShelf shelf = shelfRepository.getShelfById(shelfID);

		return shelf.getAllElements();

	}
}
