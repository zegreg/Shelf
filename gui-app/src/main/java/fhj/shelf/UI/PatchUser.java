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


@SuppressWarnings("serial")
public class PatchUser extends JFrame {

	//Declara e cria os componentes   
	
	private JTextField jtfName,jtfNewPassword,jtfOldPassword;
	private JLabel jlUsername,jlPassword,jlNewPassword,jlEmpty ;
	private JButton jbSaveChange;
	private UserRepository repository;

	//Construtor
	public PatchUser(UserRepository repository) {    
		this.repository = repository;
		
		jlUsername = new JLabel ("Username");
		jtfName = new JTextField(20);
		jlPassword = new JLabel ("OldPassword");
		jtfOldPassword = new JTextField(20);
		jlNewPassword = new JLabel ("NewPassword");
		jtfNewPassword = new JTextField(20);
		jbSaveChange = new JButton("Save");
		jlEmpty = new JLabel("");
		
		
		
		//Define as porpriedades da janela
		setTitle("New User");
		setSize(350,190);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setVisible(true);

		//Define o tamanho dos rótulos
		jlUsername.setPreferredSize(new Dimension(65,20));
		jlPassword.setPreferredSize(new Dimension(65,20));
		jlNewPassword.setPreferredSize(new Dimension(65,20));
		jlEmpty.setPreferredSize(new Dimension(325,10));

		//Adiciona os componentes à janela
		getContentPane().add(jlUsername);
		getContentPane().add(jtfName);
		getContentPane().add(jlPassword);
		getContentPane().add(jtfOldPassword);
		getContentPane().add(jlNewPassword);
		getContentPane().add(jtfNewPassword);
		getContentPane().add(jlEmpty);
		getContentPane().add(jbSaveChange);

		/*Registo do listener ActionListener junto dos botões.
        Quando for gerado um evento por estes componentes, é
        criada uma instância da classe EventoJBGuardar ou EventoJBLimpar,
        onde está o código que deve ser executado quando tal acontece*/
		jbSaveChange.addActionListener(new EventModelExecuter());
	}

	
	
	
	//Classe interna que contém o código que é executado quando se pressiona o botão jbGuardar
	private class EventModelExecuter implements ActionListener {

		public void actionPerformed(ActionEvent ev) {

			if(ev.getSource()==jbSaveChange)
			{

				if (jtfName.getText().equals("") || jtfOldPassword.getText().equals("") || jtfNewPassword.getText().equals("") )
					JOptionPane.showMessageDialog(null,"All fields are required!");
				else {
					try {

						new EventWorker().execute();

					}
					catch(Exception e) {
						System.out.println("Unable to perform the operation. ");
						e.printStackTrace();
					}
				}
			}

			

		}

	}


	private class EventWorker extends SwingWorker<String, Void> {
		
					@Override
					protected String doInBackground() throws Exception {
							System.out.println(Thread.currentThread());
						return new EditUser(repository ,jtfName.getText(), jtfOldPassword.getText(),
								jtfNewPassword.getText()).call();
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


	private void deleteTextField() {
		jtfName.setText("");
		jtfOldPassword.setText("");
		jtfNewPassword.setText("");
		
	}

	}


