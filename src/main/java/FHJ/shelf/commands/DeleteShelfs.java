package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.AbstractShelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

/**
 * This class defines how the process of deleting shelfs is done.
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class DeleteShelfs extends BasePostCommand implements Command {

	/**
	 * Class that implements the {@link DeleteShelfs} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * Holds the shelf repository to be used by the command
		 */
		private final ShelfRepository repository;

		/**
		 * Holds the user repository to be used by the command
		 */
		private final UserRepository userRepo;

		/**
		 * This is the constructor for the class above, it defines the
		 * construction of the factory
		 * 
		 * @param repository
		 *            is an instance of ShelfRepository
		 */
		public Factory(UserRepository userRepo, ShelfRepository repository) {
			this.userRepo = userRepo;
			this.repository = repository;

		}

		/**
		 * This is an override method of the base class that returns a new
		 * instance of DeleteShelfs
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new DeleteShelfs(userRepo, repository, parameters);
		}

	}

	/**
	 * The name of the parameter holding the shelf's identifier
	 */
	public static final String SID = "sid";

	/**
	 * Holds the shelf repository to be used by the command
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * The array containing all the demanding parameters of this command
	 */
	private static final String[] DEMANDING_PARAMETERS = { SID };

	/**
	 * Initiates an instance with the given the repository{user, shelf} and
	 * command parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private DeleteShelfs(UserRepository userRepo, ShelfRepository repository,
			Map<String, String> parameters) {
		super(userRepo, parameters);
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
	 * Return a message for login success
	 */
	@Override
	protected String validLoginPostExecute() throws CommandException {
		long shelfId = Long.parseLong(parameters.get(SID));

		AbstractShelf shelf = shelfRepository.getShelfById(shelfId);
		shelfRepository.remove(shelf);

		String result = "Remove shelf succesfully to shelf repository";

		return result;
	}

}
