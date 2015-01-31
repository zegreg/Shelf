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
public class DVD extends JFrame {

	private static final int JTFDURATION_COLUMNS = 10;
	private static final int JTFDURATIONBOUNDS_HEIGHT = 18;
	private static final int JTFDURATIONBOUNDS_WIDTH = 292;
	private static final int JTFDURATIONBOUNDS_Y = 132;
	private static final int JTFDURATIONBOUNDS_X = 100;
	private static final int LBLDURATIONBOUNDS_HEIGHT = 31;
	private static final int LBLDURATIONBOUNDS_WIDTH = 42;
	private static final int LBLDURATIONBOUNDS_Y = 126;
	private static final int LBLDURATIONBOUNDS_X = 21;
	private static final int BTNDELBOUNDS_HEIGHT = 31;
	private static final int BTNDELBOUNDS_WIDTH = 115;
	private static final int BTNDELBOUNDS_Y = 192;
	private static final int BTNDELBOUNDS_X = 277;
	private static final int BTNADDBOUNDS_HEIGHT = 31;
	private static final int BTNADDBOUNDS_WIDTH = 96;
	private static final int BTNADDBOUNDS_Y = 192;
	private static final int BTNADDBOUNDS_X = 100;
	private static final int JTFTITLEBOUNDS_HEIGHT = 19;
	private static final int JTFTITLEBOUNDS_WIDTH = 292;
	private static final int JTFTITLEBOUNDS_Y = 88;
	private static final int JTFTITLEBOUNDS_X = 100;
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
	private static final int COMBOBOX_BOUNDS_HEIGHT = 24;
	private static final int COMBOBOX_BOUNDS_WIDTH = 109;
	private static final int COMBOBOX_BOUNDS_Y = 31;
	private static final int COMBOBOX_BOUNDS_X = 101;
	private static final int JTFTITLE = 6;
	/**
	 * Attributes
	 */

	private ShelfRepository shelfRepository;
	private ElementsRepository elementsRepository;
	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfTitle;
	private JComboBox<Object> comboBox;
	private final JButton btnAddDVD;
	private final JButton btnDelete;
	private final JLabel lblDuration;
	private JTextField jtfDuration;

	/**
	 * Constructor
	 * 
	 * @param shelfRepository
	 * @param elementsRepository
	 */
	public DVD(ShelfRepository shelfRepository,
			ElementsRepository elementsRepository) {

		this.shelfRepository = shelfRepository;
		this.elementsRepository = elementsRepository;

		this.btnAddDVD = new JButton("AddDVD");
		this.jtfDuration = new JTextField();
		this.btnDelete = new JButton("Delete");
		this.lblDuration = new JLabel("Duration");
		this.comboBox = new JComboBox<Object>();
		this.jtfTitle = new JTextField(JTFTITLE);
		this.jlTitle = new JLabel("Title");
		this.jlElementType = new JLabel("ShelfId");
		this.comboBox.setBounds(COMBOBOX_BOUNDS_X, COMBOBOX_BOUNDS_Y, COMBOBOX_BOUNDS_WIDTH, COMBOBOX_BOUNDS_HEIGHT);

		/* Thread to fill jCombox with shelfRepository data */
		SwingWorker<?, ?> worker = fillComboxFromMap();
		worker.execute();

		/* Adding containers and components to Frame */
		createContentPane();

		/*
		 * Registration ActionListener in the button. When an event is generated
		 * by this component, is created an instance of the private class
		 * EventDVD.
		 */
		btnAddDVD.addActionListener(new EventDVD());

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
		jtfTitle.setBounds(JTFTITLEBOUNDS_X, JTFTITLEBOUNDS_Y, JTFTITLEBOUNDS_WIDTH, JTFTITLEBOUNDS_HEIGHT);
		btnAddDVD.setBounds(BTNADDBOUNDS_X, BTNADDBOUNDS_Y, BTNADDBOUNDS_WIDTH, BTNADDBOUNDS_HEIGHT);
		btnDelete.setBounds(BTNDELBOUNDS_X, BTNDELBOUNDS_Y, BTNDELBOUNDS_WIDTH, BTNDELBOUNDS_HEIGHT);
		lblDuration.setBounds(LBLDURATIONBOUNDS_X, LBLDURATIONBOUNDS_Y, LBLDURATIONBOUNDS_WIDTH, LBLDURATIONBOUNDS_HEIGHT);
		jtfDuration.setBounds(JTFDURATIONBOUNDS_X, JTFDURATIONBOUNDS_Y, JTFDURATIONBOUNDS_WIDTH, JTFDURATIONBOUNDS_HEIGHT);
		jtfDuration.setColumns(JTFDURATION_COLUMNS);

		getContentPane().add(comboBox);
		getContentPane().add(jlElementType);
		getContentPane().add(jlTitle);
		getContentPane().add(jtfTitle);
		getContentPane().add(btnAddDVD);
		getContentPane().add(btnDelete);
		getContentPane().add(lblDuration);
		getContentPane().add(jtfDuration);

	}

	/**
	 * 
	 * This Inner Class implements ActionListener. Creates an Element Shelf by a
	 * background thread with SwingWorker Framework.
	 *
	 */
	private class EventDVD implements ActionListener {

		private static final int CAEIAS_DURATION = 0;

		@Override
		public void actionPerformed(ActionEvent e) {

			SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {

					return new CreateAnElementInAShelf(
							shelfRepository,
							elementsRepository,
							Long.valueOf(comboBox.getSelectedItem().toString()),
							"CD", jtfTitle.getText(), null, Integer
									.valueOf(jtfDuration.getText()), CAEIAS_DURATION).call();
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

					/* Invokes the low method implemented */

					cleanFields();
					dispose();
				}
			};

			worker.execute();

		}

	}

	private void cleanFields() {
		jtfTitle.setText("");
		jtfDuration.setText("");

	}

}
