package fhj.shelf.UI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import fhj.shelf.utils.AdminLogin;

@SuppressWarnings("serial")
public class Login extends JDialog {

	/**
	 * Attributes
	 */
	private static JTextField tfUsername;
	private static JPasswordField pfPassword;
	private static JLabel lbUsername, lbPassword;
	private static JButton btnLogin;
	private boolean succeeded;
	private static JSeparator separator;
	private static ImagePanel jlImagem_1;
	private AdminLogin login;
	private JPanel panel;
	private final static String source = "/User1.png";

	/**
	 * 
	 * @param ownerFrame
	 *            - the Frame from which the dialog is displayed
	 */
	public Login(JFrame ownerframe) {
		// If true, the modality type property is set to DEFAULT_MODALITY_TYPE,
		// otherwise the dialog is modeless.
		super(ownerframe, "Login", true);
		// create an AdminLogin Object
		login = new AdminLogin("admin", "admin", "", "");
		// create the GUI of JDialog
		createGUI();

	}

	public String getUsername() {
		return tfUsername.getText().trim();
	}

	public String getPassword() {
		return new String(pfPassword.getPassword());
	}

	public boolean isSucceeded() {

		return succeeded;
	}

	/**
	 * Method to set Image in the Window
	 */
	private void setImage() {
		try {

			BufferedImage image = ImageIO.read(getClass().getResourceAsStream(
					source));
			BufferedImage resizedImage = resize(image, 100, 100);// resize the
																	// image to
																	// 100x100
			jlImagem_1 = new ImagePanel(resizedImage);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
	}

	/**
	 * Auxiliary Method for treatment resize image
	 * 
	 * @param image
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage resize(BufferedImage image, int width,
			int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

	/**
	 * Method to create and display GUI
	 */
	private void createGUI() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		createJPanel();

		setImage();

		jlImagem_1.add(btnLogin);

		GroupLayout groupLayout = createGroupLayout();

		getContentPane().setLayout(groupLayout);
		getContentPane().add(panel);
		getContentPane().add(jlImagem_1);

		pack();
		setResizable(false);
	}

	/**
	 * Method to create an GroupLayout
	 * 
	 * @return
	 */
	private GroupLayout createGroupLayout() {
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 236,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(jlImagem_1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(12)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																Alignment.TRAILING,
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				panel,
																				GroupLayout.DEFAULT_SIZE,
																				100,
																				Short.MAX_VALUE)
																		.addGap(29))
														.addComponent(
																jlImagem_1,
																Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																123,
																Short.MAX_VALUE))
										.addGap(23)));
		return groupLayout;
	}

	/**
	 * Method to create a Panel
	 */
	private void createJPanel() {
		panel = new JPanel();

		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setLayout(null);

		lbUsername = new JLabel("Username: ");
		lbUsername.setBounds(10, 11, 80, 20);
		panel.add(lbUsername);

		tfUsername = new JTextField(20);
		tfUsername.setBounds(82, 11, 144, 20);
		panel.add(tfUsername);

		lbPassword = new JLabel("Password: ");
		lbPassword.setBounds(10, 57, 78, 21);
		panel.add(lbPassword);

		pfPassword = new JPasswordField(20);
		pfPassword.setBounds(82, 57, 144, 21);
		panel.add(pfPassword);

		separator = new JSeparator();
		separator.setBounds(0, 48, 236, 8);
		panel.add(separator);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(10, 106, 78, 23);
		btnLogin.setActionCommand("Login");
		btnLogin.addActionListener(new EventLoginHandling());
	}

	/**
	 * Inner Class to treat Event thread in the EDT, by implementing
	 * ActionListener Interface and invoke actionPerformed method.
	 *
	 */
	private class EventLoginHandling implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (login.loginAuthentication(getUsername(), getPassword())) {

				JOptionPane.showMessageDialog(Login.this, "Wellcome "
						+ getUsername() + "! You have successfully logged in.",
						"Login", JOptionPane.INFORMATION_MESSAGE);

				succeeded = true;

				dispose();

			} else {
				JOptionPane.showMessageDialog(Login.this,
						"Invalid username or password", "Login",
						JOptionPane.ERROR_MESSAGE);
				// reset username and password
				tfUsername.setText("");
				pfPassword.setText("");
				succeeded = false;

			}

		}
	}

}