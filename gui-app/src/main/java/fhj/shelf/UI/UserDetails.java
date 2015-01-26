package fhj.shelf.UI;

/**
 * Write a description of class ListarContactos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.swing.ListSelectionModel;


import javax.swing.ScrollPaneConstants;

import fhj.shelf.commandsDomain.GetAllShelfs;
import fhj.shelf.commandsDomain.GetAllUsers;
import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.repos.AbstractUser;
import fhj.shelf.utils.repos.UserRepository;

public class UserDetails extends JFrame  {

    //Declara e cria os componentes   
	private JLabel jlTitulo;
	private DefaultTableModel tmContactos;
	private JTable jtContactos;
	private JScrollPane jspContactos;
    
    private UserRepository userRepository;
    
    //Construtor
    public UserDetails(UserRepository repository) {    
        this.userRepository = repository;
        
        createAndShowGUI();


    }


	private void createAndShowGUI() {
		createJTable(); 
         //Define as propriedades da janela
        setTitle("User List");
        setSize(500,200);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());
        setVisible(true);
       
        
        //Adiciona os componentes à janela
        getContentPane().add(jlTitulo);
        getContentPane().add(jspContactos);
        
         new EventHandling().execute();
	}


	private JTable createJTable() {
		jlTitulo = new JLabel ("UserDetails");
		jtContactos = new JTable(new DefaultTableModel(
				new Object[][] {
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
				},
				new String[] {
						"Nome", "Password", "FullName", "E-mail"
				}
				));

		jspContactos = new JScrollPane(jtContactos);
		jspContactos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspContactos.setPreferredSize(new Dimension(475,125));
		jtContactos.setCellSelectionEnabled(true);
		//Impede que a seleção de mais do que uma linha da tabela em simultâneo
		jtContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return jtContactos;
	}


    public UserRepository getUserRepository() {
		return userRepository;
	}
    
    private class EventHandling extends SwingWorker<Map<String, AbstractUser>, Void>{
    	
    	
    	
    	@Override
    	protected  Map<String, AbstractUser> doInBackground() throws Exception 
    	{
			return new GetAllUsers(getUserRepository()).call();
		
    	}

    	@Override
    	protected void done() {

    		
    		int i = 0;

    		
				try {
					for (Entry<String, AbstractUser> element : get().entrySet()) {


						//Preenche as células da linha vazia. A numeração das colunas começa em 0
						jtContactos.setValueAt(element.getKey(),i,0);
						jtContactos.setValueAt(element.getValue().getLoginPassword(),i,1);
						jtContactos.setValueAt(element.getValue().getFullName(),i,2);
						jtContactos.setValueAt(element.getValue().getEmail(),i,3);
						i++;
					}
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				} catch (ExecutionException e) {
				
					e.printStackTrace();
				}
			
			

    	}




    }
    
    
    

}
