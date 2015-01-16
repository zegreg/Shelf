package fhj.shelf.commands;

import java.lang.reflect.Method;
import java.util.Map;

import fhj.shelf.commands.exceptions.CommandException;
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
import fhj.shelf.utils.repos.UserRepository;

/**
 * This class defines the process of editing an element
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class PatchElement extends BasePostCommand implements Command {

	/**
	 * Class that implements the {@link PatchElement} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * Holds the shelf repository to be used by the command
		 */
		private final ShelfRepository shelfRepo;

		/**
		 * Holds the elements repository to be used by the command
		 */
		private final ElementsRepository elementsRepo;

		/**
		 * Holds the user repository to be used by the command
		 */
		private final UserRepository userRepo;

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param userRepo
		 *            is an instance of UserRepository
		 * @param shelfRepo
		 *            is an instance of ShelfRepository
		 * @param elementsRepo
		 *            is an instance of ElementsRepository
		 */
		public Factory(UserRepository userRepo, ShelfRepository shelfRepo,
				ElementsRepository elementsRepo) {
			this.userRepo = userRepo;
			this.shelfRepo = shelfRepo;
			this.elementsRepo = elementsRepo;
		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of PatchElement
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new PatchElement(userRepo, shelfRepo, elementsRepo,
					parameters);
		}

	}

	/**
	 * Holds the shelf repository to be used by the command
	 */
	private final ShelfRepository shelfRepo;

	/**
	 * Holds the elements repository to be used by the command
	 */
	private final ElementsRepository elementsRepo;

	/**
	 * demanding parameter
	 */
	public static final String NAME = "name";

	/**
	 * demanding parameter
	 */
	public static final String DURATION = "duration";

	/**
	 * demanding parameter
	 */
	public static final String TRACKSNUMBER = "tracksNumber";

	/**
	 * demanding parameter
	 */
	public static final String AUTHOR = "author";

	/**
	 * The name of the parameter holding the shelf's identifier
	 */
	public static String SID = "sid";

	/**
	 * The name of the parameter holding the element's identifier
	 */
	public static String EID = "eid";

	/**
	 * The array containing all the demanding parameters of this command
	 */
	public static final String[] MANDATORY_PARAMETERS = { SID, EID };

	/**
	 * Initiates an instance with the given the repository{user, shelf, element}
	 * and command parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private PatchElement(UserRepository userRepo, ShelfRepository shelfRepo,
			ElementsRepository elementsRepo, Map<String, String> parameters) {
		super(userRepo, parameters);
		this.shelfRepo = shelfRepo;
		this.elementsRepo = elementsRepo;
	}

	/**
	 * This is an override method of the base class, that returns the demanding
	 * parameters
	 */
	@Override
	protected String[] getMandatoryParameters() {
		return MANDATORY_PARAMETERS;
	}

	/**
	 * This is an override method of the base class, it executes and validates
	 * the command post login and throws an exception when execution isn't valid
	 */
	@Override
	protected String validLoginPostExecute() throws CommandException {

		long shelfsID = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(shelfsID);

		long elementsID = Long.parseLong(parameters.get(EID));
		Element element = (Element) elementsRepo.getElementById(elementsID);

		if (shelf.remove(element)) {

			String nameType = element.getClass().getName();

			for (int i = 0; i <= nameType.length(); i++) {

				int index = nameType.lastIndexOf('.');
				nameType = nameType.substring(index + 1, nameType.length());
			}

			String methodName = "return" + nameType;

			Class<? extends PatchElement> c = this.getClass();

			Method creatorMethod;
			try {
				creatorMethod = c.getDeclaredMethod(methodName, Element.class);
				creatorMethod.invoke(this, element);
			} catch (Exception e) {
				throw new CommandException("Error finding method to create a "
						+ nameType, e);
			}

		}
		String result = "";
		if (shelf.add(element)) {
			result = "Updated Element:\n" + element.toString();
		} else
			throw new CommandException("Unable to edit Element");
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

		cd.setTitle(this.getParameterAsString(NAME));

		cd.setTracksNumber(this.getParameterAsInt(TRACKSNUMBER));

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

		book.setTitle(this.getParameterAsString(NAME));
		book.setAuthro(this.getParameterAsString(AUTHOR));

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

		dvd.setTitle(this.getParameterAsString(NAME));
		dvd.setDuration(this.getParameterAsInt(DURATION));

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

		dvd.setTitle(this.getParameterAsString(NAME));

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

		cd.setTitle(this.getParameterAsString(NAME));

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

		book.setTitle(this.getParameterAsString(NAME));
	}

}
