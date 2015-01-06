package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserInterface;
import main.java.FHJ.shelf.model.repos.UserRepository;

public class PatchUsers extends BasePostCommand implements Command {

	/**
	 * Class that implements the {@link PostElement} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		private final UserRepository repository;

		public Factory(UserRepository repository) {
			this.repository = repository;

		}

		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new PatchUsers(repository, parameters);
		}

	}

	public static final String USERNAME = "username";

	public static final String OLD_PASSWORD = "oldPassword";

	public static final String NEW_PASSWORD = "newPassword";

	/**
	 * Gets a UserRepository instance
	 */
	private final UserRepository userRepository;

	/**
	 * Gets an array of parameters of the actual Command
	 */
	private static final String[] DEMANDING_PARAMETERS = { USERNAME,
			OLD_PASSWORD, NEW_PASSWORD };

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private PatchUsers(UserRepository repository, Map<String, String> parameters) {
		super(repository, parameters);
		this.userRepository = repository;
	}

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

