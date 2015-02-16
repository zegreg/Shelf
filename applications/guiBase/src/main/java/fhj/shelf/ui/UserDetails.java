package fhj.shelf.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import fhj.shelf.actionwindow.GetActionWindow;
import fhj.shelf.actionwindowfactory.GetActionWindowFactory;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;

import java.awt.SystemColor;



@SuppressWarnings("serial")
public class UserDetails extends JFrame implements GetActionWindow{

	/**
	 * 
	 * Class that a single instance of SearchShelf class. Implements
	 * GetActionWindowFactory  and returns a GetActionWindow 
	 *
	 */
	public static class Factory implements GetActionWindowFactory {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of UserDetails
		 */
		
		@Override
		public GetActionWindow newInstance(Map<String, CommandFactory> mapCommands) {
			return new UserDetails(mapCommands);
		}
	}
	
	
	
	private static final int DIM_HEIGHT = 125;
	private static final int DIM_WIDTH = 475;
	private static final int LOCATION_Y = 100;
	private static final int LOCATION_X = 100;
	private static final int SIZE_HEIGHT = 200;
	private static final int SIZE_WIDTH = 500;
	/**
	 * Declares components
	 */
	private static JLabel jlTitulo;
	private static DefaultTableModel tmContactos;
	private static JTable jtContactos;
	private static JScrollPane jspContactos;
	Map<String, CommandFactory> mapCommands;
	/**
	 * Constructor
	 * 
	 * @param mapCommands
	 */
	public UserDetails(Map<String, CommandFactory> mapCommands) {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		this.mapCommands = mapCommands;

		createAndShowGUI();

	}

	private void createAndShowGUI() {
		createJTable();

		// Sets window properties
		setTitle("User List");
		setSize(397, 216);
		setLocation(LOCATION_X, LOCATION_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		// Adds components to the window
		getContentPane().add(jlTitulo);
		getContentPane().add(jspContactos);

		new EventUserDetailsHandling().execute();
	}

	/**
	 * Method to create a JTable with a 4 columns and 4 rows
	 * 
	 * @return
	 */
	private JTable createJTable() {
		jlTitulo = new JLabel("UserList");
		jlTitulo.setBounds(153, 11, 54, 14);
		jtContactos = new JTable(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"Name"
			}
		));

		jspContactos = new JScrollPane(jtContactos);
		jspContactos.setBounds(35, 28, 300, 125);
		jspContactos
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspContactos.setPreferredSize(new Dimension(300, 125));
		jtContactos.setCellSelectionEnabled(true);
		// Prevents the selection of more than one table row simultaneously
		jtContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return jtContactos;
	}

	/**
	 * Inner Class that implements ActionListener Interface, and invoke
	 * actionPerformed method. The action is made in an Background Thread, by
	 * run SwingWorker framework.
	 */
	private class EventUserDetailsHandling extends
			SwingWorker<Map<String, String>, Void> {

		private static final int JTCEV_COLUMN = 3;
		private static final int JTCFV_COLUMN = 2;
		private static final int JTCPV_COLUMN = 1;
		private static final int JTCV_COLUMN = 0;

		@Override
		protected Map<String, String> doInBackground() throws Exception {
			CommandGetFactoryWithoutParameters getUsers = (CommandGetFactoryWithoutParameters) mapCommands.get("getUsers");
		
			return  getUsers.newInstance().execute();
		}

		@Override
		protected void done() {

	
				try {
					for (int j = 0; j < 4; j++) {
						jtContactos.setValueAt(get().get("Username="+j), j, JTCV_COLUMN);
					}
					
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				} catch (ExecutionException e) {
				
					e.printStackTrace();
				}
			
			

		}

	}

}
