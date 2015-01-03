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
	
		 new TreeMap<String, String>();
		
		Map<String, String> map = putCommandResultInAMap(shelf);
	
		return map;
	}
	
	protected Map<String, String> putCommandResultInAMap( Shelf shelf) {
		Map<String, String> containerToCommandResult = new TreeMap<String, String>();
		String shelfID = String.valueOf(shelf.getId());

		String elementContained = null;
		for (int i = 0; i < shelf.getInfoAboutAllElementsContained().length; i++) {
			elementContained = shelf.getInfoAboutAllElementsContained()[i].toString();
		} 
		
		containerToCommandResult.put("Shelf Details ID :"+String.valueOf(shelf.getId())  , shelf.details() +"\n"+ "Details Elements : \n\t" + elementContained);
//		containerToCommandResult.put("Shelf Details", shelf.details());
		return containerToCommandResult;
	}

}
