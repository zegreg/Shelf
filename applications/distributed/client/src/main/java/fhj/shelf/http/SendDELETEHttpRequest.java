package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;

public class SendDELETEHttpRequest {

	private final String requestURL;
	private final int PORT = 8081;
	private final String HOST = "localhost";

	public SendDELETEHttpRequest() {
		requestURL = "http://" + HOST + ":" + PORT;
	}

	public String sendDeleteRequest(Map<String, String> params, String path)
			throws ExceptionsClientServer {
		String response;
		HttpURLConnection connection = DeleteRequest.sendDeleteRequest(
				requestURL, params, path);

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
