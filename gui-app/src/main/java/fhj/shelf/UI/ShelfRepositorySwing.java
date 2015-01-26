package fhj.shelf.UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;





import javax.swing.SwingUtilities;

import fhj.shelf.utils.repos.ShelfRepository;
import fhj.shelf.utils.repos.UserRepository;

import java.awt.Color;



@SuppressWarnings("serial")
public class ShelfRepositorySwing extends JFrame {

   private  JMenuBar barraMenu = new JMenuBar();
   private JMenu mnEdit = new JMenu("Edit");
   private JMenuItem jmiNewShelf = new JMenuItem("New Shelf");
   private JMenuItem jmiShelfList = new JMenuItem("Shelf List");
   private JMenu mnSearch = new JMenu("Search");
   private JMenuItem jmiProcNome = new JMenuItem("By name");
   private JMenuItem jmiProcTelf = new JMenuItem("By id");
   private JMenu jmExit = new JMenu("Exit");
   private  ImageIcon img = new ImageIcon("user.jpg");
   private  JLabel jlImagem = new JLabel(new ImageIcon("C:\\Users\\José Oliveira\\Pictures\\icone.gif"));
   private SaveShelf novoContacto;
   private ShelfDetails listarContactos;
   private SearchShelf searchShelf;
    
          
   private ShelfRepository shelfRepository;
   private UserRepository repository;
   
   
    public ShelfRepositorySwing(UserRepository repository, ShelfRepository shelfRepository) {
    	this.shelfRepository = shelfRepository;
    	this.repository = repository;
    	
    	getContentPane().setBackground(Color.WHITE);
        setTitle("ShelfRepository");
        setSize(300,366);
        setLocation(50,50);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(new FlowLayout());
        setJMenuBar(barraMenu);
        barraMenu.add(mnEdit);
        mnEdit.add(jmiNewShelf);
        mnEdit.add(jmiShelfList);
        barraMenu.add(mnSearch);
        mnSearch.add(jmiProcNome);
        mnSearch.add(jmiProcTelf);
        barraMenu.add(jmExit);
        
        getContentPane().add(jlImagem);
        
        jmiNewShelf.addActionListener(new EventoJMenuItem());
        jmiShelfList.addActionListener(new EventoJMenuItem());
        jmiProcNome.addActionListener(new EventoJMenuItem());
        jmiProcTelf.addActionListener(new EventoJMenuItem());
        jmExit.addMouseListener(new EventoJMenuSair());
    }

    
    private class EventoJMenuItem implements ActionListener {
    
        public void actionPerformed(ActionEvent ev) {
        	ensureEventThread();
            if (ev.getSource() == jmiNewShelf) {
                 novoContacto = new SaveShelf(repository, shelfRepository);
                 novoContacto.setVisible(true);
            }
            else if (ev.getSource() == jmiShelfList) {
                listarContactos = new ShelfDetails(repository, shelfRepository);
                listarContactos.setVisible(true);

            }    
            else if (ev.getSource() == jmiProcNome) {
            	searchShelf = new SearchShelf(shelfRepository);
                searchShelf.setVisible(true);
            }

        }
    }
    
    private class EventoJMenuSair implements MouseListener {
    
        public void mouseClicked(MouseEvent ev) {
            System.exit(0);
        }
        
        public void mouseEntered (MouseEvent ev) {}
        
        public void mouseExited(MouseEvent ev) {}
        
        public void mouseReleased(MouseEvent ev) {}
        
        public void mousePressed(MouseEvent ev) {}
    }
    
    private void ensureEventThread() {
  		// throws an exception if not invoked by the
  		// event thread.
  		if ( SwingUtilities.isEventDispatchThread() ) 
  		 return;
  		
  		throw new RuntimeException("only the event " +
  				"thread should invoke this method");
  	}
}
