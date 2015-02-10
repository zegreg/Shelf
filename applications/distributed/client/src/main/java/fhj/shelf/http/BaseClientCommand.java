package fhj.shelf.http;

public abstract class  BaseClientCommand implements ClientCommand {

	
	private final  int PORT;
	private final  String HOST;
	
	
	public BaseClientCommand() {
		PORT = 8081;
		HOST = "localhost";
	}
	
	public String executeURL(){
		return ;
		
		
	}
	
	
	
}
