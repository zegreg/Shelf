package fhj.shelf.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;
import fhj.shelf.exceptions.ExecutionUrlClientServer;

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
			Map<String, String> params, String path) throws ExceptionsClientServer {

		OutputStream output = null;
		URL url = null;
		HttpURLConnection connection = null;
		
		
		try{
			url = new URL(path);
		} catch (MalformedURLException ex) {
			throw new ExecutionUrlClientServer("malformed url execptions");
		
		}
		
		
		
		
		try{
	    connection = (HttpURLConnection) url.openConnection();
		
		// true indicates the server returns response
		connection.setDoInput(true);

		// true indicates POST request
		connection.setDoOutput(true);

		connection.setRequestMethod("POST");

		} 
		catch (IOException ex) {
			throw new ExecutionCommunicationException("openConnection exceptions delte request");

		}

		
		try {
		writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(path);
		writer.flush();
		writer.close();
		
		
		} catch (IOException e) {
			throw new ExecutionCommunicationException("outputStrem exceptions delete request");
		}
		

		return connection;

	}

}
