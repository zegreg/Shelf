package main.java.FHJ.shelf.commands;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.User;
import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;
import main.java.FHJ.shelf.model.repos.UserInterface;
import main.java.FHJ.shelf.model.repos.UserRepository;


public class GetUsers extends BaseGetCommand implements Command {

	/**
	 * Class that implements the {@link GetUsers} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {
		
		/**
	     * Holds the user repository to be used by the command
	     */
		private final UserRepository repository;
		
		
		public Factory(UserRepository repository)
		{
			this.repository = repository;
			
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new GetUsers(repository, parameters);
		}
		
	}

	/**
     * Holds the user repository to be used by the command
     */
	private final UserRepository userRepository;
	
	/**
     * The array containing all the demanding parameters of this command
     */
	private static final String[] DEMANDING_PARAMETERS = {};

	/**
     * Initiates an instance with the given the repository{user} and command parameters
     * 
     * @param repository the repository to be used
     * @param parameters the command's unparsed parameters
     */
	private GetUsers(UserRepository repository, Map<String, String> parameters) {
		super(parameters);
		this.userRepository = repository;
	}

	/*
	 * @Override protected void internalExecute() throws CommandException {
	 * Iterable<UserInterface> iterator = userRepository.getDatabaseElements();
	 * String result = "Users List"; for (UserInterface element : iterator) {
	 * 
	 * result += "\n" + element.getLoginName(); } System.out.println(result);
	 * 
	 * }
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
		Iterable<UserInterface> iterator = userRepository.getDatabaseElements();

		
		Map<String, UserInterface> map = userRepository.getUsers();
//		Map<String, String> map = new TreeMap<String, String>();
//
//		for (UserInterface element : iterator) {
//
//			User user = (User) element;
			Map<String, String> finalMap = putCommandResultInAMap(map);
//
//		}
		return finalMap;
	}

	protected Map<String, String> putCommandResultInAMap(
			Map<String, UserInterface> map) {
		Map<String, String> tmp = new TreeMap<String, String>();
	
		
		for (Entry<String, UserInterface> entry : map.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue().toString();
		    tmp.put(key, value);
		}
		  
		 return tmp;
	}
}
