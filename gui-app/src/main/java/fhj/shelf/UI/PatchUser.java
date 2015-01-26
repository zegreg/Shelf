package fhj.shelf.UI;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import fhj.shelf.commandsDomain.EditUser;
import fhj.shelf.utils.repos.UserRepository;

import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class PatchUser extends JFrame {
	private JTextField jtfUsername;
	private JTextField jtfOldPassword;
	private JTextField jtfNewPassword;
	private UserRepository userRepository;
	private JTextField jtfName, jtfNewP, jtfPassword;
	private JLabel jlUsername, jlOldPassword, jlNewPassword, jlEmpty;
	private JButton jbPatch;
	

	public PatchUser(UserRepository userRepo) {

		this.userRepository = userRepo;

		jlUsername = new JLabel("UserName");
		jtfName = new JTextField(20);
		jlOldPassword = new JLabel("OldPassword");
		jtfPassword = new JTextField(20);
		jlNewPassword = new JLabel("NewPassword");
		jtfNewP = new JTextField(20);
		jbPatch = new JButton("Edit");
		jlEmpty = new JLabel("");

		// Define as porpriedades da janela
		setTitle("Password Change ");
		setSize(350, 190);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(new FlowLayout());
		
		// Define o tamanho dos rótulos
		jlUsername.setPreferredSize(new Dimension(65, 20));
		jlOldPassword.setPreferredSize(new Dimension(65, 20));
		jlNewPassword.setPreferredSize(new Dimension(65, 20));
		jlEmpty.setPreferredSize(new Dimension(325, 10));
		

		// Adiciona os componentes à janela
		getContentPane().add(jlUsername);
		getContentPane().add(jtfName);
		getContentPane().add(jlOldPassword);
		getContentPane().add(jtfPassword);
		getContentPane().add(jlNewPassword);
		getContentPane().add(jtfNewP);
		getContentPane().add(jlEmpty);
		getContentPane().add(jbPatch);

		jbPatch.addActionListener(new EventChange());

	}
	
	public JTextField getJtfName() {
		return jtfName;
	}
	public JTextField getJtfOldPassword() {
		return jtfOldPassword;
	}
	public JTextField getJtfNewPassword() {
		return jtfNewPassword;
	}
	public UserRepository getUserRepository() {
		return userRepository;
	}

	private class EventChange implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
//			ensureEventThread();
			System.out.println(Thread.currentThread());
			if (getJtfName().getText().equals("") || getJtfOldPassword().getText().equals("") || getJtfNewPassword().getText().equals("") )
				JOptionPane.showMessageDialog(null,"All fields are required!");
			else {
				
				
					new EventWorker().execute();

			}
		}

	 class EventWorker extends SwingWorker<String, Void> {

			@Override
			protected String doInBackground() throws Exception {
					System.out.println(Thread.currentThread());
				return new EditUser(getUserRepository(), getJtfName().getText(), getJtfOldPassword().getText(),
						getJtfNewPassword().getText()).call();
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
				cleanFields();
				dispose();
			}

		}
	}

	private void cleanFields() {
		jtfUsername.setText("");
		jtfOldPassword.setText("");
		jtfNewPassword.setText("");

	}
	
	private void ensureEventThread() {
		// throws an exception if not invoked by the
		// event thread.
		if ( SwingUtilities.isEventDispatchThread() ) 
		 return;
		
		throw new RuntimeException("only the event " +
				"thread should invoke this method");
	}


	// public static void main(String[] args) {
	// SwingUtilities.invokeLater(new Runnable() {
	//
	// @Override
	// public void run() {
	// UserRepository repository = new InMemoryUserRepository();
	// new PatchUser(repository);
	//
	// }
	// });
	// }

}
