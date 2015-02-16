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

import fhj.shelf.actionWindow.PostActionWindow;
import fhj.shelf.actionWindowFactory.PostActionWindowFactory;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.imageUI.CreateImage;

@SuppressWarnings("serial")
public class UserRepositorySwing extends CreateImage implements PostActionWindow {
	
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
		private final int width;
		private final int heigth;

		public Factory() {
			this.width = 360;
			this.heigth = 300;
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
					source, width, heigth);
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
			Map<String, CommandFactory> mapCommands, String source, int width,
			int heigth) {
		super(source, width, heigth);
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

		jlImagem = setBackGroundImage();

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

	/**
	 * 
	 * Inner Class to treat Event  Close in the EDT, by implementing
	 * MouseListener Interface and invoke mouseClicked method.
	 *
	 */
	@SuppressWarnings("unused")
	private class EventThreadClose implements MouseListener {

		public void mouseClicked(MouseEvent ev) {
			System.exit(0);
		}

		public void mouseEntered(MouseEvent ev) {
		}

		public void mouseExited(MouseEvent ev) {
		}

		public void mouseReleased(MouseEvent ev) {
		}

		public void mousePressed(MouseEvent ev) {
		}
	}

}
