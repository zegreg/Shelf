package fhj.shelf.actionWindow;

import java.awt.HeadlessException;
import java.io.IOException;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;




public class HandlerPost extends AbstractHandlerPost {
	
	
    private  final static Logger logger = LoggerFactory.getLogger(HandlerPost.class);

	
	public HandlerPost(Map<String, String> params,
			Map<String, CommandFactory> shelfCommands, String command) {
		super(params, shelfCommands, command);
		
	}


	public static void PostUserInformation(Map<String, String> params, Map<String, CommandFactory> shelfCommands, 
			String command) throws IOException {

		SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

			@Override
			protected String doInBackground() throws Exception {
				CommandPostFactoryWithParameters postElement;

					postElement = (CommandPostFactoryWithParameters) shelfCommands
							.get(command);
					return postElement.newInstance(params).execute();


			}

			@Override
			protected void done() {

				try {
					JOptionPane.showMessageDialog(null,
							"Data were successfully saved!" + get());
				} catch (HeadlessException e) {

					logger.error(
							"FailedCreateActivityFunction Exception Occured : ",
							e);
				} catch (InterruptedException e) {

					logger.error(
							"FailedCreateActivityFunction Exception Occured : ",
							e);
				} catch (ExecutionException e) {

					logger.error(
							"FailedCreateActivityFunction Exception Occured : ",
							e);
				}

				/* Invokes the low method implemented */
//				cleanFields();
//				dispose();
			}

		};

		worker.execute();
		
	}






}

