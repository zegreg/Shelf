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

	private static final int LBLREADERBOUNDS_HEIGHT = 24;
	private static final int LBLREADERBOUNDS_WIDTH = 72;
	private static final int LBLREADERBOUNDS_Y = 11;
	private static final int LBLREADERBOUNDS_X = 163;
	private static final int LBLREADER_FONT = 14;
	private static final int SPBOUNDS_HEIGHT = 215;
	private static final int SPBOUNDS_WIDTH = 535;
	private static final int SPBOUNDS_Y = 0;
	private static final int SPBOUNDS_X = 0;
	private static final int TA_COLUMNS = 60;
	private static final int TA_ROWS = 100;
	private static final int PBOUNDS_HEIGHT = 226;
	private static final int PBOUNDS_WIDTH = 535;
	private static final int PBOUNDS_Y = 30;
	private static final int PBOUNDS_X = 23;
	private static final int SIZE_HEIGHT = 314;
	private static final int SIZE_WIDTH = 584;
	private static JTextArea textArea;
	private static JPanel panel;
	private static JScrollPane scrollPane;
	private static JLabel lblReader;
	private static final String source = "/README.txt";

	public Help() {
		setTitle("USER INSTRUCTIONS");

		setSize(SIZE_WIDTH, SIZE_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(PBOUNDS_X, PBOUNDS_Y, PBOUNDS_WIDTH, PBOUNDS_HEIGHT);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setLayout(null);

		textArea = new JTextArea(TA_ROWS, TA_COLUMNS);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(SPBOUNDS_X, SPBOUNDS_Y, SPBOUNDS_WIDTH, SPBOUNDS_HEIGHT);
		textArea.setEditable(false);
		panel.add(scrollPane);

		lblReader = new JLabel("README");
		lblReader.setFont(new Font("Tahoma", Font.BOLD, LBLREADER_FONT));
		lblReader.setBounds(LBLREADERBOUNDS_X, LBLREADERBOUNDS_Y, LBLREADERBOUNDS_WIDTH, LBLREADERBOUNDS_HEIGHT);
		getContentPane().add(lblReader);

		start();
	}

	private void start() {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					getClass().getResourceAsStream(source)));

			String str;
			while ((str = br.readLine()) != null) {
				textArea.append("\n" + str);
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Could not read the file!");
			e.printStackTrace();
		}
	}

}
