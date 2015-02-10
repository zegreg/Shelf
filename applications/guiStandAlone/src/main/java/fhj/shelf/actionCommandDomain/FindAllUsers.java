package fhj.shelf.actionCommandDomain;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import fhj.shelf.commandsDomain.GetAllUsers;
import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.UserRepository;


public class FindAllUsers {
	
	private UserRepository userRepo = StandAloneDatabase.getUserRepoInstance();

	public FindAllUsers() {

	}

	public Map<String, String> execute() throws NumberFormatException,
			Exception {
		return formatExecuter(new GetAllUsers(userRepo).call());
	}
	
	public static Map<String, String>  formatExecuter(Map<String,AbstractUser> commandResult) throws Exception{
		
		Map<String, String> map = new TreeMap<String, String>();
		

		for (Entry<String, AbstractUser> entry : commandResult.entrySet()) {

			String key = "Username";

			String username = entry.getValue().getLoginName();

			map.put(key, username);
		}
		return map;
	}
	
	
}

