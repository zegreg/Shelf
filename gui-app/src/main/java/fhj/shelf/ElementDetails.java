package fhj.shelf;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ElementDetails extends JFrame {
	
	
	private JScrollPane listScrollPane = new JScrollPane();
	private String[] stringArray = {"Testing", "This", "Stuff"};
	
	
	
	
	public ElementDetails() {
		setTitle("Elements List");  

		setSize(292, 165);  
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		
		getContentPane().setLayout(null);
		
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(39, 95, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(166, 95, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(38, 28, 214, 42);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
		JList list = new JList(stringArray);

		list.setVisibleRowCount(2);
		listScrollPane.setViewportView(list);
		panel.setLayout(new BorderLayout());
		panel.add(listScrollPane);
	
		
		JLabel lblShelfRepository = new JLabel("Elements Repository");
		lblShelfRepository.setHorizontalAlignment(SwingConstants.CENTER);
		lblShelfRepository.setBounds(81, 11, 122, 23);
		getContentPane().add(lblShelfRepository);
		
		
		
	}

		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ElementDetails().setVisible(true);  
	}

}
