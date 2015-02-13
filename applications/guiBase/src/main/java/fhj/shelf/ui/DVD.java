package fhj.shelf.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.ShelfRepository;


@SuppressWarnings("serial")
public class DVD extends JFrame {

	/**
	 * Attributes
	 */

	private JLabel jlElementType;
	private JLabel jlTitle;
	private JTextField jtfTitle;
	private JComboBox<Object> comboBox;
	private final JButton btnAddDVD;
	private final JButton btnDelete;
	private final JLabel lblDuration;
	private JTextField jtfDuration;

	Map<String, CommandFactory> shelfCommands;
	/**
	 * Constructor
	 * @param shelfCommands 
	 * 
	 * @param shelfRepository
	 * @param elementsRepository
	 */
	public DVD(Map<String, CommandFactory> shelfCommands) {
this.shelfCommands = shelfCommands;

		this.btnAddDVD = new JButton("AddDVD");
		this.jtfDuration = new JTextField();
		this.btnDelete = new JButton("Delete");
		this.lblDuration = new JLabel("Duration");
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
		 * EventDVD.
		 */
		btnAddDVD.addActionListener(new EventDVD());

	}

	/**
	 * Method that have the responsibility to fill jCombox with repository data
	 * in a background thread.
	 * 
	 * @return
	 */
	private SwingWorker<?, ?> fillComboxFromMap() {
		SwingWorker<Map<String, String>, Void> worker = new SwingWorker<Map<String, String>, Void>() {
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

						comboBox.addItem(iterable_element.getKey().split("=")[1]);

					}
				} catch (InterruptedException e) {

					e.printStackTrace();
				} catch (ExecutionException e) {

					e.printStackTrace();
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
		btnAddDVD.setBounds(100, 192, 96, 31);
		btnDelete.setBounds(277, 192, 115, 31);
		lblDuration.setBounds(21, 126, 42, 31);
		jtfDuration.setBounds(100, 132, 292, 18);
		jtfDuration.setColumns(10);

		getContentPane().add(comboBox);
		getContentPane().add(jlElementType);
		getContentPane().add(jlTitle);
		getContentPane().add(jtfTitle);
		getContentPane().add(btnAddDVD);
		getContentPane().add(btnDelete);
		getContentPane().add(lblDuration);
		getContentPane().add(jtfDuration);

	}

	/**
	 * 
	 * This Inner Class implements ActionListener. Creates an Element Shelf by a
	 * background thread with SwingWorker Framework.
	 *
	 */
	private class EventDVD implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Map<String, String> params = new HashMap<String, String>();
			params.put("loginName", "Lima");
			params.put("loginPassword", "SLB");
			params.put("name", jtfTitle.getText());
			params.put("duration", jtfDuration.getText());
			params.put("type", "DVD");
			params.put("id", comboBox.getSelectedItem().toString());
		
			
			SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {

					
	CommandPostFactoryWithParameters postBook = (CommandPostFactoryWithParameters) shelfCommands.get("postElement");
					
					return postBook.newInstance(params).execute();
				}

				@Override
				protected void done() {

					try {
						JOptionPane.showMessageDialog(null,
								"Data were successfully saved!" + get());
					} catch (HeadlessException e) {

						e.printStackTrace();
					} catch (InterruptedException e) {

						e.printStackTrace();
					} catch (ExecutionException e) {

						e.printStackTrace();
					}

					/* Invokes the low method implemented */

					cleanFields();
					dispose();
				}
			};

			worker.execute();

		}

	}

	private void cleanFields() {
		jtfTitle.setText("");
		jtfDuration.setText("");

	}

}
