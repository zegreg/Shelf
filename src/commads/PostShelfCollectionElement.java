package commads;

import java.lang.reflect.Method;
import java.util.Map;

import Database.ElementsRepository;
import Database.ShelfRepository;
import afterSOLIDrevisionEHL.model.AbstractElement;
import afterSOLIDrevisionEHL.model.Book;
import afterSOLIDrevisionEHL.model.BookCollection;
import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.CDCollection;
import afterSOLIDrevisionEHL.model.DVD;
import afterSOLIDrevisionEHL.model.DVDCollection;
import afterSOLIDrevisionEHL.model.Shelf;
import exceptions.CommandException;

public class PostShelfCollectionElement extends BaseCommand implements Command{


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

		public Factory(ShelfRepository shelfRepo, ElementsRepository elementsRepo)
		{
			this.shelfRepo = shelfRepo;
			this.elementsRepo = elementsRepo;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostShelfCollectionElement(shelfRepo, elementsRepo, parameters);
		}

	}

	private final ShelfRepository shelfRepo;
	private final ElementsRepository elementsRepo;


	public static  String SID = "sid";
	public static  String EID = "eid";

	public static final String[] DEMANDING_PARAMETERS = {SID, EID, ELEMENT_TYPE, NAME};

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private PostShelfCollectionElement(ShelfRepository shelfRepo, ElementsRepository elementsRepo, Map<String, String> parameters)
	{
		super(parameters);
		this.shelfRepo = shelfRepo;
		this.elementsRepo = elementsRepo;
	}
	
	//forgive me god of java but its hammer time https://www.youtube.com/watch?v=otCpCn0l4Wo
	@Override
	public void internalExecute() throws CommandException
	{
		
		String elementType = parameters.get(ELEMENT_TYPE);
		String name = parameters.get(NAME);

		AbstractElement p = null;

		String methodNameToCreateElement = "create" + elementType;

		Class<? extends PostShelfCollectionElement> c = this.getClass();

		Method creatorMethodToCreate;
		try {
			creatorMethodToCreate = c.getMethod(methodNameToCreateElement, String.class);
			p = (AbstractElement) creatorMethodToCreate.invoke(this, name);
		} catch (Exception e) {
			throw new CommandException("Error finding method to create a " + elementType, e);
		}
		
		elementsRepo.insert(p);
		
		String methodNameToAddElement = "addTo" + elementType + "Collection";

		Class<? extends PostShelfCollectionElement> d = this.getClass();

		Method creatorMethodToAdd;
		try {
			creatorMethodToAdd = d.getMethod(methodNameToAddElement, AbstractElement.class);
			creatorMethodToAdd.invoke(this, p);
		} catch (Exception e) {
			throw new CommandException("Error finding method to create a " + elementType, e);
		}
		
		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);
	

		
		System.out.println(new StringBuilder("ElementID: ")
		                      .append(p.getId()));
		
	}

	public AbstractElement createCD(String name)
	{
		int tracksNumber = getParameterAsInt(TRACKSNUMBER);
		return new CD(name, tracksNumber);
	}


	public AbstractElement createDVD(String name){
		int duration = getParameterAsInt(DURATION);
		return new DVD(name, duration);
	}


	public AbstractElement createBook(String name){
		return new Book(name, AUTHOR);
	}

	public  AbstractElement createCDCollection(String name)
	{
		return new CDCollection(name);
	}


	public  AbstractElement createDVDCollection(String name){
		return new DVDCollection(name);
	}


	public AbstractElement createBookCollection(String name){
		return new BookCollection(name);
	}

	public void addToCDCollection(AbstractElement element)
	{
		long eid = Long.parseLong(parameters.get(EID));
		CDCollection col = (CDCollection) elementsRepo.getElementById(eid);
		col.addElement((CD)element);
	}


	public  void addToDVDCollection(AbstractElement element){
		long eid = Long.parseLong(parameters.get(EID));
		DVDCollection col = (DVDCollection) elementsRepo.getElementById(eid);
		col.addElement((DVD)element);
	}


	public void addToBookCollection(AbstractElement element){
		long eid = Long.parseLong(parameters.get(EID));
		BookCollection col = (BookCollection) elementsRepo.getElementById(eid);
		col.addElement((Book)element);
	}

	@Override
	protected String[] getDemandingParametres() {
		return DEMANDING_PARAMETERS;
	}
}

