package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

public class SendEDITHttpRequest {
	
		
		
		
		public static String sendEditRequest( Map<String, String> params, String path)  throws IOException{
			

			HttpURLConnection connection = EditRequest.sendEditRequest(params, path);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String response = reader.readLine();
			reader.close();

			response = connection.getResponseMessage() +" " +response;
			
			
			
			return response; 
			
			
			
			}
}
