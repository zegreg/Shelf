package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;




import fhj.shelf.utils.Element;

import fhj.shelf.utils.repos.AbstractShelf;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.ShelfRepository;

/**
 * Class whose instances represent the command that gets an element that is in a
 * shelf
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetAnElementThatIsInAShelf implements Callable<Element> {

	/**
	 * Holds the user's repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Holds the elements repository
	 */
	private final ElementsRepository elementsRepository;

	/**
	 * Shelf identification number
	 */
	private long shelfID;

	/**
	 * element identification number
	 */
	private long elementID;

	/**
	 * Creates a command instance with the given shelfs repository, elements
	 * repository, shelf's identification number and element's identification
	 * number wanted
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public GetAnElementThatIsInAShelf(ShelfRepository shelfRepo,
			ElementsRepository elementsRepo, Long shelfID, Long elementID) {
		this.shelfRepository = shelfRepo;
		this.elementsRepository = elementsRepo;
		this.shelfID = shelfID;
		this.elementID = elementID;
	}

	/**
	 * 
	 * @return the element with the identification number given that was in the
	 *         shelf with the identification number given
	 * @throws Exception
	 */
	@Override
	public Element call() throws Exception {

		AbstractShelf shelf = shelfRepository.getShelfById(shelfID);

		Element element = (Element) elementsRepository
				.getDatabaseElementById(elementID);

		if (shelf.contains((Element)element)) {
			return element;
		}

		return null;
	}

}
