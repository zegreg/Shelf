package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;


import fhj.shelf.utils.AbstractShelf;

import fhj.shelf.utils.repos.ShelfRepository;

public class EraseShelf implements Callable <String> {

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;
	

	/**
	 * Shelf capacity
	 */
	private long shelfID;

	/**
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public EraseShelf(ShelfRepository shelfRepo, long shelfID) {
		this.shelfRepository = shelfRepo;

		this.shelfID = shelfID;
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
		
		shelf.removeAllElements();
	
		if(shelf.getFreeSpace() == shelf.getCapacity()){
			shelfRepository.remove(shelf);
			return "Shelf "+ shelfID + " removed successfuly";
		}
		

		return "Unable to add shelf to Database";
	}
}
