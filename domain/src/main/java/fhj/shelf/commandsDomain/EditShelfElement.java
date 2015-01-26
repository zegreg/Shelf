package fhj.shelf.commandsDomain;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import fhj.shelf.commandsDomain.Exceptions.CommandDomainException;
import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.Book;
import fhj.shelf.utils.BookCollection;
import fhj.shelf.utils.CD;
import fhj.shelf.utils.CDCollection;
import fhj.shelf.utils.DVD;
import fhj.shelf.utils.DVDCollection;
import fhj.shelf.utils.Element;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.ShelfRepository;


public class EditShelfElement implements Callable<String> {

	/**
	 * Holds the associated repository
	 */
	private final ShelfRepository shelfRepository;

	private final ElementsRepository elementsRepository;

	private long shelfID;

	private long elementID;

	private String name;

	private String author;

	private int tracksnumber;

	private int duration;

	/**
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated product repository
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
	 * 
	 * @return the repository with all the users
	 * @throws Exception
	 */
	@Override
	public String call() throws Exception {

		AbstractShelf shelf = shelfRepository.getShelfById(shelfID);

		Element element = (Element) elementsRepository
				.getDatabaseElementById(elementID);

		if (shelf.remove(element)) {
			String nameType = element.getClass().getName();

			for (int i = 0; i <= nameType.length(); i++) {

				int index = nameType.lastIndexOf('.');
				nameType = nameType.substring(index + 1, nameType.length());
			}

			String methodName = "return" + nameType;

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
	 * This method returns cd elements
	 * 
	 * @param ele
	 *            is an instance of Element
	 */
	@SuppressWarnings("unused")
	private void returnCD(Element ele) {

		CD cd = (CD) ele;

		if (!name.equals("name")) {
			cd.setTitle(name);
		}

		if (tracksnumber != 0) {
			cd.setTracksNumber(tracksnumber);
		}
	}

	/**
	 * This method returns book elements
	 * 
	 * @param ele
	 *            is an instance of Element
	 * @param book
	 *            is an instance of Book
	 */
	@SuppressWarnings("unused")
	private void returnBook(Element ele) {
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
	private void returnDVD(Element ele) {
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
	private void returnDVDCollection(Element ele) {
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
	private void returnCDCollection(Element ele) {
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
	private void returnBookCollection(Element ele) {
		BookCollection book = (BookCollection) ele;

		book.setTitle(name);
	}
}
