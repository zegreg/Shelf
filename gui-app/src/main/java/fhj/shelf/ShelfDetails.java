package fhj.shelf;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.util.Map.Entry;

import javax.swing.JTable;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import javax.swing.JTextField;

import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.repos.ShelfRepository;
import fhj.shelf.utils.repos.UserRepository;

public class ShelfDetails extends JFrame {


	    //Declara e cria os componentes   
	    JLabel jlTitle = new JLabel ("ShelfsDetails");

	    JTable jtShelfContents = new JTable(new DefaultTableModel(
	    	new Object[][] {
	    		{null, null, null},
	    		{null, null, null},
	    		{null, null, null},
	    		{null, null, null},
	    		{null, null, null},
	    	},
	    	new String[] {
	    		"idElement", "Number Elements", "Free Space"
	    	}
	    ));
	    
	    
	    
	    JScrollPane jspShelfContents = new JScrollPane(jtShelfContents);
	    
	    private UserRepository repository;
	    private ShelfRepository shelfRepository;
	    	    
	    
	    //Construtor
	    public ShelfDetails(UserRepository repository, ShelfRepository shelfRepository) {
	    	
	    	this.repository = repository;
	    	this.shelfRepository = shelfRepository;
	        
	        //Define as propriedades da janela
	        setTitle("Shelfs Details");
	        setSize(500,200);
	        setLocation(450,250);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        getContentPane().setLayout(new FlowLayout());
	        setVisible(true);
	        jspShelfContents.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        jspShelfContents.setPreferredSize(new Dimension(475,125));
	        jtShelfContents.setCellSelectionEnabled(true);
	        
	        
	        
	        //Impede que a seleção de mais do que uma linha da tabela em simultâneo
	        jtShelfContents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
	        
	        //Adiciona os componentes à janela
	        getContentPane().add(jlTitle);
	        getContentPane().add(jspShelfContents);
	        
	 			
			class EventHandling extends SwingWorker{
				Iterable<AbstractShelf> it;
				@Override
				protected Iterable<AbstractShelf> doInBackground() throws Exception {
					
					it =shelfRepository.getDatabaseElements();
					return it ;
				}
				
				@Override
				protected void done() {
					
					int i = 0;
					for (AbstractShelf element : 	it) {

		        		//Preenche as células da linha vazia. A numeração das colunas começa em 0
		        		jtShelfContents.setValueAt(element.getId(),i,0);		        			      		
		        		jtShelfContents.setValueAt(element.getCapacity(),i,1);
		        		jtShelfContents.setValueAt(element.getFreeSpace(),i,2);
		        		
		        		
		        		i++;
		        	}
					
				}
					
				
			}
			new EventHandling().execute();
		
	    }


	    }
//	   private SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
//
//	    	@Override
//	    	protected Void doInBackground() throws Exception {
//
//	    		// Simulate doing something useful.
//
//	    		for (int i = 0; i <= 10; i++) {
//
//	    			Thread.sleep(1000);
//
//	    			System.out.println("Running " + i);
//	    		}
//
//	    		return null;
//	    	}
//
//	   }
	   



