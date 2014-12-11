package commads;

import java.lang.reflect.*;
import java.util.Map;



import exceptions.CommandException;
import afterSOLIDrevisionEHL.model.Book;
import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.AbstractElement;
import afterSOLIDrevisionEHL.model.AbstractShelf;
import afterSOLIDrevisionEHL.model.DVD;
import afterSOLIDrevisionEHL.model.Shelf;
import afterSOLIDrevisionEHL.model.SimpleElement;
import Database.Repository;
import Database.ShelfRepository;


/**
 * Class whose instances represent the command that gets all products in the repository.
 */
public class PostElement extends BaseCommand implements Command {

	public static final String NBELEMENTS = "nbElements";



	public static final String TITLE = "title";


	public static final String DETAILS = "details";


	public static final String TYPE = "type";

	/**
	 * Class that implements the {@link GetProducts} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

		private final ShelfRepository repository;

		public Factory(ShelfRepository productRepo)
		{
			this.repository = productRepo;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostElement(repository, parameters);
		}

	}

	private final ShelfRepository shelfRepository;

	
	
	public static  String SID = "sid";
	public static  String EID  = "eid";

	public static final String[] DEMANDING_PARAMETERS = {TITLE, DETAILS};

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
	
	
	{	String title = parameters.get(TITLE);
		String elementType = parameters.get(TYPE);
		String details = parameters.get(DETAILS);
		
		
		AbstractElement p = null;
		
		String methodName = "create" + elementType;
		
		Class<? extends PostElement> c = this.getClass();
		Method creatorMethod;
		try {
			creatorMethod = c.getMethod(methodName, (Class<?>[])null);
			p = (AbstractElement) creatorMethod.invoke(this, title, details);
		} catch (Exception e) {
			throw new CommandException("Error finding method to create a " + elementType, e);
		}
		
		
		long elementId = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepository.GetElementById(elementId);
		shelf.add(p);
	}


	private  AbstractElement createCD(String title, String details){
		int trackNumbers = getParameterAsInt(details);
		return new CD(title, trackNumbers);
	}
	
	
	private  AbstractElement createDVD(String title, String details){
		int duration = getParameterAsInt(details);
	 return new DVD(title, duration);
	}

	
	private AbstractElement createBook(String title, String details){
	
	 return new Book(title, details);
	}
	
	
	
	@Override
	protected String[] getDemandingParametres() {
		
		return DEMANDING_PARAMETERS;
	}
}
