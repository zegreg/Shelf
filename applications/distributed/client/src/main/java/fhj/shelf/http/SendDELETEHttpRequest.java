package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

public class SendDELETEHttpRequest {

	
	
	
	public static String sendDeleteRequest(String requestURL, Map<String, String> params, String path)  throws IOException{
		
		
//		String path ="POST /users loginName=Lima&loginPassword=SLB&";
		
		HttpURLConnection connection = DeleteRequest.sendDeleteRequest(requestURL,params, path);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String response = reader.readLine();
		reader.close();

		response = connection.getResponseMessage() +" " +response;
		
		
		
		return response; 
		
		
		
		}
}
