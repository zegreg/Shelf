package fhj.shelf.clientCommand;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.http.SendPOSTHttpRequest;

public class PostShelfElementClient extends BaseClientCommand implements ClientCommand{
	
	private final Map<String, String> params;
	private final String method;
	private final String path;
	private final String id;
	private final String typeElement;
	
	public PostShelfElementClient(String type, String id, Map<String, String> params) {

		this.id = id;
		this.params = params;
		this.typeElement = type;
		path = "/shelfs/";
		method = "POST";
	}
	
	
	@Override
	public Object execute() throws InterruptedException, ExecutionException,
			Exception {
		
		StringBuilder builder = new StringBuilder();

		builder.append(super.getRequestURL())
		.append("?")
		.append(path)
		.append(id)
		.append("/")
		.append("elements/")
		.append(typeElement);


		
		return SendPOSTHttpRequest.sendPostRequest(params, builder.toString(), method);
	}

}
