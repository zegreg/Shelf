package main.java.FHJ.shelf.commands;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.*;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserInterface;

public class GetShelfs extends BaseGetCommand implements Command {

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
			return new GetShelfs(repository, parameters);
		}

	}

	private final ShelfRepository shelfRepository;

	private static final String[] DEMANDING_PARAMETERS = {};

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetShelfs(ShelfRepository repository, Map<String, String> parameters) {
		super(parameters);
		this.shelfRepository = repository;
	}

	/*
	 * @Override protected void internalExecute() throws CommandException {
	 * Iterable<AbstractShelf> iterator = shelfRepository.getDatabaseElements();
	 * 
	 * String result = "Shelfs Database";
	 * 
	 * for (AbstractShelf element : iterator) {
	 * 
	 * Shelf shelf = (Shelf)element;
	 * 
	 * result += "\n" +element.getId() + " " + shelf.details();
	 * 
	 * //System.out.println(element.getId() + " " +element.toString()); }
	 * System.out.println(result); }
	 */
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected Map<String, String> actionExecute() throws CommandException {
		Iterable<AbstractShelf> iterator = shelfRepository
				.getDatabaseElements();

//		AbstractShelf shelf = null;
//		for (AbstractShelf element : iterator) {
//
//			shelf =  element;
//
//			
//		}
		Map<String, String> map = putCommandResultInAMap(iterator);
		
		return map;
	}

	
	protected Map<String, String> putCommandResultInAMap( Iterable<AbstractShelf> it) {
		
		Map<String, String> map = new TreeMap<String, String>();

		Shelf shelf = null;
		for (AbstractShelf element : it) {

			shelf =  (Shelf) element;
			String elementContained = null;
			for (int i = 0; i < shelf.getInfoAboutAllElementsContained().length; i++) {
				elementContained = shelf.getInfoAboutAllElementsContained()[i].toString();
			} 
			
			map.put("Shelf Details ID :"+String.valueOf(shelf.getId())  , shelf.details() +"\n"+ "Details Elements : \n\t" + elementContained);
		}
		
		//String shelfID = String.valueOf(shelf.getId());

		//containerToCommandResult.put("Shelf Details ","shelfID : " + shelfID +"\n"+  shelf.details());
		return map;

	}

}
