package fhj.shelf.UI;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetUserRequest {
	public static OutputStreamWriter writer;
	
	
	public GetUserRequest() {
		// TODO Auto-generated constructor stub
	}
	
	 public static HttpURLConnection sendGetRequest(String requestURL, String path)
	            throws IOException {
		 
		    URL url = new URL(requestURL);
			OutputStream output = null;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			
			connection.setUseCaches(false);
			connection.setDoInput(true); // true if we want to read server's response
			connection.setDoOutput(true); // false indicates this is a GET request
			connection.setRequestMethod("GET");

			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(path);
			writer.flush();
			writer.close();



			return connection;
	 }
	
}
