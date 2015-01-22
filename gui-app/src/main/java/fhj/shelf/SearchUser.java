package fhj.shelf;

/**
 * Write a description of class ProcuraNome here.
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.Map.Entry;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import fhj.shelf.utils.repos.AbstractUser;
import fhj.shelf.utils.repos.UserRepository;


public class SearchUser extends JFrame {

    //Declara e cria os componentes   
    JLabel jlNome = new JLabel ("Nome: ");
    JTextField jtfNome = new JTextField(20);
    JLabel jlPassword = new JLabel ("Password: ");
    JTextField jtfPassword = new JTextField(20);
    JLabel jlFullname = new JLabel ("Fullname: ");
    JTextField jtfFullname = new JTextField(20);
    JLabel jlEmail = new JLabel ("E-mail: ");
    JTextField jtfEmail = new JTextField(20);
    JButton jbProcurar = new JButton("Procurar");
    JLabel jlVazia = new JLabel("");
    private UserRepository repository;
    
    //Construtor
    public SearchUser(UserRepository repository) {    
        this.repository = repository;
        
        
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
        jbProcurar.setBounds(132, 147, 73, 23);
        getContentPane().add(jbProcurar);
        
        /*Registo do listener ActionListener junto do botão.
        Quando for gerado um evento por este componente, é
        criada uma instância da classe EventoJBProcurarNome,
        onde está o código que deve ser executado quando tal acontece*/
        findUserbyName(repository);
    }


	private void findUserbyName(UserRepository repository) {
		jbProcurar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				class Teste extends SwingWorker<Object, Object>{
		    		boolean encontrouNome = false;


		    		@Override
		    		public  Object  doInBackground() {
		    			for (Entry<String, AbstractUser> element : repository.getUsers().entrySet()) {
		    				if (element.getValue().getLoginName().equals(jtfNome.getText())) {
		    					encontrouNome = true;
		    					jtfPassword.setText(String.valueOf(element.getValue().getLoginPassword()));
		    					jtfFullname.setText(String.valueOf(element.getValue().getFullName()));
		    					jtfEmail.setText(String.valueOf(element.getValue().getEmail()));
		    				}
		    			}
		    			return encontrouNome;
		    		}

		    		@Override
		    		protected void done() {
		    			if (jtfNome.getText().equals("")) {
		    				JOptionPane.showMessageDialog(null, "Preencha o campo nome!");
		    				limpaCampos();
		    			} else{

		    				if (encontrouNome == false) {
		    					JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum contacto com esse nome!");
		    					limpaCampos();
		    				}

		    			}
		    		}
		    	}


		    	new Teste().execute();
		    }
				
			
		});
	}
        
    
//    public static void main(String[] args) {
//        new SearchUser();
//    }
    
    private void limpaCampos() {
        jtfNome.setText("");
        jtfPassword.setText("");
        jtfFullname.setText("");
        jtfEmail.setText("");
    }
   	
    	
}


       


