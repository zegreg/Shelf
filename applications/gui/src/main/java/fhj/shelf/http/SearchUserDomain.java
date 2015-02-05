package fhj.shelf.http;

import java.util.Map;

import fhj.shelf.commandsDomain.GetOneUser;
import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.UserRepository;

public class SearchUserDomain {

	public SearchUserDomain() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static AbstractUser  GetUserInformation(UserRepository userRepository,Map<String, String> params) throws Exception{
		
		
		return  new GetOneUser(userRepository, params.get("username")).call();
	}
	
	
}
