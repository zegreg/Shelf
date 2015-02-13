package fhj.shelf.clientCommand;

import java.io.IOException;
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

	
	
	public static void main(String[] args) {
		

		CommandPostFactoryWithParameters postShelfClient = new PostShelfClient.Factory();

		CommandGetFactoryWithoutParameters getShelves = new GetShelvesClient.Factory();

		CommandGetFactoryWithParameters getShelf = new GetShelfClient.Factory();

		CommandPostFactoryWithParameters postUserClient = new PostUserClient.Factory();

		CommandGetFactoryWithoutParameters getUsers = new GetUsersClient.Factory();

		CommandGetFactoryWithParameters getUser = new GetUserClient.Factory();

        Map<String, CommandFactory> mapCommands= new TreeMap<String, CommandFactory>();
		
        
        mapCommands.put("postShelf",postShelfClient);
        mapCommands.put("getShelf",getShelf);
        mapCommands.put("getShelfs",getShelves);
        mapCommands.put("postUser", postUserClient);
        mapCommands.put("getUsers",getUsers);
        mapCommands.put("getUser", getUser);
        
        
		/*
		 * The invokeLater() method does not wait for the block of code, this
		 * allows the thread that posted the request to move on to other
		 * activities. Thread[AWT-EventQueue-0,6,main]
		 */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {

					StartUpFrame demo = new StartUpFrame(mapCommands );

					JFrame frame = new JFrame("Shelf");
					frame.pack();
					frame.setSize(450, 260);// sets the size of the window 450 pixels wide
											// and 260 pixels high
					frame.setVisible(true);// Makes visible window
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocation(450, 260);

					// add MenuBar to the frame
					frame.setJMenuBar(demo.createMenuBar());

					// add ImagePanel to the frame
					demo.createContentPane(frame, demo);
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		});
	}
	
	
	
}
