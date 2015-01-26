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
 * @author José Oliveira
 *
 */
public class MainFrame {
	
	private JMenuBar menuBar;
	private JMenu mnEdit, mnShelfManagement, mnSearch, mnUserManagement,
	mnHelp, mnExit, mnAbout;
	private JMenuItem menuItem, mntUserDataBase, mntShelfRepository,mntmDVD,mntmCD;
	private JSeparator separator_2;
	private ShelfRepository shelfRepository;
	private UserRepository repository;
	private ElementsRepository elementsRepository;


	/**
	 * Constructor
	 */
	public MainFrame() {
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
		// Criaçao da barra de menu
		menuBar = new JMenuBar();

		/*************************************** EDIT *******************************************************************/
		// Construçao do primeiro menu
		mnEdit = new JMenu("Edit");
		mnEdit.setEnabled(false);

		// Tecla de atalho:P
		mnEdit.setMnemonic(KeyEvent.VK_P);
		menuBar.add(mnEdit);

		// Construção UserManagement menu e adição ao menu Edit
		mnUserManagement = new JMenu("UserManagement");

		// Construção do menu UserDataBase e adição ao UserManagement
		mntUserDataBase = new JMenuItem("UserDataBase");
		mntUserDataBase.addActionListener(new EventPanel());
		mntUserDataBase.setActionCommand("UserList");
		mnUserManagement.add(mntUserDataBase);
		mnEdit.add(mnUserManagement);

		// Construção do ShelfManagement
		mnShelfManagement = new JMenu("ShelfManagement");

		// Construção do menuShelfRepositorio e adição ao ShelfManagement
		mntShelfRepository = new JMenuItem("ShelfRepository");
		mntShelfRepository.addActionListener(new EventPanel());
		mnEdit.add(mnShelfManagement);

		JMenu mnAddelement = new JMenu("AddShelfElement");
		mnShelfManagement.add(mnAddelement);

		JMenu mnElement = new JMenu("Element");
		mnAddelement.add(mnElement);

		JMenuItem mntmBook = new JMenuItem("Book");
		mntmBook.addActionListener(new EventPanel());
		mntmBook.setActionCommand("Book");
		mnElement.add(mntmBook);

		mntmDVD = new JMenuItem("DVD");
		mnElement.add(mntmDVD);

		mntmCD = new JMenuItem("CD");
		mnElement.add(mntmCD);

		JMenu mnCollection = new JMenu("Collection");
		mnAddelement.add(mnCollection);

		separator_2 = new JSeparator();
		mnShelfManagement.add(separator_2);
		mntShelfRepository.setActionCommand("ShelfList");
		mnShelfManagement.add(mntShelfRepository);

		/*************************************** SEARCH *******************************************************************/
		// Segundo menu
		mnSearch = new JMenu("Search");
		mnSearch.setMnemonic(KeyEvent.VK_N);
		menuBar.add(mnSearch);

		// Grupo de itens de menu
		menuItem = new JMenuItem("User", KeyEvent.VK_L);
		menuItem.addActionListener(new EventPanel());
		menuItem.setActionCommand("User");
		mnSearch.add(menuItem);

		/*************************************** HELP *******************************************************************/
		// Terceiro Menu
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmShowInformation = new JMenuItem("Show Information");
		mntmShowInformation.addActionListener(new EventPanel());
		mntmShowInformation.setActionCommand("Help");
		mnHelp.add(mntmShowInformation);

		/*************************************** EXIT *******************************************************************/
		// Quarto Menu
		mnExit = new JMenu("Exit");
		menuBar.add(mnExit);
		mnExit.addMouseListener(new EventoJMenuSair());

		/*************************************** ABOUT *******************************************************************/
		// Quinto Menu
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
	public void createContentPanel(JFrame frame, MainFrame demo)
			throws IOException {

		ImagePanel imagePanel = setBackGroundImage(frame);

		JButton btnClickToLogin = new JButton("Click To Login ");
		btnClickToLogin.setBackground(new Color(240, 240, 240));
		btnClickToLogin.setBounds(168, 40, 120, 37);
		btnClickToLogin.addActionListener(new EventModelExecuter(frame));

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
	 * Metho to create a GUI
	 * 
	 * @throws IOException
	 */
	private static void createAndShowGUI() throws IOException {

		MainFrame demo = new MainFrame();

		JFrame frame = new JFrame("Shelf");
		frame.pack();
		frame.setSize(450, 260);// coloca as dimensões da janela em 450 pixeis
								// de largura e 260 pixeis de alt
		frame.setVisible(true);// Torna a janela visivel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(450, 260);

		// add MenuBar to the frame
		frame.setJMenuBar(demo.createMenuBar());

		// add ImagePanel to the frame
		demo.createContentPanel(frame, demo);
	}

	/**
	 * 
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
	 * Login Button
	 * 
	 * @param frame
	 * @param demo
	 * @return
	 */
	private class EventModelExecuter implements ActionListener {

		JFrame frame;

		public EventModelExecuter(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {

			class eventHandling extends SwingWorker {
				Login loginDlg;
				Boolean flag = false;

				@Override
				protected Boolean doInBackground() throws Exception {
					// menuBar.setVisible(true);
					loginDlg = new Login(frame);
					loginDlg.setVisible(true);
					flag = loginDlg.isSucceeded();
					System.out.println(flag);
					return flag;

				}

				@Override
				protected void done() {

					try {
						if ((boolean) get() == true) {

							menuBar.setEnabled(true);
							menuItem.setEnabled(true);
							mnEdit.setEnabled(true);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
			new eventHandling().execute();
		}
	}

	private class EventPanel implements ActionListener {

		public void actionPerformed(ActionEvent ev) {

			if (ev.getActionCommand().equals("UserList")) {

				new UserRepositorySwing(repository);

			}

			else if (ev.getActionCommand().equals("ShelfList")) {
				new ShelfRepositorySwing(repository, shelfRepository);
			}

			else if (ev.getActionCommand().equals("Book")) {

				new Book(shelfRepository, elementsRepository);

			}
			//
			// else if (ev.getActionCommand().equals("User")) {
			//
			// new SearchUser();
			//
			// }
		}
	}

	private class EventoJMenuSair implements MouseListener {

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
		// cria e mostra a interface gráfica deste exemplo
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
