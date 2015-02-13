package fhj.shelf.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;

import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.repos.UserRepository;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ShelfDetails extends JFrame {

	private static final int JSPSD_HEIGHT = 125;
	private static final int JSPSD_WIDTH = 475;
	private static final int LOCATION_Y = 250;
	private static final int LOCATION_X = 450;
	private static final int SIZE_HEIGHT = 200;
	private static final int SIZE_WIDTH = 500;
	// Declares and creates components
	private static JTable jtShelfContents;
	private static JScrollPane jspShelfContents;
	private UserRepository repository;
	private ShelfRepository shelfRepository;
	private final JButton btnShelfdetails;
	Map<String, CommandFactory> shelfCommands;

	// Construtor
	public ShelfDetails(Map<String, CommandFactory> shelfCommands) {

		this.shelfCommands = shelfCommands;
	
		
		btnShelfdetails = new JButton("ShelfDetails");
		createAndShowGUI();

	}

	

	public ShelfRepository getShelfRepository() {
		return shelfRepository;
	}

	/**
	 * Method to create the Frame
	 */
	private void createAndShowGUI() {
		createContentTable();
		// Sets window properties
		setTitle("Shelfs Details");
		setSize(SIZE_WIDTH, SIZE_HEIGHT);
		setLocation(LOCATION_X, LOCATION_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setVisible(true);
		getContentPane().add(btnShelfdetails);
		getContentPane().add(jspShelfContents);

		
		btnShelfdetails.addActionListener(new EventShelfDetailsHandling());
		
	}

	/**
	 * Method to create a JTable
	 * 
	 * @return
	 */
	private JTable createContentTable() {

		jtShelfContents = new JTable(new DefaultTableModel(new Object[][] {
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null },
				{ null, null, null }, }, new String[] { "idElement",
				"Number Elements", "Free Space" }));

		jspShelfContents = new JScrollPane(jtShelfContents);
		jspShelfContents
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspShelfContents.setPreferredSize(new Dimension(JSPSD_WIDTH, JSPSD_HEIGHT));
		jtShelfContents.setCellSelectionEnabled(true);
		// Prevents the selection of more than one table row simultaneously
		jtShelfContents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return jtShelfContents;
	}

	/**
	 * Inner Class that implements ActionListener Interface, and invoke
	 * actionPerformed method. The action is made in an Background Thread, by
	 * run SwingWorker framework.
	 */
	private class EventShelfDetailsHandling implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {

			class eventHandling extends SwingWorker<Object , Void>  {
               
				String  path = "GET /shelfs/ accept=application/json";
				boolean modeStandAlone = false;
				
				@Override
				protected Object  doInBackground() throws Exception
				{    

					CommandGetFactoryWithoutParameters getShelves = (CommandGetFactoryWithoutParameters) shelfCommands.get("getShelfs");
					return getShelves.newInstance().execute();
				}

				@SuppressWarnings("unchecked")
				@Override
				protected void done() {

					int i = 0;


					try {
						for (Entry<String, String> element : ((Map<String, String>) get()).entrySet()) 
						{
//							[{"Shelf_id=01":"Elements:0&FreeSpace:10"}]}
							// Fill the cells in the empty line. The numbering of the
							// columns starts at 0
							jtShelfContents.setValueAt(element.getKey(), i, 0);
							String[] str =element.getValue().split("&");
							jtShelfContents.setValueAt(str[0], i, 1);
							jtShelfContents.setValueAt(str[1], i, 2);
							//					jtShelfContents.setValueAt(element.getValue()
							//							.getFreeSpace(), i, JTSVFV_COLUMNS);

							i++;
						}
						
//						jtShelfContents.setValueAt(get().get("Shelf_id="), i, 0);
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
}
