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
import fhj.shelf.utils.repos.UserRepository;

/**
 * 
 * Class whose instance gives a User Interface Representation of the domain Class {@code CreateUser()} 
 *@author Filipa Estiveira, Hugo Leal, José Oliveira
 */
@SuppressWarnings("serial")
public class SaveUser extends JFrame {

	/**
	 * Attributes
	 */
	private static  JTextField jtfName,jtfFullName,jtfPassword,jtfEmail;
	private static  JLabel jlName,jlPassword,jlFullName,jlEmail,jlEmpty ;
	private static JButton jbSave,jbDelete;
	private UserRepository repository;

/**
 * Constructor
 * @param repository
 */
	public SaveUser(UserRepository repository) {    
		this.repository = repository;
		
		jlName = new JLabel ("Name");
		jtfName = new JTextField(20);
		jlPassword = new JLabel ("Password");
		jtfPassword = new JTextField(20);
		jlFullName = new JLabel ("FullName");
		jtfFullName = new JTextField(20);
		jlEmail = new JLabel ("Email");
		jtfEmail = new JTextField(20);
		jbSave = new JButton("Save");
		jbDelete= new JButton("Delete");
		jlEmpty = new JLabel("");
		
		
		
		  //Sets window properties
		setTitle("New User");
		setSize(350,190);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setVisible(true);

		// Set the size of the labels
		jlName.setPreferredSize(new Dimension(65,20));
		jlPassword.setPreferredSize(new Dimension(65,20));
		jlFullName.setPreferredSize(new Dimension(65,20));
		jlEmail.setPreferredSize(new Dimension(65,20));
		jlEmpty.setPreferredSize(new Dimension(325,10));

		  //Adds components to the window
		getContentPane().add(jlName);
		getContentPane().add(jtfName);
		getContentPane().add(jlPassword);
		getContentPane().add(jtfPassword);
		getContentPane().add(jlFullName);
		getContentPane().add(jtfFullName);
		getContentPane().add(jlEmail);
		getContentPane().add(jtfEmail);
		getContentPane().add(jlEmpty);
		getContentPane().add(jbSave);
		getContentPane().add(jbDelete);

		/*ActionListener listener registration  in the button Save and button Delete.
         When an event is generated by this component, is
         created an instance of the inner class EventShelfSearch() and EventShelfDelete()*/
		jbSave.addActionListener(new EventModelExecuter());
		jbDelete.addActionListener(new EventModelExecuter());
	}



 // Getters
	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfFullName() {
		return jtfFullName;
	}


	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public JTextField getJtfPassword() {
		return jtfPassword;
	}

	public UserRepository getRepository() {
		return repository;
	}
	
	
	
	/**
     * Inner class that contains the code that is executed when it is press the button jbSave
     * This Class implements ActionListener Interface, and invoke actionPerformed method.
     * The action is made in an Background Thread, by run SwingWorker framework by execute a EventHandling() object.
     * 
     *@author Filipa Estiveira, Hugo Leal, José Oliveira
     */
	private class EventModelExecuter implements ActionListener {

		public void actionPerformed(ActionEvent ev) {

			if(ev.getSource()==jbSave)
			{

				if (jtfName.getText().equals("") || jtfPassword.getText().equals("") || jtfFullName.getText().equals("") || jtfEmail.getText().equals(""))
					JOptionPane.showMessageDialog(null,"All fields are required!");
				else {
					try {

						new EventHandling().execute();

					}
					catch(Exception e) {
						System.out.println("Unable to perform the operation. ");
						e.printStackTrace();
					}
				}
			}

			else if(ev.getSource()==jbDelete)
			{
				deleteTextField();
			}

		}

	}

	private void deleteTextField() {
		jtfName.setText("");
		jtfPassword.setText("");
		jtfFullName.setText("");
		jtfEmail.setText("");
	}


	/**
	 * Inner Class whose instance create a User in the domain
	 * 
	 *@author Filipa Estiveira, Hugo Leal, José Oliveira
	 */
	private class EventHandling extends SwingWorker<String, Void>{


		@Override
		protected  String  doInBackground() throws Exception {

			return new CreateUser(getRepository(), getJtfName().getText(), getJtfPassword().getText(),
					getJtfEmail().getText(), getJtfFullName().getText()).call();

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


	}

