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
import fhj.shelf.utils.repos.AbstractUser;
import fhj.shelf.utils.repos.UserRepository;

public class UserDetails extends JFrame  {

    //Declara e cria os componentes   
    JLabel jlTitulo = new JLabel ("UserDetails");
    DefaultTableModel tmContactos = new DefaultTableModel (null, new String[]{"Nome", "Password", "FullName", "E-mail"});
    JTable jtContactos = new JTable(tmContactos);
    JScrollPane jspContactos = new JScrollPane(jtContactos);
    private UserRepository userRepository;
    
    //Construtor
    public UserDetails(UserRepository repository) {    
        this.userRepository = repository;
        
        
        //Define as propriedades da janela
        setTitle("User List");
        setSize(500,200);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());
        setVisible(true);
        jspContactos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        jspContactos.setPreferredSize(new Dimension(475,125));
        TableColumnModel tcm = jtContactos.getColumnModel();
        TableColumn tc0 = tcm.getColumn(0);
        //Define a largura de cada coluna (100+100+100+175=475 que corresponde à largura do JScrollpane)
        tc0.setMaxWidth(100);
        TableColumn tc1 = tcm.getColumn(1);
        tc1.setMaxWidth(100);
        TableColumn tc2 = tcm.getColumn(2);
        tc2.setMaxWidth(100);
        TableColumn tc3 = tcm.getColumn(3);
        tc3.setMaxWidth(175);
        jtContactos.setEnabled(false);
        //Impede que a seleção de mais do que uma linha da tabela em simultâneo
        jtContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        
        //Adiciona os componentes à janela
        getContentPane().add(jlTitulo);
        getContentPane().add(jspContactos);
        
        
        
        //      Adiciona uma linha vazia à tabela
        String [] campos = new String[] {null, null, null, null};
        
        tmContactos.addRow(campos);
        tmContactos.addRow(campos);
        tmContactos.addRow(campos);
        tmContactos.addRow(campos);
        tmContactos.addRow(campos);
        tmContactos.addRow(campos);
        
        
        new EventHandling().execute();


    }
    
    
    public UserRepository getUserRepository() {
		return userRepository;
	}
    
    private class EventHandling extends SwingWorker<Object, Object>{
    	
    	
    	
    	@Override
    	protected  Map<String, AbstractUser> doInBackground() throws Exception 
    	{
			return new GetAllUsers(getUserRepository()).call();
		
    	}

    	@Override
    	protected void done() {

    		int i = 0;

    		try {
				for (Entry<String, AbstractUser> element : ((Map<String, AbstractUser>) get()).entrySet()) {


					//Preenche as células da linha vazia. A numeração das colunas começa em 0
					tmContactos.setValueAt(element.getKey(),i,0);
					tmContactos.setValueAt(element.getValue().getLoginPassword(),i,1);
					tmContactos.setValueAt(element.getValue().getFullName(),i,2);
					tmContactos.setValueAt(element.getValue().getEmail(),i,3);
					i++;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



    	}




    }
    
    
    

}
