package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

public class SendPOSTHttpRequest{

	
	public  static String sendPostRequest(Map<String, String> params, String path, String method)  throws IOException{


		HttpURLConnection connection = PostRequest.sendPostRequest(params, path, method);

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String response = reader.readLine();
		
		reader.close();

		response = connection.getResponseMessage() +" " +response;


		return response; 


	
	}
}
