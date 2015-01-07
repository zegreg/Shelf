package main.java.FHJ.shelf.commands;

import java.util.Map;
import java.lang.reflect.Method;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.AbstractElement;
import main.java.FHJ.shelf.model.Book;
import main.java.FHJ.shelf.model.BookCollection;
import main.java.FHJ.shelf.model.CD;
import main.java.FHJ.shelf.model.CDCollection;
import main.java.FHJ.shelf.model.DVD;
import main.java.FHJ.shelf.model.DVDCollection;
import main.java.FHJ.shelf.model.Element;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;


/**
 * Class whose instances represent the command that gets all products in the repository.
 */
public class PostElement extends BasePostCommand implements Command {

	/**
	 * demanding parameter
	 */
	public static final String ELEMENT_TYPE = "elementType";
	
	/**
	 * demanding parameter
	 */
	private static final String NAME = "name";

	/**
	 * demanding parameter
	 */
	private static final String AUTHOR = "author";

	/**
	 * demanding parameter
	 */
	private static final String TRACKSNUMBER = "tracksnumber";

	/**
	 * demanding parameter
	 */
	private static final String DURATION = "duration";
	

	/**
	 * Class that implements the {@link PostElement} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

	
		private final ShelfRepository shelfRepo;
		private final ElementsRepository elementsRepo;
		private final UserRepository userRepo;

		public Factory(UserRepository userRepo, ShelfRepository shelfRepo, ElementsRepository elementsRepo)
		{
			this.userRepo = userRepo;
			this.shelfRepo = shelfRepo;
			this.elementsRepo = elementsRepo;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostElement(userRepo, shelfRepo, elementsRepo, parameters);
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
     * The name of the parameter holding the shelf's identifier
     */
	public static  String SID = "sid";

	
	/**
     * The array containing all the demanding parameters of this command
     */
	public static final String[] DEMANDING_PARAMETERS = {SID, ELEMENT_TYPE, NAME};

	
	 /**
     * Initiates an instance with the given the repository{user, shelf, element} and command parameters
     * 
     * @param repository the repository to be used
     * @param parameters the command's unparsed parameters
     */
	private PostElement(UserRepository userRepo, ShelfRepository shelfRepo, ElementsRepository elementsRepo, Map<String, String> parameters)
	{
		super(userRepo, parameters);
		this.shelfRepo = shelfRepo;
		this.elementsRepo = elementsRepo;
	}

	
	/**
     * {@see Command#getMandatoryParameters()}
     */
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}
	

	@Override
	protected String validLoginPostExecute() throws CommandException {
		
		String elementType = parameters.get(ELEMENT_TYPE);
		String name = parameters.get(NAME);
		
		AbstractElement p = null;
		
		String methodName = "create" + elementType;
		
		Class<? extends PostElement> c = this.getClass();
		
		Method creatorMethod;
		try {
			creatorMethod = c.getDeclaredMethod(methodName, String.class); 	
			p = (AbstractElement) creatorMethod.invoke(this, name);
		} catch (Exception e) {
			throw new CommandException("Error finding method to create a " + elementType, e);
		}
		
		elementsRepo.insert(p);
		
		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

		String result = "";
		/*
		if(shelf.add((Element)p))
		{
		result = new StringBuilder("ElementID: ")
		.append(p.getId()).toString();
		}
		*/
		shelf.add((Element)p);
		result = new StringBuilder("ElementID: ")
		.append(p.getId()).toString();
		
		return result;
	}

	
	/**
	 * Instantiate a Element CD by reflection
	 * @param name, element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private AbstractElement createCD(String name)
	{
		int tracksNumber = getParameterAsInt(TRACKSNUMBER);
		return new CD(name, tracksNumber);
	}

	/**
	 * Instantiate a Element DVD by reflection
	 * @param name, element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private AbstractElement createDVD(String name){
		int duration = getParameterAsInt(DURATION);
		return new DVD(name, duration);
	}

	/**
	 * Instantiate a Element Book by reflection
	 * @param name, element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private AbstractElement createBook(String name){
		return new Book(name, AUTHOR);
	}

	/**
	 * Instantiate a Element CDCollection by reflection
	 * @param name, element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private  AbstractElement createCDCollection(String name)
	{
		return new CDCollection(name);
	}

	/**
	 * Instantiate a Element DVDCollection by reflection
	 * @param name, element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private  AbstractElement createDVDCollection(String name){
		return new DVDCollection(name);
	}

	/**
	 * Instantiate a Element BookCollection by reflection
	 * @param name, element characteristic
	 * @return
	 */
	@SuppressWarnings("unused")
	private AbstractElement createBookCollection(String name){
		return new BookCollection(name);
	}

}
