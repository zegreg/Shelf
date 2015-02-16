package fhj.shelf.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fhj.shelf.actionWindow.PostActionWindow;
import fhj.shelf.actionWindowFactory.PostActionWindowFactory;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.imageUI.CreateImage;


import java.util.Map;

@SuppressWarnings("serial")
public class ShelfRepositorySwing extends CreateImage implements PostActionWindow {
	
	private static final int LOCATION_Y = 50;
	private static final int LOCATION_X = 50;
	private static final int SIZE_HEIGHT = 366;
	private static final int SIZE_WIDTH = 300;
	private static JMenuBar barraMenu;
	private static JMenu mnEdit;
	private static JMenuItem jmiNewShelf;
	private static JMenuItem jmiShelfList;
	private static JMenu mnSearch;
	private static JMenuItem jmiProcNome;
	private static JMenu jmExit;
	private final JPanel jlImagem;

	Map<String, CommandFactory> shelfCommands;
	private String username;
	private String password;
	
	/**
	 * 
	 * Class that a single instance of UserRepositorySwing class.
	 * Implements PostActionWindowFactory and returns a PostActionWindow 
	 *
	 */
	public static class Factory implements PostActionWindowFactory {

		private static ShelfRepositorySwing singleIsntance;
		/**
		 * This is the constructor for the class above, it defines the factory
		 * of ShelfRepositorySwing class. Defines the image source and the size		
		 */
		
		private static String source ;
		private int width;
		private int heigth;
		
		public Factory() {
              source = "/icone.gif" ;
              width = 300;
              heigth =360;
		}

		/**
		 * This is an override method of the base class, it returns a new single 
		 * instance of ShelfRepositorySwing
		 */
		
		@Override
		public PostActionWindow newInstance(String username, String password, Map<String, CommandFactory> mapCommands) {
			
			if (singleIsntance == null) {
				singleIsntance = new ShelfRepositorySwing(username, password,mapCommands, source, width, heigth);
				return singleIsntance;
			}
			return singleIsntance;
		}
	}



	/**
	 * Constructor define and show window
	 * @param username
	 * @param password
	 * @param shelfCommands
	 * @param source
	 * @param width
	 * @param heigth
	 */
	public ShelfRepositorySwing(String username, String password, Map<String, CommandFactory> shelfCommands, String source, int width, int heigth) {
		super(source, width,heigth);
		this.username = username;
		this.password = password;
		this.shelfCommands = shelfCommands;

		barraMenu = new JMenuBar();
		mnEdit = new JMenu("Edit");
		jmiNewShelf = new JMenuItem("New Shelf");
		jmiShelfList = new JMenuItem("Shelf List");
		mnSearch = new JMenu("Search");
		jmiProcNome = new JMenuItem("By id");
		jmExit = new JMenu("Exit");

		jlImagem = setBackGroundImage();

		setTitle("ShelfRepository");
		setSize(SIZE_WIDTH, SIZE_HEIGHT);
		setLocation(LOCATION_X, LOCATION_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(new FlowLayout());
		setJMenuBar(barraMenu);
		barraMenu.add(mnEdit);
		mnEdit.add(jmiNewShelf);
		mnEdit.add(jmiShelfList);
		barraMenu.add(mnSearch);
		mnSearch.add(jmiProcNome);
		barraMenu.add(jmExit);

		getContentPane().add(jlImagem);

		jmiNewShelf.addActionListener(new EventThread());
		jmiShelfList.addActionListener(new EventThread());
		jmiProcNome.addActionListener(new EventThread());
		jmExit.addMouseListener(new EventClose());
	}

	/**
	 * Inner Class to treat Event in the EDT, by implementing
	 * ActionListener Interface and invoke actionPerformed method.
	 * 
	 */
	private class EventThread implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
		
			
			if (ev.getSource() == jmiNewShelf) {
		
			new SaveShelf.Factory().newInstance(username, username, shelfCommands);
			
			} else if (ev.getSource() == jmiShelfList) {
			new ShelfDetails.Factory().newInstance(shelfCommands);
			
			} else if (ev.getSource() == jmiProcNome) {
			 new SearchShelf.Factory().newInstance(shelfCommands);
			
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

		private static final int RF_STATUS = 0;

		public void mouseClicked(MouseEvent ev) {
			System.exit(RF_STATUS);
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
