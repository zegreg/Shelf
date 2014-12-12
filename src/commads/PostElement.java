package commads;

import java.util.Map;
import java.lang.reflect.Method;

import exceptions.CommandException;
import afterSOLIDrevisionEHL.model.Book;
import afterSOLIDrevisionEHL.model.BookCollection;
import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.AbstractElement;
import afterSOLIDrevisionEHL.model.CDCollection;
import afterSOLIDrevisionEHL.model.DVD;
import afterSOLIDrevisionEHL.model.DVDCollection;
import afterSOLIDrevisionEHL.model.Shelf;
import Database.ShelfRepository;


/**
 * Class whose instances represent the command that gets all products in the repository.
 */
public class PostElement extends BaseCommand implements Command {


	public static final String ELEMENT_TYPE = "elementType";
	
	public static final String NAME = "name";

	public static final String AUTHOR = "author";

	public static final String TRACKSNUMBER = "tracksnumber";

	public static final String DURATION = "duration";

	/**
	 * Class that implements the {@link GetProducts} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

		private final ShelfRepository repository;

		public Factory(ShelfRepository repository)
		{
			this.repository = repository;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostElement(repository, parameters);
		}

	}

	private final ShelfRepository shelfRepository;

	
	public static  String SID = "sid";

	public static final String[] DEMANDING_PARAMETERS = {SID, ELEMENT_TYPE, NAME};

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private PostElement(ShelfRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.shelfRepository = repository;
	}

	@Override
	public void internalExecute() throws CommandException
	{
		String elementType = parameters.get(ELEMENT_TYPE);
		String name = parameters.get(NAME);
			
		AbstractElement p = null;
		
		String methodName = "create" + elementType;
		
		Class<? extends PostElement> c = this.getClass();

		Method creatorMethod;
		try {
			creatorMethod = c.getMethod(methodName, (Class<?>[])null);
			p = (AbstractElement) creatorMethod.invoke(this, name);
		} catch (Exception e) {
			throw new CommandException("Error finding method to create a " + elementType, e);
		}

		long elementId = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepository.getElementById(elementId);
		shelf.add(p);
	}



	private AbstractElement createCD(String name)
	{
		int tracksNumber = getParameterAsInt(TRACKSNUMBER);
		return new CD(name, tracksNumber);
	}
	

	private AbstractElement createDVD(String name){
		int duration = getParameterAsInt(DURATION);
	 return new DVD(name, duration);
	}


	private AbstractElement createBook(String name){
		return new Book(name, AUTHOR);
	}
	
	private  AbstractElement createCDCollection(String name)
	{
		return new CDCollection(name);
	}
	

	private  AbstractElement createDVDCollection(String name){
	 return new DVDCollection(name);
	}


	private AbstractElement createBookCollection(String name){
		return new BookCollection(name);
	}
	


	@Override
	protected String[] getDemandingParametres() {
		
		return DEMANDING_PARAMETERS;
	}
}
