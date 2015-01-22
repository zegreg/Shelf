package fhj.shelf.UI;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

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

import main.java.FHJ.shelf.commands.PostElement;
import main.java.FHJ.shelf.model.AbstractShelf;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;


public class CDCollection extends JFrame {
	
	
	// Declarations
	private UserRepository userRepository;
	private ShelfRepository  shelfRepository;
	private ElementsRepository elementsRepository;
	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfShelfData;
	private JComboBox comboBox;
	private final JButton btnAddcdCollection ;
	private final JButton btnDelete;
	private final JLabel lblArtist;
	private final JLabel lblTracks;
	private final JLabel lblItems;
	private JTextField textField;
	


     // constructor
     public CDCollection(UserRepository repository, ShelfRepository shelfRepository, ElementsRepository elementsRepository) {
    	 this.userRepository = repository;
    	 this.shelfRepository = shelfRepository;
    	 this.elementsRepository = elementsRepository;

    	 
    	 
    	 btnAddcdCollection = new JButton("AddCDCollection");
    	 textField = new JTextField();
    	 btnDelete = new JButton("Delete");
    	 lblArtist = new JLabel("Artist");
    	 comboBox = new JComboBox();
    	 jtfShelfData = new JTextField(6);
    	 jlTitle = new JLabel ("Title");
    	 lblTracks = new JLabel ("Tracks");
    	 lblItems = new JLabel ("Items");
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
    	 btnAddcdCollection.setBounds(100, 192, 96, 31);
    	 btnDelete.setBounds(277, 192, 115, 31);
    	 lblArtist.setBounds(21, 126, 42, 31);
    	 lblTracks.setBounds(21, 126, 42, 31);
    	 lblItems.setBounds(21, 126, 42, 31);
    	 textField.setBounds(100, 132, 292, 18);
    	 textField.setColumns(10); 


    	 //Adiciona os componentes à janela 
    	 getContentPane().add(comboBox);
    	 getContentPane().add(jlElementType);
    	 getContentPane().add(jlTitle);
    	 getContentPane().add(jtfShelfData);
    	 getContentPane().add(btnAddcdCollection);
    	 getContentPane().add(btnDelete);
    	 getContentPane().add(lblArtist);
    	 getContentPane().add(lblTracks);
    	 getContentPane().add(lblItems);
    	 getContentPane().add(textField);



    	 /*Registo do listener ActionListener junto do botão.
        Quando for gerado um evento por este componente, é
        criada uma instância da classe EventoCDCollection,
        onde está o código que deve ser executado quando tal acontece*/

    	 btnAddcdCollection.addActionListener(new EventCDCollection(repository,shelfRepository,elementsRepository));
	}
	
	 private class EventCDCollection implements ActionListener{

    	 private UserRepository userRepository;
    	 private ShelfRepository  shelfRepository;
    	 private ElementsRepository elementsRepository;



    	 public EventCDCollection(UserRepository repository, ShelfRepository shelfRepository, ElementsRepository elementsRepository) {
    		 this.userRepository = repository;
    		 this.shelfRepository = shelfRepository;
    		 this.elementsRepository = elementsRepository;
    	 }



    	 @Override
    	 public void actionPerformed(ActionEvent e) {

    		 SwingWorker worker = new SwingWorker() {

    			 @Override
    			 protected  String doInBackground() throws Exception {


    				 Map<String, String> map = new TreeMap<>();

    				 map.put("elementType", "CDCollection");
    				 map.put("name", jtfShelfData.getText());
    				 map.put("artist", textField.getText());
    				 map.put("tracks", textField.getText());
    				 map.put("items", textField.getText());


    				 PostElement element = (PostElement) new PostElement.Factory(userRepository,
    						 shelfRepository, elementsRepository).newInstance(map);

    				 //			main.java.FHJ.shelf.model.CDCollection cdCollection = 
    				// 				new main.java.FHJ.shelf.model.CDCollection(jtfShelfData.getText(), textField.getText(), textField.getText(), textField.getText());



    				 //					CDCollection.this.shelfRepository.insert(cdCollection);


    				 return element.validLoginPostExecute();
    			 }
    			 @Override
    			 protected void done() {
    				 JOptionPane.showMessageDialog(null,"Data successfully saved!");
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
