package fhj.shelf.startui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import java.util.logging.Logger;

import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.imageui.CreateImage;
import fhj.shelf.imageui.ImagePanel;
import fhj.shelf.loginui.Login;
import fhj.shelf.ui.Book;
import fhj.shelf.ui.BookCollection;
import fhj.shelf.ui.CD;
import fhj.shelf.ui.CDCollection;
import fhj.shelf.ui.DVD;
import fhj.shelf.ui.DVDCollection;
import fhj.shelf.ui.Help;
import fhj.shelf.ui.ShelfRepositorySwing;
import fhj.shelf.ui.UserRepositorySwing;

import javax.swing.JLabel;

import java.awt.Font;

/**
 * 
 */

/**
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 *
 */

@SuppressWarnings("serial")
public class StartUpFrame extends CreateImage{

	private static JMenuBar menuBar;
	private static JMenu mnEdit, mnShelfManagement, mnSearch, mnUserManagement,
			mnHelp, mnExit, mnAbout,mnCollection,mnAddelement,mnElement;
	private static JMenuItem menuItem, mntUserDataBase, mntShelfRepository,
			mntmDVD, mntmCD, mntmBook, mntmShowInformation, bookcollection,cdcollection,
	dvdcollection, mntmShelf, mntmShelfelements;
	private static JSeparator separator_2;
	private static JLabel lbmessage;
	private static JButton btnClickToLogin;
	
	private Map<String, CommandFactory> userCommands;
	private Map<String, CommandFactory> shelfCommands;
	private String username;
	private String password;

	private static String source = "/Bookshelf-2.jpg";
	/**
	 * Constructor
	 * @param password 
	 * @param username 
	 * @param mapCommands 
	 * @param mapCommands 
	 * @throws IOException 
	 * @wbp.parser.entryPoint
	 */
	public StartUpFrame(Map<String, CommandFactory> userCommands, Map<String, CommandFactory> shelfCommands, String username, String password) throws IOException {
		super(source, 460,260);
		this.username = username;
		this.password  = password;
		this.userCommands = userCommands;
		this.shelfCommands = shelfCommands;
		
		
		/**
		 * sets the size of the window 450 pixels wide  and 260 pixels high
		 */
		JFrame frame = new JFrame("Shelf");
		frame.pack();
		frame.setSize(450, 260);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(450, 260);

		/**
		 *  add MenuBar to the frame
		 */
		frame.setJMenuBar(createMenuBar());

		/**
		 *  add ImagePanel to the frame
		 */
		createContentPane(frame);
		
		
	}
	
	/**
	 * Getters
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	
	public String getUsername() {
		return username;
	}

	
	
	/**
	 * Method that creates the Menu
	 * 
	 * @return
	 * @wbp.parser.entryPoint
	 */
	public  JMenuBar createMenuBar() {
		/**
		 * menu bar creation
		 */
		menuBar = new JMenuBar();

		/*************************************** EDIT *******************************************************************/
		/**
		 * first menu creation
		 */
		mnEdit = new JMenu("Edit");
		mnEdit.setEnabled(false);

		/**
		 *  keyEvent: P
		 */
		mnEdit.setMnemonic(KeyEvent.VK_P);
		menuBar.add(mnEdit);

		/**
		 * UserManagement menu construction and added to the Edit menu
		 */
		mnUserManagement = new JMenu("UserManagement");

		/**
		 * UserDataBase menu construction and added to the UserManagement
		 */
		mntUserDataBase = new JMenuItem("UserDataBase");
		mntUserDataBase.addActionListener(new EventThread());
		mntUserDataBase.setActionCommand("UserList");
		mnUserManagement.add(mntUserDataBase);
		mnEdit.add(mnUserManagement);

		/**
		 * ShelfManagement construction
		 */
		mnShelfManagement = new JMenu("ShelfManagement");

		/**
		 *  ShelfRepository menu construction and added to the ShelfManagement
		 */
		mntShelfRepository = new JMenuItem("ShelfRepository");
		mntShelfRepository.addActionListener(new EventThread());
		mnEdit.add(mnShelfManagement);

		mnAddelement = new JMenu("AddShelfElement");
		mnShelfManagement.add(mnAddelement);

		mnElement = new JMenu("Element");
		mnCollection = new JMenu("Collection");
		mnAddelement.add(mnElement);
		mnAddelement.add(mnCollection);


		mntmBook = new JMenuItem("Book");
		mntmBook.addActionListener(new EventThread());
		mntmBook.setActionCommand("Book");
		mnElement.add(mntmBook);

		mntmDVD = new JMenuItem("DVD");
		mntmDVD.addActionListener(new EventThread());
		mntmDVD.setActionCommand("DVD");
		mnElement.add(mntmDVD);

		mntmCD = new JMenuItem("CD");
		mntmCD.addActionListener(new EventThread());
		mntmCD.setActionCommand("CD");
		mnElement.add(mntmCD);

		
		bookcollection = new JMenuItem("BookCollection");
		bookcollection.addActionListener(new EventThread());
		bookcollection.setActionCommand("BookCollection");
		mnCollection.add(bookcollection);
		
	    cdcollection = new JMenuItem("CDCollection");
		cdcollection.addActionListener(new EventThread());
		cdcollection.setActionCommand("CDCollection");
		mnCollection.add(cdcollection);
		
		dvdcollection = new JMenuItem("DVDCollection");
		dvdcollection.addActionListener(new EventThread());
		dvdcollection.setActionCommand("DVDCollection");
		mnCollection.add(dvdcollection);

		separator_2 = new JSeparator();
		mnShelfManagement.add(separator_2);
		mntShelfRepository.setActionCommand("ShelfList");
		mnShelfManagement.add(mntShelfRepository);

		/*************************************** SEARCH *******************************************************************/
		/**
		 * Second menu
		 */
		mnSearch = new JMenu("Search");
		mnSearch.setMnemonic(KeyEvent.VK_N);
		menuBar.add(mnSearch);

		menuItem = new JMenuItem("User", KeyEvent.VK_L);
		menuItem.addActionListener(new EventThread());
		menuItem.setActionCommand("User");
		mnSearch.add(menuItem);

		mntmShelf = new JMenuItem("Shelf");
		mnSearch.add(mntmShelf);

	     mntmShelfelements = new JMenuItem("ShelfElements");
		mnSearch.add(mntmShelfelements);

		/*************************************** HELP *******************************************************************/
		/**
		 *  Third menu
		 */
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmShowInformation = new JMenuItem("Show Information");
		mntmShowInformation.addActionListener(new EventThread());
		mntmShowInformation.setActionCommand("Help");
		mnHelp.add(mntmShowInformation);

		/*************************************** EXIT *******************************************************************/
		/**
		 *  Fourth menu
		 */
		mnExit = new JMenu("Exit");
		menuBar.add(mnExit);
		mnExit.addMouseListener(new EventClose());

		/*************************************** ABOUT *******************************************************************/
		/**
		 *  Fifth menu
		 */
		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		return menuBar;

	}

	/**
	 * Method to create a Panel
	 * 
	 * @param frame
	 * @param demo
	 * @throws IOException
	 */
	public void createContentPane(JFrame frame)
			throws IOException {
	
		/**
		 *  Create an ImagePanel Object
		 */
		ImagePanel imagePanel = setBackGroundImage(frame) ;

		btnClickToLogin = new JButton("Click To Login ");
		btnClickToLogin.setBackground(new Color(240, 240, 240));
		btnClickToLogin.setBounds(168, 40, 120, 37);
		btnClickToLogin.addActionListener(new EventLoginHandling(frame));
		imagePanel.add(btnClickToLogin);
		
		lbmessage = new JLabel("");
		lbmessage.setForeground(Color.WHITE);
		lbmessage.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
		lbmessage.setBounds(308, 11, 132, 20);
		imagePanel.add(lbmessage);
	}



	/**
	 * Inner Class that interact with the Login Button, by implementing
	 * ActionListener Interface and invoke actionPerformed method. The action is
	 * made in an Background Thread, by run SwingWorker framework.
	 * 
	 * @param frame
	 * @param demo
	 * @return
	 */
	private class EventLoginHandling implements ActionListener {

		JFrame frame;
		

		public EventLoginHandling(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {

			SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
			
				Login loginDlg;
				Boolean flag = false;
			

				@Override
				protected Boolean doInBackground() throws Exception {
					System.out.println(Thread.currentThread());

					loginDlg = new Login(getUsername(), getUsername(),frame);
					loginDlg.setVisible(true);
					return loginDlg.isSucceeded();

				}

				@Override
				protected void done() {

					try {
						if ((boolean) get() == true) {

							menuBar.setEnabled(true);
							menuItem.setEnabled(true);
							mnEdit.setEnabled(true);
							lbmessage.setText("WellCome " + username.toUpperCase());
							btnClickToLogin.setVisible(false);


						}
					} catch (InterruptedException e) {
						Logger.getLogger( EventLoginHandling.class.getName()).log(Level.SEVERE, "InterruptedException in done method SwingWorker", e );
				        
					} catch (ExecutionException e) {

						Logger.getLogger( EventLoginHandling.class.getName()).log(Level.SEVERE, "ExecutionException in done method SwingWorker", e );
					}

				}

			};
			worker.execute();
		}
	}

	/**
	 * Inner Class to treat Event in the EDT, by implementing
	 * ActionListener Interface and invoke actionPerformed method.
	 * 
	 */
	private class EventThread implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
		
			if (ev.getActionCommand().equals("UserList")) {
				new UserRepositorySwing.Factory().newInstance(getUsername(), getPassword(),userCommands);
			}
			
			else if (ev.getSource()== mntShelfRepository) {
				new ShelfRepositorySwing.Factory().newInstance(getUsername(), getPassword(), shelfCommands);
			}

			else if (ev.getSource()== mntmBook) {
				new Book.Factory().newInstance(getUsername(), getPassword(), shelfCommands);
			}

			else if (ev.getSource()==mntmCD ){
				new CD.Factory().newInstance(getUsername(), getPassword(), shelfCommands);

			} else if (ev.getActionCommand().equals("DVD")) {
				new DVD.Factory().newInstance(getUsername(), getPassword(), shelfCommands);
			}

			else if (ev.getActionCommand().equals("DVDCollection")) {
				new DVDCollection.Factory().newInstance(getUsername(), getPassword(), shelfCommands);
			}
			else if (ev.getActionCommand().equals("CDCollection")) {
				new CDCollection.Factory().newInstance(getUsername(), getPassword(), shelfCommands);
			}
			
			else if (ev.getSource()==bookcollection) {
				new BookCollection.Factory().newInstance(getUsername(), getPassword(), shelfCommands);
			}
			else if (ev.getSource()==mntmShowInformation) {
				new Help();

			}
		}
	}

	/**
	 * 
	 * Inner Class to treat Event Close in the EDT, by implementing
	 * MouseListener Interface and invoke mouseClicked method.
	 *
	 */
	private class EventClose implements MouseListener {

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
