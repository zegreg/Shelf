package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.gson.Gson;

public class SendGETHttpRequest extends ActionCommand {

	
	public SendGETHttpRequest() {
		
	}
	
	
	public static  String sendGetRequest(String requestURL, Map<String, String> params) throws IOException{
	
		
		
    String path ="GET /users/"+params.get("username")+" accept=application/json";
    
	HttpURLConnection connection = GetUserRequest.sendGetRequest(requestURL, path);
	
	return readResponse(connection);
	
	}
	
	
	 /**
     * Returns only one line from the server's response. This method should be
     * used if the server returns only a single line of String.
     *
     * @return a String of the server's response
     * @throws IOException
     *             thrown if any I/O error occurred
     */
    public static  String readResponse(HttpURLConnection connection ) throws IOException {
            
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    	
    	String response = reader.readLine();
    	
    	reader.close();
    	
    	Gson gson = new Gson();
    	
    	
    	
//    	
//    	for (Entry<String, String> string : map.entrySet()) {
//			System.out.println(string + " ; ");
//		}
    	
    	
    	
    	
//    	return response = connection.getResponseMessage() + " " + response;
    	return response;
    }
 
	
	
	
	
	
	
	
}
