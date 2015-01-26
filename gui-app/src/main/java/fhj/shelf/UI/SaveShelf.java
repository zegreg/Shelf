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




public class SaveShelf extends JFrame {

    //Declara e cria os componentes   
    JLabel jlName = new JLabel ("Shelf Capacity");
    JTextField jtfnbElments = new JTextField(4);
    JButton jbGuardar = new JButton("Save");
    JButton jbLimpar= new JButton("Delete");
    JLabel jlVazia = new JLabel("");
    
    private ShelfRepository shelfRepository;
    private UserRepository repository;
    
    //Construtor
    public SaveShelf( UserRepository repository,ShelfRepository shelfRepository) {    
        this.shelfRepository = shelfRepository;
        this.repository = repository;
        
        
        //Define as porpriedades da janela
        setTitle("New Shelf");
        setSize(350,157);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        jlVazia.setPreferredSize(new Dimension(325,10));
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        //Define o tamanho dos rótulos
        jlName.setPreferredSize(new Dimension(78, 20));
        
        //Adiciona os componentes à janela
        getContentPane().add(jlName);
        getContentPane().add(jtfnbElments);
        getContentPane().add(jlVazia);
        getContentPane().add(jbGuardar);
        getContentPane().add(jbLimpar);
        
        /*Registo do listener ActionListener junto dos botões.
        Quando for gerado um evento por estes componentes, é
        criada uma instância da classe EventoJBGuardar ou EventoJBLimpar,
        onde está o código que deve ser executado quando tal acontece*/
        jbGuardar.addActionListener(new EventModelExecuter());
        jbLimpar.addActionListener(new EventToClean());
    }
    
    
    public ShelfRepository getShelfRepository() {
		return shelfRepository;
	}
    
    public JTextField getjtfnbElements() {
		return jtfnbElments;
	}
    
    //Classe interna que contém o código que é executado quando se pressiona o botão jbGuardar
    private class EventModelExecuter implements ActionListener {
        
        public void actionPerformed(ActionEvent ev) {

             if (jtfnbElments.getText().equals("") )
                JOptionPane.showMessageDialog(null,"All fields are required!");
            else {
                try {
                	               	
                	new eventHandling().execute();
                       

                }
                catch(Exception e) {
                    System.out.println("Unable to perform the operation. ");
                    e.printStackTrace();
                }
            }
        }
    }
    
    class eventHandling extends SwingWorker<String, Void>{
   	
    	
		@Override
		protected String doInBackground() throws Exception {
			
			CreateShelf createShelf = new CreateShelf(getShelfRepository(), Integer.valueOf(getjtfnbElements().getText()));
			
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
			
            JOptionPane.showMessageDialog(null,"Data were successfully saved!  " + str);
            //Invoca o método implementado em baixo
            deleteFields();
            dispose();
    	}

    	
    	
    	
    }
   
    private void deleteFields() {
        jtfnbElments.setText("");
   
    }
    
    //Classe interna que contém o código que é executado quando se pressiona o botão jbLimpar
    private class EventToClean implements ActionListener {
        
        public void actionPerformed(ActionEvent ev) {
            deleteFields();
        }
    }




}
