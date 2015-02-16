package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;

public class SendPOSTHttpRequest {

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
			throw new ExecutionCommunicationException(
					"BufferedReader exceptions post request");
		}
		return response;

	}
}
