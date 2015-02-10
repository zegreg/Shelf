package fhj.shelf.clientCommand;

public abstract class  BaseClientCommand implements ClientCommand {

	private final String requestURL;
	private final  int PORT = 8081;
	private final  String HOST = "localhost";
	
	
	public BaseClientCommand() {
		requestURL =  "http://" + HOST+":"+PORT;
	}
	
	//Setters and Getters
	public String getRequestURL() {
		return requestURL;
	}
	
	public String getHOST() {
		return HOST;
	}
	public int getPORT() {
		return PORT;
	}
	

	
	
	
	
	
	
}
