package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import model.fhj.shelf.model.Element;
import model.fhj.shelf.model.Shelf;
import model.fhj.shelf.model.mutations.ElementCreationDescriptor;
import fhj.shelf.exceptions.CommandDomainException;
import fhj.shelf.inMemoryRepositories.ElementsRepository;
import fhj.shelf.inMemoryRepositories.ShelfRepository;



/**
 * Class whose instances represent the command that creates an element in a
 * shelf.
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class CreateAnElementInAShelf implements Callable<String> {

	/**
	 * Holds the shelf repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Holds the elements repository
	 */
	private final ElementsRepository elementsRepository;
	
	/**
	 * Holds the shelf id
	 */
	private final long shelfId;
	
	/**
	 * The element instance to insert into the repository 
	 */
	private final ElementCreationDescriptor<?> creationDescriptor; 

	/**
	 * Creates a command instance with the given repository of shelfs, the id of
	 * the shelf, and the element to create parameters The name parameters is
	 * always necessary, the others are dependent of the type of element.
	 * 
	 * @param shelfRepo
	 * @param elementsRepo
	 * @param shelfID
	 * @param elementType
	 * @param title
	 * @param author
	 * @param tracksnumber
	 * @param duration
	 */
	public CreateAnElementInAShelf(ShelfRepository shelfRepo,
			ElementsRepository elementsRepo,long shelfId, ElementCreationDescriptor<?> creationDescriptor) {
		this.shelfRepository = shelfRepo;
		this.elementsRepository = elementsRepo;
		this.shelfId = shelfId;
		this.creationDescriptor = creationDescriptor;
	}

	/**
	 * This method creates an element, adds the element to the elements
	 * repository and then to a shelf. Returns a String with the id of the
	 * element created, if insertion in the repository was successful, else
	 * returns a message of error.
	 * 
	 * @return string with the id of the element created if insertion in the
	 *         repository was successful, else returns a message of error.
	 * 
	 * @throws CommandDomainException
	 */
	@Override
	public String call() throws CommandDomainException {

		long elementId = elementsRepository.add(creationDescriptor);
		
		Shelf shelf = shelfRepository.getShelfById(shelfId);
		
		Element newElement = elementsRepository.getDatabaseElementById(elementId);
		
			if(shelf.add(newElement))
		
			return new StringBuilder("ElementID: ").append(elementId)
					.toString();
			else
				return "Unable to add element to shelf";
	}
		
	
}
