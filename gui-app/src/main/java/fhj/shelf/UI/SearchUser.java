package fhj.shelf.UI;


import javax.sound.midi.Patch;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import fhj.shelf.commandsDomain.EditUser;
import fhj.shelf.commandsDomain.GetOneUser;
import fhj.shelf.utils.User;
import fhj.shelf.utils.repos.UserRepository;


@SuppressWarnings("serial")
public class SearchUser extends JFrame {

	//Declara e cria os componentes   
	private JLabel jlNome;
	private JTextField jtfNome;
	private JLabel jlPassword;
	private JTextField jtfPassword;
	private JLabel jlFullname;
	private JTextField jtfFullname;
	private JLabel jlEmail;
	private JTextField jtfEmail;
	private JButton jbSearch;
	private JLabel jlVazia;
	private UserRepository repository;
	private JButton jbPatch;


	//Construtor
	public SearchUser(UserRepository repository) {    
		this.repository = repository;

		jlNome = new JLabel ("Nome: ");
		jtfNome = new JTextField(20);
		jlPassword = new JLabel ("Password: ");
		jtfPassword = new JTextField(20);
		jlFullname = new JLabel ("Fullname: ");
		jtfFullname = new JTextField(20);
		jlEmail = new JLabel ("E-mail: ");
		jtfEmail = new JTextField(20);
		jbSearch = new JButton("Search");
		jlVazia = new JLabel("");
		
		
		//Define as propriedades da janela
		setTitle("Procura por nome");
		setSize(350,234);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		jlNome.setBounds(14, 23, 65, 20);

		//Define o tamanho dos rótulos
		jlNome.setPreferredSize(new Dimension(65,20));
		jlPassword.setBounds(14, 54, 65, 20);
		jlPassword.setPreferredSize(new Dimension(65,20));
		jlFullname.setBounds(14, 85, 65, 20);
		jlFullname.setPreferredSize(new Dimension(65,20));
		jlEmail.setBounds(15, 116, 65, 20);
		jlEmail.setPreferredSize(new Dimension(65,20));
		jlVazia.setBounds(5, 105, 325, 10);
		jlVazia.setPreferredSize(new Dimension(325,10));

		/*Define as caixas de texto como não editáveis,
        uma vez que não será necessário a introdução de dados nestes campos*/
		jtfPassword.setBounds(84, 54, 166, 20);
		jtfPassword.setEditable(false);
		jtfFullname.setBounds(84, 85, 166, 20);
		jtfFullname.setEditable(false);
		jtfEmail.setBounds(84, 116, 166, 20);
		jtfEmail.setEditable(false);
		getContentPane().setLayout(null);

		//Adiciona os componentes à janela
		getContentPane().add(jlNome);
		jtfNome.setBounds(84, 23, 166, 20);
		getContentPane().add(jtfNome);
		getContentPane().add(jlPassword);
		getContentPane().add(jtfPassword);
		getContentPane().add(jlFullname);
		getContentPane().add(jtfFullname);
		getContentPane().add(jlEmail);
		getContentPane().add(jtfEmail);
		getContentPane().add(jlVazia);
		jbSearch.setBounds(84, 147, 73, 23);
		getContentPane().add(jbSearch);

		/*Registo do listener ActionListener junto do botão.
        Quando for gerado um evento por este componente, é
        criada uma instância da classe EventoJBProcurarNome,
        onde está o código que deve ser executado quando tal acontece*/


		jbSearch.addActionListener(new EventSearch());
		

	}
	

	private class EventSearch implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			class EventHandling extends SwingWorker<User, Object> {
			
				@Override
				protected User doInBackground() throws Exception {

					User user = (User) new GetOneUser(repository,
							jtfNome.getText()).call();

					return user;
				}

				@Override
				protected void done() {

					try {

						jtfPassword.setText(String.valueOf(((User) get())
								.getLoginPassword()));
						jtfFullname.setText(String.valueOf(((User) get())
								.getFullName()));
						jtfEmail.setText(String.valueOf(((User) get())
								.getEmail()));

					} catch (HeadlessException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
						JOptionPane.showMessageDialog(null,
								"Não foi encontrado nenhum contacto com esse nome!"
										+ e);
					     	e.printStackTrace();
							cleanFields();
					}

				}
			}
			new EventHandling().execute();

		}
	}
	
	private void cleanFields() {
		jtfNome.setText("");
		jtfPassword.setText("");
		jtfFullname.setText("");
		jtfEmail.setText("");
	}
}


       


