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
import fhj.shelf.repos.AbstractShelf;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.ShelfRepository;

@SuppressWarnings("serial")
public class BookCollection extends JFrame {

	private static final int BTNDELETE_HEIGHT = 31;
	private static final int BTNDELETE_WIDTH = 115;
	private static final int BTNDELETE_Y = 192;
	private static final int BTNDLELETE_X = 277;
	private static final int BTNADDBOOKCOLLECTION_HEIGHT = 31;
	private static final int BTNADDBOOKCOLLECTION_WIDTH = 96;
	private static final int BTNADDBOOKCOLLECTION_Y = 192;
	private static final int BTNADDBOOKCOLLECTION_X = 100;
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

	private ShelfRepository shelfRepository;
	private ElementsRepository elementsRepository;
	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfTitle;
	private JComboBox<Object> comboBox;
	private final JButton btnAddBookCollection;
	private final JButton btnDelete;
	private static final Logger logger = LoggerFactory.getLogger(BookCollection.class);

	/**
	 * Constructor
	 * 
	 * @param shelfRepository
	 * @param elementsRepository
	 */
	public BookCollection(ShelfRepository shelfRepository,
			ElementsRepository elementsRepository) {

		this.shelfRepository = shelfRepository;
		this.elementsRepository = elementsRepository;

		this.btnAddBookCollection = new JButton("AddDVD");
		this.btnDelete = new JButton("Delete");
		this.comboBox = new JComboBox<Object>();
		this.jtfTitle = new JTextField(6);
		this.jlTitle = new JLabel("Title");
		this.jlElementType = new JLabel("ShelfId");
		this.comboBox.setBounds(COMBOX_X, COMBOBOX_Y, COMBOBOX_WIDTH, COMBOBOX_HEIGHT);

		/* Thread to fill jCombox with shelfRepository data */
		SwingWorker<?, ?> worker = fillComboxFromMap();
		worker.execute();
		/* Adding containers and components to Frame */
		createContentPanel();

		/*
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
	private SwingWorker<?, ?> fillComboxFromMap() {
		SwingWorker<Map<Long, AbstractShelf>, Void> worker = new SwingWorker<Map<Long, AbstractShelf>, Void>() {
			@Override
			protected Map<Long, AbstractShelf> doInBackground()
					throws Exception {
			return new GetAllShelfs(shelfRepository).call();
			}

			@Override
			protected void done() {

				try {
					for (Entry<Long, AbstractShelf> iterable_element : get().entrySet()) {

						comboBox.addItem(iterable_element.getKey());

					}
				} catch (InterruptedException e) {

					logger .error( "FailedCreateActivityFunction Exception Occured : " ,e );
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
	private void createContentPanel() {

		setTitle("AddShelfElement");
		setSize(WIDTH_FRAME, HEIGHT_FRAME);
		setLocation(FRAME_X, FRAME_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		jlElementType.setBounds(JLELEMENTTYPE_X, JLELEMENTTYPE_Y, JLELEMENTTYPE_WIDTH, JLELEMENTTYPE_HEIGHT);
		jlTitle.setBounds(JLTITLE_X, JLTITLE_Y, JLTITLE_WIDTH, JLTITLE_HEIGHT);
		jtfTitle.setBounds(JTFTITLE_X, JTFTITLE_Y, JTFTITLE_WIDTH, JTFTITLE_HEIGHT);
		btnAddBookCollection.setBounds(BTNADDBOOKCOLLECTION_X, BTNADDBOOKCOLLECTION_Y, BTNADDBOOKCOLLECTION_WIDTH, 
				BTNADDBOOKCOLLECTION_HEIGHT);
		btnDelete.setBounds(BTNDLELETE_X, BTNDELETE_Y, BTNDELETE_WIDTH, BTNDELETE_HEIGHT);

		getContentPane().add(comboBox);
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

			SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {

					return new CreateAnElementInAShelf(
							shelfRepository,
							elementsRepository,
							Long.valueOf(comboBox.getSelectedItem().toString()),
							"CD", jtfTitle.getText(), null, 0, 0).call();
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
		jtfTitle.setText("");

	}
}
