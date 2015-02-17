package fhj.shelf.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;




import fhj.shelf.actionwindow.HandlerPost;
import fhj.shelf.actionwindow.PostActionWindow;
import fhj.shelf.actionwindowfactory.PostActionWindowFactory;
import fhj.shelf.exceptions.ExecutionExceptionReturn;
import fhj.shelf.exceptions.PostHandlerExceptions;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;


@SuppressWarnings("serial")
public class CD extends JFrame implements PostActionWindow {

	
	/**
	 * 
	 * Class that a single instance of UserRepositorySwing class. Implements
	 * PostActionWindowFactory and returns a PostActionWindow
	 *
	 */
	public static class Factory implements PostActionWindowFactory {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of CD
		 */
		
		@Override
		public PostActionWindow newInstance(String username, String password, Map<String, CommandFactory> mapCommands) {
			return new CD(username, password,mapCommands);
		}
	}
	
	
	
	
	private static final int JTFTRACKS_COLUMNS = 10;
	private static final int BTNDELETE_HEIGHT = 31;
	private static final int BTNDELETE_WIDTH = 115;
	private static final int BTNDELETE_Y = 192;
	private static final int BTNDELETE_X = 277;
	private static final int BTNADDBOOK_HEIGHT = 31;
	private static final int BTNADDBOOK_WIDTH = 96;
	private static final int BTNADDBOOK_Y = 192;
	private static final int BTNADDBOOK_X = 100;
	private static final int JTFTITLE_HEIGHT = 19;
	private static final int JTFTITLE_WIDTH = 292;
	private static final int JTFTITLE_Y = 88;
	private static final int JTFTITLE_X = 100;
	private static final int JLTITLE_HEIGHT = 18;
	private static final int JLTITLE_WIDTH = 42;
	private static final int JLTITLE_Y = 89;
	private static final int JLTITLE_X = 21;
	private static final int JLELEMENTTYPE_HEIGHT = 31;
	private static final int JLELEMENTTYPE_WIDTH = 96;
	private static final int JLELEMENTTYPE_Y = 28;
	private static final int JLELEMENTTYPE_X = 21;
	private static final int FRAME_Y = 100;
	private static final int FRAME_X = 100;
	private static final int FRAME_HEIGHT = 330;
	private static final int FRAME_WIDTH = 500;
	private static final int COMBOBOX_HEIGHT = 24;
	private static final int COMBOBOX_WIDTH = 109;
	private static final int COMBOBOX_Y = 31;
	private static final int COMBOBOX_X = 101;
	/**
	 * Attributes
	 */

	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfTitle;
	private JComboBox<Object> comboBox;
	private final JButton btnAddbook;
	private final JButton btnDelete;
	private final JLabel lblAuthor;
	private JTextField jtfTracks;
	
	private Map<String, CommandFactory> shelfCommands;
	private String username;
	private String password;
	private JTextField textField;
	/**
	 * Constructor
	 * @param shelfCommands 
	 * 
	 * @param shelfRepository
	 * @param elementsRepository
	 */
	public CD(String username, String password,Map<String, CommandFactory> shelfCommands) {
		this.username = username;
		this.password = password;
this.shelfCommands = shelfCommands;


		this.btnAddbook = new JButton("AddCD");
		this.jtfTracks = new JTextField();
		this.btnDelete = new JButton("Delete");
		this.lblAuthor = new JLabel("TracksNumber");
		this.comboBox = new JComboBox<Object>();
		this.jtfTitle = new JTextField();
		this.jlTitle = new JLabel("Title");
		this.jlElementType = new JLabel("ShelfId");
		this.comboBox.setBounds(COMBOBOX_X, COMBOBOX_Y, COMBOBOX_WIDTH, COMBOBOX_HEIGHT);

		/* Thread to fill jCombox with shelfRepository data */
		SwingWorker<Map<String, String>, Void> worker = fillComboxFromMap();
		worker.execute();

		/* Adding containers and components to Frame */
		createContentPanel();

		/*
		 * Registration ActionListener in the button. When an event is generated
		 * by this component, is created an instance of the private class
		 * EventCD.
		 */

		btnAddbook.addActionListener(new EventCD());

	}

	/**
	 * Method that have the responsibility to fill jCombox with repository data
	 * in a background thread.
	 * 
	 * @return
	 */
	private SwingWorker<Map<String, String>, Void> fillComboxFromMap() {
		SwingWorker<Map<String, String>, Void> worker = new SwingWorker<Map<String, String>, Void>() {
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
					
					
						Logger.getLogger(CD.class.getName()).log(Level.WARNING, " Exception Occured : done() method ", 
								e.getClass().getName());
					
				}

			}
		};
		return worker;
	}

	/**
	 * Method to define the window property and add components to frame
	 */
	private void createContentPanel() {

		setTitle("AddShelfElement");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(FRAME_X, FRAME_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		jlElementType.setBounds(JLELEMENTTYPE_X, JLELEMENTTYPE_Y, JLELEMENTTYPE_WIDTH, JLELEMENTTYPE_HEIGHT);
		jlTitle.setBounds(JLTITLE_X, JLTITLE_Y, JLTITLE_WIDTH, JLTITLE_HEIGHT);
		jtfTitle.setBounds(JTFTITLE_X, JTFTITLE_Y, JTFTITLE_WIDTH, JTFTITLE_HEIGHT);
		btnAddbook.setBounds(BTNADDBOOK_X, BTNADDBOOK_Y, BTNADDBOOK_WIDTH, BTNADDBOOK_HEIGHT);
		btnDelete.setBounds(BTNDELETE_X, BTNDELETE_Y, BTNDELETE_WIDTH, BTNDELETE_HEIGHT);
		lblAuthor.setBounds(21, 126, 76, 31);
		jtfTracks.setBounds(100, 132, 42, 25);
		jtfTracks.setColumns(JTFTRACKS_COLUMNS);

		// Adiciona os componentes à janela
		getContentPane().add(comboBox);
		getContentPane().add(jlElementType);
		getContentPane().add(jlTitle);
		getContentPane().add(jtfTitle);
		getContentPane().add(btnAddbook);
		getContentPane().add(btnDelete);
		getContentPane().add(lblAuthor);
		getContentPane().add(jtfTracks);
		
		JLabel lblElementid = new JLabel("ElementID");
		lblElementid.setBounds(257, 37, 86, 18);
		getContentPane().add(lblElementid);
		
		textField = new JTextField();
		textField.setBounds(333, 33, 42, 24);
		getContentPane().add(textField);
		textField.setColumns(10);

	}

	/**
	 * 
	 * This Inner Class implements ActionListener. Creates an Element Shelf by a
	 * background thread with SwingWorker Framework.
	 *
	 */
	private class EventCD implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("loginName", username);
			params.put("loginPassword", password);
			params.put("name", jtfTitle.getText());
			params.put("tracksnumber", jtfTracks.getText());
			params.put("type", "CD");
			params.put("id", comboBox.getSelectedItem().toString());

			try {
				
				if (textField!= null) {
					
					params.put("eid", textField.getText());
					HandlerPost.PostUserInformation(params, shelfCommands, "postCollectionElement");
				}
				else{

					HandlerPost.PostUserInformation(params, shelfCommands, "postElement");
					dispose();
					cleanFields();
				}
				} catch (IOException e1) {
					
						Logger.getLogger(CD.class.getName()).log(Level.WARNING, " IOException Occured : HandlerPost ", 
								e1.getClass().getName());
				
			};

		}

	}

	private void cleanFields() {
		jtfTitle.setText("");
		jtfTracks.setText("");

	}

}

