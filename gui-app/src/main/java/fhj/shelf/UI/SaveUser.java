package fhj.shelf.UI;

/**
 * Write a description of class NovoCliente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import fhj.shelf.commandsDomain.CreateUser;
import fhj.shelf.utils.repos.UserRepository;


public class SaveUser extends JFrame {

	//Declara e cria os componentes   
	
	private JTextField jtfName,jtfFullName,jtfPassword,jtfEmail;
	private JLabel jlName,jlPassword,jlFullName,jlEmail,jlEmpty ;
	private JButton jbSave,jbDelete;
	private UserRepository repository;

	//Construtor
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
		
		
		
		//Define as porpriedades da janela
		setTitle("New User");
		setSize(350,190);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setVisible(true);

		//Define o tamanho dos rótulos
		jlName.setPreferredSize(new Dimension(65,20));
		jlPassword.setPreferredSize(new Dimension(65,20));
		jlFullName.setPreferredSize(new Dimension(65,20));
		jlEmail.setPreferredSize(new Dimension(65,20));
		jlEmpty.setPreferredSize(new Dimension(325,10));

		//Adiciona os componentes à janela
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

		/*Registo do listener ActionListener junto dos botões.
        Quando for gerado um evento por estes componentes, é
        criada uma instância da classe EventoJBGuardar ou EventoJBLimpar,
        onde está o código que deve ser executado quando tal acontece*/
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
	
	
	
	//Classe interna que contém o código que é executado quando se pressiona o botão jbGuardar
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


	private class EventHandling extends SwingWorker{


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

			//Invoca o método implementado em baixo
			deleteTextField();
		}

	}


	}

