package fhj.shelf.clientCommand;

import java.util.concurrent.ExecutionException;

import fhj.shelf.http.SendGETHttpRequest;

public class GetUsersClient extends BaseClientCommand implements ClientCommand {
	
	private final String path;
	
	public GetUsersClient() {
		path = "/users";
	}
	
	
	

	@Override
	public Object execute() throws InterruptedException, ExecutionException,
			Exception {
		
		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append("?").append(path);

		 
		
		return SendGETHttpRequest.sendGetRequest(builder.toString());
	}

}
