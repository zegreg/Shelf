package fhj.shelf.clientCommand;

import java.util.concurrent.ExecutionException;

import fhj.shelf.http.SendGETHttpRequest;

public class GetShelfClient extends BaseClientCommand implements ClientCommand {

	long id ;
	String path;
	
	public GetShelfClient(long id) {
		this.id = id;
		
		path = "/shelfs/";
	}
	
	


	@Override
	public Object execute() throws InterruptedException, ExecutionException, Exception
	{

		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append("?").append(path).append(id).append("/").append("details");

		 
		
		return SendGETHttpRequest.sendGetRequest(builder.toString());
	}
}
