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
import fhj.shelf.repos.UserRepository;

@SuppressWarnings("serial")
public class UserRepositorySwing extends JFrame {

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
	private static SaveUser novoContacto;
	private static SearchUser procurarNome;
	private static UserDetails listarContactos;
	private static UserRepository repository;
	private static JMenuItem mntmPatchuser;
	private static PatchUser patchUser;
	private final static String source = "/User1.png";
	private static ImagePanel jlImagem_1;

	/**
	 * Constructor
	 * 
	 * @param repository
	 */
	public UserRepositorySwing(UserRepository repository) {
		this.repository = repository;

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
		jmExit.addMouseListener(new EventThreadClose());
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

			this.jlImagem = new ImagePanel(resizedImage);
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
			System.out.println("repositorio" + Thread.currentThread());
			if (ev.getSource() == jmiUser) {
				novoContacto = new SaveUser(repository);
				novoContacto.setVisible(true);
			} else if (ev.getSource() == jmiUserList) {
				listarContactos = new UserDetails(repository);
				listarContactos.setVisible(true);
			} else if (ev.getSource() == jmibyName) {
				procurarNome = new SearchUser(repository);
				procurarNome.setVisible(true);
			} else if (ev.getSource() == mntmPatchuser) {
				patchUser = new PatchUser(repository);
				patchUser.setVisible(true);
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
