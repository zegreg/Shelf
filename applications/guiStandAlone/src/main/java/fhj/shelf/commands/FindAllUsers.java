package fhj.shelf.commands;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import fhj.shelf.commands.UIGetCommand;
import fhj.shelf.commandsDomain.GetAllUsers;
import fhj.shelf.database.StandAloneDatabase;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;
import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.UserRepository;

public class FindAllUsers implements UIGetCommand {

	public static class Factory implements CommandGetFactoryWithoutParameters {

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
		public UIGetCommand newInstance() {
			return new FindAllUsers();
		}
	}

	private UserRepository userRepo = StandAloneDatabase.getUserRepoInstance();

	public FindAllUsers() {

	}

	@Override
	public Map<String, String> execute() throws NumberFormatException,
			Exception {
		return formatExecuter(new GetAllUsers(userRepo).call());
	}

	public static Map<String, String> formatExecuter(
			Map<String, AbstractUser> commandResult) throws Exception {

		Map<String, String> map = new TreeMap<String, String>();

		for (Entry<String, AbstractUser> entry : commandResult.entrySet()) {

			String key = "Username";

			String username = entry.getValue().getLoginName();

			map.put(key, username);
		}
		return map;
	}

}
