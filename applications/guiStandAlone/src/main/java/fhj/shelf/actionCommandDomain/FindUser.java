package fhj.shelf.actionCommandDomain;

import java.util.Map;
import java.util.TreeMap;

import fhj.shelf.commandsDomain.GetOneUser;
import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.UserRepository;

public class FindUser {

	private UserRepository userRepo = StandAloneDatabase.getUserRepoInstance();

	private Map<String, String> parameters;

	private static final String USERNAME = "username";

	public FindUser(Map<String, String> parameters) {
		this.parameters = parameters;
	}

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
