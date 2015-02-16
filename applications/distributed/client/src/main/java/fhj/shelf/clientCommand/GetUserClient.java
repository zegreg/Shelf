package fhj.shelf.clientCommand;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.UIGetCommand;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;
import fhj.shelf.http.SendGETHttpRequest;


/**
* Class whose instance represent a String url path for get method
* 
*@author Filipa Estiveira, Hugo Leal, José Oliveira
*/
public class GetUserClient extends BaseClientCommand implements UIGetCommand {

	
	
	public static class Factory implements CommandGetFactoryWithParameters {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of GetUserClient
		 */
		@Override
		public UIGetCommand newInstance(Map<String, String> parameters) {
			return new GetUserClient(parameters);
		}
	}
	
	
	
	private final String path;
	private final String username;
	

	public GetUserClient(Map<String, String>  username) 
	{
		this.username =username.get("username");
		
		path = "/users/";
	}


	@Override
	public Map<String, String> execute() throws InterruptedException, ExecutionException, Exception
	{

		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append(path).append(username).append("?");

		 
		
		return SendGETHttpRequest.sendGetRequest(builder.toString());
	}


	

}
