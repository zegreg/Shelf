package fhj.shelf.clientCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;

import fhj.shelf.commands.UICommand;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.startUI.StartUpFrame;

public class StartClient {

	static Map<String, CommandFactory> userCommands;
	static Map<String, CommandFactory> shelfCommands;

	public static Map<String, CommandFactory> registerUserCommands() {
		Map<String, CommandFactory> userCommands = new HashMap<String, CommandFactory>();
		userCommands.put("postUser", new PostUserClient.Factory());
		userCommands.put("getUser", new GetUserClient.Factory());
		userCommands.put("getUsers", new GetUsersClient.Factory());
		userCommands.put("patchUser", new PatchUserClient.Factory());
		
		return userCommands;
	}

	public static Map<String, CommandFactory> registerShelfCommands() {
		Map<String, CommandFactory> shelfCommands = new HashMap<String, CommandFactory>();
		shelfCommands.put("postShelf", new PostShelfClient.Factory());
		shelfCommands.put("getShelf", new GetShelfClient.Factory());
		shelfCommands.put("getShelfs", new GetShelfsClient.Factory());
		shelfCommands.put("postElement", new PostShelfElementClient.Factory());
		// shelfsCommands.put("postCollectionElement", new
		// PostShelfCollectionClient.Factory());

		return shelfCommands;
	}

	public static void main(String[] args) {

		/*
		 * The invokeLater() method does not wait for the block of code, this
		 * allows the thread that posted the request to move on to other
		 * activities. Thread[AWT-EventQueue-0,6,main]
		 */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				userCommands = registerUserCommands();
				shelfCommands = registerShelfCommands();
			    try {
					new StartUpFrame(userCommands,shelfCommands);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}