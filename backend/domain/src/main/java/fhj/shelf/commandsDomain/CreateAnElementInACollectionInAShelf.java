package fhj.shelf.commandsDomain;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.Callable;

import fhj.shelf.commandsDomain.exceptions.CommandDomainException;
import fhj.shelf.utils.Book;
import fhj.shelf.utils.BookCollection;
import fhj.shelf.utils.CD;
import fhj.shelf.utils.CDCollection;
import fhj.shelf.utils.DVD;
import fhj.shelf.utils.DVDCollection;
import fhj.shelf.utils.Element;
import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.mutation.ElementCreationDescriptor;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.ShelfRepository;

/**
 * Class whose instances represent the command that creates an element in a
 * collection that is in a shelf.
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class CreateAnElementInACollectionInAShelf implements Callable<String> {

	/**
	 * Holds the shelfs repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Holds the elements repository
	 */
	private final ElementsRepository elementsRepository;

	/**
	 * Element type
	 */
	private String elementType;

	/**
	 * Shelf identity number
	 */
	private long shelfID;

	/**
	 * Element identity number
	 */
	private long collectionID;

	/**
	 * The element instance to insert into the repository
	 */
	private final ElementCreationDescriptor<?> creationDescriptor;

	/**
	 * Creates a command instance with the given repository's of shelfs and
	 * elements, the id of the shelf and collection, and the element to create
	 * parameters The name parameters is always necessary, the others are
	 * dependent of the type of element.
	 * 
	 * @param shelfRepo
	 * @param elementsRepo
	 * @param shelfID
	 * @param collectionID
	 * @param elementType
	 * @param name
	 * @param author
	 * @param tracksnumber
	 * @param duration
	 */
	public CreateAnElementInACollectionInAShelf(ShelfRepository shelfRepo,
			ElementsRepository elementsRepo, long shelfID, long collectionID,
			ElementCreationDescriptor<?> creationDescriptor, String elementType) {
		this.shelfRepository = shelfRepo;
		this.elementsRepository = elementsRepo;
		this.shelfID = shelfID;
		this.collectionID = collectionID;
		this.elementType = elementType;
		this.creationDescriptor = creationDescriptor;
	}

	/*
	 * This method creates an element, adds the element to the elements
	 * repository and then to a collection that is in a shelf. Returns a String
	 * with the id of the element created if insertion in the collection was
	 * successful else returns a error message.
	 * 
	 * @return a String with the id of the element created if insertion in the
	 * collection was successful else returns a error message.
	 * 
	 * @throws CommandDomainException
	 */
	@Override
	public String call() throws CommandDomainException {

		long elementId = elementsRepository.add(creationDescriptor);

		Element newElement = elementsRepository
				.getDatabaseElementById(elementId);

		String result = "";

		String methodNameToAddElement = "addTo" + elementType + "Collection";

		Class<? extends CreateAnElementInACollectionInAShelf> d = this
				.getClass();

		Method creatorMethodToAdd;
		try {
			creatorMethodToAdd = d.getDeclaredMethod(methodNameToAddElement,
					Element.class);

			if ((boolean) creatorMethodToAdd.invoke(this, newElement)) {
				result = new StringBuilder("ElementID: ").append(elementId)
						.toString();
			}

		} catch (Exception e) {
			throw new CommandDomainException(
					"Error finding method to create a " + elementType, e);
		}
		return result;
	}

	/**
	 * Adds a CD to a CDCollection
	 * 
	 * @param element
	 *            to be added to collection
	 * @return true if the element was successfully added to the collection else
	 *         returns false
	 */
	@SuppressWarnings("unused")
	private boolean addToCDCollection(Element element) {

		CDCollection col = (CDCollection) elementsRepository
				.getDatabaseElementById(collectionID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		if (col.addElement((CD) element)) {
			return shelf.add(col);
		}

		return false;
	}

	/**
	 * Adds a DVD to a DVDCollection
	 * 
	 * @param element
	 *            to be added to collection
	 * @return true if the element was successfully added to the collection else
	 *         returns false
	 */
	@SuppressWarnings("unused")
	private boolean addToDVDCollection(Element element) {

		DVDCollection col = (DVDCollection) elementsRepository
				.getDatabaseElementById(collectionID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		if (col.addElement((DVD) element)) {
			return shelf.add(col);
		}

		return false;
	}

	/**
	 * Adds a Book to a BookCollection
	 * 
	 * @param element
	 *            to be added to collection
	 * @return true if the element was successfully added to the collection else
	 *         returns false
	 */
	@SuppressWarnings("unused")
	private boolean addToBookCollection(Element element) {

		BookCollection col = (BookCollection) elementsRepository
				.getDatabaseElementById(collectionID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		col.addElement((Book) element);

		return shelf.add(col);
	}

	/**
	 * Adds a BookCollection to a BookCollection
	 * 
	 * @param element
	 *            to be added to collection
	 * @return true if the element was successfully added to the collection else
	 *         returns false
	 */
	@SuppressWarnings("unused")
	private boolean addToBookCollectionCollection(Element element) {

		BookCollection col = (BookCollection) elementsRepository
				.getDatabaseElementById(collectionID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		if (col.addCollection((BookCollection) element)) {

			return shelf.add(col);
		}

		return false;
	}

	/**
	 * Adds a DVDCollection to a DVDCollection
	 * 
	 * @param element
	 *            to be added to collection
	 * @return true if the element was successfully added to the collection else
	 *         returns false
	 */
	@SuppressWarnings("unused")
	private boolean addToDVDCollectionCollection(Element element) {

		DVDCollection col = (DVDCollection) elementsRepository
				.getDatabaseElementById(collectionID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		if (col.addCollection((DVDCollection) element)) {
			return shelf.add(col);
		}

		return false;
	}

	/**
	 * Adds a CDCollection to a CDCollection
	 * 
	 * @param element
	 *            to be added to collection
	 * @return true if the element was successfully added to the collection else
	 *         returns false
	 */
	@SuppressWarnings("unused")
	private boolean addToCDCollectionCollection(Element element) {

		CDCollection col = (CDCollection) elementsRepository
				.getDatabaseElementById(collectionID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		if (col.addCollection((CDCollection) element)) {
			return shelf.add(col);
		}

		return false;
	}

}