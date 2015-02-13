package fhj.shelf.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.commandsDomain.CreateAnElementInAShelf;
import fhj.shelf.commandsDomain.GetAllShelfs;
import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.Shelf;
import fhj.shelf.utils.mutation.CDCollectionCreationDescriptor;

@SuppressWarnings("serial")
public class CDCollection extends JFrame {

	/**
	 * Attributes
	 */

	private ShelfRepository shelfRepository;
	private ElementsRepository elementsRepository;
	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfTitle;
	private JComboBox<Object> comboBox;
	private final JButton btnAddCDCollection;
	private final JButton btnDelete;
	private static final Logger logger = LoggerFactory.getLogger(CDCollection.class);
	private Map<String, CommandFactory> shelfCommands;

	/**
	 * Constructor
	 * 
	 * @param shelfRepository
	 * @param elementsRepository
	 */
	public CDCollection(Map<String, CommandFactory> shelfCommands) {
this.shelfCommands = shelfCommands;

		this.btnAddCDCollection = new JButton("AddCDCollection");
		this.btnDelete = new JButton("Delete");
		this.comboBox = new JComboBox<Object>();
		this.jtfTitle = new JTextField(6);
		this.jlTitle = new JLabel("Title");
		this.jlElementType = new JLabel("ShelfId");
		this.comboBox.setBounds(101, 31, 109, 24);

		/* Thread to fill jCombox with shelfRepository data */
		SwingWorker<?, ?> worker = fillComboxFromMap();
		worker.execute();
		/* Adding containers and components to Frame */
		createContentPane();

		/*
		 * Registration ActionListener in the button. When an event is generated
		 * by this component, is created an instance of the private class
		 * EventCDCollection.
		 */
		btnAddCDCollection.addActionListener(new EventCDCollection());

	}

	/**
	 * Method that have the responsibility to fill jCombox with repository data
	 * in a background thread.
	 * 
	 * @return
	 */
	private SwingWorker<?, ?> fillComboxFromMap() {
		SwingWorker<Map<String, String> , Void> worker = new SwingWorker<Map<String, String> , Void>() {
			@Override
			protected Map<String, String> doInBackground()
					throws Exception {
				
				CommandGetFactoryWithoutParameters getShelfs =  (CommandGetFactoryWithoutParameters) shelfCommands.get("getShelfs");
				return getShelfs.newInstance().execute();
			}

			@Override
			protected void done() {

				try {
					for (Entry<String, String> iterable_element : get()
							.entrySet()) {

						comboBox.addItem(iterable_element.getKey());

					}
				} catch (InterruptedException e) {

					logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
				} catch (ExecutionException e) {

					logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
				}

			}
		};
		return worker;
	}

	/**
	 * Method to define the window property and add components to frame
	 */
	private void createContentPane() {

		setTitle("AddShelfElement");
		setSize(500, 330);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		jlElementType.setBounds(21, 28, 96, 31);
		jlTitle.setBounds(21, 89, 42, 18);
		jtfTitle.setBounds(100, 88, 292, 19);
		btnAddCDCollection.setBounds(100, 192, 96, 31);
		btnDelete.setBounds(277, 192, 115, 31);

		// Adiciona os componentes à janela
		getContentPane().add(comboBox);
		getContentPane().add(jlElementType);
		getContentPane().add(jlTitle);
		getContentPane().add(jtfTitle);
		getContentPane().add(btnAddCDCollection);
		getContentPane().add(btnDelete);

	}

	/**
	 * 
	 * This Inner Class implements ActionListener. Creates an Element Shelf by a
	 * background thread with SwingWorker Framework.
	 *
	 */
	private class EventCDCollection implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Map<String, String> params = new TreeMap<String, String>();
			params.put("loginName", "Lima");
			params.put("loginPassword", "SLB");
			params.put("name", jtfTitle.getText());
			params.put("id", comboBox.getSelectedItem().toString());
			params.put("type", "CDCollection");
			
			SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {

	CommandPostFactoryWithParameters postBook = (CommandPostFactoryWithParameters) shelfCommands.get("postElement");
					
					Map<String, String> params = null;
					
					return postBook.newInstance(params).execute();
				}

				@Override
				protected void done() {

					try {
						JOptionPane.showMessageDialog(null,
								"Data were successfully saved!" + get());
					} catch (HeadlessException e) {

						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					} catch (InterruptedException e) {

						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					} catch (ExecutionException e) {

						logger.error( "FailedCreateActivityFunction Exception Occured : " ,e );
					}
					// Invoca o método implementado em baixo

					cleanFields();
					dispose();
				}
			};

			worker.execute();

		}

	}

	private void cleanFields() {
		jtfTitle.setText("");

	}
}