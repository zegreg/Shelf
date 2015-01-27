package fhj.shelf.UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import fhj.shelf.commandsDomain.CreateUser;
import fhj.shelf.commandsDomain.EditUser;
import fhj.shelf.utils.repos.UserRepository;

/**
 * 
 * Class whose instance gives a User Interface Representation of the domain Class {@code EditUser()} 
 *@author Filipa Estiveira, Hugo Leal, José Oliveira
 */
@SuppressWarnings("serial")
public class PatchUser extends JFrame {

	/**
	 * Declares and creates components
	 */
	private JTextField jtfName, jtfNewPassword, jtfOldPassword;
	private JLabel jlUsername, jlPassword, jlNewPassword, jlEmpty;
	private JButton jbSaveChange;
	private UserRepository repository;

	/**
	 * Constructor
	 * 
	 * @param repository
	 */
	public PatchUser(UserRepository repository) {
		this.repository = repository;

		jlUsername = new JLabel("Username");
		jtfName = new JTextField(20);
		jlPassword = new JLabel("OldPassword");
		jtfOldPassword = new JTextField(20);
		jlNewPassword = new JLabel("NewPassword");
		jtfNewPassword = new JTextField(20);
		jbSaveChange = new JButton("Save");
		jlEmpty = new JLabel("");

		/* Sets window properties */
		setTitle("New User");
		setSize(350, 190);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setVisible(true);

		/* Sets the size of the labels */
		jlUsername.setPreferredSize(new Dimension(65, 20));
		jlPassword.setPreferredSize(new Dimension(65, 20));
		jlNewPassword.setPreferredSize(new Dimension(65, 20));
		jlEmpty.setPreferredSize(new Dimension(325, 10));

		/* Adds components to the window */
		getContentPane().add(jlUsername);
		getContentPane().add(jtfName);
		getContentPane().add(jlPassword);
		getContentPane().add(jtfOldPassword);
		getContentPane().add(jlNewPassword);
		getContentPane().add(jtfNewPassword);
		getContentPane().add(jlEmpty);
		getContentPane().add(jbSaveChange);

		/*
		 * Registration ActionListener listener with the buttons. When
		 * an event is generated by these components, is created an
		 * instance of EventModelExecuter, where is the code that
		 * should be executed when this happens
		 */
		jbSaveChange.addActionListener(new EventModelExecuter());
	}

	/**
	 * Inner class that contains the code that is executed when you press the
	 * button jbSaveChange
	 * 
	 * @author José Oliveira
	 *
	 */
	private class EventModelExecuter implements ActionListener {

		public void actionPerformed(ActionEvent ev) {

			if (ev.getSource() == jbSaveChange) {

				if (jtfName.getText().equals("")
						|| jtfOldPassword.getText().equals("")
						|| jtfNewPassword.getText().equals(""))
					JOptionPane.showMessageDialog(null,
							"All fields are required!");
				else {
					try {

						new EventWorker().execute();

					} catch (Exception e) {
						System.out.println("Unable to perform the operation. ");
						e.printStackTrace();
					}
				}
			}

		}

	}

	/**
	 * Inner Class to execute a StringWorker Thread
	 * 
	 *
	 */
	private class EventWorker extends SwingWorker<String, Void> {

		@Override
		protected String doInBackground() throws Exception {
			
			return new EditUser(repository, jtfName.getText(),
					jtfOldPassword.getText(), jtfNewPassword.getText()).call();
		}

		@Override
		protected void done() {
			try {
				JOptionPane.showMessageDialog(null, get());
			} catch (HeadlessException e) {

				e.printStackTrace();
			} catch (InterruptedException e) {

				e.printStackTrace();
			} catch (ExecutionException e) {

				e.printStackTrace();
			}

			deleteTextField();
			dispose();

		}

	}

	/**
	 * Method to put field empty
	 */
	private void deleteTextField() {
		jtfName.setText("");
		jtfOldPassword.setText("");
		jtfNewPassword.setText("");

	}

}
