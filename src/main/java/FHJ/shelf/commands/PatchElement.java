package main.java.FHJ.shelf.commands;

import java.lang.reflect.Method;
import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
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

		public Factory(UserRepository userRepo, ShelfRepository shelfRepo, ElementsRepository elementsRepo)
		{
			this.userRepo = userRepo;
			this.shelfRepo = shelfRepo;
			this.elementsRepo = elementsRepo;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PatchElement(userRepo, shelfRepo, elementsRepo, parameters);
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
	public static  String SID = "sid";
	
	
	/**
     * The name of the parameter holding the element's identifier
     */
	public static  String EID = "eid";

	
	/**
     * The array containing all the demanding parameters of this command
     */
	public static final String[] DEMANDING_PARAMETERS = {SID, EID};

	 /**
     * Initiates an instance with the given the repository{user, shelf, element} and command parameters
     * 
     * @param repository the repository to be used
     * @param parameters the command's unparsed parameters
     */
	private PatchElement(UserRepository userRepo, ShelfRepository shelfRepo, ElementsRepository elementsRepo, Map<String, String> parameters)
	{
		super(userRepo, parameters);
		this.shelfRepo = shelfRepo;
		this.elementsRepo = elementsRepo;
	}
	
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}
	
	
	/**
     * {@see Command#getMandatoryParameters()}
     */
	@Override
	protected String validLoginPostExecute() throws CommandException {
	
			
		long shelfsID = Long.parseLong(parameters.get(SID));
		Shelf shelf =  (Shelf) shelfRepo.getShelfById(shelfsID);
		
		long elementsID = Long.parseLong(parameters.get(EID));
		Element element = (Element)elementsRepo.getElementById(elementsID);

		System.out.println("before");
		
		if (shelf.remove(element)) {
			
		System.out.println("into");
		
		String nameType = element.getClass().getName();
		
		
		for (int i = 0; i <= nameType.length(); i++) {

			int index = nameType.lastIndexOf('.');
			nameType =nameType.substring(index+1, nameType.length());
		}

		System.out.println(nameType);
		
		String methodName = "return" + nameType;
		
	
		
		
		Class<? extends PatchElement> c = this.getClass();
				
		Method creatorMethod;
		try {
			creatorMethod = c.getDeclaredMethod(methodName, Element.class); 	
			creatorMethod.invoke(this, element);
		} catch (Exception e) {
			throw new CommandException("Error finding method to create a " + nameType, e);
		}
		
		
		
		}
		
		shelf.add(element);
		
		return element.toString();
	
	}
	
	
	/**
	 * 
	 * @param element
	 */
	@SuppressWarnings("unused")
	private void returnCD( Element ele)
	{
		
		CD cd = (CD) ele;
		
		cd.setTitle(this.getParameterAsString(NAME) );
		
		cd.setTracksNumber(this.getParameterAsInt(TRACKSNUMBER));
		
	}
	

	@SuppressWarnings("unused")
	private void returnBook( Element ele)
	{
		Book book = (Book) ele;
		
		book.setTitle(this.getParameterAsString(NAME));
		book.setAuthro(this.getParameterAsString(AUTHOR));
		
	}
	
	@SuppressWarnings("unused")
	private void returnDVD( Element ele)
	{
		DVD dvd = (DVD) ele;
		
		dvd.setTitle(this.getParameterAsString(NAME));
		dvd.setDuration(this.getParameterAsInt(DURATION));
	
	}
	
	
	@SuppressWarnings("unused")
	private void returnDVDCollection( Element ele)
	{
		DVDCollection dvd = (DVDCollection) ele;
		
		dvd.setTitle(this.getParameterAsString(NAME));
			
	}
	
	@SuppressWarnings("unused")
	private void returnCDCollection( Element ele)
	{
		CDCollection cd = (CDCollection) ele;
		
		cd.setTitle(this.getParameterAsString(NAME));
			
	}
	
	@SuppressWarnings("unused")
	private void returnBookCollection( Element ele)
	{
		BookCollection book = (BookCollection) ele;
		
		book.setTitle(this.getParameterAsString(NAME));
			
	}
	
}

