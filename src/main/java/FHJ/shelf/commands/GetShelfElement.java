package main.java.FHJ.shelf.commands;

import java.util.Arrays;
import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Element;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;

public class GetShelfElement extends BaseGetCommand implements Command{

	/**
	 * Class that implements the {@link PostElement} factory, according to the 
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
			return new GetShelfElement(shelfRepo, elementsRepo, parameters);
		}
		
	}

	private final ShelfRepository shelfRepo;
	private final ElementsRepository elementsRepo;
	
	public static final String SID = "sid";
	
	public static final String EID = "eid";
	//private final long shelfId;
	
	private static final String[] DEMANDING_PARAMETERS = {SID, EID};
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetShelfElement(ShelfRepository shelfRepo, ElementsRepository elementsRepo, Map<String, String> parameters)
	{
		super(parameters);
		this.shelfRepo = shelfRepo;
		this.elementsRepo = elementsRepo;
	}
	/*
	@Override
	protected void internalExecute() throws CommandException 
	{	
		long shelfID = Long.parseLong(parameters.get(SID));
		Shelf shelf =  (Shelf) shelfRepo.getShelfById(shelfID);
		
		long elementsID = Long.parseLong(parameters.get(EID));
		Element element = (Element)elementsRepo.getElementById(elementsID);
		
		Element requestedElement = shelf.requestElement(element);
		
		System.out.println(requestedElement.toString());
	}
*/
	
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}
	@Override
	protected Map<String, String> actionExecute() throws CommandException {
		long shelfID = Long.parseLong(parameters.get(SID));
		Shelf shelf =  (Shelf) shelfRepo.getShelfById(shelfID);
		
		long elementsID = Long.parseLong(parameters.get(EID));
		Element element = (Element)elementsRepo.getElementById(elementsID);
		
		String result = Arrays.toString(shelf.findElementsWithTheSameTypeAndTitleAs(element));
		return null;
	}
	
}


