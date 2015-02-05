package fhj.shelf.commands;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.output.StackMensage;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.Shelf;


/**
 * Class that implements the command to obtain the {@link Shelf} instance with a given identifier.
 */
public class GetShelf extends BaseGetCommand implements Command {

	 /**
     * Class that implements the {@link GetShelf} factory, according to the Abstract Factory
     * design pattern (GoF).
     */
	public static class Factory implements CommandFactory {

		private final ShelfRepository repository;

		public Factory(ShelfRepository repository) {
			this.repository = repository;

		}

		@Override
		public Command newInstance(Map<String, String> parameters) {
		
			return new GetShelf(repository, parameters);
		}

	}

	
	 /**
     * Holds the shelf repository to be used by the command
     */
	private final ShelfRepository shelfRepository;

	
	 /**
     * The name of the parameter holding the shelf's identifier
     */
	public static final String SID = "sid";
	// private final long shelfId;

	/**
     * The array containing all the demanding parameters of this command
     */
	 	private static final String[] DEMANDING_PARAMETERS = { SID };

	 	 /**
	     * Initiates an instance with the given shelf repository and command parameters
	     * 
	     * @param repository the repository to be used
	     * @param parameters the command's unparsed parameters
	     */
	private GetShelf(ShelfRepository repository, Map<String, String> parameters) {
		super(parameters);
		this.shelfRepository = repository;
	}
	
	/**
     * {@see Command#getMandatoryParameters()}
     */
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected Map<String, String> actionExecute() throws CommandException, ExecutionException {
		long shelfId = Long.parseLong(parameters.get(SID));

		try{
			Shelf shelf =  new fhj.shelf.commandsDomain.GetOneShelf(
					shelfRepository, shelfId).call();
		
			return putCommandResultInAMapPreparedForTheOutput(shelf);

		} catch (Exception cause) {
			throw new ExecutionException(cause);
		}

	}

	protected Map<String, String> putCommandResultInAMapPreparedForTheOutput(Shelf shelf) {
		
		Map<String, String> containerOfCommandResult = new TreeMap<String, String>();

		String shelf_id = "Shelf" + String.valueOf(shelf.getId());
		
		int i = 0;
		containerOfCommandResult.put(shelf_id,null);
		for (String element : ((Shelf)shelf).getInfoAboutAllElementsContained()) {
			containerOfCommandResult.put("ShelfElement="+i,element);
			i++;
		}
		

		
		return containerOfCommandResult;
	}

}
