package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import model.fhj.shelf.model.Element;
import model.fhj.shelf.model.Shelf;
import fhj.shelf.inMemoryRepositories.ElementsRepository;
import fhj.shelf.inMemoryRepositories.ShelfRepository;



/**
 * Class whose instances represent the command that eliminates an element that
 * is in a shelf from an elements repository
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class EraseShelfElement implements Callable<String> {

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Holds the elements repository
	 */
	private ElementsRepository elementsRepository;

	/**
	 * Shelf identification number
	 */
	private long shelfID;

	/**
	 * Element identification number
	 */
	private long elementID;

	/**
	 * Creates a command instance with the given shelf repository, element
	 * repository, shelf identification number and element identification number
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public EraseShelfElement(ShelfRepository shelfRepo,
			ElementsRepository elementsRepo, long shelfID, long elementID) {
		this.shelfRepository = shelfRepo;
		this.elementsRepository = elementsRepo;
		this.shelfID = shelfID;
		this.elementID = elementID;
	}

	/**
	 * This method gets the element with the identification number given, remove
	 * the element from the shelf, , and removes the element from the elements
	 * repository
	 * 
	 * @return a string with information about the success of the elimination of
	 *         the element in elements repository
	 */
	@Override
	public String call() throws Exception {

		Shelf shelf = shelfRepository.getShelfById(shelfID);
		Element element = (Element) elementsRepository
				.getDatabaseElementById(elementID);

		if (shelf.remove(element)) {
			elementsRepository.remove(element);

			return "Element " + elementID + " successful remove from shelf ";
		}

		return "Unable to remove element" + elementID;
	}

}
