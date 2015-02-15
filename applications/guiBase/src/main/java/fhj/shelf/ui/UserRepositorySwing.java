package fhj.shelf.ui;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import fhj.shelf.commandsFactory.PostUserGUI;
import fhj.shelf.factoriesWindows.PostUserCommandFactory;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.imageUI.ImagePanel;


@SuppressWarnings("serial")
public class UserRepositorySwing extends JFrame implements LoginContract,PostUserGUI {

	
	
	
	public static class Factory implements PostUserCommandFactory {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param userRepo
		 *            is an instance of UserRepository
		 * @param shelfRepo
		 *            is an instance of ShelfRepository
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of PostShelf
		 */
		
		@Override
		public PostUserGUI newInstance(String username, String password, Map<String, CommandFactory> mapCommands) {
			return new UserRepositorySwing(username, password,mapCommands);
		}
	}
	
		
	
	private static final int DI_Y = 0;
	private static final int DI_X = 0;
	private static final int RS_HEIGHT = 340;
	private static final int RS_WIDTH = 300;
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
	private final static String source = "/User1.png";

	Map<String, CommandFactory> userCommands;
private String username;
private String password;
	/**
	 * Constructor
	 * 
	 * @param repository
	 */
	public UserRepositorySwing(String username, String password,Map<String, CommandFactory> mapCommands) {
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

		setImage();
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
//		jmExit.addMouseListener(new EventThreadClose());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}





	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}





	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}



	/**
	 * Method to set Image in the Window
	 */
	private void setImage() {
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(source));
			BufferedImage resizedImage = resize(image, RS_WIDTH, RS_HEIGHT);// resize the
																	// image to
																	// 300x340

			UserRepositorySwing.jlImagem = new ImagePanel(resizedImage);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Auxiliary Method for treatment resize image
	 * 
	 * @param image
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage resize(BufferedImage image, int width,
			int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, DI_X, DI_Y, width, height, null);
		g2d.dispose();
		return bi;
	}

	/**
	 * Inner Class to treat Event thread in the EDT, by implementing
	 * ActionListener Interface and invoke actionPerformed method.
	 * 
	 */
	private class EventThread implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			
			ensureEventThread();
						
			if (ev.getSource() == jmiUser) {
				new SaveUser.Factory().newInstance(getUsername(), getPassword(), userCommands);
				
			} else if (ev.getSource() == jmiUserList) {
				new UserDetails.Factory().newInstance(getUsername(), getPassword(), userCommands);
			
			} else if (ev.getSource() == jmibyName) {
				new SearchUser.Factory().newInstance(userCommands);
				
			} else if (ev.getSource() == mntmPatchuser) {
				new PatchUser.Factory().newInstance(getUsername(), getPassword(), userCommands);
			
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

	/**
	 * Method to ensure if the code runs on a special Thread known as the EDT
	 * (EventDispatchThread)
	 */
	private void ensureEventThread() {
		// throws an exception if not invoked by the
		// event thread.
		if (SwingUtilities.isEventDispatchThread())
			return;

		throw new RuntimeException("only the event "
				+ "thread should invoke this method");
	}





	
}

