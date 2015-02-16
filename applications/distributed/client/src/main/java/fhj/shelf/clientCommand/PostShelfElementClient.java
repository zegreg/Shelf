package fhj.shelf.clientCommand;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.UIPostCommand;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.http.SendPOSTHttpRequest;

public class PostShelfElementClient extends BaseClientCommand implements UIPostCommand{
	
	
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
			return new PostShelfElementClient(parameters);
		}
	}
	
	
	
	
	
	
	private final Map<String, String> params;
	private final String method;
	private final String path;
	private final String id;
	private final String typeElement;
	
	public PostShelfElementClient(Map<String, String> params) {

		this.id = params.get("id");
		this.params = params;
		this.typeElement = params.get("type");
		params.remove("type");
		params.remove("id");
		path = "/shelfs/";
		method = "POST";
	}
	
	
	@Override
	public String execute() throws InterruptedException, ExecutionException,
			Exception {
		
		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL())
		.append(path)
		.append(id)
		.append("/")
		.append("elements/")
		.append(typeElement)
		.append("?");


		System.out.println("BuilderClientRequest "+ builder.toString());
		
		return SendPOSTHttpRequest.sendPostRequest(params, builder.toString(), method);
	}

}
