package fhj.shelf.ui;

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

import fhj.shelf.repos.AdminLogin;

@SuppressWarnings("serial")
public class Login extends JDialog {

	private static final int BTNLBOUNDS_HEIGHT = 23;
	private static final int BTNLBOUNDS_WIDTH = 78;
	private static final int BTNLBOUNDS_Y = 106;
	private static final int BTNLBOUNDS_X = 10;
	private static final int SEPBOUNDS_HEIGHT = 8;
	private static final int SEPBOUNDS_WIDTH = 236;
	private static final int SEPBOUNDS_Y = 48;
	private static final int SEPBOUNDS_X = 0;
	private static final int PFBOUNDS_HEIGHT = 21;
	private static final int PFBOUNDS_WIDTH = 144;
	private static final int PFBOUNDS_Y = 57;
	private static final int PFBOUNDS_X = 82;
	private static final int PF_COLUMNS = 20;
	private static final int LBPBOUNDS_HEIGHT = 21;
	private static final int LBPBOUNDS_WIDTH = 78;
	private static final int LBPBOUNDS_Y = 57;
	private static final int LBPBOUNDS_X = 10;
	private static final int TFUBOUNDS_HEIGHT = 20;
	private static final int TFUBOUNDS_WIDTH = 144;
	private static final int TFUBOUNDS_Y = 11;
	private static final int TFUBOUNDS_X = 82;
	private static final int TFU_COLUMNS = 20;
	private static final int LBLUBOUNDS_HEIGHT = 20;
	private static final int LBLUBOUNDS_WIDTH = 80;
	private static final int LBLUBOUNDS_Y = 11;
	private static final int LBLUBOUNDS_X = 10;
	private static final int AC_SIZE_SGE = 23;
	private static final int AC_PREFERRED_PG = 123;
	private static final int AG_SIZE_SG = 29;
	private static final int AC_PREFERRED_SG = 100;
	private static final int AG_SIZE = 12;
	private static final int AC_PREFERRED = 236;
	private static final int DI_Y = 0;
	private static final int DI_X = 0;
	private static final int RESIZE_HEIGHT = 100;
	private static final int RESIZE_WIDTH = 100;
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
			BufferedImage resizedImage = resize(image, RESIZE_WIDTH, RESIZE_HEIGHT);// resize the
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
		g2d.drawImage(image, DI_X, DI_Y, width, height, null);
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
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, AC_PREFERRED,
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
										.addGap(AG_SIZE)
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
																				AC_PREFERRED_SG,
																				Short.MAX_VALUE)
																		.addGap(AG_SIZE_SG))
														.addComponent(
																jlImagem_1,
																Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																AC_PREFERRED_PG,
																Short.MAX_VALUE))
										.addGap(AC_SIZE_SGE)));
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
		lbUsername.setBounds(LBLUBOUNDS_X, LBLUBOUNDS_Y, LBLUBOUNDS_WIDTH, LBLUBOUNDS_HEIGHT);
		panel.add(lbUsername);

		tfUsername = new JTextField(TFU_COLUMNS);
		tfUsername.setBounds(TFUBOUNDS_X, TFUBOUNDS_Y, TFUBOUNDS_WIDTH, TFUBOUNDS_HEIGHT);
		panel.add(tfUsername);

		lbPassword = new JLabel("Password: ");
		lbPassword.setBounds(LBPBOUNDS_X, LBPBOUNDS_Y, LBPBOUNDS_WIDTH, LBPBOUNDS_HEIGHT);
		panel.add(lbPassword);

		pfPassword = new JPasswordField(PF_COLUMNS);
		pfPassword.setBounds(PFBOUNDS_X, PFBOUNDS_Y, PFBOUNDS_WIDTH, PFBOUNDS_HEIGHT);
		panel.add(pfPassword);

		separator = new JSeparator();
		separator.setBounds(SEPBOUNDS_X, SEPBOUNDS_Y, SEPBOUNDS_WIDTH, SEPBOUNDS_HEIGHT);
		panel.add(separator);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(BTNLBOUNDS_X, BTNLBOUNDS_Y, BTNLBOUNDS_WIDTH, BTNLBOUNDS_HEIGHT);
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