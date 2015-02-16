package fhj.shelf.clientCommand;

public abstract class  BaseClientCommand implements ClientCommand {

	private final String requestURL;
	private final  int PORT = 8081;
	private final  String HOST = "localhost";
	
	
	/**
	 * This Super Class defines the request url and provides to its sub classes
	 * http://Localhost/8081
	 */
	public BaseClientCommand() {
		requestURL =  "http://" + HOST+":"+PORT;
	}
	
	/**
	 * Getters
	 * @return
	 * String requestURL
	 */
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
