package fhj.shelf.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import fhj.shelf.commandsDomain.GetAllShelfs;
import fhj.shelf.utils.repos.AbstractShelf;
import fhj.shelf.utils.repos.ShelfRepository;
import fhj.shelf.utils.repos.UserRepository;

@SuppressWarnings("serial")
public class ShelfDetails extends JFrame {

	// Declares and creates components
	private static JTable jtShelfContents;
	private static JLabel jlTitle;
	private static JScrollPane jspShelfContents;
	private UserRepository repository;
	private ShelfRepository shelfRepository;

	// Construtor
	public ShelfDetails(UserRepository repository,
			ShelfRepository shelfRepository) {

		this.repository = repository;
		this.shelfRepository = shelfRepository;

		createAndShowGUI();

	}

	public ShelfRepository getShelfRepository() {
		return shelfRepository;
	}

	/**
	 * Method to create the Frame
	 */
	private void createAndShowGUI() {
		createContentTable();
		jlTitle = new JLabel("ShelfsDetails");
		// Sets window properties
		setTitle("Shelfs Details");
		setSize(500, 200);
		setLocation(450, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setVisible(true);

		// Adds components to the window
		getContentPane().add(jlTitle);
		getContentPane().add(jspShelfContents);

		new EventShelfDetailsHandling().execute();
	}

	/**
	 * Method to create a JTable
	 * 
	 * @return
	 */
	private JTable createContentTable() {

		jtShelfContents = new JTable(new DefaultTableModel(new Object[][] {
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, }, new String[] { "idElement",
				"Number Elements", "Free Space" }));

		jspShelfContents = new JScrollPane(jtShelfContents);
		jspShelfContents
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspShelfContents.setPreferredSize(new Dimension(475, 125));
		jtShelfContents.setCellSelectionEnabled(true);
		// Prevents the selection of more than one table row simultaneously
		jtShelfContents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return jtShelfContents;
	}

	/**
	 * Inner Class that implements ActionListener Interface, and invoke
	 * actionPerformed method. The action is made in an Background Thread, by
	 * run SwingWorker framework.
	 */
	private class EventShelfDetailsHandling extends
			SwingWorker<Map<Long, AbstractShelf>, Void> {

		@Override
		protected Map<Long, AbstractShelf> doInBackground() throws Exception {

			return new GetAllShelfs(
					getShelfRepository()).call();

		}

		@Override
		protected void done() {

			int i = 0;

			try {
				for (Entry<Long, AbstractShelf> element : get().entrySet()) {

					// Fill the cells in the empty line. The numbering of the
					// columns starts at 0
					jtShelfContents.setValueAt(element.getKey(), i, 0);
					jtShelfContents.setValueAt(element.getValue().getCapacity()
							- element.getValue().getFreeSpace(), i, 1);
					jtShelfContents.setValueAt(element.getValue()
							.getFreeSpace(), i, 2);

					i++;
				}
			} catch (InterruptedException e) {

				e.printStackTrace();
			} catch (ExecutionException e) {

				e.printStackTrace();
			}

		}

	}

}
