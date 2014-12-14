package commads;

import java.util.Map;
import java.lang.reflect.Method;

import exceptions.CommandException;
import User.UserRepository;
import afterSOLIDrevisionEHL.model.AbstractElement;
import afterSOLIDrevisionEHL.model.Book;
import afterSOLIDrevisionEHL.model.BookCollection;
import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.Element;
import afterSOLIDrevisionEHL.model.CDCollection;
import afterSOLIDrevisionEHL.model.DVD;
import afterSOLIDrevisionEHL.model.DVDCollection;
import afterSOLIDrevisionEHL.model.Shelf;
import Database.ElementsRepository;
import Database.ShelfRepository;


/**
 * Class whose instances represent the command that gets all products in the repository.
 */
public class PostElement extends BasePostCommand implements Command {


	public static final String ELEMENT_TYPE = "elementType";

	private static final String NAME = "name";

	private static final String AUTHOR = "author";

	private static final String TRACKSNUMBER = "tracksnumber";

	private static final String DURATION = "duration";
	

	/**
	 * Class that implements the {@link GetProducts} factory, according to the 
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

	private final ShelfRepository shelfRepo;
	private final ElementsRepository elementsRepo;


	public static  String SID = "sid";

	public static final String[] DEMANDING_PARAMETERS = {SID, ELEMENT_TYPE, NAME};

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private PostElement(UserRepository userRepo, ShelfRepository shelfRepo, ElementsRepository elementsRepo, Map<String, String> parameters)
	{
		super(userRepo, parameters);
		this.shelfRepo = shelfRepo;
		this.elementsRepo = elementsRepo;
	}

	@Override
	protected String[] getDemandingParametres() {
		return DEMANDING_PARAMETERS;
	}
	

	@Override
	protected void validLoginPostExecute() throws CommandException {
		
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
		shelf.add((Element)p);
		System.out.println(new StringBuilder("ElementID: ")
		.append(p.getId()));
	}

	@SuppressWarnings("unused")
	private AbstractElement createCD(String name)
	{
		int tracksNumber = getParameterAsInt(TRACKSNUMBER);
		return new CD(name, tracksNumber);
	}

	@SuppressWarnings("unused")
	private AbstractElement createDVD(String name){
		int duration = getParameterAsInt(DURATION);
		return new DVD(name, duration);
	}

	@SuppressWarnings("unused")
	private AbstractElement createBook(String name){
		return new Book(name, AUTHOR);
	}

	@SuppressWarnings("unused")
	private  AbstractElement createCDCollection(String name)
	{
		return new CDCollection(name);
	}

	@SuppressWarnings("unused")
	private  AbstractElement createDVDCollection(String name){
		return new DVDCollection(name);
	}

	@SuppressWarnings("unused")
	private AbstractElement createBookCollection(String name){
		return new BookCollection(name);
	}

}
