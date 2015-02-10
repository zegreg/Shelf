package fhj.shelf.clientCommand;


import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.http.SendPOSTHttpRequest;

public class PatchUserClient extends BaseClientCommand implements ClientCommand {

	private final String username;
	private final Map<String, String> params;
	private final String path;
	private final String method;
	
	public PatchUserClient(String username, Map<String, String> params) {
		this.params= params;
		this.username = username;
		path = "/users/";
		method ="PATCH";
	}
	

	@Override
	public Object execute() throws InterruptedException, ExecutionException,
			Exception {
		
		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append("?").append(path);

		 
		
		return SendPOSTHttpRequest.sendPostRequest(params, builder.toString(), method);
	}

}
