package fhj.shelf.clientCommand;

import java.util.concurrent.ExecutionException;

import fhj.shelf.http.SendGETHttpRequest;

public class GetUserClient extends BaseClientCommand implements ClientCommand {

	private final String path;
	private final String username;
	

	public GetUserClient(String username) 
	{
		this.username =username;
		
		path = "/users/";
	}


	@Override
	public Object execute() throws InterruptedException, ExecutionException, Exception
	{

		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append("?").append(path).append(username);

		 
		
		return SendGETHttpRequest.sendGetRequest(builder.toString());
	}


	

}
