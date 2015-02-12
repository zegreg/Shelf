package fhj.shelf.clientCommand;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.UIGetCommand;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;
import fhj.shelf.http.SendGETHttpRequest;

public class GetShelvesClient extends BaseClientCommand implements UIGetCommand {
	
	
	
	public static class Factory implements CommandGetFactoryWithoutParameters {

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
		public UIGetCommand newInstance() {
			return new GetShelvesClient();
		}
	}
	
	
	
	
	private final String path;
	
	public GetShelvesClient() {
	path = "/shelfs/";
	}
	
	

	@Override
	public Map<String, String>  execute() throws InterruptedException, ExecutionException,
			Exception {
		
		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append("?").append(path);
		
		return SendGETHttpRequest.sendGetRequest(builder.toString());

	}

}
