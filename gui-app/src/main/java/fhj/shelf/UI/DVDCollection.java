package fhj.shelf.UI;


import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import fhj.shelf.commandsDomain.CreateAnElementInAShelf;
import fhj.shelf.commandsDomain.GetAllShelfs;





import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import fhj.shelf.commandsDomain.CreateAnElementInAShelf;
import fhj.shelf.commandsDomain.GetAllShelfs;
import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.InMemoryElementsRepository;
import fhj.shelf.utils.repos.InMemoryShelfRepository;
import fhj.shelf.utils.repos.InMemoryUserRepository;
import fhj.shelf.utils.repos.ShelfRepository;
import fhj.shelf.utils.repos.UserRepository;

public class DVDCollection extends JFrame {
	
	
	
	// Declarations
		
		private ShelfRepository  shelfRepository;
		private ElementsRepository elementsRepository;
		private JLabel jlElementType;
		private JLabel jlTitle;
		private JTextField jtfTitle;
		private JComboBox<Object> comboBox;
		private final JButton btnAddDVDCollection ;
		private final JButton btnDelete;
		


	     // constructor
	     public DVDCollection(ShelfRepository shelfRepository, ElementsRepository elementsRepository) {
	    	
	    	 this.shelfRepository = shelfRepository;
	    	 this. elementsRepository = elementsRepository;

	    	 
	    	 
	    	 btnAddDVDCollection = new JButton("AddDVDCollection");
	    	 btnDelete = new JButton("Delete");
	    	 comboBox = new JComboBox<Object>();
	    	 jtfTitle = new JTextField(6);
	    	 jlTitle = new JLabel ("Title");
	    	 jlElementType = new JLabel ("ShelfId");
	    	 comboBox.setBounds(101, 31, 109, 24);
	    	 
	    	 SwingWorker<?, ?> worker = fillComboxFromMap();
	    	 worker.execute();
	    	 
	    	 createContentPanel();




	    	 /*Registo do listener ActionListener junto do botão.
	        Quando for gerado um evento por este componente, é
	        criada uma instância da classe EventoBook,
	        onde está o código que deve ser executado quando tal acontece*/

	    	 
	    	 
	    	 btnAddDVDCollection.addActionListener(new EventDVDCollection());
		}

		 private SwingWorker<?, ?> fillComboxFromMap() {
			SwingWorker<Map<Long, AbstractShelf>, Void> worker = new SwingWorker<Map<Long, AbstractShelf>, Void>() {
	    		 @Override
	    		 protected Map<Long, AbstractShelf> doInBackground() throws Exception {
	    			 Map<Long, AbstractShelf> map  = new GetAllShelfs(shelfRepository).call() ;
	    			 
	    			 return map;
	    		 }
	    		 @Override
	    		 protected void done() {

	    			 try {
						for (Entry<Long, AbstractShelf> iterable_element :get().entrySet())
						 {  

							 comboBox.addItem(iterable_element.getKey());

						 }
					} catch (InterruptedException e) {
				
						e.printStackTrace();
					} catch (ExecutionException e) {
				
						e.printStackTrace();
					}

	    			

	    		 }
	    	 };
			return worker;
		}

		 private void createContentPanel() {
			//Define as porpriedades da janela
	    	 setTitle("AddShelfElement");
	    	 setSize(500,330);
	    	 setLocation(100,100);
	    	 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	 setVisible(true);
	    	 getContentPane().setLayout(null);


	    	 jlElementType.setBounds(21, 28, 96, 31); 
	    	 jlTitle.setBounds(21, 89, 42, 18);
	    	 jtfTitle.setBounds(100, 88, 292, 19);
	    	 btnAddDVDCollection.setBounds(100, 192, 96, 31);
	    	 btnDelete.setBounds(277, 192, 115, 31);


	    	 //Adiciona os componentes à janela 
	    	 getContentPane().add(comboBox);
	    	 getContentPane().add(jlElementType);
	    	 getContentPane().add(jlTitle);
	    	 getContentPane().add(jtfTitle);
	    	 getContentPane().add(btnAddDVDCollection);
	    	 getContentPane().add(btnDelete);

		}
		 
		
		 private class EventDVDCollection implements ActionListener{



	    	 @Override
	    	 public void actionPerformed(ActionEvent e) {

	    		 SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

	    			 @Override
	    			 protected  String doInBackground() throws Exception {

	    				 CreateAnElementInAShelf element = new CreateAnElementInAShelf(shelfRepository, elementsRepository,
	    						 Long.valueOf(comboBox.getSelectedItem().toString()), "CD",
	    						 jtfTitle.getText(),null,0,0);
	    				
	    				 return element.call();
	    			 }
	    			 @Override
	    			 protected void done() {
	    				 
	    				 try {
							JOptionPane.showMessageDialog(null,"Data were successfully saved!"+ get());
						} catch (HeadlessException e) {
						
							e.printStackTrace();
						} catch (InterruptedException e) {
						
							e.printStackTrace();
						} catch (ExecutionException e) {
							
							e.printStackTrace();
						}
	    				 //Invoca o método implementado em baixo

	    				 cleanFields();
	    				 dispose();
	    			 }
	    		 };

	    		 worker.execute();


	    	 }

	     }
	
	     
	     private void cleanFields() {
	    	 jtfTitle.setText("");
	    	

	     }
}
