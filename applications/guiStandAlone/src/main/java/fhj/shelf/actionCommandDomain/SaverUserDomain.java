package fhj.shelf.actionCommandDomain;


import java.util.Map;




import fhj.shelf.commandsDomain.CreateUser;
import fhj.shelf.repos.UserRepository;

public class SaverUserDomain {
	
	 public SaverUserDomain() {
		
		
	}

	public static  String PostUserInformation(UserRepository userRepository  ,Map<String, String> params) throws Exception {
		
		
		return new CreateUser(userRepository,params.get("username") ,
				params.get("password") , params.get("email") ,
				params.get("fullname") ).call();
		
		     

		}
	
	
	
	
}
