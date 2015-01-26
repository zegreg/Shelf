package fhj.shelf.commandsDomain;


import java.util.Iterator;
import java.util.concurrent.Callable;

import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.Element;
import fhj.shelf.utils.repos.ShelfRepository;

public class GetAllShelfElements implements Callable<Iterator<Element>> {

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;


	private long shelfID;


	/**
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public GetAllShelfElements(ShelfRepository shelfRepo, Long shelfID) {
		this.shelfRepository = shelfRepo;
		this.shelfID = shelfID;
	}

	/**
	 * 
	 * @return the repository with all the users
	 * @throws Exception
	 */
	@Override
	public Iterator<Element> call() throws Exception {

		AbstractShelf shelf = shelfRepository.getShelfById(shelfID);
		
		return shelf.getAllElements();
			
	}
}
