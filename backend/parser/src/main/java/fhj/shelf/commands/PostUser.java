package fhj.shelf.commands;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.exceptions.CommandException;
import fhj.shelf.repos.UserRepository;

/**
 * Class whose instances represent the command that posts the user.
 * 
 * @authors Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class PostUser extends BasePostCommand implements Command {

	/**
	 * Class that implements the {@link PostUser} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * Holds the user repository to be used by this command factory
		 */
		private final UserRepository repository;

		public Factory(UserRepository productRepo) {
			this.repository = productRepo;
		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of PostUser
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new PostUser(repository, parameters);
		}
	}

	/**
	 * Mandatory parameter
	 */
	public static final String USERNAME = "username";

	/**
	 * Mandatory parameter
	 */
	public static final String PASSWORD = "password";

	/**
	 * Mandatory parameter
	 */
	public static final String EMAIL = "email";

	/**
	 * Mandatory parameter
	 */
	public static final String FULLNAME = "fullname";

	/**
	 * The array containing all the mandatory parameters of this command
	 */
	private static final String[] MANDATORY_PARAMETERS = { USERNAME, PASSWORD,
			EMAIL, FULLNAME };

	/**
	 * Mandatory parameter
	 */
	public final UserRepository userRepository;

	/**
	 * Initiates an instance with the given the repository{shelf} and command
	 * parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private PostUser(UserRepository repository, Map<String, String> parameters) {
		super(repository, parameters);
		this.userRepository = repository;
	}

	/**
	 * Return a parameter map result of the command execution
	 * 
	 * @throws ExecutionException
	 */
	@Override
	protected String validLoginPostExecute() throws CommandException,
			IllegalArgumentException, ExecutionException {

		String username = parameters.get(USERNAME);
		String password = parameters.get(PASSWORD);
		String email = parameters.get(EMAIL);
		String fullname = parameters.get(FULLNAME);

		try {
			return new fhj.shelf.commandsDomain.CreateUser(userRepository,
					username, password, email, fullname).call();

		} catch (Exception cause) {
			throw new ExecutionException(cause);
		}
	}

	/**
	 * {@see Command#getMandatoryParameters()}
	 */
	@Override
	protected String[] getMandatoryParameters() {
		return MANDATORY_PARAMETERS;
	}

}
