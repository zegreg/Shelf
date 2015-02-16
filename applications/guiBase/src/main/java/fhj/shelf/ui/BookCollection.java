package fhj.shelf.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;



import fhj.shelf.actionWindow.HandlerPost;
import fhj.shelf.actionWindow.PostActionWindow;
import fhj.shelf.actionWindowFactory.PostActionWindowFactory;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;

import java.awt.SystemColor;

@SuppressWarnings("serial")
public class BookCollection extends JFrame implements PostActionWindow {

	
	public static class Factory implements PostActionWindowFactory {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of BookCollection
		 */
		
		@Override
		public PostActionWindow newInstance(String username, String password, Map<String, CommandFactory> mapCommands) {
			return new BookCollection(username, password,mapCommands);
		}
	}
	
	
	
	
	
	
	private static final int BTNDELETE_HEIGHT = 31;
	private static final int BTNDELETE_WIDTH = 115;
	private static final int BTNDELETE_Y = 192;
	private static final int BTNDLELETE_X = 277;
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
	private static final int HEIGHT_FRAME = 330;
	private static final int WIDTH_FRAME = 500;
	private static final int COMBOBOX_HEIGHT = 24;
	private static final int COMBOBOX_WIDTH = 109;
	private static final int COMBOBOX_Y = 31;
	private static final int COMBOX_X = 101;
	
	
	/**
	 * Attributes
	 */
	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfTitle;
	private final JButton btnAddBookCollection;
	private final JButton btnDelete;
	private Map<String, CommandFactory> shelfCommands;
	private JComboBox<Object> comboBoxCollection;
	private String username;
	private String password;

	
	/**
	 * Constructor
	 * @param username
	 * @param password
	 * @param shelfCommands
	 */
	public BookCollection(String username, String password,Map<String, CommandFactory> shelfCommands) {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		this.username = username;
		this.password = password;
		this.shelfCommands = shelfCommands;

		this.btnAddBookCollection = new JButton("AddBookCollection");
		this.btnDelete = new JButton("Delete");
		this.comboBoxCollection = new JComboBox<Object>();
		this.jtfTitle = new JTextField(6);
		this.jlTitle = new JLabel("Title");
		this.jlElementType = new JLabel("ShelfId");
		this.comboBoxCollection.setBounds(COMBOX_X, COMBOBOX_Y, COMBOBOX_WIDTH,
				COMBOBOX_HEIGHT);

		/** Thread to fill jCombox with shelfRepository data */
		SwingWorker<?, ?> worker = fillCombox();
		worker.execute();
		/** Adding containers and components to Frame */
		createContentPanel();

		/**
		 * Registration ActionListener in the button. When an event is generated
		 * by this component, is created an instance of the private class
		 * EventBookCollection.
		 */
		btnAddBookCollection.addActionListener(new EventBookCollection());

	}

	/**
	 * Method that have the responsibility to fill jCombox with repository data
	 * in a background thread.
	 * 
	 * @return
	 */
	private SwingWorker<Map<String, String>, Void> fillCombox() {

		SwingWorker<Map<String, String>, Void> worker = new SwingWorker<Map<String, String>, Void>() {
			@Override
			protected Map<String, String> doInBackground() throws Exception {

				CommandGetFactoryWithoutParameters getShelfs = (CommandGetFactoryWithoutParameters) shelfCommands
						.get("getShelfs");
				return getShelfs.newInstance().execute();
			}

			@Override
			protected void done() {

				try {

					for (Entry<String, String> iterable_element : get().entrySet()) {

						comboBoxCollection.addItem(iterable_element.getKey().split("=")[1]);

					}
				} catch (InterruptedException e) {

					Logger.getLogger(BookCollection.class.getName()).log(Level.WARNING, " InterruptedException Occured : comboBox addItem ", e);
				} catch (ExecutionException e) {
					Logger.getLogger(BookCollection.class.getName()).log(Level.WARNING, " InterruptedException Occured : comboBox addItem  ", e);
							
				}

			}
		};
		return worker;
	}

	/**
	 * Method to define the window property and add components to frame
	 */
	private void createContentPanel() {

		setTitle("AddCollection");
		setSize(WIDTH_FRAME, HEIGHT_FRAME);
		setLocation(FRAME_X, FRAME_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		jlElementType.setBounds(JLELEMENTTYPE_X, JLELEMENTTYPE_Y,
				JLELEMENTTYPE_WIDTH, JLELEMENTTYPE_HEIGHT);
		jlTitle.setBounds(JLTITLE_X, JLTITLE_Y, JLTITLE_WIDTH, JLTITLE_HEIGHT);
		jtfTitle.setBounds(JTFTITLE_X, JTFTITLE_Y, JTFTITLE_WIDTH,
				JTFTITLE_HEIGHT);
		btnAddBookCollection.setBounds(100,
				192, 155,
				31);
		btnDelete.setBounds(BTNDLELETE_X, BTNDELETE_Y, BTNDELETE_WIDTH,
				BTNDELETE_HEIGHT);

		getContentPane().add(comboBoxCollection);
		getContentPane().add(jlElementType);
		getContentPane().add(jlTitle);
		getContentPane().add(jtfTitle);
		getContentPane().add(btnAddBookCollection);
		getContentPane().add(btnDelete);

	}

	/**
	 * 
	 * This Inner Class implements ActionListener. Creates an Element Shelf by a
	 * background thread with SwingWorker Framework.
	 *
	 */
	private class EventBookCollection implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Map<String, String> params = new TreeMap<String, String>();
			params.put("loginName", username);
			params.put("loginPassword", password);
			params.put("name", jtfTitle.getText());
			params.put("id", comboBoxCollection.getSelectedItem().toString());
			params.put("type", "BookCollection");


			try {
				HandlerPost.PostUserInformation(params, shelfCommands, "postElement");
				dispose();
				cleanFields();
			} catch (IOException e1) {
				
				Logger.getLogger(BookCollection.class.getName()).log(Level.WARNING, " IOException Occured : HandlerPost ", e1);
			};

	}

	/**
	 * Clean all fields after action event operation
	 */
	private void cleanFields() {
		jtfTitle.setText("");

	}
}
}
