package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserInterface;
import main.java.FHJ.shelf.model.repos.UserRepository;

/**
 * This class defines the process of editing users
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class PatchUsers extends BasePostCommand implements Command {

	/**
	 * Class that implements the {@link PatchUsers} factory, according to the
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
		 * instance of PatchUsers
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new PatchUsers(repository, parameters);
		}

	}

	/**
	 * Mandatory parameter
	 */
	public static final String USERNAME = "username";

	/**
	 * Mandatory parameter
	 */
	public static final String OLD_PASSWORD = "oldPassword";

	/**
	 * Mandatory parameter
	 */
	public static final String NEW_PASSWORD = "newPassword";

	/**
	 * Holds the user repository to be used by the command
	 */
	private final UserRepository userRepository;

	/**
	 * The array containing all the demanding parameters of this command
	 */
	private static final String[] MANDATORY_PARAMETERS = { USERNAME,
			OLD_PASSWORD, NEW_PASSWORD };

	/**
	 * Initiates an instance with the given the repository{user, shelf, element}
	 * and command parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private PatchUsers(UserRepository repository, Map<String, String> parameters) {
		super(repository, parameters);
		this.userRepository = repository;
	}

	/**
	 * {@see Command#getMandatoryParameters()}
	 */
	@Override
	protected String[] getMandatoryParameters() {
		return MANDATORY_PARAMETERS;
	}

	/**
	 * This is an override method of the base class, it executes and validates
	 * commands and throws an exception when execution isn't valid
	 */
	@Override
	protected String validLoginPostExecute() throws CommandException {
		String username = parameters.get(USERNAME);
		String oldPassword = parameters.get(OLD_PASSWORD);
		String newPassword = parameters.get(NEW_PASSWORD);

		UserInterface user = userRepository.getUserName(username);

		String result = "";

		if (user.getLoginPassword().equals(oldPassword)) {
			user.setLoginPassword(newPassword);
			result = "The Password has been changed successfully";
		} else
			throw new CommandException("Unable to change password, try again");

		return result;
	}
}
