package fhj.shelf.clientCommand;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.UIGetCommand;
import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;
import fhj.shelf.http.SendGETHttpRequest;


/**
 * Class whose instance represent a String url path for get method
 * 
 *@author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetShelfClient extends BaseClientCommand implements UIGetCommand{

	
	/**
	 * Factory for instance GetShelfClient
	 * 
	 *@author Filipa Estiveira, Hugo Leal, José Oliveira
	 */
	public static class Factory implements CommandGetFactoryWithParameters {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of GetShelfClient
		 */
		@Override
		public UIGetCommand newInstance(Map<String, String> parameters) {
			return new GetShelfClient(parameters);
		}
	}
	
	
	
	private final String id ;
	private final String path;


	/**
	 * Constructor
	 * @param parameters
	 */
	public GetShelfClient(Map<String, String> parameters) {
		path = "/shelfs/";
		this.id = parameters.get("id");
	}




	@Override
	public Map<String, String> execute() throws InterruptedException, ExecutionException, Exception
	{

		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append(path).append(id).append("/").append("elements").append("?");

		
			return SendGETHttpRequest.sendGetRequest(builder.toString());
		
	}
}
