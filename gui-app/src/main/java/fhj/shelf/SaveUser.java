package fhj.shelf;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.TreeMap;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import fhj.shelf.utils.repos.UserRepository;


public class SaveUser extends JFrame {

    //Declara e cria os componentes   
    JLabel jlName = new JLabel ("Name");
    JTextField jtfName = new JTextField(20);
    JLabel jlPassword = new JLabel ("Password");
    JTextField jtfPassword = new JTextField(20);
    JLabel jlFullName = new JLabel ("FullName");
    JTextField jtfFullName = new JTextField(20);
    JLabel jlEmail = new JLabel ("Email");
    JTextField jtfEmail = new JTextField(20);
    JButton jbGuardar = new JButton("Save");
    JButton jbLimpar= new JButton("Delete");
    JLabel jlVazia = new JLabel("");
    
    //Construtor
    public SaveUser(UserRepository repository) {    
        
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
        jlVazia.setPreferredSize(new Dimension(325,10));
        
        //Adiciona os componentes à janela
        getContentPane().add(jlName);
        getContentPane().add(jtfName);
        getContentPane().add(jlPassword);
        getContentPane().add(jtfPassword);
        getContentPane().add(jlFullName);
        getContentPane().add(jtfFullName);
        getContentPane().add(jlEmail);
        getContentPane().add(jtfEmail);
        getContentPane().add(jlVazia);
        getContentPane().add(jbGuardar);
        getContentPane().add(jbLimpar);
        
        /*Registo do listener ActionListener junto dos botões.
        Quando for gerado um evento por estes componentes, é
        criada uma instância da classe EventoJBGuardar ou EventoJBLimpar,
        onde está o código que deve ser executado quando tal acontece*/
        jbGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
				if (jtfName.getText().equals("") || jtfPassword.getText().equals("") || jtfFullName.getText().equals("") || jtfEmail.getText().equals(""))
	                JOptionPane.showMessageDialog(null,"All fields are required!");
	            else {
	                try {
	                	
	                	
	                	Map<String, String> map = new TreeMap<>();
	                	
	                	map.put("username", jtfName.getText());
	                	map.put("password", jtfPassword.getText());
	                	map.put("email", jtfEmail.getText());
	                	map.put("fullname", jtfFullName.getText());
	                	
	                	
	                	
	                	new Teste(map, repository).execute();

	                	               
	                   
	                }
	                catch(Exception e) {
	                    System.out.println("Unable to perform the operation. ");
	                    e.printStackTrace();
	                }
	            }
	        }
		
		});
        
        
        jbLimpar.addActionListener(new EventoJBLimpar());
    }
    
//    public static void main(String[] args) {
//        new SaveUser();
//    }
    
    //Classe interna que contém o código que é executado quando se pressiona o botão jbGuardar
    private class EventoJBGuardar implements ActionListener {
        
        public void actionPerformed(ActionEvent ev) {
//
//             if (jtfName.getText().equals("") || jtfPassword.getText().equals("") || jtfFullName.getText().equals("") || jtfEmail.getText().equals(""))
//                JOptionPane.showMessageDialog(null,"All fields are required!");
//            else {
//                try {
//                	
//                	
//                	Map<String, String> map = new TreeMap<>();
//                	
//                	map.put("username", jtfName.getText());
//                	map.put("password", jtfPassword.getText());
//                	map.put("email", jtfEmail.getText());
//                	map.put("fullname", jtfFullName.getText());
//                	
//                	
//                	
//                	new Teste(map).execute();
//
//                	               
//                   
//                }
//                catch(Exception e) {
//                    System.out.println("Unable to perform the operation. ");
//                    e.printStackTrace();
//                }
//            }
        }
    	
    }
    
    private void limpaCampos() {
        jtfName.setText("");
        jtfPassword.setText("");
        jtfFullName.setText("");
        jtfEmail.setText("");
    }
    
    //Classe interna que contém o código que é executado quando se pressiona o botão jbLimpar
    private class EventoJBLimpar implements ActionListener {
        
        public void actionPerformed(ActionEvent ev) {
            limpaCampos();
        }
    }
    
    
    class Teste extends SwingWorker{


    	Map<String, String> map;

    	private UserRepository repository;


    	public Teste(Map<String, String> map2, UserRepository repository2) {
    		this.repository = repository2;
    		this.map = map2;
    	}


    	@Override
    	protected  String  doInBackground() throws Exception {

    		PostUser user = (PostUser) new PostUser.Factory(repository).newInstance(map);
    		return user.validLoginPostExecute();

    	}


    	@Override
    	protected void done() {

    		JOptionPane.showMessageDialog(null,"Data were successfully saved!");

    		//Invoca o método implementado em baixo
    		limpaCampos();
    	}




    }
}
