package fhj.shelf.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Help extends JFrame {

	public Help() {
		setTitle("User Register");  

		setSize(425, 314);  
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		setVisible(true);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(23, 30, 363, 226);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea(100,1);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		scrollPane.setBounds(0, 0, 363, 215);
		textArea.setEditable(true);
		
		panel.add(scrollPane);
		
		JLabel lblReader = new JLabel("Read me");
		lblReader.setBounds(163, 11, 72, 24);
		getContentPane().add(lblReader);

		
		try {
			FileReader fr = new FileReader("C:\\Users\\José Oliveira\\Pictures\\teste.txt");
			BufferedReader br  = new BufferedReader(fr);
			
			String nextLine = null;
		
		
			while ((br.ready())) {
			  nextLine = br.readLine();
					textArea.append(nextLine);;
				
				}
				
				
			
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	
	}
	
	public static void main(String args[]){  
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               Help jListScroll = new Help();
            }
        });


	}
	
}
	
