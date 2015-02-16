package fhj.shelf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fhj.shelf.commands.DeleteShelf;
import fhj.shelf.commands.FindAllShelfs;
import fhj.shelf.commands.FindAllUsers;
import fhj.shelf.commands.FindShelf;
import fhj.shelf.commands.FindUser;
import fhj.shelf.commands.ModifyUser;
import fhj.shelf.commands.NewShelf;
import fhj.shelf.commands.NewShelfCollectionElement;
import fhj.shelf.commands.NewShelfElement;
import fhj.shelf.commands.NewUser;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.startui.StartUpFrame;

public class ShelfGuiStandAloneApp {

	static Map<String, CommandFactory> userCommands;
	static Map<String, CommandFactory> shelfCommands;

	public static Map<String, CommandFactory> registerUserCommands() {
		Map<String, CommandFactory> userCommands = new HashMap<String, CommandFactory>();
		userCommands.put("postUser", new NewUser.Factory());
		userCommands.put("getUser", new FindUser.Factory());
		userCommands.put("getUsers", new FindAllUsers.Factory());
		userCommands.put("patchUser", new ModifyUser.Factory());

		return userCommands;
	}

	public static Map<String, CommandFactory> registerShelfCommands() {
		Map<String, CommandFactory> shelfCommands = new HashMap<String, CommandFactory>();
		shelfCommands.put("postShelf", new NewShelf.Factory());
		shelfCommands.put("getShelf", new FindShelf.Factory());
		shelfCommands.put("getShelfs", new FindAllShelfs.Factory());
		shelfCommands.put("postElement", new NewShelfElement.Factory());
		shelfCommands.put("postCollectionElement",
				new NewShelfCollectionElement.Factory());
		shelfCommands.put("deleteShelf", new DeleteShelf.Factory());

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
					new StartUpFrame(userCommands, shelfCommands, "admin",
							"admin");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
