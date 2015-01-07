package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserInterface;
import main.java.FHJ.shelf.model.repos.UserRepository;

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

		
		public Factory(UserRepository repository) {
			this.repository = repository;

		}

		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new PatchUsers(repository, parameters);
		}

	}

	/**
	 * demanding parameter
	 */
	public static final String USERNAME = "username";

	/**
	 * demanding parameter
	 */
	public static final String OLD_PASSWORD = "oldPassword";

	/**
	 * demanding parameter
	 */
	public static final String NEW_PASSWORD = "newPassword";

	/**
     * Holds the user repository to be used by the command
     */
	private final UserRepository userRepository;

	/**
     * The array containing all the demanding parameters of this command
     */
	private static final String[] DEMANDING_PARAMETERS = { USERNAME,
			OLD_PASSWORD, NEW_PASSWORD };

	
	 /**
     * Initiates an instance with the given the repository{user, shelf, element} and command parameters
     * 
     * @param repository the repository to be used
     * @param parameters the command's unparsed parameters
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
		return DEMANDING_PARAMETERS;
	}

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
		}

		return result;
	}
}

