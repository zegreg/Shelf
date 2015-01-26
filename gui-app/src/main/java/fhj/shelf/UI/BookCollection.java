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

import fhj.shelf.utils.AbstractShelf;

import fhj.shelf.utils.repos.ElementsRepository;

import fhj.shelf.utils.repos.ShelfRepository;

@SuppressWarnings("serial")
public class BookCollection extends JFrame {

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
		this.comboBox.setBounds(101, 31, 109, 24);

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
				Map<Long, AbstractShelf> map = new GetAllShelfs(shelfRepository)
						.call();

				return map;
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
	private void createContentPanel() {

		setTitle("AddShelfElement");
		setSize(500, 330);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		jlElementType.setBounds(21, 28, 96, 31);
		jlTitle.setBounds(21, 89, 42, 18);
		jtfTitle.setBounds(100, 88, 292, 19);
		btnAddBookCollection.setBounds(100, 192, 96, 31);
		btnDelete.setBounds(277, 192, 115, 31);

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

					CreateAnElementInAShelf element = new CreateAnElementInAShelf(
							shelfRepository,
							elementsRepository,
							Long.valueOf(comboBox.getSelectedItem().toString()),
							"CD", jtfTitle.getText(), null, 0, 0);

					return element.call();
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

	/**
	 * Clean all fields after action event operation
	 */
	private void cleanFields() {
		jtfTitle.setText("");

	}
}
