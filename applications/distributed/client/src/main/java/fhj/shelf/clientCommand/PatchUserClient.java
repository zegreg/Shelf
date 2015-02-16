package fhj.shelf.clientCommand;


import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.UICommand;
import fhj.shelf.commands.UIPostCommand;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.http.SendPOSTHttpRequest;


/**
* Class whose instance represent a String url path for patch method
* 
*@author Filipa Estiveira, Hugo Leal, José Oliveira
*/
public class PatchUserClient extends BaseClientCommand implements UICommand {

	
	
	
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
	
	
	private final String username;
	private final Map<String, String> params;
	private final String path;
	private final String method;
	
	public PatchUserClient( Map<String, String> params) {
		this.params= params;
		this.username = params.get("username");
		path = "/users/";
		method ="PATCH";
	}
	

	@Override
	public Object execute() throws InterruptedException, ExecutionException,
			Exception {
		
		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append(path).append("?");

		 
		
		return SendPOSTHttpRequest.sendPostRequest(params, builder.toString(), method);
	}

}
