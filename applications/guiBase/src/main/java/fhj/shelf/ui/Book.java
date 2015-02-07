package fhj.shelf.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.commandsDomain.CreateAnElementInAShelf;
import fhj.shelf.commandsDomain.GetAllShelfs;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.mutation.BookCreationDescriptor;
import fhj.shelf.utils.mutation.ElementCreationDescriptor;

@SuppressWarnings("serial")
public class Book extends JFrame {

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
	private ShelfRepository shelfRepository;
	private ElementsRepository elementsRepository;
	private static final Logger logger = LoggerFactory.getLogger(Book.class);
	private static JLabel jlElementType;
	private static JLabel jlTitle;
	private static JTextField jtfShelfData;
	private static JComboBox<Object> comboBox;
	private static JButton btnAddbook;
	private static JButton btnDelete;
	private static JLabel lblAuthor;
	private static JTextField textField;

	/**
	 * Constructor
	 * 
	 * @param shelfRepository
	 * @param elementsRepository
	 */
	public Book(ShelfRepository shelfRepository,
			ElementsRepository elementsRepository) {

		this.shelfRepository = shelfRepository;
		this.elementsRepository = elementsRepository;

		btnAddbook = new JButton("AddBooK");
		textField = new JTextField();
		btnDelete = new JButton("Delete");
		lblAuthor = new JLabel("Author");
		comboBox = new JComboBox<Object>();
		jtfShelfData = new JTextField(NUMBER_COLUMNS);
		jlTitle = new JLabel("Title");
		jlElementType = new JLabel("ShelfId");
		comboBox.setBounds(X_LOCATION, Y_LOCATION, WIDTH_COMBOX, HEIGHT_COMBOX);
	
		/* Thread to fill jCombox with shelfRepository data */

		SwingWorker<?, ?> fillDataThread = fillComboxFromMap();
		fillDataThread.execute();

		/* Adding containers and components to Frame */
		createContentPane();

		/*
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
	 * @return
	 */
	private SwingWorker<Map<Long, Shelf>, Void> fillComboxFromMap() {
		
		
		SwingWorker<Map<Long, Shelf>, Void> worker = new SwingWorker<Map<Long, Shelf>, Void>() {
			@Override
			protected Map<Long, Shelf> doInBackground() throws Exception {

				return new GetAllShelfs(shelfRepository).call();
			}

			@Override
			protected void done() {

				try {
					for (Entry<Long, Shelf> iterable_element : get().entrySet()) {

						comboBox.addItem(iterable_element.getKey());

					}
				} catch (InterruptedException e) {

					logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
				} catch (ExecutionException e) {

					logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
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

		jlElementType.setBounds(JLELEMENTTYPE_X, JLELEMENTTYPE_Y, JLELEMENTTYPE_WIDTH, JLELEMENTTYPE_HEIGHT);
		jlTitle.setBounds(JLTITLE_X, JLTITLE_Y, JLTITLE_WIDTH, JLTITLE_HEGHT);
		jtfShelfData.setBounds(JTFSHELFDATA_X, JTFSHELFDATA_Y, JTFSHELFDATA_WIDTH, JTFSHELFDATA_HEIGHT);
		btnAddbook.setBounds(BTNADDBOOK_X, BTNADDBOOK_Y, BTNADDBOOK_WIDTH, BTNADDBOOK_HEIGHT);
		btnDelete.setBounds(BTNDELETE_X, BTNDELETE_Y, BTNDELETE_WIDTH, BTNDELETE_HEIGHT);
		lblAuthor.setBounds(LBLAUTHOR_X, LBLAUTHOR_Y, LBLAUTHOR_WIDTH, LBLAUTHOR_HEIGHT);
		textField.setBounds(TEXTFIELD_X, TEXTFIELD_Y, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		textField.setColumns(TEXTFIELD_COLUMNS);

		// Adiciona os componentes à janela
		getContentPane().add(comboBox);
		getContentPane().add(jlElementType);
		getContentPane().add(jlTitle);
		getContentPane().add(jtfShelfData);
		getContentPane().add(btnAddbook);
		getContentPane().add(btnDelete);
		getContentPane().add(lblAuthor);
		getContentPane().add(textField);

	}

	/**
	 * 
	 * This Inner Class implements ActionListener. Creates an Element Shelf by a
	 * background thread with SwingWorker Framework.
	 *
	 */
	private class EventBook implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {

					 return new CreateAnElementInAShelf(
						       shelfRepository,
						       elementsRepository,
						       Long.valueOf(comboBox.getSelectedItem().toString()),
						       new BookCreationDescriptor( jlTitle.getText(), jtfShelfData.getText()
						       )).call();
							

				}

				@Override
				protected void done() {

					try {
						JOptionPane.showMessageDialog(null,
								"Data were successfully saved!" + get());
					} catch (HeadlessException e) {

						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					} catch (InterruptedException e) {

						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					} catch (ExecutionException e) {

						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					}

					/* Invokes the low method implemented */
					cleanFields();
					dispose();
				}

			};

			worker.execute();

		}

	}

	/**
	 * Clean all fields after action event operation
	 */
	private void cleanFields() {
		jtfShelfData.setText("");
		textField.setText("");

	}

}
