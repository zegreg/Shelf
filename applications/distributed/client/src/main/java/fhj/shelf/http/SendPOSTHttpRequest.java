package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

public class SendPOSTHttpRequest{
	private final String requestURL; 
	private final  int PORT = 8081;
	private final  String HOST = "localhost";


	public SendPOSTHttpRequest() {
		requestURL =  "http://" + HOST+":"+PORT;
	}

	public  String sendPostRequest(Map<String, String> params, String path)  throws IOException{


		HttpURLConnection connection = PostRequest.sendPostRequest(requestURL,params, path);

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String response = reader.readLine();
		
		reader.close();

		response = connection.getResponseMessage() +" " +response;


		return response; 


	
	}
}
