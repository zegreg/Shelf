package fhj.shelf.clientCommand;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import fhj.shelf.commands.UIGetCommand;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;
import fhj.shelf.http.SendGETHttpRequest;

public class GetShelfClient extends BaseClientCommand implements UIGetCommand{

	
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
			return new GetShelfClient(parameters);
		}
	}
	
	
	
	private final String id ;
	private final String path;


	public GetShelfClient(Map<String, String> parameters) {
		path = "/shelfs/";
		this.id = parameters.get("id");
	}




	@Override
	public Map<String, String> execute() throws InterruptedException, ExecutionException, Exception
	{

		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append("?").append(path).append(id).append("/").append("details");

		 
		
		return SendGETHttpRequest.sendGetRequest(builder.toString());
	}
}
