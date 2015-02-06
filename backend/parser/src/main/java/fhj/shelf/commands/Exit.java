package fhj.shelf.commands;

import java.util.Map;

import fhj.shelf.exceptions.CommandException;
import fhj.shelf.output.StackMensage;
import fhj.shelf.repos.UserRepository;

public class Exit extends BaseCommand implements Command {

	public static class Factory implements CommandFactory {

		private final UserRepository repository;

		public Factory(UserRepository repository) {
			this.repository = repository;

		}

		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new Exit(repository, parameters);
		}

	}

	@SuppressWarnings("unused")
	private final UserRepository userRepository;

	private static final String[] DEMANDING_PARAMETERS = {};

	public Exit(UserRepository repository, Map<String, String> parameters) {
		super(parameters);
		this.userRepository = repository;
	}

	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected String internalExecute() throws CommandException {
	
		String exitMessage = "***************************************"
				+ "\n Thanks for using FHJ's App! Bye :)";
		//System.exit(0);
		
		return exitMessage;
	}
}

