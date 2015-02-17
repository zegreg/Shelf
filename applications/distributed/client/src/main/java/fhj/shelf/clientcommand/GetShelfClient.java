package fhj.shelf.clientcommand;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import fhj.shelf.commands.UIGetCommand;
import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;
import fhj.shelf.http.SendGETHttpRequest;
import fhj.shelf.ui.Book;


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
	public Map<String, String> execute() throws ExceptionsClientServer
	{
		Map<String, String> map = new TreeMap<String, String>();
		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append(path).append(id).append("/").append("elements").append("?");
		
		
			try {
				map = SendGETHttpRequest.sendGetRequest(builder.toString());
			} catch (Exception e) {
				Logger.getLogger(GetShelfClient.class.getName()).log(Level.WARNING, "Exception Occured : on getShelfClient ", e);
				throw new ExecutionCommunicationException( e.getClass()+"getShelfClient request");
				
			} 
		return map;
	}
}
