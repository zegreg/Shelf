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
		setSize(500, 200);
		setLocation(100, 100);
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
		jspContactos.setPreferredSize(new Dimension(475, 125));
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
					jtContactos.setValueAt(element.getKey(), i, 0);
					jtContactos.setValueAt(element.getValue()
							.getLoginPassword(), i, 1);
					jtContactos.setValueAt(element.getValue().getFullName(), i,
							2);
					jtContactos.setValueAt(element.getValue().getEmail(), i, 3);
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
