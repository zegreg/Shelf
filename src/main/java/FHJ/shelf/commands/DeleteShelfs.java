package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.AbstractShelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

public class DeleteShelfs extends BasePostCommand implements Command {

	/**
	 * Class that implements the {@link PostElement} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		private final ShelfRepository repository;
		private final UserRepository userRepo;

		public Factory(UserRepository userRepo, ShelfRepository repository) {
			this.userRepo = userRepo;
			this.repository = repository;

		}

		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new DeleteShelfs(userRepo, repository, parameters);
		}

	}

	public static final String SID = "sid";

	private final ShelfRepository shelfRepository;

	private static final String[] DEMANDING_PARAMETERS = { SID };

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private DeleteShelfs(UserRepository userRepo, ShelfRepository repository,
			Map<String, String> parameters) {
		super(userRepo, parameters);
		this.shelfRepository = repository;
	}

	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected String validLoginPostExecute() throws CommandException {
		long shelfId = Long.parseLong(parameters.get(SID));

		AbstractShelf shelf = shelfRepository.getShelfById(shelfId);
		shelfRepository.remove(shelf);

		String result = "Remove shelf succesfully to shelf repository";

		return result;
	}

}
