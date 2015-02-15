package fhj.shelf.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;







import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import fhj.shelf.commandsFactory.GetUserGUI;
import fhj.shelf.factoriesWindows.GetUserCommandFactory;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;

/**
 * 
 * Class whose instance gives a User Interface Representation of the domain
 * Class {@code GetOneUser()}
 *
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
@SuppressWarnings("serial")
public class SearchUser extends JFrame implements GetUserGUI {

	public static class Factory implements GetUserCommandFactory {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param userRepo
		 *            is an instance of UserRepository
		 * @param shelfRepo
		 *            is an instance of ShelfRepository
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of SaveUser
		 */
		
		@Override
		public GetUserGUI newInstance( Map<String, CommandFactory> mapCommands) {
			return new SearchUser(mapCommands);
		}
	}
	
	
	
	
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
	private static JButton jbPatch;
	Map<String, CommandFactory> mapCommands;
	private String username;
	private String password;
	/**
	 * Constructor
	 * 
	 * @param mapCommands
	 */
	public SearchUser( Map<String, CommandFactory> mapCommands) {
		this.mapCommands = mapCommands;
      
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
	
		@Override
		public void actionPerformed(ActionEvent arg0) {

			Map<String, String> params = new HashMap<String, String>();

			params.put("username", jtfNome.getText());

			
			class EventHandling extends SwingWorker<Map<String, String>, Void> {
				private final Logger logger = LoggerFactory.getLogger(EventHandling.class);
			   

				@Override
				protected Map<String, String> doInBackground() throws Exception {
			    
					CommandGetFactoryWithParameters getUser = (CommandGetFactoryWithParameters) mapCommands.get("getUser");
					return getUser.newInstance(params).execute();
				}

				@Override
				protected void done() {

					try {

						jtfPassword.setText((get()).get("password"));
						jtfFullname.setText(get().get("fullname"));
						jtfEmail.setText(get().get("email"));

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
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
			new EventHandling().execute();

		}
	}

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
