package fhj.shelf.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class DeleteRequest {

	public static OutputStreamWriter writer;
	
	
	/**
	 * Method with the responsibility to send a HTTP request using POST method
	 * to the specified URL
	 * 
	 * @param requestURL the URL of the remote server
	 * @param params
	 * @param path
	 * @return An HttPURLConnectionobeject
	 * @throws IOException
	 */
	public static HttpURLConnection sendDeleteRequest(String requestURL,
			Map<String, String> params, String path) throws IOException {

		URL url = new URL(requestURL);
		OutputStream output = null;
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		// true indicates the server returns response
		connection.setDoInput(true);

		// true indicates POST request
		connection.setDoOutput(true);

		connection.setRequestMethod("POST");

		

		writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(path);
		writer.flush();
		writer.close();

		return connection;

	}

}
