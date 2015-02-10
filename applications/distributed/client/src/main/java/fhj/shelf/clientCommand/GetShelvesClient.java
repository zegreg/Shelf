package fhj.shelf.clientCommand;

import java.util.concurrent.ExecutionException;

import fhj.shelf.http.SendGETHttpRequest;

public class GetShelvesClient extends BaseClientCommand implements ClientCommand {
	
	String path;
	
	public GetShelvesClient() {
	path = "/shelfs/";
	}
	
	

	@Override
	public Object execute() throws InterruptedException, ExecutionException,
			Exception {
		
		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append("?").append(path);
		
		return SendGETHttpRequest.sendGetRequest(builder.toString());

	}

}
