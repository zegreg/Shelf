package fhj.shelf.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.http.ActionCommandFactory;
import fhj.shelf.http.GetUserRequest;
import fhj.shelf.repos.UserRepository;

/**
 * 
 * Class whose instance gives a User Interface Representation of the domain
 * Class {@code GetOneUser()}
 *
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
@SuppressWarnings("serial")
public class SearchUser extends JFrame {

	private static final int JBSBOUNDS_HEIGHT = 23;
	private static final int JBSBOUNDS_WIDTH = 73;
	private static final int JBSBOUNDS_Y = 147;
	private static final int JBSBOUNDS_X = 131;
	private static final int JTFNBOUNDS_HEIGHT = 20;
	private static final int JTFNBOUNDS_WIDTH = 166;
	private static final int JTFNBOUNDS_Y = 23;
	private static final int JTFNBOUNDS_X = 84;
	private static final int JTFEBOUNDS_HEIGHT = 20;
	private static final int JTFEBOUNDS_WIDTH = 166;
	private static final int JTFEBOUNDS_Y = 116;
	private static final int JTFEBOUNDS_X = 84;
	private static final int JTFBOUNDS_HEIGHT = 20;
	private static final int JTFBOUNDS_WIDTH = 166;
	private static final int JTFBOUNDS_Y = 85;
	private static final int JTFBOUNDS_X = 84;
	private static final int JTFPBOUNDS_HEIGHT = 20;
	private static final int JTFPBOUNDS_WIDTH = 166;
	private static final int JTFPBOUNDS_Y = 54;
	private static final int JTFPBOUNDS_X = 84;
	private static final int JLVD_HEIGHT = 10;
	private static final int JLVD_WIDTH = 325;
	private static final int JLVBOUNDS_HEIGHT = 10;
	private static final int JLVBOUNDS_WIDTH = 325;
	private static final int JLVBOUNDS_Y = 105;
	private static final int JLVBOUNDS_X = 5;
	private static final int JLED_HEIGHT = 20;
	private static final int JLED_WIDTH = 65;
	private static final int JLEBOUNDS_HEIGHT = 20;
	private static final int JLEBOUNDS_WIDTH = 65;
	private static final int JLEBOUNDS_Y = 116;
	private static final int JLEBOUNDS_X = 15;
	private static final int JLFD_HEIGHT = 20;
	private static final int JLFD_WIDTH = 65;
	private static final int JLFBOUNDS_HEIGHT = 20;
	private static final int JLFBOUNDS_WIDTH = 65;
	private static final int JLFBOUNDS_Y = 85;
	private static final int JLFBOUNDS_X = 14;
	private static final int JLPD_HEIGHT = 20;
	private static final int JLPD_WIDTH = 65;
	private static final int JLPBOUNDS_HEIGHT = 20;
	private static final int JLPBOUNDS_WIDTH = 65;
	private static final int JLPBOUNDS_Y = 54;
	private static final int JLPBOUNDS_X = 14;
	private static final int JLND_HEIGHT = 20;
	private static final int JLND_WIDTH = 65;
	private static final int JLNBOUNDS_HEIGHT = 20;
	private static final int JLNBOUNDS_WIDTH = 65;
	private static final int JLNBOUNDS_Y = 23;
	private static final int JLNBOUNDS_X = 14;
	private static final int LOCATION_Y = 100;
	private static final int LOCATION_X = 100;
	private static final int SIZE_HEIGHT = 234;
	private static final int SIZE_WIDTH = 350;
	private static final int JTFE_COLUMNS = 20;
	private static final int JTFF_COLUMNS = 20;
	private static final int JTFP_COLUMNS = 20;
	private static final int JTFN_COLUMNS = 20;
	/**
	 * Attributes
	 */
	private static JLabel jlNome;
	private static JTextField jtfNome;
	private static JLabel jlPassword;
	private static JTextField jtfPassword;
	private static JLabel jlFullname;
	private static JTextField jtfFullname;
	private static JLabel jlEmail;
	private static JTextField jtfEmail;
	private static JButton jbSearch;
	private static JLabel jlVazia;
	private UserRepository repository;
	private static JButton jbPatch;

	/**
	 * Constructor
	 * 
	 * @param repository
	 */
	public SearchUser(UserRepository repository) {
		this.repository = repository;

		jlNome = new JLabel("Nome: ");
		jtfNome = new JTextField(JTFN_COLUMNS);
		jlPassword = new JLabel("Password: ");
		jtfPassword = new JTextField(JTFP_COLUMNS);
		jlFullname = new JLabel("Fullname: ");
		jtfFullname = new JTextField(JTFF_COLUMNS);
		jlEmail = new JLabel("E-mail: ");
		jtfEmail = new JTextField(JTFE_COLUMNS);
		jbSearch = new JButton("Search");
		jlVazia = new JLabel("");

		// Sets window properties
		setTitle("Procura por nome");
		setSize(SIZE_WIDTH, SIZE_HEIGHT);
		setLocation(LOCATION_X, LOCATION_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		jlNome.setBounds(JLNBOUNDS_X, JLNBOUNDS_Y, JLNBOUNDS_WIDTH, JLNBOUNDS_HEIGHT);

		// Set the size of the labels
		jlNome.setPreferredSize(new Dimension(JLND_WIDTH, JLND_HEIGHT));
		jlPassword.setBounds(JLPBOUNDS_X, JLPBOUNDS_Y, JLPBOUNDS_WIDTH, JLPBOUNDS_HEIGHT);
		jlPassword.setPreferredSize(new Dimension(JLPD_WIDTH, JLPD_HEIGHT));
		jlFullname.setBounds(JLFBOUNDS_X, JLFBOUNDS_Y, JLFBOUNDS_WIDTH, JLFBOUNDS_HEIGHT);
		jlFullname.setPreferredSize(new Dimension(JLFD_WIDTH, JLFD_HEIGHT));
		jlEmail.setBounds(JLEBOUNDS_X, JLEBOUNDS_Y, JLEBOUNDS_WIDTH, JLEBOUNDS_HEIGHT);
		jlEmail.setPreferredSize(new Dimension(JLED_WIDTH, JLED_HEIGHT));
		jlVazia.setBounds(JLVBOUNDS_X, JLVBOUNDS_Y, JLVBOUNDS_WIDTH, JLVBOUNDS_HEIGHT);
		jlVazia.setPreferredSize(new Dimension(JLVD_WIDTH, JLVD_HEIGHT));

		/*
		 * Sets the text boxes as non-editable,          since it is not
		 * necessary to enter data in these fields
		 */
		jtfPassword.setBounds(JTFPBOUNDS_X, JTFPBOUNDS_Y, JTFPBOUNDS_WIDTH, JTFPBOUNDS_HEIGHT);
		jtfPassword.setEditable(false);
		jtfFullname.setBounds(JTFBOUNDS_X, JTFBOUNDS_Y, JTFBOUNDS_WIDTH, JTFBOUNDS_HEIGHT);
		jtfFullname.setEditable(false);
		jtfEmail.setBounds(JTFEBOUNDS_X, JTFEBOUNDS_Y, JTFEBOUNDS_WIDTH, JTFEBOUNDS_HEIGHT);
		jtfEmail.setEditable(false);
		getContentPane().setLayout(null);

		// Adds components to the window
		getContentPane().add(jlNome);
		jtfNome.setBounds(JTFNBOUNDS_X, JTFNBOUNDS_Y, JTFNBOUNDS_WIDTH, JTFNBOUNDS_HEIGHT);
		getContentPane().add(jtfNome);
		getContentPane().add(jlPassword);
		getContentPane().add(jtfPassword);
		getContentPane().add(jlFullname);
		getContentPane().add(jtfFullname);
		getContentPane().add(jlEmail);
		getContentPane().add(jtfEmail);
		getContentPane().add(jlVazia);
		jbSearch.setBounds(JBSBOUNDS_X, JBSBOUNDS_Y, JBSBOUNDS_WIDTH, JBSBOUNDS_HEIGHT);
		getContentPane().add(jbSearch);

		/*
		 * ActionListener listener registration in the button Search .         
		 * When an event is generated by this component, is          created an
		 * instance of the inner class EventSearch()
		 */
		jbSearch.addActionListener(new EventSearch());

	}
	/**
	 * Inner Class that implements ActionListener Interface, and invoke
	 * actionPerformed method for Search Button. The action is made in an
	 * Background Thread, by run SwingWorker framework.
	 */
	private class EventSearch implements ActionListener {
		private final int PORT = 8081;
		private final String HOST = "localhost";
		
		
		
		
		@Override
		public void actionPerformed(ActionEvent arg0) {

			Map<String, String> params = new HashMap<String, String>();

			params.put("username", jtfNome.getText());

			
			class EventHandling extends SwingWorker<Object, Void> {
				private final Logger logger = LoggerFactory.getLogger(EventHandling.class);
				String requestURL = "http://" + HOST+":"+PORT;
				
				
//				
////				String path ="GET /users/" +input+" accept=application/json";
//				String path ="GET /users/" +input;
				@Override
				protected Object doInBackground() throws Exception {
//				HttpURLConnection connection = GetUserRequest.sendGetRequest(requestURL, path);
//				
//				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//				String response = reader.readLine();
//				reader.close();
//				
//				
////				return response = connection.getResponseMessage() + " " + response;
//				return response;
				
				return ActionCommandFactory.createActionCommand("SearchUserHttp", params, requestURL, repository);
				
				}

				@Override
				protected void done() {

					try {

												
						jtfPassword.setText(String.valueOf(( get())));
						jtfFullname.setText(String.valueOf(( get())));
						jtfEmail.setText(String.valueOf(( get())));

					} catch (HeadlessException e) {
						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					} catch (InterruptedException e) {
						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					} catch (ExecutionException e) {
						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					} catch (NullPointerException e) {
						JOptionPane.showMessageDialog(null,
								"No user with this name was found!" + e);
						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
						cleanFields();
					}

				}
			}
			new EventHandling().execute();

		}
	}
	
		

//	/**
//	 * Inner Class that implements ActionListener Interface, and invoke
//	 * actionPerformed method for Search Button. The action is made in an
//	 * Background Thread, by run SwingWorker framework.
//	 */
//	private class EventSearch implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//
//			class EventHandling extends SwingWorker<User, Object> {
//
//				@Override
//				protected User doInBackground() throws Exception {
//
//					return (User) new GetOneUser(repository,
//							jtfNome.getText()).call();
//				}
//
//				@Override
//				protected void done() {
//
//					try {
//
//						jtfPassword.setText(String.valueOf(((User) get())
//								.getLoginPassword()));
//						jtfFullname.setText(String.valueOf(((User) get())
//								.getFullName()));
//						jtfEmail.setText(String.valueOf(((User) get())
//								.getEmail()));
//
//					} catch (HeadlessException e) {
//						e.printStackTrace();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					} catch (ExecutionException e) {
//						e.printStackTrace();
//					} catch (NullPointerException e) {
//						JOptionPane.showMessageDialog(null,
//								"No user with this name was found!" + e);
//						e.printStackTrace();
//						cleanFields();
//					}
//
//				}
//			}
//			new EventHandling().execute();
//
//		}
//	}

	/**
	 * Method to clean all fields in JTextField
	 */
	private void cleanFields() {
		jtfNome.setText("");
		jtfPassword.setText("");
		jtfFullname.setText("");
		jtfEmail.setText("");
	}
}
