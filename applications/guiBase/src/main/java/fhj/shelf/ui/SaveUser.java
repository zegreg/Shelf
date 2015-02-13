package fhj.shelf.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;




import javax.swing.SwingWorker;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.repos.UserRepository;
import guiHandler.HandlerPost;

/**
 * 
 * Class whose instance gives a User Interface Representation of the domain
 * Class {@code CreateUser()}
 *
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */

@SuppressWarnings("serial")
public class SaveUser extends JFrame {

	private static final int JLVD_HEIGHT = 10;
	private static final int JLVD_WIDTH = 325;
	private static final int JLED_HEIGHT = 20;
	private static final int JLED_WIDTH = 65;
	private static final int JLFD_HEIGHT = 20;
	private static final int JLFD_WIDTH = 65;
	private static final int JLPD_HEIGHT = 20;
	private static final int JLPD_WIDTH = 65;
	private static final int JLND_HEIGHT = 20;
	private static final int JLND_WIDTH = 65;
	private static final int LOCATION_Y = 100;
	private static final int LOCATION_X = 100;
	private static final int SIZE_HEIGHT = 190;
	private static final int SIZE_WIDTH = 350;
	private static final int JTFE_COLUMNS = 20;
	private static final int JTFF_COLUMNS = 20;
	private static final int JTFP_COLUMNS = 20;
	private static final int JTFN_COLUMNS = 20;
	/**
	 * Attributes
	 */
	private static JTextField jtfName, jtfFullName, jtfPassword, jtfEmail;
	private static JLabel jlName, jlPassword, jlFullName, jlEmail, jlEmpty;
	private static JButton jbSave, jbDelete;
	private UserRepository repository;
    private Map<String, String> params;
    
	private static final Logger logger = LoggerFactory.getLogger(SaveUser.class);
	
	Map<String, CommandFactory> mapCommands;
	/**
	 * Constructor
	 * 
	 * @param repository
	 */
	public SaveUser(Map<String, CommandFactory> mapCommands) {
		this.mapCommands = mapCommands;
//		this.createNewUser =mapCommands;
//		this.params = new TreeMap<String, String>();
		
		
		jlName = new JLabel("Name");
		jtfName = new JTextField(JTFN_COLUMNS);
		jlPassword = new JLabel("Password");
		jtfPassword = new JTextField(JTFP_COLUMNS);
		jlFullName = new JLabel("FullName");
		jtfFullName = new JTextField(JTFF_COLUMNS);
		jlEmail = new JLabel("Email");
		jtfEmail = new JTextField(JTFE_COLUMNS);
		jbSave = new JButton("Save");
		jbDelete = new JButton("Delete");
		jlEmpty = new JLabel("");

		// Sets window properties
		setTitle("New User");
		setSize(SIZE_WIDTH, SIZE_HEIGHT);
		setLocation(LOCATION_X, LOCATION_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setVisible(true);

		// Set the size of the labels
		jlName.setPreferredSize(new Dimension(JLND_WIDTH, JLND_HEIGHT));
		jlPassword.setPreferredSize(new Dimension(JLPD_WIDTH, JLPD_HEIGHT));
		jlFullName.setPreferredSize(new Dimension(JLFD_WIDTH, JLFD_HEIGHT));
		jlEmail.setPreferredSize(new Dimension(JLED_WIDTH, JLED_HEIGHT));
		jlEmpty.setPreferredSize(new Dimension(JLVD_WIDTH, JLVD_HEIGHT));

		// Adds components to the window
		getContentPane().add(jlName);
		getContentPane().add(jtfName);
		getContentPane().add(jlPassword);
		getContentPane().add(jtfPassword);
		getContentPane().add(jlFullName);
		getContentPane().add(jtfFullName);
		getContentPane().add(jlEmail);
		getContentPane().add(jtfEmail);
		getContentPane().add(jlEmpty);
		getContentPane().add(jbSave);
		getContentPane().add(jbDelete);

		/*
		 * ActionListener listener registration in the button Save and button
		 * Delete.          When an event is generated by this component, is
		 *          created an instance of the inner class EventShelfSearch()
		 * and EventShelfDelete()
		 */
		jbSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {

					if (jtfName.getText().equals("")
							||jtfPassword.getText().equals("")
							||jtfFullName.getText().equals("")
							|| jtfEmail.getText().equals(""))
						JOptionPane.showMessageDialog(null,
								"All fields are required!");
					else {
						try {
							params.put("loginName", "Lima");
							params.put("loginPassword", "SLB");
							params.put("username", jtfName.getText());
							params.put("fullname", jtfFullName.getText());
							params.put("email", jtfEmail.getText());
							params.put("password", jtfPassword.getText());
							
							PostUserInformation(params);

						} catch (Exception e) {
							System.out.println("Unable to perform the operation. ");
							logger.error( "Unable to perform the operation. Exception Occured : " ,e );
						}

					}
				}
		});
		
		
		
		jbDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			deleteFields();
				
			}
		});
	}
	
	
	private void PostUserInformation(Map<String, String> params) throws IOException {

		SwingWorker<Object, Void> worker =new SwingWorker<Object, Void>() {


			@Override
			protected Object doInBackground() throws Exception {
		 CommandPostFactoryWithParameters postUser = (CommandPostFactoryWithParameters) mapCommands.get("postUser");
			return  postUser.newInstance(params).execute();
							
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

	
	
	public Map<String, String>  buildingMap(){

	
		System.out.println(params);
		return params;

	}
	
	
	
	
	
	// Getters
	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfFullName() {
		return jtfFullName;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public JTextField getJtfPassword() {
		return jtfPassword;
	}

	public UserRepository getRepository() {
		return repository;
	}

	
	
	
	/**
	 * Inner class that contains the code that is executed when it is press the
	 * button jbSave This Class implements ActionListener Interface, and invoke
	 * actionPerformed method. The action is made in an Background Thread, by
	 * run SwingWorker framework by execute a EventHandling() object.
	 * 
	 * @author Filipa Estiveira, Hugo Leal, José Oliveira
	 */
	

	/**
	 * Method to clean all fields in JTextField
	 */
	private void deleteFields() {
		jtfPassword.setText("");
		jlFullName.setText("");
		jtfName.setText("");
		jtfFullName.setText("");

	}
		
		

	

}
