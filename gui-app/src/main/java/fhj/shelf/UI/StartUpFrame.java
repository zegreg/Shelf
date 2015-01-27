package fhj.shelf.UI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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









import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.InMemoryElementsRepository;
import fhj.shelf.utils.repos.InMemoryShelfRepository;
import fhj.shelf.utils.repos.InMemoryUserRepository;
import fhj.shelf.utils.repos.ShelfRepository;
import fhj.shelf.utils.repos.UserRepository;

import java.awt.BorderLayout;

/**
 * 
 */

/**
 * @author Filipa Estiveira, Hugo Leal e José Oliveira
 *
 */
public class StartUpFrame {
	
	
	
	private static JMenuBar menuBar;
	private static JMenu mnEdit, mnShelfManagement, mnSearch, mnUserManagement,
	mnHelp, mnExit, mnAbout;
	private static JMenuItem menuItem, mntUserDataBase, mntShelfRepository,mntmDVD,mntmCD;
	private static JSeparator separator_2;
	private static ShelfRepository shelfRepository;
	private static UserRepository repository;
	private static ElementsRepository elementsRepository;
	private static JButton btnClickToLogin;


	/**
	 * Constructor
	 */
	public StartUpFrame() {
		repository = new InMemoryUserRepository();
		shelfRepository = new InMemoryShelfRepository();
		elementsRepository = new InMemoryElementsRepository();
	}

	/**
	 * Method that creates the Menu
	 * 
	 * @return
	 */
	public JMenuBar createMenuBar() {
		//menu bar creation 
		menuBar = new JMenuBar();

		/*************************************** EDIT *******************************************************************/
		//first menu creation
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

		//  ShelfRepository menu construction and added to the ShelfManagement
		mntShelfRepository = new JMenuItem("ShelfRepository");
		mntShelfRepository.addActionListener(new EventThread());
		mnEdit.add(mnShelfManagement);

		JMenu mnAddelement = new JMenu("AddShelfElement");
		mnShelfManagement.add(mnAddelement);

		JMenu mnElement = new JMenu("Element");
		mnAddelement.add(mnElement);

		JMenuItem mntmBook = new JMenuItem("Book");
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

		JMenu mnCollection = new JMenu("Collection");
		mnAddelement.add(mnCollection);

		separator_2 = new JSeparator();
		mnShelfManagement.add(separator_2);
		mntShelfRepository.setActionCommand("ShelfList");
		mnShelfManagement.add(mntShelfRepository);

		/*************************************** SEARCH *******************************************************************/
		//Second menu
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

		JMenuItem mntmShowInformation = new JMenuItem("Show Information");
		mntmShowInformation.addActionListener(new EventThread());
		mntmShowInformation.setActionCommand("Help");
		mnHelp.add(mntmShowInformation);

		/*************************************** EXIT *******************************************************************/
		// Fourth menu
		mnExit = new JMenu("Exit");
		menuBar.add(mnExit);
		mnExit.addMouseListener(new EventThreadClose());

		/*************************************** ABOUT *******************************************************************/
		//Fifth menu
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
	public void createContentPane(JFrame frame, StartUpFrame demo)
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
	 */
	private static ImagePanel setBackGroundImage(JFrame frame)
			throws IOException {
		File file = new File(
				"C:\\Users\\José Oliveira\\Pictures\\Bookshelf-2.jpg");
		BufferedImage image = ImageIO.read(file);
		BufferedImage resizedImage = resize(image, 450, 260);// resize the image
																// to 100x100
		ImagePanel imagePanel = new ImagePanel(resizedImage);
		frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
		return imagePanel;
	}

	/**
	 * Method to create the  GUI
	 * 
	 * @throws IOException
	 */
	private static void createAndShowGUI() throws IOException {

		StartUpFrame demo = new StartUpFrame();

		JFrame frame = new JFrame("Shelf");
		frame.pack();
		frame.setSize(450, 260);// sets the size of the window 450 pixels wide and 260 pixels high
		frame.setVisible(true);// Makes visible window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(450, 260);

		// add MenuBar to the frame
		frame.setJMenuBar(demo.createMenuBar());

		// add ImagePanel to the frame
		demo.createContentPane(frame, demo);
	}

	/**
	 * Auxiliary Method for treatment resize image
	 * @param image
	 * @param width
	 * @param height
	 * @return
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
	 * Method to ensure if the code runs on a special Thread known as the EDT (EventDispatchThread)
	 */
	private void ensureEventThread() {
		
		if ( SwingUtilities.isEventDispatchThread() ) 
		 return;
		// throws an exception if not invoked by the event thread.
		throw new RuntimeException("only the event " +
				"thread should invoke this method");
	}
	
	
	/**
	 * Inner Class that interact with the Login Button, by implementing ActionListener Interface
	 *  and invoke actionPerformed method.
	 * The action is made in an Background Thread, by run SwingWorker framework.
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

				@Override
				protected Boolean doInBackground() throws Exception {
					System.out.println(Thread.currentThread());
				
					loginDlg = new Login(frame);
					loginDlg.setVisible(true);
					flag = loginDlg.isSucceeded();
				
					return flag;

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
					
						e.printStackTrace();
					} catch (ExecutionException e) {
					
						e.printStackTrace();
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
			if (ev.getActionCommand().equals("UserList"))
			{
				new UserRepositorySwing(repository);
			}

			else if (ev.getActionCommand().equals("ShelfList"))
			{
				new ShelfRepositorySwing(repository, shelfRepository);
			}

			else if (ev.getActionCommand().equals("Book"))
			{
				new Book(shelfRepository, elementsRepository);
			}
			
			else if (ev.getActionCommand().equals("CD"))
			{
				new CD(shelfRepository, elementsRepository);
			}
			else if (ev.getActionCommand().equals("DVD"))
			{
				new DVD(shelfRepository, elementsRepository);
			}
			
			 else if (ev.getActionCommand().equals("Help"))
			 {			
				 new Help();
			
			 }
		}
	}

	/**
	 * 
	 *Inner Class to treat Event thread Close in the EDT, by implementing 
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

	public static void main(String[] args) {

		/*
		 * The invokeLater() method does not wait for the block of code, this allows the thread
		 * that posted the request to move on to other activities.
		 * Thread[AWT-EventQueue-0,6,main]
		 */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
	
				try {
					
					createAndShowGUI();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			}
		});
	}
}
