package fhj.shelf.UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import javax.swing.ListSelectionModel;

import javax.swing.ScrollPaneConstants;

import fhj.shelf.commandsDomain.GetAllUsers;
import fhj.shelf.utils.repos.AbstractUser;
import fhj.shelf.utils.repos.UserRepository;

@SuppressWarnings("serial")
public class UserDetails extends JFrame {

	private static final int DIM_HEIGHT = 125;
	private static final int DIM_WIDTH = 475;
	private static final int LOCATION_Y = 100;
	private static final int LOCATION_X = 100;
	private static final int SIZE_HEIGHT = 200;
	private static final int SIZE_WIDTH = 500;
	/**
	 * Declares components
	 */
	private static JLabel jlTitulo;
	private static DefaultTableModel tmContactos;
	private static JTable jtContactos;
	private static JScrollPane jspContactos;
	private UserRepository userRepository;

	/**
	 * Constructor
	 * 
	 * @param repository
	 */
	public UserDetails(UserRepository repository) {
		this.userRepository = repository;

		createAndShowGUI();

	}

	private void createAndShowGUI() {
		createJTable();

		// Sets window properties
		setTitle("User List");
		setSize(SIZE_WIDTH, SIZE_HEIGHT);
		setLocation(LOCATION_X, LOCATION_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setVisible(true);

		// Adds components to the window
		getContentPane().add(jlTitulo);
		getContentPane().add(jspContactos);

		new EventUserDetailsHandling().execute();
	}

	/**
	 * Method to create a JTable with a 4 columns and 4 rows
	 * 
	 * @return
	 */
	private JTable createJTable() {
		jlTitulo = new JLabel("UserDetails");
		jtContactos = new JTable(new DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, },
				new String[] { "Name", "Password", "FullName", "E-mail" }));

		jspContactos = new JScrollPane(jtContactos);
		jspContactos
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspContactos.setPreferredSize(new Dimension(DIM_WIDTH, DIM_HEIGHT));
		jtContactos.setCellSelectionEnabled(true);
		// Prevents the selection of more than one table row simultaneously
		jtContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return jtContactos;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	/**
	 * Inner Class that implements ActionListener Interface, and invoke
	 * actionPerformed method. The action is made in an Background Thread, by
	 * run SwingWorker framework.
	 */
	private class EventUserDetailsHandling extends
			SwingWorker<Map<String, AbstractUser>, Void> {

		private static final int JTCEV_COLUMN = 3;
		private static final int JTCFV_COLUMN = 2;
		private static final int JTCPV_COLUMN = 1;
		private static final int JTCV_COLUMN = 0;

		@Override
		protected Map<String, AbstractUser> doInBackground() throws Exception {
			return new GetAllUsers(getUserRepository()).call();

		}

		@Override
		protected void done() {

			int i = 0;

			try {
				for (Entry<String, AbstractUser> element : get().entrySet()) {

					// Fill the cells in the empty line. The numbering of the
					// columns starts at 0
					jtContactos.setValueAt(element.getKey(), i, JTCV_COLUMN);
					jtContactos.setValueAt(element.getValue()
							.getLoginPassword(), i, JTCPV_COLUMN);
					jtContactos.setValueAt(element.getValue().getFullName(), i,
							JTCFV_COLUMN);
					jtContactos.setValueAt(element.getValue().getEmail(), i, JTCEV_COLUMN);
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
