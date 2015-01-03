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

/**
 * 
 *
 */
public class GetUsers extends BaseGetCommand implements Command {

	/**
	 * Class that implements the {@link PostElement} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {
		

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
	 * Gets a UserRepository instance
	 */
	private final UserRepository userRepository;
	
	/**
	 * Gets an array of parameters of the actual Command
	 */
	private static final String[] DEMANDING_PARAMETERS = {};

	/**
	 * 
	 * @param repository
	 * @param id
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

	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

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
