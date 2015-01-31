package fhj.shelf.UI;

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

import fhj.shelf.commandsDomain.CreateAnElementInAShelf;
import fhj.shelf.commandsDomain.GetAllShelfs;
import fhj.shelf.utils.repos.AbstractShelf;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.ShelfRepository;

@SuppressWarnings("serial")
public class CDCollection extends JFrame {

	private static final int BTNDELBOUNDS_HEIGHT = 31;
	private static final int BTNDELBOUNDS_WIDTH = 115;
	private static final int BTNDELBOUNDS_Y = 192;
	private static final int BTNDELBOUNDS_X = 277;
	private static final int BTNADDBOUNDS_HEIGHT = 31;
	private static final int BTNADDBOUNDS_WIDTH = 96;
	private static final int BTNADDBOUNDS_Y = 192;
	private static final int BTNADDBOUNDS_X = 100;
	private static final int JTFBOUNDS_HEIGHT = 19;
	private static final int JTFBOUNDS_WIDTH = 292;
	private static final int JTFBOUNDS_Y = 88;
	private static final int JTFBOUNDS_X = 100;
	private static final int JLTBOUNDS_HEIGHT = 18;
	private static final int JLTBOUNDS_WIDTH = 42;
	private static final int JLTBOUNDS_Y = 89;
	private static final int JLTBOUNDS_X = 21;
	private static final int JLEBOUNDS_HEIGHT = 31;
	private static final int JLEBOUNDS_WIDTH = 96;
	private static final int JLEBOUNDS_Y = 28;
	private static final int JLEBOUNDS_X = 21;
	private static final int LOCATION_Y = 100;
	private static final int LOCATION_X = 100;
	private static final int SIZE_HEIGHT = 330;
	private static final int SIZE_WIDTH = 500;
	private static final int COMBOBOX_HEIGHT = 24;
	private static final int COMBOBOX_WIDTH = 109;
	private static final int COMBOBOX_Y = 31;
	private static final int COMBOBOX_X = 101;
	private static final int JTFTITLE_COLUMN = 6;
	/**
	 * Attributes
	 */

	private ShelfRepository shelfRepository;
	private ElementsRepository elementsRepository;
	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfTitle;
	private JComboBox<Object> comboBox;
	private final JButton btnAddCDCollection;
	private final JButton btnDelete;

	/**
	 * Constructor
	 * 
	 * @param shelfRepository
	 * @param elementsRepository
	 */
	public CDCollection(ShelfRepository shelfRepository,
			ElementsRepository elementsRepository) {

		this.shelfRepository = shelfRepository;
		this.elementsRepository = elementsRepository;

		this.btnAddCDCollection = new JButton("AddCDCollection");
		this.btnDelete = new JButton("Delete");
		this.comboBox = new JComboBox<Object>();
		this.jtfTitle = new JTextField(JTFTITLE_COLUMN);
		this.jlTitle = new JLabel("Title");
		this.jlElementType = new JLabel("ShelfId");
		this.comboBox.setBounds(COMBOBOX_X, COMBOBOX_Y, COMBOBOX_WIDTH, COMBOBOX_HEIGHT);

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
		SwingWorker<Map<Long, AbstractShelf>, Void> worker = new SwingWorker<Map<Long, AbstractShelf>, Void>() {
			@Override
			protected Map<Long, AbstractShelf> doInBackground()
					throws Exception {
			return new GetAllShelfs(shelfRepository).call();
			}

			@Override
			protected void done() {

				try {
					for (Entry<Long, AbstractShelf> iterable_element : get()
							.entrySet()) {

						comboBox.addItem(iterable_element.getKey());

					}
				} catch (InterruptedException e) {

					e.printStackTrace();
				} catch (ExecutionException e) {

					e.printStackTrace();
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
		setSize(SIZE_WIDTH, SIZE_HEIGHT);
		setLocation(LOCATION_X, LOCATION_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		jlElementType.setBounds(JLEBOUNDS_X, JLEBOUNDS_Y, JLEBOUNDS_WIDTH, JLEBOUNDS_HEIGHT);
		jlTitle.setBounds(JLTBOUNDS_X, JLTBOUNDS_Y, JLTBOUNDS_WIDTH, JLTBOUNDS_HEIGHT);
		jtfTitle.setBounds(JTFBOUNDS_X, JTFBOUNDS_Y, JTFBOUNDS_WIDTH, JTFBOUNDS_HEIGHT);
		btnAddCDCollection.setBounds(BTNADDBOUNDS_X, BTNADDBOUNDS_Y, BTNADDBOUNDS_WIDTH, BTNADDBOUNDS_HEIGHT);
		btnDelete.setBounds(BTNDELBOUNDS_X, BTNDELBOUNDS_Y, BTNDELBOUNDS_WIDTH, BTNDELBOUNDS_HEIGHT);

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

		private static final int CAEIAS_DURATION = 0;
		private static final int CAEIAS_TRACKS = 0;

		@Override
		public void actionPerformed(ActionEvent e) {

			SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {

					return new CreateAnElementInAShelf(
							shelfRepository,
							elementsRepository,
							Long.valueOf(comboBox.getSelectedItem().toString()),
							"CD", jtfTitle.getText(), null, CAEIAS_TRACKS, CAEIAS_DURATION).call();
				}

				@Override
				protected void done() {

					try {
						JOptionPane.showMessageDialog(null,
								"Data were successfully saved!" + get());
					} catch (HeadlessException e) {

						e.printStackTrace();
					} catch (InterruptedException e) {

						e.printStackTrace();
					} catch (ExecutionException e) {

						e.printStackTrace();
					}
					// Invoca o método implementado em baixo

					cleanFields();
					dispose();
				}
			};

			worker.execute();

		}

	}

	private void cleanFields() {
		jtfTitle.setText("");

	}
}