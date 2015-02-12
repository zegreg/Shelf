package fhj.shelf.clientCommand;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.UIGetCommand;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;
import fhj.shelf.http.SendGETHttpRequest;

public class GetUserClient extends BaseClientCommand implements UIGetCommand {

	
	
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

		builder.append(super.getRequestURL()).append("?").append(path).append(username);

		 
		
		return SendGETHttpRequest.sendGetRequest(builder.toString());
	}


	

}
