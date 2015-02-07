package fhj.shelf.actionCommand;

import java.util.Map;

import fhj.shelf.actionCommandDomain.SaverShelfDomain;
import fhj.shelf.actionCommandDomain.SaverUserDomain;
import fhj.shelf.actionCommandDomain.SearchShelfDomain;
import fhj.shelf.actionCommandDomain.SearchUserDomain;
import fhj.shelf.http.SendDELETEHttpRequest;
import fhj.shelf.http.SendGETHttpRequest;
import fhj.shelf.http.SendPOSTHttpRequest;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.repos.UserRepository;

public  class ActionCommandFactory {
	private final static int PORT = 8081;
	private final static String HOST = "localhost";
	
	

	
    public static Object createActionCommand(String what, Map<String, String> params, 
    	 UserRepository userRepository, ShelfRepository repository, String path) throws Exception {
    	
    	String requestURL = "http://" + HOST+":"+PORT;
       
        if (what.equals("SaverUserDomain")) {
            return SaverUserDomain.PostUserInformation(userRepository, params);
        }
        
        else if(what.equals("SaverUserHttp")){
        	return SendPOSTHttpRequest.sendPostRequest(requestURL, params, path) ;
        }
        
        else if(what.equals("SearchUserHttp")){
        	return SendGETHttpRequest.sendGetRequest(requestURL, params, path) ;
        }
        else if(what.equals("SearchUserDomain")){
        	return SearchUserDomain.GetUserInformation(userRepository, params);
        }
        else if(what.equals("SaverShelfHttp")){
        	return SendPOSTHttpRequest.sendPostRequest(requestURL, params, path) ;
        }
        else if(what.equals("SaverShelfDomain")){
        	 return SaverShelfDomain.PostShelfInformation(repository, params);
        }
        else if(what.equals("SearchShelfDomain")){
        	return SearchShelfDomain.GetShelfInformation(repository, params);
       }
        else if(what.equals("SearchShelfHttp")){
        	return SendGETHttpRequest.sendGetRequest(requestURL, params, path) ;
       }
        else if(what.equals("EraseShelfHttp")){
        	return SendDELETEHttpRequest.sendDeleteRequest(requestURL, params, path) ;
       }
        else if(what.equals("GetShelfDetailsHttp")){
        	return SendGETHttpRequest.sendGetRequest(requestURL, params, path) ;
       }
        return null;
    }

	
	
	
}
