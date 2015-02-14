package fhj.shelf.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;


import fhj.shelf.factorys.CommandFactory;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;



/**
 * 
 * Class whose instance gives a User Interface Representation of the domain
 * Class {@code CreateShelf()}
 *
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
@SuppressWarnings("serial")
public class SaveShelf extends JFrame {

	private static final int JLND_HEIGHT = 20;
	private static final int JLND_WIDTH = 78;
	private static final int CPL_VERTICALGAP = 5;
	private static final int CPL_HORIZONTALGAP = 5;
	private static final int JLVD_HEIGHT = 10;
	private static final int JLVD_WIDTH = 325;
	private static final int LOCATION_Y = 100;
	private static final int LOCATION_X = 100;
	private static final int SIZE_HEIGHT = 157;
	private static final int SIZE_WIDTH = 350;
	private static final int JTFNB_COLUMNS = 4;
	/**
	 * Attributes
	 * 
	 */
	private static JLabel jlName;
	private static JTextField jtfnbElments;
	private static JButton jbSave;
	private static JButton jbDelete;
	private static JLabel jlVazia;

	Map<String, CommandFactory> shelfCommands;
	/**
	 * Constructor
	 * 
	 * @param repository
	 * @param shelfRepository
	 */
	public SaveShelf(Map<String, CommandFactory> shelfCommands) {
		this.shelfCommands = shelfCommands;

		jlName = new JLabel("Shelf Capacity");
		jtfnbElments = new JTextField(JTFNB_COLUMNS);
		jbSave = new JButton("Save");
		jbDelete = new JButton("Delete");
		jlVazia = new JLabel("");

		// Sets window properties
		setTitle("New Shelf");
		setSize(SIZE_WIDTH, SIZE_HEIGHT);
		setLocation(LOCATION_X, LOCATION_Y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set the size of the labels
		setVisible(true);
		jlVazia.setPreferredSize(new Dimension(JLVD_WIDTH, JLVD_HEIGHT));
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, CPL_HORIZONTALGAP, CPL_VERTICALGAP));
		jlName.setPreferredSize(new Dimension(JLND_WIDTH, JLND_HEIGHT));

		// Adds components to the window
		getContentPane().add(jlName);
		getContentPane().add(jtfnbElments);
		getContentPane().add(jlVazia);
		getContentPane().add(jbSave);
		getContentPane().add(jbDelete);

		/*
		 * ActionListener listener registration in the button Save and button
		 * Delete.          When an event is generated by this component, is
		 *          created an instance of the inner class EventShelfSearch()
		 * and EventShelfDelete()
		 */
		jbSave.addActionListener(new EventShelfSave());
		jbDelete.addActionListener(new EventShelfDelete());
	}


	public JTextField getjtfnbElements() {
		return jtfnbElments;
	}

	/**
	 * Inner class that contains the code that is executed when it is press the
	 * button jbSave This Class implements ActionListener Interface, and invoke
	 * actionPerformed method. The action is made in an Background Thread, by
	 * run SwingWorker framework by execute a EventHandling() object.
	 * 
	 */
	private class EventShelfSave implements ActionListener 
	{

		public void actionPerformed(ActionEvent ev) 
		{

			if (jtfnbElments.getText().equals(""))
				JOptionPane.showMessageDialog(null, "All fields are required!");
			else 
			{
				try {

					Map<String, String> params = new HashMap<String, String>();
					
					params.put("loginName", "Lima");
					params.put("loginPassword", "SLB");
					
					params.put("nbElements", getjtfnbElements().getText());

					PostShelfInformation(params);

				} catch (Exception e) {
					System.out.println("Unable to perform the operation. ");
					e.printStackTrace();
				}
			}
		}
	}


	
	
	private void PostShelfInformation(Map<String, String> params) throws IOException {
	/**
	 * Class whose execution create a shelf in the domain
	 */
		SwingWorker<Object, Void> worker =new SwingWorker<Object, Void>() 	{
		
		@Override
		protected Object doInBackground() throws Exception
		{
			
			CommandPostFactoryWithParameters postShelf = (CommandPostFactoryWithParameters) shelfCommands.get("postShelf");
			return postShelf.newInstance(params).execute();
		}

		@Override
		protected void done() 
		{
			String str = null;
			try {
				str = (String) get();
			} catch (InterruptedException e) {
				e.getMessage();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(null,
					"Data were successfully saved!  " + str);

			deleteFields();
			dispose();
		

	
	   }
	};
	worker.execute();
}

	/**
	 * Method to clean all fields in JTextField
	 */
	private void deleteFields() {
		jtfnbElments.setText("");

	}

	/**
	 * Inner class that contains the code that is executed when it is pressed
	 * the jbDelete button.
	 * 
	 */
	private class EventShelfDelete implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			deleteFields();
		}
	}

}
	
