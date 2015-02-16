package fhj.shelf.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import fhj.shelf.exceptions.ExceptionsClientServer;
import fhj.shelf.exceptions.ExecutionCommunicationException;
import fhj.shelf.exceptions.ExecutionUrlClientServer;

public class EditRequest {

	public static OutputStreamWriter writer;

	public static HttpURLConnection sendEditRequest(Map<String, String> params,
			String path) throws ExceptionsClientServer {

		URL url = null;
		HttpURLConnection connection = null;

		try {
			url = new URL(path);
		} catch (MalformedURLException ex) {
			throw new ExecutionUrlClientServer("malformed url execptions");

		}

		try {
			connection = (HttpURLConnection) url.openConnection();

			/**
			 * true indicates the server returns response
			 */
			connection.setDoInput(true);

			/**
			 * true indicates POST request
			 */
			connection.setDoOutput(true);

			connection.setRequestMethod("POST");
		} catch (IOException ex) {
			throw new ExecutionCommunicationException(
					"openConnection exceptions post request");

		}

		try {
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(path);
			writer.flush();
			writer.close();

			return connection;
		} catch (IOException e) {
			throw new ExecutionCommunicationException(
					"outputStrem exceptions post request");
		}

	}

}
