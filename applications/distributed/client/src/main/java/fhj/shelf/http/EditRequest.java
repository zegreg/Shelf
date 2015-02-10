package fhj.shelf.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class EditRequest {

	public static OutputStreamWriter writer;
	public static HttpURLConnection sendEditRequest(Map<String, String> params, String path) throws IOException {

		URL url = new URL(path);
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
