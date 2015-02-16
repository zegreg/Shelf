package fhj.shelf.clientCommand;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.UIPostCommand;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.http.SendPOSTHttpRequest;

public class PostShelfClient extends  BaseClientCommand implements UIPostCommand{
	
	
	public static class Factory implements CommandPostFactoryWithParameters {

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
		public UIPostCommand newInstance(Map<String, String> parameters) {
			return new PostShelfClient(parameters);
		}
	}
	
	
    private final String path;
	private final Map<String, String> params;
	private final String method;
	

	public PostShelfClient(Map<String, String> params2) 
	{
	
		
		this.params = params2;
		path = "/shelfs";
		method = "POST";
	}



	@Override
	public String execute() throws InterruptedException, ExecutionException, Exception
	{

		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append(path).append("?");

		 
		
		return SendPOSTHttpRequest.sendPostRequest(params, builder.toString(), method);
	}
	
}
