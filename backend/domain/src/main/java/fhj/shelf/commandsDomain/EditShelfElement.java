package fhj.shelf.commandsDomain;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import fhj.shelf.commandsDomain.exceptions.CommandDomainException;
import fhj.shelf.repos.AbstractShelf;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.Book;
import fhj.shelf.utils.BookCollection;
import fhj.shelf.utils.CD;
import fhj.shelf.utils.CDCollection;
import fhj.shelf.utils.DVD;
import fhj.shelf.utils.DVDCollection;
import fhj.shelf.utils.Element;

/**
 * Class whose instances represent the command that edits an element that is in
 * a shelf
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class EditShelfElement implements Callable<String> {

	/**
	 * Holds the shelf repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Holds the elements repository
	 */
	private final ElementsRepository elementsRepository;

	/**
	 * Shelf identification number that have the element to be edited
	 */
	private long shelfID;

	/**
	 * Identification number of the element to be edited
	 */
	private long elementID;

	/**
	 * Element name
	 */
	private String name;

	/**
	 * Boo author
	 */
	private String author;

	/**
	 * CD tracks number
	 */
	private int tracksnumber;

	/**
	 * DVD duration
	 */
	private int duration;

	
	/**
	 * Creates a command instance with the given shelfs repository, elements
	 * repository, identification number of the shelf that the element is in it,
	 * and all parameters necessary to edit an element dependent of the element type.
	 * 
	 * @param shelfRepo
	 * @param elementsRepo
	 * @param sid
	 * @param eid
	 * @param name
	 * @param author
	 * @param tracksnumber
	 * @param duration
	 */
	public EditShelfElement(ShelfRepository shelfRepo,
			ElementsRepository elementsRepo, long sid, long eid, String name,
			String author, int tracksnumber, int duration) {
		this.shelfRepository = shelfRepo;
		this.elementsRepository = elementsRepo;
		this.shelfID = sid;
		this.elementID = eid;
		this.name = name;
		this.author = author;
		this.tracksnumber = tracksnumber;
		this.duration = duration;
	}
	
	/**
	 * This method gets the element that is in a shelf and update the given
	 * parameters of the element and returns the element to the shelf
	 * 
	 * @return a message with the information if the element edit was successful
	 *         or not
	 * @throws CommandDomainException
	 */
	@Override
	public String call() throws CommandDomainException {

		AbstractShelf shelf = shelfRepository.getShelfById(shelfID);

		Element element = (Element) elementsRepository
				.getDatabaseElementById(elementID);

		if (shelf.remove(element)) {
			String nameType = element.getClass().getName();

			for (int i = 0; i <= nameType.length(); i++) {

				int index = nameType.lastIndexOf('.');
				nameType = nameType.substring(index + 1, nameType.length());
			}

			String methodName = "edit" + nameType;

			Class<? extends EditShelfElement> c = this.getClass();

			Method creatorMethod;
			try {
				creatorMethod = c.getDeclaredMethod(methodName, Element.class);
				creatorMethod.invoke(this, element);
			} catch (Exception e) {
				throw new CommandDomainException(
						"Error finding method to create a " + nameType, e);
			}

		}
		String result = "";
		if (shelf.add(element)) {
			result = "Updated Element:\n" + element.toString();
		} else
			throw new CommandDomainException("Unable to edit Element");
		return result;

	}

	/**
	 * This method edits a CD
	 * 
	 * @param ele
	 *            is an instance of Element
	 */
	@SuppressWarnings("unused")
	private void editCD(Element ele) {

		CD cd = (CD) ele;

		if (!name.equals("name")) {
			cd.setTitle(name);
		}

		if (tracksnumber != 0) {
			cd.setTracksNumber(tracksnumber);
		}
	}

	/**
	 * This method edits a book 
	 * 
	 * @param ele
	 *            is an instance of Element
	 * @param book
	 *            is an instance of Book
	 */
	@SuppressWarnings("unused")
	private void editBook(Element ele) {
		Book book = (Book) ele;

		if (!name.equals("name")) {
			book.setTitle(name);
		}

		if (!author.equals("author")) {
			book.setAuthro(author);
		}
	}

	/**
	 * This method returns dvd elements
	 * 
	 * @param ele
	 *            is an instance of Element
	 * @param dvd
	 *            is an instance of DVD
	 */
	@SuppressWarnings("unused")
	private void editDVD(Element ele) {
		DVD dvd = (DVD) ele;

		if (!name.equals("name")) {
			dvd.setTitle(name);
		}

		if (duration != 0) {
			dvd.setDuration(duration);
		}

	}

	/**
	 * This method returns dvd collection elements
	 * 
	 * @param ele
	 *            is an instance of Element
	 * @param dvd
	 *            is an instance of DVD
	 */
	@SuppressWarnings("unused")
	private void editDVDCollection(Element ele) {
		DVDCollection dvd = (DVDCollection) ele;

		dvd.setTitle(name);

	}

	/**
	 * This method returns cd collection elements
	 * 
	 * @param ele
	 *            is an instance of Element
	 * @param cd
	 *            is an instance of CDCollection
	 */
	@SuppressWarnings("unused")
	private void editCDCollection(Element ele) {
		CDCollection cd = (CDCollection) ele;

		cd.setTitle(name);

	}

	/**
	 * This method returns book collection elements
	 * 
	 * @param ele
	 *            is an instance of Element
	 * @param book
	 *            is an instance of BookCollection
	 */
	@SuppressWarnings("unused")
	private void editBookCollection(Element ele) {
		BookCollection book = (BookCollection) ele;

		book.setTitle(name);
	}
}
