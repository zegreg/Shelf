package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.AbstractShelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;

public class DeleteShelfs extends BaseCommand implements Command {

	/**
	 * Class that implements the {@link PostElement} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		private final ShelfRepository repository;

		public Factory(ShelfRepository repository) {
			this.repository = repository;

		}

		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new DeleteShelfs(repository, parameters);
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
	private DeleteShelfs(ShelfRepository repository,
			Map<String, String> parameters) {
		super(parameters);
		this.shelfRepository = repository;
	}

	@Override
	protected void internalExecute() throws CommandException {

		long shelfId = Long.parseLong(parameters.get(SID));

		AbstractShelf shelf = shelfRepository.getShelfById(shelfId);
		shelfRepository.remove(shelf);

		//String result = "";
		System.out.println("Remove shelf succesfully to shelf repository");
	}

	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

}
