package fhj.shelf.UI;

/**
 * Write a description of class AgendaVersaoBD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import fhj.shelf.utils.repos.UserRepository;



public class UserRepositorySwing extends JFrame {

	private JMenuBar barraMenu;
	private JMenu mnEdit;
	private JMenuItem jmiUser;
	private  JMenuItem jmiUserList;
	private JMenu mnSearch;
	private JMenuItem jmibyName;
	private  JMenuItem jmibyid;
	private  JMenu jmExit;
	//    ImageIcon img = new ImageIcon("user.jpg");
	private JLabel jlImagem;
	private   SaveUser novoContacto;
	private  SearchUser procurarNome;
	private  UserDetails listarContactos;
	//    ShearchByName procurarNome;
	//    ShearchById procurarid;
	private UserRepository repository;
	private JMenuItem mntmPatchuser;
	private PatchUser patchUser;


    
    
    public UserRepositorySwing(UserRepository repository) {
    	this.repository = repository;
    	
    	
    	this.barraMenu = new JMenuBar();
    	this.mnEdit = new JMenu("Edit");
    	this.jmiUser = new JMenuItem("New User");
    	this.jmiUserList = new JMenuItem("User List");
    	this.mnSearch = new JMenu("Search");
    	this.jmibyName = new JMenuItem("by Name\r\n");
    	this.jmibyid = new JMenuItem("by id");
    	this.jmExit = new JMenu("Exit");
    	this.jlImagem = new JLabel(new ImageIcon("C:\\Users\\José Oliveira\\Pictures\\User1.png"));
    	
        setTitle("UserRepository");
        setSize(300,366);
        setLocation(50,50);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(new FlowLayout());
        setJMenuBar(barraMenu);
        barraMenu.add(mnEdit);
        mnEdit.add(jmiUser);
        mnEdit.add(jmiUserList);
        
        mntmPatchuser = new JMenuItem("PatchUser");
        mnEdit.add(mntmPatchuser);
        barraMenu.add(mnSearch);
        
        mnSearch.add(jmibyName);
        mnSearch.add(jmibyid);
        barraMenu.add(jmExit);
        
        getContentPane().add(jlImagem);

        mntmPatchuser.addActionListener(new EventoJMenuItem());
        jmiUser.addActionListener(new EventoJMenuItem());
        jmiUserList.addActionListener(new EventoJMenuItem());
        jmibyName.addActionListener(new EventoJMenuItem());
        jmibyid.addActionListener(new EventoJMenuItem());
        jmExit.addMouseListener(new EventoJMenuSair());
    }

    
    private class EventoJMenuItem implements ActionListener {
    
        

		public void actionPerformed(ActionEvent ev) {
        	ensureEventThread();
            if (ev.getSource() == jmiUser) {
                 novoContacto = new SaveUser(repository);
                 novoContacto.setVisible(true);
            }
            else if (ev.getSource() == jmiUserList) {
               listarContactos = new UserDetails(repository);
                listarContactos.setVisible(true);
            }    
            else if (ev.getSource() == jmibyName) {
                procurarNome = new SearchUser(repository);
                procurarNome.setVisible(true);
            }
            else if (ev.getSource() == mntmPatchuser) {
                patchUser = new PatchUser(repository);
                patchUser.setVisible(true);
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
