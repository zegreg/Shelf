package fhj.shelf.commandsDomain;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import fhj.shelf.commandsDomain.Exceptions.CommandDomainException;
import fhj.shelf.utils.AbstractElement;
import fhj.shelf.utils.Book;
import fhj.shelf.utils.BookCollection;
import fhj.shelf.utils.CD;
import fhj.shelf.utils.CDCollection;
import fhj.shelf.utils.DVD;
import fhj.shelf.utils.DVDCollection;
import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.ShelfRepository;

public class CreateAnElementInACollectionInAShelf implements Callable<String> {

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Shelf capacity
	 */
	private final ElementsRepository elementsRepository;

	private String name;

	private String elementType;

	private String author;

	private int tracksNumber;

	private int duration;

	private long shelfID;
	
	private long elementID;

	/**
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public CreateAnElementInACollectionInAShelf(ShelfRepository shelfRepo,
			ElementsRepository elementsRepo, long shelfID, long elementID,
			String elementType, String name, String author,
			int tracksnumber, int duration) {
		this.shelfRepository = shelfRepo;
		this.elementsRepository = elementsRepo;
		this.shelfID = shelfID;
		this.elementID = elementID;
		this.elementType = elementType;
		this.name = name;
		this.author = author;
		this.tracksNumber = tracksnumber;
		this.duration = duration;
	}

	/**
	 * This method creates a user, adds the user to an user repository and
	 * returns a String with a message if insertion in the repository was
	 * successful or not.
	 * 
	 * @return a string with information about the success of the insertion of
	 *         an user in an user repository
	 * @throws Exception
	 */
	@Override
	public String call() throws Exception {
		// forgive me god of java but its hammer time
		// https://www.youtube.com/watch?v=otCpCn0l4Wo

		AbstractElement p = null;

		String methodNameToCreateElement = "create" + elementType;

		Class<? extends CreateAnElementInACollectionInAShelf> c = this.getClass();

		Method creatorMethodToCreate;
		try {
			creatorMethodToCreate = c.getDeclaredMethod(
					methodNameToCreateElement, String.class);
			p = (AbstractElement) creatorMethodToCreate.invoke(this, name);
		} catch (Exception e) {
			throw new CommandDomainException("Error finding method to create a "
					+ elementType, e);
		}

		elementsRepository.add(p);

		String result = "";

		String methodNameToAddElement = "addTo" + elementType + "Collection";

		Class<? extends CreateAnElementInACollectionInAShelf> d = this.getClass();

		Method creatorMethodToAdd;
		try {
			creatorMethodToAdd = d.getDeclaredMethod(methodNameToAddElement,
					AbstractElement.class);

			if ((boolean) creatorMethodToAdd.invoke(this, p)) {
				result = new StringBuilder("ElementID: ").append(p.getId())
						.toString();
			}

		} catch (Exception e) {
			throw new CommandDomainException("Error finding method to create a "
					+ elementType, e);
		}
		return result;
	}

	/**
	 * Instantiate a Element CD by reflection
	 * 
	 * @param name
	 *            , element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private AbstractElement createCD(String name) {

		return new CD(name, tracksNumber);
	}

	/**
	 * Instantiate a Element DVD by reflection
	 * 
	 * @param name
	 *            , element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private AbstractElement createDVD(String name) {

		return new DVD(name, duration);
	}

	/**
	 * Instantiate a Element Book by reflection
	 * 
	 * @param name
	 *            , element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private AbstractElement createBook(String name) {
	
		return new Book(name, author);
	}

	/**
	 * Instantiate a Element CDCollection by reflection
	 * 
	 * @param name
	 *            , element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private AbstractElement createCDCollection(String name) {
		return new CDCollection(name);
	}

	/**
	 * Instantiate a Element DVDCollection by reflection
	 * 
	 * @param name
	 *            , element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private AbstractElement createDVDCollection(String name) {
		return new DVDCollection(name);
	}

	/**
	 * Instantiate a Element BookCollection by reflection
	 * 
	 * @param name
	 *            , element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private AbstractElement createBookCollection(String name) {
		return new BookCollection(name);
	}

	/**
	 * Adding a CD to a Collection by reflection
	 * 
	 * @param element
	 *            , AbstractElement
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean addToCDCollection(AbstractElement element) {
		
		CDCollection col = (CDCollection) elementsRepository.getDatabaseElementById(elementID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		col.addElement((CD) element);

		return shelf.add(col);
	}

	/**
	 * Adding a DVD to a Collection by reflection
	 * 
	 * @param element
	 *            , AbstractElement
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean addToDVDCollection(AbstractElement element) {

		DVDCollection col = (DVDCollection) elementsRepository.getDatabaseElementById(elementID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		col.addElement((DVD) element);

		return shelf.add(col);
	}

	/**
	 * Adding a Book to a Collection by reflection
	 * 
	 * @param element
	 *            , AbstractElement
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean addToBookCollection(AbstractElement element) {

		BookCollection col = (BookCollection) elementsRepository.getDatabaseElementById(elementID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		col.addElement((Book) element);

		return shelf.add(col);
	}

	/**
	 * Adding a BookCollection to a Collection by reflection
	 * 
	 * @param element
	 *            , AbstractElement
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean addToBookCollectionCollection(AbstractElement element) {

		BookCollection col = (BookCollection) elementsRepository.getDatabaseElementById(elementID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		col.addCollection((BookCollection) element);

		return shelf.add(col);
	}

	/**
	 * Adding a DVDCollection to a Collection by reflection
	 * 
	 * @param element
	 *            , AbstractElement
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean addToDVDCollectionCollection(AbstractElement element) {

		DVDCollection col = (DVDCollection) elementsRepository.getDatabaseElementById(elementID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		col.addCollection((DVDCollection) element);

		return shelf.add(col);
	}

	/**
	 * Adding a CDCollection to a Collection by reflection
	 * 
	 * @param element
	 *            , AbstractElement
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean addToCDCollectionCollection(AbstractElement element) {

		CDCollection col = (CDCollection) elementsRepository.getDatabaseElementById(elementID);

		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

		shelf.remove(col);

		col.addCollection((CDCollection) element);

		return shelf.add(col);
	}
	
}
