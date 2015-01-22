package fhj.shelf.UI;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
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
import fhj.shelf.utils.AbstractShelf;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.ShelfRepository;
import fhj.shelf.utils.repos.UserRepository;




public class Book extends JFrame {
	
	
	// Declarations
	private UserRepository userRepository;
	private ShelfRepository  shelfRepository;
	private ElementsRepository elementsRepository;
	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfShelfData;
	private JComboBox comboBox;
	private final JButton btnAddbook ;
	private final JButton btnDelete;
	private final JLabel lblAuthor;
	private JTextField textField;
	


     // constructor
     public Book(UserRepository repository, ShelfRepository shelfRepository, ElementsRepository elementsRepository) {
    	 this.userRepository = repository;
    	 this.shelfRepository = shelfRepository;
    	 this. elementsRepository = elementsRepository;

    	 
    	 
    	 btnAddbook = new JButton("AddBooK");
    	 textField = new JTextField();
    	 btnDelete = new JButton("Delete");
    	 lblAuthor = new JLabel("Author");
    	 comboBox = new JComboBox();
    	 jtfShelfData = new JTextField(6);
    	 jlTitle = new JLabel ("Title");
    	 jlElementType = new JLabel ("ShelfId");
    	 
    	 
    	 createContentPanel(repository, shelfRepository, elementsRepository);

    
	}

	 private void createContentPanel(UserRepository repository,
			ShelfRepository shelfRepository,
			ElementsRepository elementsRepository) {
		//Define as porpriedades da janela
    	 setTitle("ElementSearch");
    	 setSize(500,330);
    	 setLocation(100,100);
    	 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	 setVisible(true);
    	 getContentPane().setLayout(null);




    	 for (AbstractShelf iterable_element : shelfRepository.getDatabaseElements()) {
    		 comboBox.addItem(iterable_element);
    	 }

    	 comboBox.setBounds(101, 31, 109, 24);
    	 jlElementType.setBounds(21, 28, 96, 31); 
    	 jlTitle.setBounds(21, 89, 42, 18);
    	 jtfShelfData.setBounds(100, 88, 292, 19);
    	 btnAddbook.setBounds(100, 192, 96, 31);
    	 btnDelete.setBounds(277, 192, 115, 31);
    	 lblAuthor.setBounds(21, 126, 42, 31);
    	 textField.setBounds(100, 132, 292, 18);
    	 textField.setColumns(10); 


    	 //Adiciona os componentes à janela 
    	 getContentPane().add(comboBox);
    	 getContentPane().add(jlElementType);
    	 getContentPane().add(jlTitle);
    	 getContentPane().add(jtfShelfData);
    	 getContentPane().add(btnAddbook);
    	 getContentPane().add(btnDelete);
    	 getContentPane().add(lblAuthor);
    	 getContentPane().add(textField);



    	 /*Registo do listener ActionListener junto do botão.
        Quando for gerado um evento por este componente, é
        criada uma instância da classe EventoBook,
        onde está o código que deve ser executado quando tal acontece*/

    	 btnAddbook.addActionListener(new EventBook(repository,shelfRepository,elementsRepository));
	}
	
	 private class EventBook implements ActionListener{

    	 private UserRepository userRepository;
    	 private ShelfRepository  shelfRepository;
    	 private ElementsRepository elementsRepository;



    	 public EventBook(UserRepository repository, ShelfRepository shelfRepository, ElementsRepository elementsRepository) {
    		 this.userRepository = repository;
    		 this.shelfRepository = shelfRepository;
    		 this. elementsRepository = elementsRepository;
    	 }



    	 @Override
    	 public void actionPerformed(ActionEvent e) {

    		 SwingWorker worker = new SwingWorker() {

    			 @Override
    			 protected  String doInBackground() throws Exception {


//    				 Map<String, String> map = new TreeMap<>();
//
//    				 map.put("elementType", "Book");
//    				 map.put("name", jtfShelfData.getText());
//    				 map.put("author", textField.getText());


    				 CreateAnElementInAShelf element = new CreateAnElementInAShelf(shelfRepository, elementsRepository, (long)comboBox.getSelectedIndex(), "Book",
    						 jlTitle.getText(), jtfShelfData.getText(),0,0);

    				 //					main.java.FHJ.shelf.model.Book book = new main.java.FHJ.shelf.model.Book(jtfShelfData.getText(), textField.getText());



    				 //					Book.this.shelfRepository.insert(book);


    				 return element.call();
    			 }
    			 @Override
    			 protected void done() {
    				 
    				 try {
						JOptionPane.showMessageDialog(null,"Data were successfully saved!"+ get());
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				 //Invoca o método implementado em baixo

    				 limpaCampos();
    				 dispose();
    			 }




    		 };

    		 worker.execute();


    	 }

     }
     
     private void limpaCampos() {
    	 jtfShelfData.setText("");
    	 textField.setText("");

     }


}
