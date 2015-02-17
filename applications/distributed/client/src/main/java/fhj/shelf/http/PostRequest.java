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


import java.util.logging.Level;
import java.util.logging.Logger;

import fhj.shelf.clientcommand.GetUsersClient;
import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;
import fhj.shelf.exceptions.ExecutionUrlClientServer;
import fhj.shelf.exceptions.ReaderMessageException;


public class PostRequest {

	public static OutputStreamWriter writer;

	private   PostRequest() {
	}

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
	public static HttpURLConnection sendPostRequest(Map<String, String> params, String path, String method) throws  
	ExceptionsClientServer {
	
		URL url = null;
		HttpURLConnection connection = null;
		
		
		try{
			url = new URL(path);
		} catch (MalformedURLException ex) {
			Logger.getLogger(PostRequest.class.getName()).log(Level.WARNING, "MalformedURLException Occured : on PostRequest ", ex);
			throw new ExecutionUrlClientServer(ex.getClass().getName()+" PostRequest");
		
		}
		
		

		
		try{
			connection = (HttpURLConnection) url.openConnection();

			/**
			 * true indicates the server returns response
			 */
			connection.setDoInput(true);

			/**
			 * true indicates POST request
			 */
			connection.setDoOutput(true);

			connection.setRequestMethod(method);
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		} 
		catch (Exception ex) {
			Logger.getLogger(PostRequest.class.getName()).log(Level.WARNING, "Connection Exception Occured : on PostRequest ", ex);
			throw new ReaderMessageException(ex.getClass().getName()+"PostRequest");

		}

		
		
		
		
		try {
			
			/**
			 * creates the params string, encode them using URLEncoder
			 */
			StringBuffer urlParameters = new StringBuffer();
			
			Iterator<String> paramIterator = params.keySet().iterator();
			while (paramIterator.hasNext()) {
				String key = paramIterator.next();
				String value = params.get(key);

				urlParameters.append(URLEncoder.encode(key, "UTF-8"));
				urlParameters.append("=").append(URLEncoder.encode(value, "UTF-8"));
				urlParameters.append("&");
			}
			

			
		OutputStream outputStream =connection.getOutputStream();

		writer = new OutputStreamWriter(outputStream);
		writer.write(urlParameters.toString().substring(0,
						urlParameters.toString().length() - 1));
		writer.close();

		} catch (IOException e) {
			Logger.getLogger(PostRequest.class.getName()).log(Level.WARNING, "IOException Occured : on PostRequest ", e);
			throw new ReaderMessageException(e.getClass().getName()+"PostRequest");
		}
		
		return connection;

	}

}