package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import fhj.shelf.utils.AbstractElement;
import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.ShelfRepository;

public class GetAnElementThatIsInAShelf implements Callable<AbstractElement> {

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;

	private final ElementsRepository elementsRepository;

	private long shelfID;

	private long elementID;

	/**
	 * Creates a command instance with the given repository
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
	 * @return the repository with all the users
	 * @throws Exception
	 */
	@Override
	public AbstractElement call() throws Exception {

		AbstractShelf shelf = shelfRepository.getShelfById(shelfID);

		AbstractElement element = elementsRepository.getElementById(elementID);

		if (shelf.contains(element))
			return element;

		return null;
	}

}
