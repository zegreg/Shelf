package guiHandler;

import java.awt.HeadlessException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.factorys.CommandPostFactoryWithParameters;



public class HandlerPost {

	private final Logger logger = LoggerFactory.getLogger(HandlerPost.class);
	Map<String, String> params = new HashMap<String, String>();
	CommandPostFactoryWithParameters createNewShelf;


	public HandlerPost(Map<String, String> params, CommandPostFactoryWithParameters createNewShelf) throws IOException {
		this.params = params;
		this.createNewShelf = createNewShelf;
		PostUserInformation(params);
	}


	private void PostUserInformation(Map<String, String> params) throws IOException {

		SwingWorker<Object, Void> worker =new SwingWorker<Object, Void>() {


			@Override
			protected Object doInBackground() throws Exception {
			
			return createNewShelf.newInstance(params).execute();
							
			}
			@Override
			protected void done() {
				try {


					JOptionPane.showMessageDialog(null,"Established Connection." + get());
					
				} catch (HeadlessException e) {

					logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
				} catch (InterruptedException e) {

					logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
				} catch (ExecutionException e) {

					logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
				}
				//
				//				deleteTextField();
				//				dispose();
			}

		};
		worker.execute();

	}

	//private void deleteTextField() {
	//	jtfName.setText("");
	//	jtfPassword.setText("");
	//	jtfFullName.setText("");
	//	jtfEmail.setText("");
	//}






}

