package fhj.shelf.clientcommand;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.UIGetCommand;
import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;
import fhj.shelf.http.SendGETHttpRequest;


/**
* Class whose instance represent a String url path for get method
* 
*@author Filipa Estiveira, Hugo Leal, José Oliveira
*/
public class GetUsersClient extends BaseClientCommand implements UIGetCommand {
	
	
	public static class Factory implements CommandGetFactoryWithoutParameters {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of GetUsersClient
		 */
		@Override
		public UIGetCommand newInstance() {
			return new GetUsersClient();
		}
	}
	
	
	
	private final String path;
	
	public GetUsersClient() {
		path = "/users";
	}
	
	
	

	@Override
	public Map<String, String> execute() throws ExceptionsClientServer{
		Map<String, String> map = new TreeMap<String, String>();
		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append(path).append("?");

		 
		
		try {
			map = SendGETHttpRequest.sendGetRequest(builder.toString());
		} catch (InterruptedException e) {
			throw new ExecutionCommunicationException("InterruptedException getUsers request");
			
		} catch (ExecutionException e) {
			throw new ExecutionCommunicationException("ExecutionException getUsers request");
			
		} catch (Exception e) {
			throw new ExecutionCommunicationException("Exception getUsers request");
		
		}
		return map;
	}

}
