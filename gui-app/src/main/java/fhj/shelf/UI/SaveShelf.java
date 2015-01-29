package fhj.shelf.UI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import fhj.shelf.commandsDomain.CreateShelf;
import fhj.shelf.utils.repos.ShelfRepository;
import fhj.shelf.utils.repos.UserRepository;

/**
 * 
 * Class whose instance gives a User Interface Representation of the domain
 * Class {@code CreateShelf()}
 *
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
@SuppressWarnings("serial")
public class SaveShelf extends JFrame {

	/**
	 * Attributes
	 * 
	 */
	private static JLabel jlName;
	private static JTextField jtfnbElments;
	private static JButton jbSave;
	private static JButton jbDelete;
	private static JLabel jlVazia;
	private ShelfRepository shelfRepository;
	private UserRepository repository;

	/**
	 * Constructor
	 * 
	 * @param repository
	 * @param shelfRepository
	 */
	public SaveShelf(UserRepository repository, ShelfRepository shelfRepository) {
		this.shelfRepository = shelfRepository;
		this.repository = repository;

		jlName = new JLabel("Shelf Capacity");
		jtfnbElments = new JTextField(4);
		jbSave = new JButton("Save");
		jbDelete = new JButton("Delete");
		jlVazia = new JLabel("");

		// Sets window properties
		setTitle("New Shelf");
		setSize(350, 157);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set the size of the labels
		setVisible(true);
		jlVazia.setPreferredSize(new Dimension(325, 10));
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jlName.setPreferredSize(new Dimension(78, 20));

		// Adds components to the window
		getContentPane().add(jlName);
		getContentPane().add(jtfnbElments);
		getContentPane().add(jlVazia);
		getContentPane().add(jbSave);
		getContentPane().add(jbDelete);

		/*
		 * ActionListener listener registration in the button Save and button
		 * Delete.          When an event is generated by this component, is
		 *          created an instance of the inner class EventShelfSearch()
		 * and EventShelfDelete()
		 */
		jbSave.addActionListener(new EventShelfSave());
		jbDelete.addActionListener(new EventShelfDelete());
	}

	public ShelfRepository getShelfRepository() {
		return shelfRepository;
	}

	public JTextField getjtfnbElements() {
		return jtfnbElments;
	}

	/**
	 * Inner class that contains the code that is executed when it is press the
	 * button jbSave This Class implements ActionListener Interface, and invoke
	 * actionPerformed method. The action is made in an Background Thread, by
	 * run SwingWorker framework by execute a EventHandling() object.
	 * 
	 */
	private class EventShelfSave implements ActionListener {

		public void actionPerformed(ActionEvent ev) {

			if (jtfnbElments.getText().equals(""))
				JOptionPane.showMessageDialog(null, "All fields are required!");
			else {
				try {

					new eventHandling().execute();

				} catch (Exception e) {
					System.out.println("Unable to perform the operation. ");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Class whose execution create a shelf in the domain
	 */
	class eventHandling extends SwingWorker<String, Void> {

		@Override
		protected String doInBackground() throws Exception {

			CreateShelf createShelf = new CreateShelf(getShelfRepository(),
					Integer.valueOf(getjtfnbElements().getText()));

			return createShelf.call();
		}

		@Override
		protected void done() {
			String str = null;
			try {
				str = (String) get();
			} catch (InterruptedException e) {
				e.getMessage();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(null,
					"Data were successfully saved!  " + str);

			deleteFields();
			dispose();
		}

	}

	/**
	 * Method to clean all fields in JTextField
	 */
	private void deleteFields() {
		jtfnbElments.setText("");

	}

	/**
	 * Inner class that contains the code that is executed when it is pressed
	 * the jbDelete button.
	 * 
	 */
	private class EventShelfDelete implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			deleteFields();
		}
	}

}
