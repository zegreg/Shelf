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
public class CD extends JFrame {

	private static final int JTFTRACKS_COLUMNS = 10;
	private static final int JTFTRACKS_HEIGHT = 18;
	private static final int JTFTRACKS_WIDTH = 292;
	private static final int JTFTRACKS_Y = 132;
	private static final int JTFTRACKS_X = 100;
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
	private ShelfRepository shelfRepository;
	private ElementsRepository elementsRepository;
	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfTitle;
	private JComboBox<Object> comboBox;
	private final JButton btnAddbook;
	private final JButton btnDelete;
	private final JLabel lblAuthor;
	private JTextField jtfTracks;
	private static final Logger logger = LoggerFactory.getLogger(CD.class);

	/**
	 * Constructor
	 * 
	 * @param shelfRepository
	 * @param elementsRepository
	 */
	public CD(ShelfRepository shelfRepository,
			ElementsRepository elementsRepository) {

		this.shelfRepository = shelfRepository;
		this.elementsRepository = elementsRepository;

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
		SwingWorker<Map<Long, AbstractShelf>, Void> worker = fillComboxFromMap();
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
	private SwingWorker<Map<Long, AbstractShelf>, Void> fillComboxFromMap() {
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
		lblAuthor.setBounds(LBLAUTHOR_X, LBLAUTHOR_Y, LBLAUTHOR_WIDTH, LBLAUTHOR_HEIGHT);
		jtfTracks.setBounds(JTFTRACKS_X, JTFTRACKS_Y, JTFTRACKS_WIDTH, JTFTRACKS_HEIGHT);
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

			SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {

					return new CreateAnElementInAShelf(shelfRepository,
							elementsRepository, Long.valueOf(comboBox
									.getSelectedItem().toString()), "CD",
							jtfTitle.getText(), null, Integer.valueOf(jtfTracks
									.getText()), 0).call();
				}

				@Override
				protected void done() {

					try {
						JOptionPane.showMessageDialog(null, "Data were successfully saved!" + get());
					} catch (HeadlessException e) {

						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					} catch (InterruptedException e) {

						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					} catch (ExecutionException e) {

						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					}

					cleanFields();
					dispose();
				}
			};

			worker.execute();

		}

	}

	private void cleanFields() {
		jtfTitle.setText("");
		jtfTracks.setText("");

	}

}
