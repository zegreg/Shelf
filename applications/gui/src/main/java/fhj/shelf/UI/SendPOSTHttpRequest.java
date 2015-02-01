package fhj.shelf.UI;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

public interface SendPOSTHttpRequest {

	public HttpURLConnection sendPostRequest(String requestURL, Map<String, String> params)  throws IOException;
	
	
	
}
