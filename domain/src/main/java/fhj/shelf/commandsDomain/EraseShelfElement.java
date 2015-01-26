package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;


import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.Element;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.ShelfRepository;

public class EraseShelfElement implements Callable<String>{
	
	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;
	
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
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public EraseShelfElement(ShelfRepository shelfRepo, ElementsRepository elementsRepo, long shelfID, long elementID) {
		this.shelfRepository = shelfRepo;
		this.elementsRepository = elementsRepo;
		this.shelfID = shelfID;
		this.elementID = elementID;
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
	
		AbstractShelf shelf =  shelfRepository.getShelfById(shelfID);
		Element element = (Element) elementsRepository.getDatabaseElementById(elementID);
		
		if (shelf.remove(element)){
			elementsRepository.remove(element);
			
			return "Element " + elementID + " successful remove from shelf ";
		}
		
		return "Unable to remove element" + elementID;
	}

}
