package main.java.FHJ.shelf.commands;

import java.util.Map;
import java.util.TreeMap;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;

public class GetShelf extends BaseGetCommand implements Command {

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
			return new GetShelf(repository, parameters);
		}

	}

	private final ShelfRepository shelfRepository;

	public static final String SID = "sid";
	// private final long shelfId;

	private static final String[] DEMANDING_PARAMETERS = { SID };

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetShelf(ShelfRepository repository, Map<String, String> parameters) {
		super(parameters);
		this.shelfRepository = repository;
	}
/*
	@Override
	protected void internalExecute() throws CommandException {
		long shelfId = Long.parseLong(parameters.get(SID));

		AbstractShelf shelf = shelfRepository.getShelfById(shelfId);
		
		String result = ((Shelf) shelf).details();
		
		System.out.println(result);
	}
*/
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected Map<String, String> actionExecute() throws CommandException {
		long shelfId = Long.parseLong(parameters.get(SID));

		Shelf shelf = (Shelf)shelfRepository.getShelfById(shelfId);
	
		Map<String, String> map = new TreeMap<String, String>();
		
		putCommandResultInAMap(map, shelf);
	
		return map;
	}
	
	protected void putCommandResultInAMap(
			Map<String, String> containerToCommandResult, Shelf shelf) {

		String shelfID = String.valueOf(shelf.getId());

		containerToCommandResult.put("shelfID", shelfID);
		containerToCommandResult.put("Shelf Details", shelf.details());
	}

}
