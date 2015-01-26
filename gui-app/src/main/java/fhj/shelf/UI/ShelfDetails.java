package fhj.shelf.UI;

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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import javax.swing.JTable;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import javax.swing.JTextField;

import fhj.shelf.commandsDomain.GetAllShelfs;
import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.repos.ShelfRepository;
import fhj.shelf.utils.repos.UserRepository;

public class ShelfDetails extends JFrame {


	  
	    private JTable jtShelfContents;
	    private  JLabel jlTitle;
	    private  JScrollPane jspShelfContents;
	    private UserRepository repository;
	    private ShelfRepository shelfRepository;
	    	    
	    
	    //Construtor
	    public ShelfDetails(UserRepository repository, ShelfRepository shelfRepository) {
	    	
	    	this.repository = repository;
	    	this.shelfRepository = shelfRepository;
	        
	    	createAndShowGUI();

	    }


	    
	    public ShelfRepository getShelfRepository() {
			return shelfRepository;
		}
	    /**
	     * Method to create the Frame
	     */
		private void createAndShowGUI() {
			createContentTable(); 
			jlTitle = new JLabel ("ShelfsDetails");
	        //Define as propriedades da janela
	        setTitle("Shelfs Details");
	        setSize(500,200);
	        setLocation(450,250);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        getContentPane().setLayout(new FlowLayout());
	        setVisible(true);
	        
	        //Adiciona os componentes à janela
	        getContentPane().add(jlTitle);
	        getContentPane().add(jspShelfContents);
	        
	        new EventHandling().execute();
		}


		/**
		 * Method to create a JTable
		 * @return
		 */
		private JTable createContentTable() {
			//Declara e cria os componentes   
		   

		    jtShelfContents = new JTable(new DefaultTableModel(
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
		    
		    
		    
		    jspShelfContents = new JScrollPane(jtShelfContents);
	        jspShelfContents.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        jspShelfContents.setPreferredSize(new Dimension(475,125));
	        jtShelfContents.setCellSelectionEnabled(true);
	        //Impede que a seleção de mais do que uma linha da tabela em simultâneo
	        jtShelfContents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        return jtShelfContents;
		}
	    
	    
		/**
		 * Class whose instance represent an event handler
		 * @author José Oliveira
		 *
		 */
	    private  class EventHandling extends SwingWorker<Map<Long, AbstractShelf>, Void>{

        	@Override
        	protected Map<Long, AbstractShelf> doInBackground() throws Exception {

        	
        		Map<Long, AbstractShelf> map = new GetAllShelfs(getShelfRepository()).call() ;
//        		publish(map);
        		return map;
        		
        	
        	}

        	@Override
        	protected void done() {


        		int i = 0;
        		
        		try {
					for (Entry<Long, AbstractShelf> element : 	get().entrySet()) {


						//Preenche as células da linha vazia. A numeração das colunas começa em 0
						jtShelfContents.setValueAt(element.getKey(),i,0);	
						jtShelfContents.setValueAt(element.getValue().getCapacity() - element.getValue().getFreeSpace(),i,1);
						jtShelfContents.setValueAt(element.getValue().getFreeSpace(),i,2);


						i++;
					}
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				} catch (ExecutionException e) {
				
					e.printStackTrace();
				}

        	}
       
//        	@Override
//        	protected void process(List chunks) {
//        	
//        		    // Here we receive the values that we publish().
//        		    // They may come grouped in chunks.
//        		    map mostRecentValue = chunks.get(chunks.size()-1);
//        		    
//        		    countLabel1.setText(Integer.toString(mostRecentValue));
//        		   }
//        	}
        	
        	
        	


        }


}
