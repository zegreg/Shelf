package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;

public class SendGETHttpRequest {

	
	public SendGETHttpRequest() {
		
	}
	
	
	public static  Map<String, String> sendGetRequest(String requestURL, Map<String, String> params, String path) throws InterruptedException, ExecutionException, Exception{
	
		
		
//    path ="GET /users/"+params.get("username")+" accept=application/json";
    
	HttpURLConnection connection = GetUserRequest.sendGetRequest(requestURL, path);
	
	return readResponse(connection);
	
	}
	
	
	 /**
     * Returns only one line from the server's response. 
     *
     * @return a String of the server's response
	 * @throws Exception 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
     */
    public static  Map<String, String> readResponse(HttpURLConnection connection ) throws InterruptedException, ExecutionException, Exception {
            
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    	
    	String response = reader.readLine();
    	
    	reader.close();

    	;
//    	return response = connection.getResponseMessage() + " " + response;
    	return parsingValueResponse(response);
    }
 
	
	
    public static Map<String, String> parsingValueResponse(String response)
    		throws InterruptedException, ExecutionException,
    		Exception {
    	
    	Map<String, String> map = new TreeMap<String, String>();
    	int beginIndex =(response.indexOf('['));
    	int endIndex =(response.lastIndexOf(']')+1);
    	String resp = (response.substring(beginIndex, endIndex));

    	StringTokenizer multiTokenizer = new StringTokenizer(resp, ",{} []");

    	while (multiTokenizer.hasMoreTokens())
    	{

    		String actualElement = multiTokenizer.nextToken();

    		StringTokenizer et = new StringTokenizer(actualElement, ":");


    		if ( et.countTokens() != 2 ) {
    			throw new Exception("Unexpeced format");
    		}

    		String key = et.nextToken();
    		String key1 = key.substring(1, key.lastIndexOf('"'));
    		String value = et.nextToken();
    		String value1 = value.substring(1, value.lastIndexOf('"'));
    		map.put(key1, value1);
    	}
		return map;
    }
	
	
	
	
}