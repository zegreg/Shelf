package fhj.shelf.actionCommandDomain;

import java.util.Map;
import java.util.TreeMap;

import fhj.shelf.commandsDomain.GetOneUser;
import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.UserRepository;

public class SearchUserDomain {

	public SearchUserDomain() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Map<String, String>  GetUserInformation(UserRepository userRepository,Map<String, String> params) throws Exception{
		
		AbstractUser  user = new GetOneUser(userRepository, params.get("username")).call();
		Map<String, String> map = new TreeMap<String, String> ();
		map.put("username", user.getLoginName());
		map.put("email", user.getEmail());
		map.put("password", user.getLoginPassword());
		map.put("fullname", user.getFullName());
		return  map;
	}
	
	
}
