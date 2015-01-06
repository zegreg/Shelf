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
			return new PatchElement(userRepo, shelfRepo, elementsRepo, parameters);
		}

	}



	private final ShelfRepository shelfRepo;
	
	private final ElementsRepository elementsRepo;



	public static final String NAME = "name";

	public static final String DURATION = "duration";

	public static final String TRACKSNUMBER = "tracksNumber";

	public static final String AUTHOR = "author";
	
	public static  String SID = "sid";
	
	public static  String EID = "eid";

	public static final String[] DEMANDING_PARAMETERS = {SID, EID};

	/**
	 * 
	 * @param repository
	 * @param id
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

