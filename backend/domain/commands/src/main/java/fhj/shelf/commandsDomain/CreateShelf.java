package fhj.shelf.commandsDomain;


import java.util.concurrent.Callable;

import model.fhj.shelf.model.mutations.ShelfCreationDescriptor;
import fhj.shelf.inMemoryRepositories.ShelfRepository;


/**
 * Class whose instances represent the command that creates a shelf
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class CreateShelf implements Callable<String> {

	
	/**
	 * Holds the shelf repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * The shelf instance to insert into the repository 
	 */
	private final ShelfCreationDescriptor creationDescriptor; 

	/**
	 * Creates a command instance with the given repository and the capacity of
	 * the shelf
	 * 
	 * @param shelfRepo
	 * @param nbElements
	 */
	public CreateShelf(ShelfRepository shelfRepo, ShelfCreationDescriptor creationDescriptor) {
		this.shelfRepository = shelfRepo;
		this.creationDescriptor = creationDescriptor;
		
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

		return new StringBuilder("ShelfId: ").append(shelfRepository.add(creationDescriptor)).toString();
		
	}
}
