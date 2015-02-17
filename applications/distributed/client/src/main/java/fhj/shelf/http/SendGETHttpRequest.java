package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import fhj.shelf.exceptions.InvalidFormatExceptions;
import fhj.shelf.exceptions.ReaderMessageException;


public class SendGETHttpRequest {

	private SendGETHttpRequest() {
		
	}
	
	
	public static Map<String, String> sendGetRequest(String path)
			throws InterruptedException, ExecutionException, Exception {

		HttpURLConnection connection = GetRequest.sendGetRequest(path);

		return readResponse(connection);

	}

	/**
	 * Returns only one line from the server's response.
	 *
	 * @return a String of the server's response
	 * @throws Exception
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static Map<String, String> readResponse(HttpURLConnection connection)
			throws InterruptedException, ExecutionException, Exception {
		
		
		StringBuilder urlParameters = null;
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			urlParameters = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				urlParameters.append(line + "\n");
			}
			System.out.println("responseClient " + urlParameters);
			reader.close();
			// return response = connection.getResponseMessage() + " " + response;
		} catch (Exception e) {
			Logger.getLogger(SendGETHttpRequest.class.getName()).log(Level.WARNING, "Exception Occured : on SendGETHttpRequest ", e);
			throw new ReaderMessageException( e.getClass().getName()+
					"SendGETHttpRequest");
		}
		
		return parsingValueResponse(urlParameters.toString());
	}

	public static Map<String, String> parsingValueResponse(String response)
			throws InterruptedException, ExecutionException, Exception {
		Map<String, String> map = new TreeMap<String, String>();

		int beginIndex = (response.indexOf('['));
		int endIndex = (response.lastIndexOf(']') + 1);
		String resp = (response.substring(beginIndex, endIndex));

		StringTokenizer multiTokenizer = new StringTokenizer(resp, ",{} []");

		
		while (multiTokenizer.hasMoreTokens()) {

			String actualElement = multiTokenizer.nextToken();

			StringTokenizer et = new StringTokenizer(actualElement, ":");

			if (et.countTokens() != 2) {
				throw new InvalidFormatExceptions(" SendGetHttpRequest in parsingValueResponse() method");
			}

			String key = et.nextToken();
			String key1 = key.substring(1, key.lastIndexOf('"'));
			String value = et.nextToken();
			String value1 = value.substring(1, value.lastIndexOf('"'));

			map.put(key1, value1);
		}

		System.out.println(map);
		return map;
	}

}
