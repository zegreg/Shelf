package fhj.shelf.ui;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fhj.shelf.utils.repos.ShelfRepository;
import fhj.shelf.utils.repos.UserRepository;

import java.io.IOException;

@SuppressWarnings("serial")
public class ShelfRepositorySwing extends JFrame {

	private static JMenuBar barraMenu;
	private static JMenu mnEdit;
	private static JMenuItem jmiNewShelf;
	private static JMenuItem jmiShelfList;
	private static JMenu mnSearch;
	private static JMenuItem jmiProcNome;
	private static JMenuItem jmiProcTelf;
	private static JMenu jmExit;
	private static SaveShelf novoContacto;
	private static ShelfDetails listarContactos;
	private static SearchShelf searchShelf;
	private final static String source = "/icone.gif";
	private static ImagePanel jlImagem_1;
	private static JPanel jlImagem;
	private ShelfRepository shelfRepository;
	private UserRepository repository;

	public ShelfRepositorySwing(UserRepository repository,
			ShelfRepository shelfRepository) {
		this.shelfRepository = shelfRepository;
		this.repository = repository;

		barraMenu = new JMenuBar();
		mnEdit = new JMenu("Edit");
		jmiNewShelf = new JMenuItem("New Shelf");
		jmiShelfList = new JMenuItem("Shelf List");
		mnSearch = new JMenu("Search");
		jmiProcNome = new JMenuItem("By name");
		jmiProcTelf = new JMenuItem("By id");
		jmExit = new JMenu("Exit");

		setImage();

		setTitle("ShelfRepository");
		setSize(300, 366);
		setLocation(50, 50);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(new FlowLayout());
		setJMenuBar(barraMenu);
		barraMenu.add(mnEdit);
		mnEdit.add(jmiNewShelf);
		mnEdit.add(jmiShelfList);
		barraMenu.add(mnSearch);
		mnSearch.add(jmiProcNome);
		mnSearch.add(jmiProcTelf);
		barraMenu.add(jmExit);

		getContentPane().add(jlImagem);

		jmiNewShelf.addActionListener(new EventThread());
		jmiShelfList.addActionListener(new EventThread());
		jmiProcNome.addActionListener(new EventThread());
		jmiProcTelf.addActionListener(new EventThread());
		jmExit.addMouseListener(new EventThreadClose());
	}

	/**
	 * Method to set Image in the Window
	 */
	private void setImage() {
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(source));
			BufferedImage resizedImage = resize(image, 300, 340);// resize the
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
		g2d.drawImage(image, 0, 0, width, height, null);
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
			if (ev.getSource() == jmiNewShelf) {
				novoContacto = new SaveShelf(repository, shelfRepository);
				novoContacto.setVisible(true);
			} else if (ev.getSource() == jmiShelfList) {
				listarContactos = new ShelfDetails(repository, shelfRepository);
				listarContactos.setVisible(true);

			} else if (ev.getSource() == jmiProcNome) {
				searchShelf = new SearchShelf(shelfRepository);
				searchShelf.setVisible(true);
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

		if (SwingUtilities.isEventDispatchThread())
			return;
		// throws an exception if not invoked by the
		// event thread.
		throw new RuntimeException("only the event "
				+ "thread should invoke this method");
	}
}
