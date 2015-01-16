package fhj.shelf.commands;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.utils.repos.UserInterface;
import fhj.shelf.utils.repos.UserRepository;

/**
 * This class defines the process of getting users
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetUsers extends BaseGetCommand implements Command {

	/**
	 * Class that implements the {@link GetUsers} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * Holds the user repository to be used by the command
		 */
		private final UserRepository repository;

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param repository
		 *            is an instance of UserRepository
		 */
		public Factory(UserRepository repository) {
			this.repository = repository;

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of GetUsers
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new GetUsers(repository, parameters);
		}
	}

	/**
	 * Holds the user repository to be used by the command
	 */
	private final UserRepository userRepository;

	/**
	 * The array containing all the demanding parameters of this command
	 */
	private static final String[] DEMANDING_PARAMETERS = {};

	/**
	 * Initiates an instance with the given the repository{user} and command
	 * parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private GetUsers(UserRepository repository, Map<String, String> parameters) {
		super(parameters);
		this.userRepository = repository;
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
	 */
	@Override
	protected Map<String, String> actionExecute() throws CommandException {
		@SuppressWarnings("unused")
		Iterable<UserInterface> iterator = userRepository.getDatabaseElements();

		Map<String, UserInterface> map = userRepository.getUsers();

		Map<String, String> finalMap = putCommandResultInAMap(map);

		return finalMap;
	}

	/**
	 * This method is the process of putting a command result in a map, returning
	 * an instance of Map<String, String>
	 */
	protected Map<String, String> putCommandResultInAMap(
			Map<String, UserInterface> map) {
		Map<String, String> tmp = new TreeMap<String, String>();

		for (Entry<String, UserInterface> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().toString();
			tmp.put(key, value);
		}

		return tmp;
	}
}
