package guiHandler;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;

public class HandlerGet {

	private final Logger logger = LoggerFactory.getLogger(HandlerGet.class);
	private static Map<String, String> map;
	
	public HandlerGet() {
		map = new TreeMap<String, String>();
	}
	
	public Map<String, String> PostUserInformation(Map<String, String> params, Map<String, CommandFactory> mapCommands, 
			String command) throws IOException {

		
		
		SwingWorker<Map<String, String>, Void> worker = new SwingWorker<Map<String, String>, Void>() {

			@Override
			protected Map<String, String> doInBackground() throws Exception {
		    
				CommandGetFactoryWithParameters getUser = (CommandGetFactoryWithParameters) mapCommands.get(command);
				map = getUser.newInstance(params).execute();
				
				return map;
			}
			
		};

		worker.execute();
		return map;
		
}
}
