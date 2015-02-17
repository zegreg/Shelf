package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import fhj.shelf.clientcommand.GetUsersClient;
import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;
import fhj.shelf.exceptions.ReaderMessageException;

public class SendPOSTHttpRequest {

	
	
	private SendPOSTHttpRequest() {
	
	} 
	
	
	public static String sendPostRequest(Map<String, String> params,
			String path, String method) throws ExceptionsClientServer {

		String response = null;
		HttpURLConnection connection = PostRequest.sendPostRequest(params,
				path, method);

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			response = reader.readLine();

			reader.close();

			response = connection.getResponseMessage() + " " + response;

		} catch (IOException e) {
			Logger.getLogger(SendPOSTHttpRequest.class.getName()).log(Level.WARNING, "Exception Occured : on SendPOSTHttpRequest ", e);
			throw new ReaderMessageException( e.getClass().getName()+
					"SendPostHttpRequest");
		}
		return response;

	}
}
