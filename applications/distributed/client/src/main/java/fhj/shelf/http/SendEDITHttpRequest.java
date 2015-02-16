package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;

public class SendEDITHttpRequest {

	public static String sendEditRequest(Map<String, String> params, String path)
			throws ExceptionsClientServer {

		HttpURLConnection connection = EditRequest
				.sendEditRequest(params, path);
		String response = null;
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
