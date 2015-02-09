package fhj.shelf.ui;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;


import fhj.shelf.actionCommandDomain.EraseShelfDomain;
import fhj.shelf.actionCommandDomain.SearchShelfDomain;

import fhj.shelf.http.SendDELETEHttpRequest;
import fhj.shelf.http.SendGETHttpRequest;

import fhj.shelf.repos.ShelfRepository;

/**
 * 
 * Class whose instance gives a User Interface Representation of the domain
 * Class {@code GetOneShelf()}
 *
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
@SuppressWarnings("serial")
public class SearchShelf extends JFrame {

	private static final int BTNDELBOUNDS_HEIGHT = 23;
	private static final int BTNDELBOUNDS_WIDTH = 73;
	private static final int BTNDELBOUNDS_Y = 142;
	private static final int BTNDELBOUNDS_X = 178;
	private static final int JBSBOUNDS_HEIGHT = 23;
	private static final int JBSBOUNDS_WIDTH = 73;
	private static final int JBSBOUNDS_Y = 142;
	private static final int JBSBOUNDS_X = 84;
	private static final int JTFNBOUNDS_HEIGHT = 20;
	private static final int JTFNBOUNDS_WIDTH = 166;
	private static final int JTFNBOUNDS_Y = 23;
	private static final int JTFNBOUNDS_X = 84;
	private static final int JTFFBOUNDS_HEIGHT = 20;
	private static final int JTFFBOUNDS_WIDTH = 166;
	private static final int JTFFBOUNDS_Y = 85;
	private static final int JTFFBOUNDS_X = 84;
	private static final int JTFPBOUNDS_HEIGHT = 20;
	private static final int JTFPBOUNDS_WIDTH = 166;
	private static final int JTFPBOUNDS_Y = 54;
	private static final int JTFPBOUNDS_X = 84;
	private static final int JLVD_HEIGHT = 10;
	private static final int JLVD_WIDTH = 325;
	private static final int JLVBOUNDS_HEIGHT = 10;
	private static final int JLVBOUNDS_WIDTH = 325;
	private static final int JLVBOUNDS_Y = 105;
	private static final int JLVBOUNDS_X = 5;
	private static final int JLFD_HEIGHT = 20;
	private static final int JLFD_WIDTH = 65;
	private static final int JLFBOUNDS_HEIGHT = 20;
	private static final int JLFBOUNDS_WIDTH = 65;
	private static final int JLFBOUNDS_Y = 85;
	private static final int JLFBOUNDS_X = 14;
	private static final int JLCD_HEIGHT = 20;
	private static final int JLCD_WIDTH = 65;
	private static final int JLCBOUNDS_HEIGHT = 20;
	private static final int JLCBOUNDS_WIDTH = 65;
	private static final int JLCBOUNDS_Y = 54;
	private static final int JLCBOUNDS_X = 14;
	private static final int JLSIDD_HEIGHT = 20;
	private static final int JLSIDD_WIDTH = 65;
	private static final int JLSIDBOUNDS_HEIGHT = 20;
	private static final int JLSIDBOUNDS_WIDTH = 65;
	private static final int JLSIDBOUNDS_Y = 23;
	private static final int JLSIDBOUNDS_X = 14;
	private static final int LOCATION_Y = 100;
	private static final int LOCATION_X = 100;
	private static final int SIZE_HEIGHT = 234;
	private static final int SIZE_WIDTH = 350;
	private static final int JTFF_COLUMNS = 20;
	private static final int JTFP_COLUMNS = 20;
	private static final int JTFNAME_COLUMNS = 20;
	/**
	 * Attributes
	 */
	private static JLabel jlShelfId;
	private static JTextField jtfName;
	private static JLabel jlCapacity;
	private static JTextField jtfPassword;
	private static JLabel jlFreeSpace;
	private static JTextField jtfFreeSpace;
	private static JButton jbSearch;
	private static JLabel jlVazia;
	private ShelfRepository repository;
	private JButton btnDelete;

	/**
	 * Constructor
	 * 
	 * @param shelfrepository
	 */
	public SearchShelf(ShelfRepository shelfrepository) {
		this.repository = shelfrepository;
		jlShelfId = new JLabel("SheflId");
		jtfName = new JTextField(JTFNAME_COLUMNS);
		jlCapacity = new JLabel("Capacity");
		jtfPassword = new JTextField(JTFP_COLUMNS);
		jlFreeSpace = new JLabel("FreeSpace");
		jtfFreeSpace = new JTextField(JTFF_COLUMNS);
		jbSearch = new JButton("Search");
		jlVazia = new JLabel("");
		btnDelete = new JButton("Delete");

		// Sets window properties
		setTitle("Search by ShelfId");
		setSize(SIZE_WIDTH, SIZE_HEIGHT);
		setLocation(LOCATION_X, LOCATION_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		jlShelfId.setBounds(JLSIDBOUNDS_X, JLSIDBOUNDS_Y, JLSIDBOUNDS_WIDTH, JLSIDBOUNDS_HEIGHT);

		// Set the size of the labels
		jlShelfId.setPreferredSize(new Dimension(JLSIDD_WIDTH, JLSIDD_HEIGHT));
		jlCapacity.setBounds(JLCBOUNDS_X, JLCBOUNDS_Y, JLCBOUNDS_WIDTH, JLCBOUNDS_HEIGHT);
		jlCapacity.setPreferredSize(new Dimension(JLCD_WIDTH, JLCD_HEIGHT));
		jlFreeSpace.setBounds(JLFBOUNDS_X, JLFBOUNDS_Y, JLFBOUNDS_WIDTH, JLFBOUNDS_HEIGHT);
		jlFreeSpace.setPreferredSize(new Dimension(JLFD_WIDTH, JLFD_HEIGHT));
		jlVazia.setBounds(JLVBOUNDS_X, JLVBOUNDS_Y, JLVBOUNDS_WIDTH, JLVBOUNDS_HEIGHT);
		jlVazia.setPreferredSize(new Dimension(JLVD_WIDTH, JLVD_HEIGHT));

		/*
		 * Sets the text boxes as non-editable,          since it is not
		 * necessary to enter data in these fields
		 */
		jtfPassword.setBounds(JTFPBOUNDS_X, JTFPBOUNDS_Y, JTFPBOUNDS_WIDTH, JTFPBOUNDS_HEIGHT);
		jtfPassword.setEditable(false);
		jtfFreeSpace.setBounds(JTFFBOUNDS_X, JTFFBOUNDS_Y, JTFFBOUNDS_WIDTH, JTFFBOUNDS_HEIGHT);
		jtfFreeSpace.setEditable(false);
		getContentPane().setLayout(null);

		// Adds components to the window
		getContentPane().add(jlShelfId);
		jtfName.setBounds(JTFNBOUNDS_X, JTFNBOUNDS_Y, JTFNBOUNDS_WIDTH, JTFNBOUNDS_HEIGHT);
		getContentPane().add(jtfName);
		getContentPane().add(jlCapacity);
		getContentPane().add(jtfPassword);
		getContentPane().add(jlFreeSpace);
		getContentPane().add(jtfFreeSpace);
		getContentPane().add(jlVazia);
		jbSearch.setBounds(JBSBOUNDS_X, JBSBOUNDS_Y, JBSBOUNDS_WIDTH, JBSBOUNDS_HEIGHT);
		getContentPane().add(jbSearch);
		btnDelete.setBounds(BTNDELBOUNDS_X, BTNDELBOUNDS_Y, BTNDELBOUNDS_WIDTH, BTNDELBOUNDS_HEIGHT);

		getContentPane().add(btnDelete);

		/*
		 * ActionListener listener registration in the button Search and button
		 * Delete.          When an event is generated by this component, is
		 *          created an instance of the inner class EventShelfSearch()
		 * and EventShelfDelete()
		 */
		jbSearch.addActionListener(new EventShelfSearch());
		btnDelete.addActionListener(new EventShelfDelete());

	}

	public ShelfRepository getRepository() {
		return repository;
	}

	/**
	 * Inner Class that implements ActionListener Interface, and invoke
	 * actionPerformed method for Search Button. The action is made in an
	 * Background Thread, by run SwingWorker framework by execute a
	 * EventHandling() object.
	 */
	private class EventShelfSearch implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (jtfName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Fill in the field name !");
				cleanFields();
			} else {
				Map<String, String> params = new TreeMap<String, String>();
				params.put("id", jtfName.getText());
				new EventHandling(params).execute();
			}

		}

	}

	/**
	 * Inner Class call in the actionPerformed method
	 * 
	 * @author Filipa Estiveira, Hugo Leal, José Oliveira
	 */
	private class EventHandling extends SwingWorker<Map<String, String>, Void> {
		Map<String, String> params;
		String path;
		boolean modeStandAlone = false;
		public EventHandling(Map<String, String> map) {
			this.params = map;
			 path = "GET /shelfs/"+Long.valueOf(params.get("id"))+"/details accept=application/json";
		}
		
		

		@SuppressWarnings("unchecked")
		@Override
		protected Map<String, String>  doInBackground() throws Exception {

			if (modeStandAlone) {
				return SearchShelfDomain.GetShelfInformation(repository, params);
			}
			SendGETHttpRequest httpRequest = new SendGETHttpRequest();
			return   httpRequest.sendGetRequest(params, path);
		}

		@Override
		protected void done() {

			try {

				jtfPassword.setText(get().get("Capacity"));
				jtfFreeSpace.setText(get().get("FreeSpace"));

			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Shelf doesn´t exist" + e);
				cleanFields();
				e.printStackTrace();
			}

		}
	}

	private class EventShelfDelete implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (jtfName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "You must searchFirst");
				cleanFields();
			} else {
			
				Map<String, String> params = new TreeMap<String, String>();
				params.put("id", jtfName.getText());
				new EventHandlingDelete(params).execute();
			}

		}

	}

	private class EventHandlingDelete extends SwingWorker<Object, Void> {
		Map<String, String> params;
		
		String path;
		boolean modeStandAlone = false;
		public EventHandlingDelete(Map<String, String> map) {
			this.params = map;
		path = "DELETE /shelfs/"+String.valueOf(params.get("id"))+" loginName=Lima&loginPassword=SLB";
		}
		
		
		@Override
		protected Object doInBackground() throws Exception {

			if (modeStandAlone) {
				return EraseShelfDomain.DeleteShelfInformation(repository, params);
			}
			
			SendDELETEHttpRequest httpRequest = new SendDELETEHttpRequest();
			return httpRequest.sendDeleteRequest(params, path);
//			return new EraseShelf(repository, Long.valueOf(jtfName
//					.getText())).call();
		}

		@Override
		protected void done() {

			try {
				JOptionPane.showMessageDialog(null,
						"Delete Shelf " + get());
			} catch (HeadlessException | InterruptedException
					| ExecutionException e) {

				e.printStackTrace();
			}
			cleanFields();

		}
	}

	/**
	 * Method to clean all fields in JTextField
	 */
	private void cleanFields() {
		jtfName.setText("");
		jtfPassword.setText("");
		jtfFreeSpace.setText("");

	}
}
