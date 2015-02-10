package fhj.shelf.clientCommand;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.http.SendPOSTHttpRequest;

public class PostShelfClient extends  BaseClientCommand implements ClientCommand{
private final String path;
	
	private final Map<String, String> params;
	private final String method;
	

	public PostShelfClient(Map<String, String> params2) 
	{
	
		
		this.params = params2;
		path = "/shelfs";
		method = "POST";
	}



	@Override
	public Object execute() throws InterruptedException, ExecutionException, Exception
	{

		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL()).append("?").append(path);

		 
		
		return SendPOSTHttpRequest.sendPostRequest(params, builder.toString(), method);
	}
	
}
