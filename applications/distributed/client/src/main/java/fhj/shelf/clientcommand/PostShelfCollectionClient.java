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
public class PostShelfCollectionClient extends BaseClientCommand implements UIPostCommand{
	
	
	public static class Factory implements CommandPostFactoryWithParameters {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of PostShelfCollectionClient
		 */
		@Override
		public UIPostCommand newInstance(Map<String, String> parameters) {
			return new PostShelfCollectionClient(parameters);
		}
	}
	
	
	private final Map<String, String> params;
	private final String method;
	private final String path;
	private final String id;
	private final String typeElement;
	private final String eid;
	
	public PostShelfCollectionClient(Map<String, String> params) {
		this.params = params;
		this.eid = params.get("eid");
		this.id = params.get("id");
		this.typeElement = params.get("type");
		params.remove("eid");
		params.remove("id");
		params.remove("type");
		path = "/shelfs/";
		method = "POST";
	}
	
	
	@Override
	public String execute() throws ExceptionsClientServer{
		
		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL())
		.append(path)
		.append(id)
		.append("/")
		.append("elements/")
		.append(typeElement)
		.append("/")
		.append(eid)
		.append("?");

		
		return SendPOSTHttpRequest.sendPostRequest(params, builder.toString(), method);
	}

}