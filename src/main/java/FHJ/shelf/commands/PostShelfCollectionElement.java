package main.java.FHJ.shelf.commands;

import java.lang.reflect.Method;
import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.AbstractElement;
import main.java.FHJ.shelf.model.Book;
import main.java.FHJ.shelf.model.BookCollection;
import main.java.FHJ.shelf.model.CD;
import main.java.FHJ.shelf.model.CDCollection;
import main.java.FHJ.shelf.model.DVD;
import main.java.FHJ.shelf.model.DVDCollection;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

/**
 * Class whose instances represent the command that posts a shelf collection element. 
 * 
 *@author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class PostShelfCollectionElement extends BasePostCommand implements
		Command {

	/**
	 * Class that implements the {@link PostShelfCollectionElement} factory,
	 * according to the AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * Holds the shelf repository to be used by the command
		 */
		private final ShelfRepository shelfRepo;

		/**
		 * Holds the element repository to be used by the command
		 */
		private final ElementsRepository elementsRepo;

		/**
		 * Holds the user repository to be used by the command
		 */
		private final UserRepository userRepo;

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param userRepo is an instance of UserRepository
		 * @param shelfRepo is an instance of ShelfRepository
		 * @param elementsRepo is an instance of ElementsRepository
		 */
		public Factory(UserRepository userRepo, ShelfRepository shelfRepo,
				ElementsRepository elementsRepo) {
			this.userRepo = userRepo;
			this.shelfRepo = shelfRepo;
			this.elementsRepo = elementsRepo;
		}

		/**
		 * This is an override method of the base class, it returns
		 * a new instance of PostShelfCollectionElement
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new PostShelfCollectionElement(userRepo, shelfRepo,
					elementsRepo, parameters);
		}

	}

	/**
	 * Holds the shelf repository to be used by the command
	 */
	private final ShelfRepository shelfRepo;

	/**
	 * Holds the element repository to be used by the command
	 */
	private final ElementsRepository elementsRepo;

	/**
	 * Mandatory parameter
	 */
	public static final String ELEMENT_TYPE = "elementType";

	/**
	 * Mandatory parameter
	 */
	private static final String NAME = "name";

	/**
	 * Book mandatory parameter
	 */
	private static final String AUTHOR = "author";

	/**
	 * CD mandatory parameter
	 */
	private static final String TRACKSNUMBER = "tracksnumber";

	/**
	 * DVD mandatory parameter
	 */
	private static final String DURATION = "duration";

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
	public static final String[] MANDATORY_PARAMETERS = { SID, EID,
			ELEMENT_TYPE, NAME };

	/**
	 * Initiates an instance with the given the repository{user, shelf, element}
	 * and command parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private PostShelfCollectionElement(UserRepository userRepo,
			ShelfRepository shelfRepo, ElementsRepository elementsRepo,
			Map<String, String> parameters) {
		super(userRepo, parameters);
		this.shelfRepo = shelfRepo;
		this.elementsRepo = elementsRepo;
	}

	/**
	 * {@see Command#getMandatoryParameters()}
	 */
	@Override
	protected String[] getMandatoryParameters() {
		return MANDATORY_PARAMETERS;
	}


	/**
	 * This is an override method of the base class, it executes and validates the command
	 * post login and throws an exception when execution isn't valid
	 */
	@Override
	protected String validLoginPostExecute() throws CommandException {
		// forgive me god of java but its hammer time
		// https://www.youtube.com/watch?v=otCpCn0l4Wo

		String elementType = parameters.get(ELEMENT_TYPE);
		String name = parameters.get(NAME);

		AbstractElement p = null;

		String methodNameToCreateElement = "create" + elementType;

		Class<? extends PostShelfCollectionElement> c = this.getClass();

		Method creatorMethodToCreate;
		try {
			creatorMethodToCreate = c.getDeclaredMethod(
					methodNameToCreateElement, String.class);
			p = (AbstractElement) creatorMethodToCreate.invoke(this, name);
		} catch (Exception e) {
			throw new CommandException("Error finding method to create a "
					+ elementType, e);
		}

		elementsRepo.insert(p);

		String result = "";

		String methodNameToAddElement = "addTo" + elementType + "Collection";

		Class<? extends PostShelfCollectionElement> d = this.getClass();

		Method creatorMethodToAdd;
		try {
			creatorMethodToAdd = d.getDeclaredMethod(methodNameToAddElement,
					AbstractElement.class);

			if ((boolean) creatorMethodToAdd.invoke(this, p)) {
				result = new StringBuilder("ElementID: ").append(p.getId())
						.toString();
			}

		} catch (Exception e) {
			throw new CommandException("Error finding method to create a "
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
		int tracksNumber = getParameterAsInt(TRACKSNUMBER);
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
		int duration = getParameterAsInt(DURATION);
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
		String author = getParameterAsString(AUTHOR);
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
		long eid = Long.parseLong(parameters.get(EID));
		CDCollection col = (CDCollection) elementsRepo.getElementById(eid);

		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

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
		long eid = Long.parseLong(parameters.get(EID));
		DVDCollection col = (DVDCollection) elementsRepo.getElementById(eid);

		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

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

		long eid = Long.parseLong(parameters.get(EID));
		BookCollection col = (BookCollection) elementsRepo.getElementById(eid);

		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

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

		long eid = Long.parseLong(parameters.get(EID));
		BookCollection col = (BookCollection) elementsRepo.getElementById(eid);

		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

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

		long eid = Long.parseLong(parameters.get(EID));
		DVDCollection col = (DVDCollection) elementsRepo.getElementById(eid);

		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

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

		long eid = Long.parseLong(parameters.get(EID));
		CDCollection col = (CDCollection) elementsRepo.getElementById(eid);

		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

		shelf.remove(col);

		col.addCollection((CDCollection) element);

		return shelf.add(col);
	}

}