package fhj.shelf.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostRequest {

	public static OutputStreamWriter writer;

	public PostRequest() {
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
	public static HttpURLConnection sendPostRequest(String requestURL,
			Map<String, String> params, String path) throws IOException {
		
		OutputStream output = null;
		URL url = null;
		HttpURLConnection connection = null;
		
		
		try{
			url = new URL(requestURL);
		} catch (MalformedURLException ex) {
			Logger.getLogger(PostRequest.class.getName()).log(Level.SEVERE, null, ex);
		}


		
		try{
		connection = (HttpURLConnection) url.openConnection();
		} catch (IOException ex) {
            Logger.getLogger(PostRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		
		// true indicates the server returns response
		connection.setDoInput(true);

		// true indicates POST request
		connection.setDoOutput(true);

		try{
		connection.setRequestMethod("POST");
		} catch (ProtocolException ex) {
            Logger.getLogger(PostRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

		
		// creates the params string, encode them using URLEncoder
		StringBuffer urlParameters = new StringBuffer();
		
		Iterator<String> paramIterator = params.keySet().iterator();
		while (paramIterator.hasNext()) {
			String key = paramIterator.next();
			String value = params.get(key);

			urlParameters.append(URLEncoder.encode(key, "UTF-8"));
			urlParameters.append("=").append(URLEncoder.encode(value, "UTF-8"));
			urlParameters.append("&");
		}

		
		writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(path + urlParameters.toString().substring(0,
						urlParameters.toString().length() - 1));
		writer.flush();
		writer.close();

		return connection;

	}

}