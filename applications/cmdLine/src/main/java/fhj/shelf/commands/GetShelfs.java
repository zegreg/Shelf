package fhj.shelf.commands;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import fhj.shelf.output.StackMensage;
import fhj.shelf.repos.AbstractShelf;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.*;

/**
 * This class defines the process of getting shelfs
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetShelfs extends BaseGetCommand implements Command {

	/**
	 * Class that implements the {@link GetShelfs} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * Holds the shelf repository to be used by the command
		 */
		private final ShelfRepository repository;

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param repository
		 *            is an instance of ShelfRepository
		 */
		public Factory(ShelfRepository repository) {
			this.repository = repository;

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of GetShelfs
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new GetShelfs(repository, parameters);
		}

	}

	/**
	 * Holds the shelf repository to be used by the command
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * The array containing all the demanding parameters of this command
	 */
	private static final String[] DEMANDING_PARAMETERS = {};

	/**
	 * Initiates an instance with the given the repository{shelf} and command
	 * parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private GetShelfs(ShelfRepository repository, Map<String, String> parameters) {
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

	/**
	 * Return a parameter map result of the command execution
	 * @throws ExecutionException 
	 * @throws Exception 
	 */
	@Override
	protected Map<String, String> actionExecute() throws ExecutionException  {
		
		try{
			Map<Long, AbstractShelf> shelfContainer =  new fhj.shelf.commandsDomain.GetAllShelfs(
					shelfRepository).call();
		
			return putCommandResultInAMapPreparedForTheOutput(shelfContainer);

		} catch (Exception cause) {
			throw new ExecutionException(cause);
		}

	}

	/**
	 * This method is the process of putting a command result in a map
	 * 
	 * @param shelf
	 *            is an instance of Shelf
	 * @param it
	 *            is an instance of Iterable<AbstractShelf>
	 * @return an instance of Map<String, String>
	 */
	protected Map<String, String> putCommandResultInAMapPreparedForTheOutput(
			Map<Long, AbstractShelf> shelfsContainer) {

		Map<String, String> map = new TreeMap<String, String>();
		map.put("Container Shelves", null);
		
		for (Entry<Long, AbstractShelf> entry : shelfsContainer.entrySet()) {

			String shelfID = "Shelf_id=0"+String.valueOf(entry.getKey());

			String shelfDetails = entry.getValue().details();

			map.put(shelfID, shelfDetails);

		}

		return map;

	}

}
