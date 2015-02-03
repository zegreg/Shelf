package fhj.shelf.UI;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import fhj.shelf.commandsDomain.EraseShelf;
import fhj.shelf.commandsDomain.GetOneShelf;
import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.repos.ShelfRepository;

/**
 * 
 * Class whose instance gives a User Interface Representation of the domain
 * Class {@code GetOneShelf()}
 *
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
@SuppressWarnings("serial")
public class SearchShelf extends JFrame {

	/**
	 * Attributes
	 */
	private static JLabel jlShelfId;
	private static JTextField jtfName;
	private static JLabel jlCapacity;
	private static JTextField jtfPassword;
	private static JLabel jlFreeSpace;
	private static JTextField jtfFreeSpace;
	private static JButton jbSearch;
	private static JLabel jlVazia;
	private ShelfRepository repository;
	private JButton btnDelete;

	/**
	 * Constructor
	 * 
	 * @param shelfrepository
	 */
	public SearchShelf(ShelfRepository shelfrepository) {
		this.repository = shelfrepository;
		jlShelfId = new JLabel("SheflId");
		jtfName = new JTextField(20);
		jlCapacity = new JLabel("Capacity");
		jtfPassword = new JTextField(20);
		jlFreeSpace = new JLabel("FreeSpace");
		jtfFreeSpace = new JTextField(20);
		jbSearch = new JButton("Search");
		jlVazia = new JLabel("");
		btnDelete = new JButton("Delete");

		// Sets window properties
		setTitle("Search by ShelfId");
		setSize(350, 234);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		jlShelfId.setBounds(14, 23, 65, 20);

		// Set the size of the labels
		jlShelfId.setPreferredSize(new Dimension(65, 20));
		jlCapacity.setBounds(14, 54, 65, 20);
		jlCapacity.setPreferredSize(new Dimension(65, 20));
		jlFreeSpace.setBounds(14, 85, 65, 20);
		jlFreeSpace.setPreferredSize(new Dimension(65, 20));
		jlVazia.setBounds(5, 105, 325, 10);
		jlVazia.setPreferredSize(new Dimension(325, 10));

		/*
		 * Sets the text boxes as non-editable,          since it is not
		 * necessary to enter data in these fields
		 */
		jtfPassword.setBounds(84, 54, 166, 20);
		jtfPassword.setEditable(false);
		jtfFreeSpace.setBounds(84, 85, 166, 20);
		jtfFreeSpace.setEditable(false);
		getContentPane().setLayout(null);

		// Adds components to the window
		getContentPane().add(jlShelfId);
		jtfName.setBounds(84, 23, 166, 20);
		getContentPane().add(jtfName);
		getContentPane().add(jlCapacity);
		getContentPane().add(jtfPassword);
		getContentPane().add(jlFreeSpace);
		getContentPane().add(jtfFreeSpace);
		getContentPane().add(jlVazia);
		jbSearch.setBounds(84, 142, 73, 23);
		getContentPane().add(jbSearch);
		btnDelete.setBounds(178, 142, 73, 23);

		getContentPane().add(btnDelete);

		/*
		 * ActionListener listener registration in the button Search and button
		 * Delete.          When an event is generated by this component, is
		 *          created an instance of the inner class EventShelfSearch()
		 * and EventShelfDelete()
		 */
		jbSearch.addActionListener(new EventShelfSearch());
		btnDelete.addActionListener(new EventShelfDelete());

	}

	public ShelfRepository getRepository() {
		return repository;
	}

	/**
	 * Inner Class that implements ActionListener Interface, and invoke
	 * actionPerformed method for Search Button. The action is made in an
	 * Background Thread, by run SwingWorker framework by execute a
	 * EventHandling() object.
	 */
	private class EventShelfSearch implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (jtfName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Fill in the field name !");
				cleanFields();
			} else {

				new EventHandling().execute();
			}

		}

	}

	/**
	 * Inner Class call in the actionPerformed method
	 * 
	 * @author Filipa Estiveira, Hugo Leal, José Oliveira
	 */
	private class EventHandling extends SwingWorker<Shelf, Void> {
		boolean nameFound = false;

		@Override
		protected Shelf doInBackground() throws Exception {

			return (Shelf) new GetOneShelf(getRepository(),
					Long.valueOf(jtfName.getText())).call();
		}

		@Override
		protected void done() {

			try {

				jtfPassword.setText(String.valueOf(((Shelf) get())
						.getCapacity()));
				jtfFreeSpace.setText(String.valueOf(((Shelf) get())
						.getFreeSpace()));

			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Shelf doesn´t exist" + e);
				cleanFields();
				e.printStackTrace();
			}

		}
	}

	private class EventShelfDelete implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (jtfName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "You must searchFirst");
				cleanFields();
			} else {

				new EventHandlingDelete().execute();
			}

		}

	}

	private class EventHandlingDelete extends SwingWorker<String, Void> {

		@Override
		protected String doInBackground() throws Exception {

			String eraseShelf = new EraseShelf(repository, Long.valueOf(jtfName
					.getText())).call();
			return eraseShelf;
		}

		@Override
		protected void done() {

			try {
				JOptionPane.showMessageDialog(null,
						"Haven't found a shelf with this id!" + get());
			} catch (HeadlessException | InterruptedException
					| ExecutionException e) {

				e.printStackTrace();
			}
			cleanFields();

		}
	}

	/**
	 * Method to clean all fields in JTextField
	 */
	private void cleanFields() {
		jtfName.setText("");
		jtfPassword.setText("");
		jtfFreeSpace.setText("");

	}
}