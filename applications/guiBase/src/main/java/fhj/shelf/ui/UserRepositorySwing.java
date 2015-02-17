package fhj.shelf.ui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Map;

import fhj.shelf.actionwindow.PostActionWindow;
import fhj.shelf.actionwindowfactory.PostActionWindowFactory;
import fhj.shelf.factorys.CommandFactory;

import fhj.shelf.imageui.ImageConcrete;


@SuppressWarnings("serial")
public class UserRepositorySwing extends JFrame implements PostActionWindow {
	
	private static final int LOCATION_Y = 50;
	private static final int LOCATION_X = 50;
	private static final int SIZE_HEIGHT = 366;
	private static final int SIZE_WIDTH = 300;
	/**
	 * Attributes
	 */
	private static JMenuBar barraMenu;
	private static JMenu mnEdit;
	private static JMenuItem jmiUser;
	private static JMenuItem jmiUserList;
	private static JMenu mnSearch;
	private static JMenuItem jmibyName;
	private static JMenuItem jmibyid;
	private static JMenu jmExit;
	private static JPanel jlImagem;
	private static JMenuItem mntmPatchuser;

	Map<String, CommandFactory> userCommands;
	private String username;
	private String password;
	
	
	/**
	 * 
	 * Class that a single instance of UserRepositorySwing class.
	 * Implements PostActionWindowFactory and returns a PostActionWindow 
	 *
	 */
	public static class Factory implements PostActionWindowFactory {

		private static UserRepositorySwing singleIsntance;
		
		private final String source;
	

		public Factory() {
			this.source = "/User1.png";
		}

		/**
		 * This is an override method of the base class, it returns a new single
		 * instance of UserRepositorySwing
		 */

		@Override
		public PostActionWindow newInstance(String username, String password,
				Map<String, CommandFactory> mapCommands) {
			if (singleIsntance == null) {
				singleIsntance = new UserRepositorySwing(username, password, mapCommands,
					source);
				return singleIsntance;
			}
			
			return singleIsntance;
		}
	}

	

	/**
	 * Constructor define and show window
	 * @param username
	 * @param password
	 * @param mapCommands
	 * @param source
	 * @param width
	 * @param heigth
	 */
	public UserRepositorySwing(String username, String password,
			Map<String, CommandFactory> mapCommands, String source) {
	
		this.username = username;
		this.password = password;
		this.userCommands = mapCommands;

		barraMenu = new JMenuBar();
		mnEdit = new JMenu("Edit");
		jmiUser = new JMenuItem("New User");
		jmiUserList = new JMenuItem("User List");
		mnSearch = new JMenu("Search");
		jmibyName = new JMenuItem("by Name\r\n");
		jmibyid = new JMenuItem("by id");
		jmExit = new JMenu("Exit");
		
		ImageConcrete imageConcrete = new ImageConcrete(source, SIZE_WIDTH,SIZE_HEIGHT);
		 jlImagem = imageConcrete.setBackGroundImage() ;
		
		

		setTitle("UserRepository");
		setSize(SIZE_WIDTH, SIZE_HEIGHT);
		setLocation(LOCATION_X, LOCATION_Y);
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

		mntmPatchuser.addActionListener(new EventThread());
		jmiUser.addActionListener(new EventThread());
		jmiUserList.addActionListener(new EventThread());
		jmibyName.addActionListener(new EventThread());
		jmibyid.addActionListener(new EventThread());
		// jmExit.addMouseListener(new EventThreadClose());
	}

	/**
	 * Inner Class to treat Eventin the EDT, by implementing
	 * ActionListener Interface and invoke actionPerformed method.
	 * 
	 */
	private class EventThread implements ActionListener {

		public void actionPerformed(ActionEvent ev) {

			if (ev.getSource() == jmiUser) {
				
				 new SaveUser.Factory().newInstance(username, password,
						userCommands);
				 return;
			} else if (ev.getSource() == jmiUserList) {
				new UserDetails.Factory().newInstance(userCommands);
				 return;
			} else if (ev.getSource() == jmibyName) {
				new SearchUser.Factory().newInstance(userCommands);
				 return;
			} else if (ev.getSource() == mntmPatchuser) {
				new PatchUser.Factory().newInstance(username, password,
						userCommands);
				 return;
			}

		}
	}

	

}
