package fhj.shelf.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import fhj.shelf.actionwindow.HandlerPost;
import fhj.shelf.actionwindow.PostActionWindow;
import fhj.shelf.actionwindowfactory.PostActionWindowFactory;
import fhj.shelf.exceptions.ExecutionExceptionReturn;
import fhj.shelf.exceptions.PostHandlerExceptions;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;

import java.awt.SystemColor;


@SuppressWarnings("serial")
public class CDCollection extends JFrame implements PostActionWindow {

	/**
	 * 
	 * Class that a single instance of UserRepositorySwing class. Implements
	 * PostActionWindowFactory and returns a PostActionWindow
	 *
	 */
	public static class Factory implements PostActionWindowFactory{

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * 
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of CDCollection
		 */
		
		@Override
		public PostActionWindow newInstance(String username, String password, Map<String, CommandFactory> mapCommands) {
			return new CDCollection(username, password,mapCommands);
		}
	}
	
	
	
	
	
	/**
	 * Attributes
	 */

	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfTitle;
	private JComboBox<Object> comboBox;
	private final JButton btnAddCDCollection;
	private final JButton btnDelete;

	private Map<String, CommandFactory> shelfCommands;
	private String username;
	private String password;

	/**
	 * Constructor
	 * 
	 * @param shelfRepository
	 * @param elementsRepository
	 */
	public CDCollection(String username, String password,Map<String, CommandFactory> shelfCommands) {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		this.username = username;
		this.password = password;
this.shelfCommands = shelfCommands;

		this.btnAddCDCollection = new JButton("AddCDCollection");
		this.btnDelete = new JButton("Delete");
		this.comboBox = new JComboBox<Object>();
		this.jtfTitle = new JTextField(6);
		this.jlTitle = new JLabel("Title");
		this.jlElementType = new JLabel("ShelfId");
		this.comboBox.setBounds(101, 31, 109, 24);

		/* Thread to fill jCombox with shelfRepository data */
		SwingWorker<?, ?> worker = fillComboxFromMap();
		worker.execute();
		/* Adding containers and components to Frame */
		createContentPane();

		/*
		 * Registration ActionListener in the button. When an event is generated
		 * by this component, is created an instance of the private class
		 * EventCDCollection.
		 */
		btnAddCDCollection.addActionListener(new EventCDCollection());

	}

	/**
	 * Method that have the responsibility to fill jCombox with repository data
	 * in a background thread.
	 * 
	 * @return
	 */
	private SwingWorker<?, ?> fillComboxFromMap() {
		SwingWorker<Map<String, String> , Void> worker = new SwingWorker<Map<String, String> , Void>() {
			@Override
			protected Map<String, String> doInBackground()
					throws Exception {
				
				CommandGetFactoryWithoutParameters getShelfs =  (CommandGetFactoryWithoutParameters) shelfCommands.get("getShelfs");
				return getShelfs.newInstance().execute();
			}

			@Override
			protected void done() {

				try {

					for (Entry<String, String> iterable_element : get().entrySet()) {

						comboBox.addItem(iterable_element.getKey().split("=")[1]);
					}
					
				} catch (Exception e) {

					
						Logger.getLogger(CDCollection.class.getName()).log(Level.WARNING, " Exception Occured : done() method ", 
								e.getClass().getName());
					
				}
			}
		};
		return worker;
	}

	/**
	 * Method to define the window property and add components to frame
	 */
	private void createContentPane() {

		setTitle("AddShelfElement");
		setSize(500, 330);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		jlElementType.setBounds(21, 28, 96, 31);
		jlTitle.setBounds(21, 89, 42, 18);
		jtfTitle.setBounds(100, 88, 292, 19);
		btnAddCDCollection.setBounds(100, 192, 142, 31);
		btnDelete.setBounds(277, 192, 115, 31);

		// Adiciona os componentes à janela
		getContentPane().add(comboBox);
		getContentPane().add(jlElementType);
		getContentPane().add(jlTitle);
		getContentPane().add(jtfTitle);
		getContentPane().add(btnAddCDCollection);
		getContentPane().add(btnDelete);

	}

	/**
	 * 
	 * This Inner Class implements ActionListener. Creates an Element Shelf by a
	 * background thread with SwingWorker Framework.
	 *
	 */
	private class EventCDCollection implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Map<String, String> params = new TreeMap<String, String>();
			params.put("loginName", username);
			params.put("loginPassword", password);
			params.put("name", jtfTitle.getText());
			params.put("id", comboBox.getSelectedItem().toString());
			params.put("type", "CDCollection");
			
			

			try {
				HandlerPost.PostUserInformation(params, shelfCommands, "postElement");
				dispose();
				cleanFields();
			} catch (IOException e1) {
				
					Logger.getLogger(CDCollection.class.getName()).log(Level.WARNING, " IOException Occured : HandlerPost ", 
							e1.getClass().getName());
				
			};

		}

	}

	private void cleanFields() {
		jtfTitle.setText("");

	}
}