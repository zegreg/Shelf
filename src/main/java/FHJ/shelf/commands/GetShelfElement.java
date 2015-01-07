package main.java.FHJ.shelf.commands;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Element;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;

public class GetShelfElement extends BaseGetCommand implements Command{

	/**
	 * Class that implements the {@link GetShelfElement} factory, according to the 
	 * AbstratFactory design pattern. 
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

	
	 /**
     * Holds the shelf repository to be used by the command
     */
	private final ShelfRepository shelfRepo;
	
	/**
     * Holds the element repository to be used by the command
     */
	private final ElementsRepository elementsRepo;
	
	/**
     * The name of the parameter holding the shelf's identifier
     */
	public static final String SID = "sid";
	
	/**
     * The name of the parameter holding the element's identifier
     */
	public static final String EID = "eid";
	//private final long shelfId;
	
	/**
     * The array containing all the demanding parameters of this command
     */
	private static final String[] DEMANDING_PARAMETERS = {SID, EID};
	
	 /**
     * Initiates an instance with the given the repository{shelf, element} and command parameters
     * 
     * @param repository the repository to be used
     * @param parameters the command's unparsed parameters
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
	/**
     * {@see Command#getMandatoryParameters()}
     */
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}
	
	/**
	 * Return a parameter map result of the command execution
	 */
	@Override
	protected Map<String, String> actionExecute() throws CommandException {
		long shelfID = Long.parseLong(parameters.get(SID));
		Shelf shelf =  (Shelf) shelfRepo.getShelfById(shelfID);
		
		long elementsID = Long.parseLong(parameters.get(EID));
		Element element = (Element)elementsRepo.getElementById(elementsID);
		

		Map<String, String> containerToCommandResult = new TreeMap<String, String>();
		
		containerToCommandResult.put("Element ID:"+ elementsID , "Element ID -"+ elementsID + element.toString()+ " ");
		return containerToCommandResult;
	}
	
}


