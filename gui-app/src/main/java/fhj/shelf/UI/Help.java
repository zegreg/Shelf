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
import javax.swing.SwingWorker;
import java.awt.Font;

public class Help extends JFrame {
	private final JTextArea textArea;
	private final JPanel panel;
	private final JScrollPane scrollPane;
	private final JLabel lblReader;

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

		try(FileReader fr = new FileReader("C:\\Users\\José Oliveira\\Documents\\README.txt");
				BufferedReader br  = new BufferedReader(fr);){


			String str;
			while ((str = br.readLine()) != null) {
				textArea.append("\n"+str);
			}
			br.close();
		} catch (Exception e)
		{
			System.out.println("Não foi possível ler o ficheiro!");
			e.printStackTrace();
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
	
