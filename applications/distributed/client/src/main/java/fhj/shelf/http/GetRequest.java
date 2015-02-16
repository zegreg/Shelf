package fhj.shelf.http;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;
import fhj.shelf.exceptions.ExecutionUrlClientServer;

public class GetRequest {

	
	private static InputStreamReader reader;


	public GetRequest() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
     * Makes an HTTP request using GET method to the specified URL.
     *
     * @param requestURL
     *            the URL of the remote server
     * @return An HttpURLConnection object
     * @throws IOException
     *             thrown if any I/O error occurred
     */
	public static HttpURLConnection sendGetRequest( String path) throws  
			ExceptionsClientServer {

		URL url = null;
		
		
		try{
			url = new URL(path);
		} catch (MalformedURLException ex) {
			throw new ExecutionUrlClientServer("malformed url execptions");
		
		}
		
		
		
		
		try{
		/**
		 * create the HttpURLConnection
		 */
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			
			connection.setUseCaches(true );
			connection.setDoInput(true); // true if we want to read server's response
			connection.setDoOutput(false); // false indicates this is a GET request
			connection.setRequestMethod("GET");
			connection.setRequestProperty("accept","application/json");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
			


			return connection;
			
		} 
		catch (IOException ex) {
			throw new ExecutionCommunicationException("openConnection exceptions get request");

		}
	 }
	
}
