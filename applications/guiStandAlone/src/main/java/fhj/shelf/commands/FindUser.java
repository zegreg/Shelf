package fhj.shelf.commands;

import java.util.Map;
import java.util.TreeMap;

import fhj.shelf.commands.UIGetCommand;
import fhj.shelf.commandsDomain.GetOneUser;
import fhj.shelf.database.StandAloneDatabase;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;
import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.UserRepository;

public class FindUser implements UIGetCommand{

	public static class Factory implements CommandGetFactoryWithParameters {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param userRepo
		 *            is an instance of UserRepository
		 * @param shelfRepo
		 *            is an instance of ShelfRepository
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of PostShelf
		 */
		@Override
		public UIGetCommand newInstance(Map<String, String> parameters) {
			return new FindUser(parameters);
		}
	}
	private UserRepository userRepo = StandAloneDatabase.getUserRepoInstance();

	private Map<String, String> parameters;

	private static final String USERNAME = "username";

	public FindUser(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public Map<String, String> execute() throws NumberFormatException,
			Exception {
		return formatExecuter(new GetOneUser(userRepo,
				parameters.get(USERNAME)).call());
	}
	
	public static Map<String, String>  formatExecuter(AbstractUser user) throws Exception{
		
		Map<String, String> map = new TreeMap<String, String> ();
		map.put("username", user.getLoginName());
		map.put("email", user.getEmail());
		map.put("password", user.getLoginPassword());
		map.put("fullname", user.getFullName());
		return  map;
	}
	
	
}
