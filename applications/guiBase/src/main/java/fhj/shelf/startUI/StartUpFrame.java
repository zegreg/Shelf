package fhj.shelf.startUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.imageUI.ImagePanel;
import fhj.shelf.loginUI.Login;
import fhj.shelf.ui.Book;
import fhj.shelf.ui.BookCollection;
import fhj.shelf.ui.CD;
import fhj.shelf.ui.CDCollection;
import fhj.shelf.ui.DVD;
import fhj.shelf.ui.DVDCollection;
import fhj.shelf.ui.Help;
import fhj.shelf.ui.ShelfRepositorySwing;
import fhj.shelf.ui.LoginContract;
import fhj.shelf.ui.UserRepositorySwing;

import java.awt.BorderLayout;

/**
 * 
 */

/**
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 *
 */

public class StartUpFrame implements LoginContract{

	private static JMenuBar menuBar;
	private static JMenu mnEdit, mnShelfManagement, mnSearch, mnUserManagement,
			mnHelp, mnExit, mnAbout,mnCollection,mnAddelement,mnElement;
	private static JMenuItem menuItem, mntUserDataBase, mntShelfRepository,
			mntmDVD, mntmCD, mntmBook, mntmShowInformation, bookcollection,cdcollection,
	dvdcollection;;
	private static JSeparator separator_2;

	private static JButton btnClickToLogin;

	
	
	
	private Map<String, CommandFactory> userCommands;
	private Map<String, CommandFactory> shelfCommands;
	private String username;
	private String password;
	
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
		
		this.username = username;
		this.password  = password;
		this.userCommands = userCommands;
		this.shelfCommands = shelfCommands;
		
		JFrame frame = new JFrame("Shelf");
		frame.pack();
		frame.setSize(450, 260);// sets the size of the window 450
								// pixels wide
								// and 260 pixels high
		frame.setVisible(true);// Makes visible window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(450, 260);

		// add MenuBar to the frame
		frame.setJMenuBar(createMenuBar());

		// add ImagePanel to the frame
		createContentPane(frame);
		
		
	}
	
	
	public String getPassword() {
		return password;
	}
	
	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Method that creates the Menu
	 * 
	 * @return
	 * @wbp.parser.entryPoint
	 */
	public  JMenuBar createMenuBar() {
		// menu bar creation
		menuBar = new JMenuBar();

		/*************************************** EDIT *******************************************************************/
		// first menu creation
		mnEdit = new JMenu("Edit");
		mnEdit.setEnabled(false);

		// keyEvent: P
		mnEdit.setMnemonic(KeyEvent.VK_P);
		menuBar.add(mnEdit);

		// UserManagement menu construction and added to the Edit menu
		mnUserManagement = new JMenu("UserManagement");

		// UserDataBase menu construction and added to the UserManagement
		mntUserDataBase = new JMenuItem("UserDataBase");
		mntUserDataBase.addActionListener(new EventThread());
		mntUserDataBase.setActionCommand("UserList");
		mnUserManagement.add(mntUserDataBase);
		mnEdit.add(mnUserManagement);

		// ShelfManagement construction
		mnShelfManagement = new JMenu("ShelfManagement");

		// ShelfRepository menu construction and added to the ShelfManagement
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
		// Second menu
		mnSearch = new JMenu("Search");
		mnSearch.setMnemonic(KeyEvent.VK_N);
		menuBar.add(mnSearch);

		menuItem = new JMenuItem("User", KeyEvent.VK_L);
		menuItem.addActionListener(new EventThread());
		menuItem.setActionCommand("User");
		mnSearch.add(menuItem);

		JMenuItem mntmShelf = new JMenuItem("Shelf");
		mnSearch.add(mntmShelf);

		JMenuItem mntmShelfelements = new JMenuItem("ShelfElements");
		mnSearch.add(mntmShelfelements);

		/*************************************** HELP *******************************************************************/
		// Third menu
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmShowInformation = new JMenuItem("Show Information");
		mntmShowInformation.addActionListener(new EventThread());
		mntmShowInformation.setActionCommand("Help");
		mnHelp.add(mntmShowInformation);

		/*************************************** EXIT *******************************************************************/
		// Fourth menu
		mnExit = new JMenu("Exit");
		menuBar.add(mnExit);
		mnExit.addMouseListener(new EventThreadClose());

		/*************************************** ABOUT *******************************************************************/
		// Fifth menu
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

		// Create an ImagePanel Object
		ImagePanel imagePanel = setBackGroundImage(frame);

		btnClickToLogin = new JButton("Click To Login ");
		btnClickToLogin.setBackground(new Color(240, 240, 240));
		btnClickToLogin.setBounds(168, 40, 120, 37);
		btnClickToLogin.addActionListener(new EventLoginHandling(frame));

		imagePanel.add(btnClickToLogin);
	}

	/**
	 * Method static to Create an ImagePanel from a given source
	 * 
	 * @param frame
	 * @return
	 * @throws IOException
	 * @wbp.parser.entryPoint
	 */
	private ImagePanel setBackGroundImage(JFrame frame) throws IOException {
		String source = "/Bookshelf-2.jpg";
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream(
				source));
		
		BufferedImage resizedImage = resize(image, 450, 260);// resize the image
																// to 100x100
		ImagePanel imagePanel = new ImagePanel(resizedImage);
		frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
		return imagePanel;
	}


	/**
	 * Auxiliary Method for treatment resize image
	 * 
	 * @param image
	 * @param width
	 * @param height
	 * @return
	 * @wbp.parser.entryPoint
	 */
	private static BufferedImage resize(BufferedImage image, int width,
			int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

	/**
	 * Method to ensure if the code runs on a special Thread known as the EDT
	 * (EventDispatchThread)
	 * @wbp.parser.entryPoint
	 */
	private void ensureEventThread() {

		if (SwingUtilities.isEventDispatchThread())
			return;
		// throws an exception if not invoked by the event thread.
		throw new RuntimeException("only the event "
				+ "thread should invoke this method");
		
		
		
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

			class eventHandling extends SwingWorker {
				
				
				Login loginDlg;
				Boolean flag = false;
				Logger logger = LoggerFactory.getLogger(eventHandling.class);

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
							btnClickToLogin.setText("WellCome");
							btnClickToLogin.setBackground(Color.GREEN);
							btnClickToLogin.setEnabled(false);

						}
					} catch (InterruptedException e) {
						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
				        
					} catch (ExecutionException e) {

						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					}

				}

			}
			new eventHandling().execute();
		}
	}

	/**
	 * Inner Class to treat Event thread in the EDT, by implementing
	 * ActionListener Interface and invoke actionPerformed method.
	 * 
	 */
	private class EventThread implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			ensureEventThread();
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
	 * Inner Class to treat Event thread Close in the EDT, by implementing
	 * MouseListener Interface and invoke mouseClicked method.
	 *
	 */
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

//	public static void main(String[] args) {
//
//		/*
//		 * The invokeLater() method does not wait for the block of code, this
//		 * allows the thread that posted the request to move on to other
//		 * activities. Thread[AWT-EventQueue-0,6,main]
//		 */
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//
//				try {
//
//					createAndShowGUI();
//				} catch (IOException e) {
//
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
