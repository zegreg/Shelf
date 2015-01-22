package fhj.shelf;

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

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import fhj.shelf.utils.repos.UserRepository;



public class UserRepositorySwing extends JFrame {

    JMenuBar barraMenu = new JMenuBar();
    JMenu mnEdit = new JMenu("Edit");
    JMenuItem jmiUser = new JMenuItem("New User");
    JMenuItem jmiUserList = new JMenuItem("User List");
    JMenu mnSearch = new JMenu("Search");
    JMenuItem jmibyName = new JMenuItem("by Name\r\n");
    JMenuItem jmibyid = new JMenuItem("by id");
    JMenu jmExit = new JMenu("Exit");
//    ImageIcon img = new ImageIcon("user.jpg");
    JLabel jlImagem = new JLabel(new ImageIcon("C:\\Users\\José Oliveira\\Pictures\\User1.png"));
    SaveUser novoContacto;
    SearchUser procurarNome;
    UserDetails listarContactos;
    private final JButton btnNewButton = new JButton("Users");
//    ShearchByName procurarNome;
//    ShearchById procurarid;
    private UserRepository repository;
    public UserRepositorySwing(UserRepository repository) {
    	this.repository = repository;
    	
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
        barraMenu.add(mnSearch);
        mnSearch.add(jmibyName);
        mnSearch.add(jmibyid);
        barraMenu.add(jmExit);
        
        getContentPane().add(jlImagem);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        
        getContentPane().add(btnNewButton);
        
//        jmiUser.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new SaveUser(repository);
//				
//			}
//		});
        
        jmiUser.addActionListener(new EventoJMenuItem());
        jmiUserList.addActionListener(new EventoJMenuItem());
        
      
        
        
        
        jmibyName.addActionListener(new EventoJMenuItem());
        jmibyid.addActionListener(new EventoJMenuItem());
        jmExit.addMouseListener(new EventoJMenuSair());
    }
    
//    public static void main(String[] args) {
//        new UserRepositorySwing();
//    }
    
    private class EventoJMenuItem implements ActionListener {
    
        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == jmiUser) {
                 novoContacto = new SaveUser(repository);
                 novoContacto.setVisible(true);
            }
            else if (ev.getSource() == jmiUserList) {
                new UserDetails(repository);
                listarContactos.setVisible(true);
            }    
            else if (ev.getSource() == jmibyName) {
                procurarNome = new SearchUser(repository);
                procurarNome.setVisible(true);
            }
//            else if (ev.getSource() == jmiProcTelf) {
//                procurarTelefone = new ProcurarTelefone();
//                procurarTelefone.setVisible(true);
//            }
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
}
