package fhj.shelf.clientcommand;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.UIPostCommand;
import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.http.SendPOSTHttpRequest;


/**
* Class whose instance represent a String url path for post method
* 
*@author Filipa Estiveira, Hugo Leal, José Oliveira
*/
public class PostShelfClient extends  BaseClientCommand implements UIPostCommand{
	
	
	public static class Factory implements CommandPostFactoryWithParameters {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of PostShelfClient
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
	public String execute()  throws ExceptionsClientServer
	{

		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append(path).append("?");

		 
		
		return SendPOSTHttpRequest.sendPostRequest(params, builder.toString(), method);
	}
	
}
