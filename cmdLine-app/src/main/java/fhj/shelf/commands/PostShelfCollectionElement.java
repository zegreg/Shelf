package fhj.shelf.commands;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.exceptions.CommandException;
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
import fhj.shelf.utils.repos.UserRepository;

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
	 * @throws ExecutionException 
	 */
	@Override
	protected String validLoginPostExecute() throws CommandException, ExecutionException {

		String elementType = parameters.get(ELEMENT_TYPE);
		long shelfID = getParameterAsLong(SID);
		long elementID = getParameterAsLong(EID);
		String name = parameters.get(NAME);
		String author = parameters.get(AUTHOR);
		
		int tracksNumber;
		if(parameters.get(TRACKSNUMBER) != null){
		tracksNumber = getParameterAsInt(TRACKSNUMBER);
		}
		else{
		tracksNumber = 0;
		}
		
		int duration;
		if(parameters.get(DURATION) != null){
			duration = getParameterAsInt(DURATION);
		}
		else{
		duration = 0;
		}
		
		
		try {
			return new fhj.shelf.commandsDomain.CreateAnElementInACollectionInAShelf(
					shelfRepo, elementsRepo, shelfID, elementID, elementType, name,
					author, tracksNumber, duration).call();

		} catch (Exception cause) {
			throw new ExecutionException(cause);
		}
	
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
		CDCollection col = (CDCollection) elementsRepo.getDatabaseElementById(eid);

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
		DVDCollection col = (DVDCollection) elementsRepo.getDatabaseElementById(eid);

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
		BookCollection col = (BookCollection) elementsRepo.getDatabaseElementById(eid);

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
		BookCollection col = (BookCollection) elementsRepo.getDatabaseElementById(eid);

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
		DVDCollection col = (DVDCollection) elementsRepo.getDatabaseElementById(eid);

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
		CDCollection col = (CDCollection) elementsRepo.getDatabaseElementById(eid);

		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

		shelf.remove(col);

		col.addCollection((CDCollection) element);

		return shelf.add(col);
	}

}