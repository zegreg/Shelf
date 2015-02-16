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

import fhj.shelf.actionwindow.CleanFields;
import fhj.shelf.actionwindow.HandlerPost;
import fhj.shelf.actionwindow.PostActionWindow;
import fhj.shelf.actionwindowfactory.PostActionWindowFactory;
import fhj.shelf.exceptions.ExceptionsGUI;
import fhj.shelf.exceptions.InterruptedOutput;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;







import java.awt.SystemColor;

@SuppressWarnings("serial")
public class Book extends JFrame implements PostActionWindow, CleanFields {

	private static final int FRAME_Y = 100;
	private static final int FRAME_X = 100;
	private static final int TEXTFIELD_COLUMNS = 10;
	private static final int TEXTFIELD_HEIGHT = 18;
	private static final int TEXTFIELD_WIDTH = 292;
	private static final int TEXTFIELD_Y = 132;
	private static final int TEXTFIELD_X = 100;
	private static final int LBLAUTHOR_HEIGHT = 31;
	private static final int LBLAUTHOR_WIDTH = 42;
	private static final int LBLAUTHOR_Y = 126;
	private static final int LBLAUTHOR_X = 21;
	private static final int BTNDELETE_HEIGHT = 31;
	private static final int BTNDELETE_WIDTH = 115;
	private static final int BTNDELETE_Y = 192;
	private static final int BTNDELETE_X = 277;
	private static final int BTNADDBOOK_HEIGHT = 31;
	private static final int BTNADDBOOK_WIDTH = 96;
	private static final int BTNADDBOOK_Y = 192;
	private static final int BTNADDBOOK_X = 100;
	private static final int JTFSHELFDATA_HEIGHT = 19;
	private static final int JTFSHELFDATA_WIDTH = 292;
	private static final int JTFSHELFDATA_Y = 88;
	private static final int JTFSHELFDATA_X = 100;
	private static final int JLTITLE_HEGHT = 18;
	private static final int JLTITLE_WIDTH = 42;
	private static final int JLTITLE_Y = 89;
	private static final int JLTITLE_X = 21;
	private static final int JLELEMENTTYPE_HEIGHT = 31;
	private static final int JLELEMENTTYPE_WIDTH = 96;
	private static final int JLELEMENTTYPE_Y = 28;
	private static final int JLELEMENTTYPE_X = 21;
	private static final int HEIGHT_FRAME = 330;
	private static final int WIDTH_FRAME = 500;
	private static final int HEIGHT_COMBOX = 24;
	private static final int WIDTH_COMBOX = 109;
	private static final int Y_LOCATION = 31;
	private static final int X_LOCATION = 101;
	private static final int NUMBER_COLUMNS = 6;
	/**
	 * Attributes
	 */

	
	private static JLabel jlElementType;
	private static JLabel jlTitle;
	private static JTextField jtfShelfData;
	private static JComboBox<Object> comboBox;
	private static JButton btnAddbook;
	private static JButton btnDelete;
	private static JLabel lblAuthor;
	private static JTextField textField;
	private Map<String, CommandFactory> shelfCommands;
	private String username;
	private String password;
	private JTextField textField_1;

	/**
	 * 
	 * Class that a single instance of UserRepositorySwing class. Implements
	 * PostActionWindowFactory and returns a PostActionWindow
	 *
	 */
	public static class Factory implements PostActionWindowFactory {

		/**
		 * This is the constructor for the class above, it defines the factory
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of Book
		 */

		@Override
		public PostActionWindow newInstance(String username, String password,
				Map<String, CommandFactory> mapCommands) {
			return new Book(username, password, mapCommands);
		}
	}

	/**
	 * Constructor that define and show window
	 * 
	 * @param username
	 * @param password
	 * @param shelfCommands
	 */
	public Book(String username, String password,
			Map<String, CommandFactory> shelfCommands) {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		this.username = username;
		this.password = password;
		this.shelfCommands = shelfCommands;

		btnAddbook = new JButton("AddBooK");
		textField = new JTextField();
		btnDelete = new JButton("Delete");
		lblAuthor = new JLabel("Author");
		comboBox = new JComboBox<Object>();
		jtfShelfData = new JTextField(NUMBER_COLUMNS);
		jlTitle = new JLabel("Title");
		jlElementType = new JLabel("ShelfId");
		comboBox.setBounds(X_LOCATION, Y_LOCATION, WIDTH_COMBOX, HEIGHT_COMBOX);

		/** Thread to fill jCombox with shelfRepository data */

		SwingWorker<?, ?> fillDataThread = fillComboxFromMap();
		fillDataThread.execute();

		/**
		 * Adding containers and components to Frame
		 */
		createContentPane();

		/**
		 * Registration ActionListener in the button. When an event is generated
		 * by this component, is created an instance of the private class
		 * EventBook.
		 */
		btnAddbook.addActionListener(new EventBook());

	}

	/**
	 * Method that have the responsibility to fill jCombox with repository data
	 * in a background thread.
	 * 
	 * @return a SwingWorker
	 */
	private SwingWorker<Map<String, String>, Void> fillComboxFromMap() {

		SwingWorker<Map<String, String>, Void> worker = new SwingWorker<Map<String, String>, Void>() {

			@Override
			protected Map<String, String> doInBackground() throws Exception {

				CommandGetFactoryWithoutParameters getShelfs = (CommandGetFactoryWithoutParameters) shelfCommands
						.get("getShelfs");
				return getShelfs.newInstance().execute();
			}

			@Override
			protected void done()   {

				try {

					for (Entry<String, String> iterable_element : get()
							.entrySet()) {

						comboBox.addItem(iterable_element.getKey().split("=")[1]);

					}

				} catch (InterruptedException e) {
                 
					Logger.getLogger(Book.class.getName()).log(Level.WARNING, " InterruptedException Occured : comboBox.addIten ", e);
				} catch (ExecutionException e) {
					Logger.getLogger(Book.class.getName()).log(Level.WARNING, " ExecutionException Occured : ", e);
					
				}

			}
		};
		return worker;
	}

	/**
	 * Method to define the window property and add components to frame
	 */
	private void createContentPane() {

		setTitle("ElementSearch");
		setSize(WIDTH_FRAME, HEIGHT_FRAME);
		setLocation(FRAME_X, FRAME_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		jlElementType.setBounds(JLELEMENTTYPE_X, JLELEMENTTYPE_Y,
				JLELEMENTTYPE_WIDTH, JLELEMENTTYPE_HEIGHT);
		jlTitle.setBounds(JLTITLE_X, JLTITLE_Y, JLTITLE_WIDTH, JLTITLE_HEGHT);
		jtfShelfData.setBounds(JTFSHELFDATA_X, JTFSHELFDATA_Y,
				JTFSHELFDATA_WIDTH, JTFSHELFDATA_HEIGHT);
		btnAddbook.setBounds(BTNADDBOOK_X, BTNADDBOOK_Y, BTNADDBOOK_WIDTH,
				BTNADDBOOK_HEIGHT);
		btnDelete.setBounds(BTNDELETE_X, BTNDELETE_Y, BTNDELETE_WIDTH,
				BTNDELETE_HEIGHT);
		lblAuthor.setBounds(LBLAUTHOR_X, LBLAUTHOR_Y, LBLAUTHOR_WIDTH,
				LBLAUTHOR_HEIGHT);
		textField.setBounds(TEXTFIELD_X, TEXTFIELD_Y, TEXTFIELD_WIDTH,
				TEXTFIELD_HEIGHT);
		textField.setColumns(TEXTFIELD_COLUMNS);

		/**
		 * Adding window containers
		 */
		getContentPane().add(comboBox);
		getContentPane().add(jlElementType);
		getContentPane().add(jlTitle);
		getContentPane().add(jtfShelfData);
		getContentPane().add(btnAddbook);
		getContentPane().add(btnDelete);
		getContentPane().add(lblAuthor);
		getContentPane().add(textField);

		JLabel lblElementid = new JLabel("ElementId");
		lblElementid.setBounds(263, 36, 76, 18);
		getContentPane().add(lblElementid);

		textField_1 = new JTextField();
		textField_1.setBounds(339, 31, 42, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

	}

	/**
	 * 
	 * This Inner Class implements ActionListener. Creates an Element Shelf by a
	 * background thread with SwingWorker Framework.
	 *
	 */

	private class EventBook implements ActionListener {

		Map<String, String> params = new HashMap<String, String>();

		@Override
		public void actionPerformed(ActionEvent e) {

			params.put("loginName", username);
			params.put("loginPassword", password);
			params.put("name", jtfShelfData.getText());
			params.put("author", textField.getText());
			params.put("type", "Book");
			params.put("id", comboBox.getSelectedItem().toString());

			try {

				if (textField_1 != null) {

					params.put("eid", textField_1.getText());
					HandlerPost.PostUserInformation(params, shelfCommands,
							"postCollectionElement");
				} else {

					HandlerPost.PostUserInformation(params, shelfCommands,
							"postElement");
					dispose();
					cleanFields();
				}
			} catch (IOException e1) {
				
				Logger.getLogger(Book.class.getName()).log(Level.WARNING, " IOException Occured : HandlerPost ", e1);
			}
			;

		}

	}

	/**
	 * Clean all fields after action event operation
	 */
	@Override
	public void cleanFields() {

		jtfShelfData.setText("");
		textField.setText("");
	}
}
