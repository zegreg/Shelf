package main.java.FHJ.shelf.commands;

import java.util.Map;
import java.util.TreeMap;


import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.*;
import main.java.FHJ.shelf.model.repos.ShelfRepository;


public class GetShelfs extends BaseGetCommand implements Command {

	/**
	 * Class that implements the {@link GetShelfs} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
	     * Holds the shelf repository to be used by the command
	     */
		private final ShelfRepository repository;

		
		public Factory(ShelfRepository repository) {
			this.repository = repository;

		}

		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new GetShelfs(repository, parameters);
		}

	}

	/**
     * Holds the shelf repository to be used by the command
     */
	private final ShelfRepository shelfRepository;

	/**
     * The array containing all the demanding parameters of this command
     */
	private static final String[] DEMANDING_PARAMETERS = {};

	/**
     * Initiates an instance with the given the repository{shelf} and command parameters
     * 
     * @param repository the repository to be used
     * @param parameters the command's unparsed parameters
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
	
	/**
     * {@see Command#getMandatoryParameters()}
     */
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

	/**
	 * Return a parameter map result of the command execution
	 */
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
