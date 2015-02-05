package fhj.shelf.commands;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.output.StackMensage;
import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.UserRepository;

/**
 * This class defines the process of getting a user
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetUser extends BaseGetCommand implements Command {

	/**
	 * Class that implements the {@link GetUser} factory, according to the
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
		 * instance of GetUser
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new GetUser(repository, parameters);
		}

	}

	/**
	 * Holds the user repository to be used by the command
	 */
	private final UserRepository userRepository;

	/**
	 * Mandatory parameter
	 */
	public static final String USERNAME = "username";

	/**
	 * The array containing all the demanding parameters of this command
	 */
	private static final String[] MANDATORY_PARAMETERS = { USERNAME };

	/**
	 * Initiates an instance with the given the repository{shelf} and command
	 * parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private GetUser(UserRepository repository, Map<String, String> parameters) {
		super(parameters);
		this.userRepository = repository;
	}

	/**
	 * Return a parameter map result of the command execution
	 * 
	 * @throws ExecutionException
	 */
	@Override
	protected Map<String, String> actionExecute() throws CommandException,
			ExecutionException {
		String username = parameters.get(USERNAME);

		try {
			AbstractUser user = new fhj.shelf.commandsDomain.GetOneUser(
					userRepository, username).call();
			
			return putCommandResultInAMapPreparedForTheOutput(user);

		} catch (Exception cause) {
			throw new ExecutionException(cause);
		}

	}

	protected Map<String, String> putCommandResultInAMapPreparedForTheOutput(
			AbstractUser user) {

		Map<String, String> map = new TreeMap<String, String>();
		
		map.put("User", null);
		map.put("username", user.getLoginName());
		map.put("password", user.getLoginPassword());
		map.put("fullname", user.getFullName());
		map.put("email", user.getEmail());
		

		return map;
	}

	/**
	 * {@see Command#getMandatoryParameters()}
	 */
	@Override
	protected String[] getMandatoryParameters() {
		return MANDATORY_PARAMETERS;
	}
}
