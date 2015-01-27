package fhj.shelf.commandsDomain;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import fhj.shelf.commandsDomain.exceptions.CommandDomainException;
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
	 * Element title
	 */
	private String title;

	/**
	 * Element type
	 */
	private String elementType;

	/**
	 * Book author
	 */
	private String author;

	/**
	 * CD tracksNumber
	 */
	private int tracksNumber;

	/**
	 * DVD duration
	 */
	private int duration;

	/**
	 * Shelf identification number
	 */
	private long shelfID;

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
			ElementsRepository elementsRepo, long shelfID, String elementType,
			String title, String author, int tracksnumber, int duration) {
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

		AbstractElement p = null;

		String methodName = "create" + elementType;

		Class<? extends CreateAnElementInAShelf> c = this.getClass();

		Method creatorMethod;

		try {
			creatorMethod = c.getDeclaredMethod(methodName, String.class);
			p = (AbstractElement) creatorMethod.invoke(this, title);
		} catch (Exception e) {
			throw new CommandDomainException(
					"Error finding method to create a " + elementType, e);
		}

		if (elementsRepository.add(p)) {

			Shelf shelf = (Shelf) shelfRepository.getShelfById(shelfID);

			String result = "";

			if (shelf.add((Element) p)) {
				result = new StringBuilder("ElementID: ").append(p.getId())
						.toString();
				return result;
			} else
				throw new CommandDomainException("Couldn't add element"
						+ p.getId() + "to shelf");
		}
		return "Unable to add element to shelf";

	}

	/**
	 * Creates a CD
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings("unused")
	private AbstractElement createCD(String name) {

		return new CD(name, tracksNumber);
	}

	/**
	 * Creates a DVD
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings("unused")
	private AbstractElement createDVD(String name) {

		return new DVD(name, duration);
	}

	/**
	 * Creates a Book
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings("unused")
	private AbstractElement createBook(String name) {

		return new Book(name, author);
	}

	/**
	 * Creates a CDCollection
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings("unused")
	private AbstractElement createCDCollection(String name) {
		return new CDCollection(name);
	}

	/**
	 * Creates a DVDCollection
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings("unused")
	private AbstractElement createDVDCollection(String name) {
		return new DVDCollection(name);
	}

	/**
	 * Creates a BookCollection
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings("unused")
	private AbstractElement createBookCollection(String name) {
		return new BookCollection(name);
	}
}
