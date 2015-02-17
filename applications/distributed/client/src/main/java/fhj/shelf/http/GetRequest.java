package fhj.shelf.http;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;
import fhj.shelf.exceptions.ExecutionUrlClientServer;
import fhj.shelf.exceptions.ReaderMessageException;

public class GetRequest {

	



	private GetRequest() {
		
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
			Logger.getLogger(GetRequest.class.getName()).log(Level.WARNING, "MalformedURLException Occured : on GetRequest ", ex);
			throw new ExecutionUrlClientServer(ex.getClass().getName()+" GetRequest");
		
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
		catch (Exception ex) {
			Logger.getLogger(GetRequest.class.getName()).log(Level.WARNING, "Connection Exception Occured : on GetRequest ", ex);
			throw new ReaderMessageException(ex.getClass().getName()+"GetRequest");

		}
	 }
	
}
