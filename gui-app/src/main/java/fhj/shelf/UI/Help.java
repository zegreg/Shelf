package fhj.shelf.UI;


import java.io.BufferedReader;

import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Font;

@SuppressWarnings("serial")
public class Help extends JFrame {
	
	private static JTextArea textArea;
	private static JPanel panel;
	private static JScrollPane scrollPane;
	private static JLabel lblReader;
	private static final String source = "/README.txt";

	
	
	
	
	public Help() {
		setTitle("USER INSTRUCTIONS");  

		setSize(425, 314);  
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		setVisible(true);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(23, 30, 363, 226);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setLayout(null);
		
		textArea = new JTextArea(100,60);
		scrollPane = new JScrollPane(textArea); 
		scrollPane.setBounds(0, 0, 363, 215);
		textArea.setEditable(false);
		panel.add(scrollPane);
		
		lblReader = new JLabel("README");
		lblReader.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReader.setBounds(163, 11, 72, 24);
		getContentPane().add(lblReader);

		start();
	}


	
	
	private void start(){ 

		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(source)));
			
			String str;
			while ((str = br.readLine()) != null) {
				textArea.append("\n"+str);
			}
			br.close();
		} catch (Exception e)
		{
			System.out.println("Could not read the file!");
			e.printStackTrace();
		}
	}

}
	
