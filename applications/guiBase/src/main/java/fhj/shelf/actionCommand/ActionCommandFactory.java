package fhj.shelf.actionCommand;


import java.io.IOException;
import java.util.Map;

import fhj.shelf.http.SaverUserDomain;
import fhj.shelf.http.SearchUserDomain;
import fhj.shelf.http.SendGETHttpRequest;
import fhj.shelf.http.SendPOSTHttpRequest;
import fhj.shelf.repos.UserRepository;

public  class ActionCommandFactory {

	
    public static Object createActionCommand(String what, Map<String, String> params, 
    		String requestURL, UserRepository userRepository) throws Exception {

       
        if (what.equals("SaverUserDomain")) {
            return SaverUserDomain.PostUserInformation(userRepository, params);
        }
        
        else if(what.equals("SaverUserHttp")){
        	return SendPOSTHttpRequest.sendPostRequest(requestURL, params) ;
        }
        
        else if(what.equals("SearchUserHttp")){
        	return SendGETHttpRequest.sendGetRequest(requestURL, params) ;
        }
        else if(what.equals("SearchUserDomain")){
        	return SearchUserDomain.GetUserInformation(userRepository, params);
        }
     
        return null;
    }

	
	
	
}
