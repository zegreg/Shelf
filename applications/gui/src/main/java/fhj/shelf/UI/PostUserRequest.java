package fhj.shelf.UI;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class PostUserRequest{
	
	public static OutputStreamWriter writer;

	public PostUserRequest() {
	}
	
	

	public static HttpURLConnection sendPostRequest(String requestURL, Map<String, String> params, String path)
	            throws IOException {
	
	URL url = new URL(requestURL);
	OutputStream output = null;
	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	// true indicates the server returns response
	connection.setDoInput(true);
	
	// true indicates POST request
	connection.setDoOutput(true);

	connection.setRequestMethod("POST");

	StringBuffer requestParams = new StringBuffer();
	// creates the params string, encode them using URLEncoder
	Iterator<String> paramIterator = params.keySet().iterator();
	while (paramIterator.hasNext()) {
		String key = paramIterator.next();
		String value = params.get(key);
		
		requestParams.append(URLEncoder.encode(key, "UTF-8"));
		requestParams.append("=").append(
				URLEncoder.encode(value, "UTF-8"));
		requestParams.append("&");
	}
	
	writer = new OutputStreamWriter(connection.getOutputStream());
	writer.write(path+requestParams.toString().substring(0, requestParams.toString().length()-1));
	writer.flush();
	writer.close();
	
	
	
	return connection;
	
	 }
	
}