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
import fhj.shelf.utils.Element;
import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.ShelfRepository;

public class CreateAnElementInAShelf implements Callable<String> {

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Shelf capacity
	 */
	private final ElementsRepository elementsRepository;

	private String title;

	private String elementType;

	private String author;

	private int tracksNumber;

	private int duration;

	private long shelfID;

	/**
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public CreateAnElementInAShelf(ShelfRepository shelfRepo,
			ElementsRepository elementsRepo, long shelfID,
			String elementType, String title, String author,
			int tracksnumber, int duration) {
		this.shelfRepository = shelfRepo;
		this.elementsRepository = elementsRepo;
		this.shelfID = shelfID;
		this.elementType = elementType;
		this.title = title;
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

		AbstractElement p = null;

		String methodName = "create" + elementType;

		Class<? extends CreateAnElementInAShelf> c = this.getClass();

		Method creatorMethod;

		try {
			creatorMethod = c.getDeclaredMethod(methodName, String.class);
			p = (AbstractElement) creatorMethod.invoke(this, title);
		} catch (Exception e) {
			throw new CommandDomainException("Error finding method to create a "
					+ elementType, e);
		}

		if(elementsRepository.add(p)){
		
		Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

			String result = "";
		
			if (shelf.add((Element) p)){
			result = new StringBuilder("ElementID: ").append(p.getId())
					.toString();
			return result;
			}
		else
			throw new CommandDomainException("Couldn't add element" + p.getId()
					+ "to shelf");
		}
		return "Unable to add element to shelf";
		
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
}

