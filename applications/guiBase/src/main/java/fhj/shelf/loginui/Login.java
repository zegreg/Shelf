package fhj.shelf.loginui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import fhj.shelf.imageui.ImageConcrete;
import fhj.shelf.imageui.ImagePanel;

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

	/**
	 * Attributes
	 */
	private static JTextField tfUsername;
	private static JPasswordField pfPassword;
	private static JLabel lbUsername, lbPassword;
	private static JButton btnLogin;
	private boolean succeeded;
	private static JSeparator separator;
	private static ImagePanel jlImagem1;

	private JPanel panel;
	private String username;
	private String password;
	private final static String source = "/User1.png";
	private static final int SIZE_WIDTH = 100;
	private static final int SIZE_HEIGHT = 100;

	/**
	 * 
	 * @param password
	 * @param username
	 * @param ownerFrame
	 *            - the Frame from which the dialog is displayed
	 */
	public Login(String username, String password, JFrame ownerframe) {
		/**
		 * If true, the modality type property is set to
		 * DEFAULT_MODALITY_TYPE,otherwise the dialog is modeless.
		 */

		super(ownerframe, "Login", true);
		/**
		 * create an AdminLogin Object
		 */

		this.username = username;
		this.password = password;

		/**
		 * create the GUI of JDialog
		 */
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
	 * Method to create and display GUI
	 */
	private void createGUI() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		createJPanel();
		ImageConcrete imageConcrete = new ImageConcrete(source, SIZE_WIDTH,
				SIZE_HEIGHT);
		jlImagem1 = imageConcrete.setBackGroundImage();
		jlImagem1.add(btnLogin);

		GroupLayout groupLayout = createGroupLayout();

		getContentPane().setLayout(groupLayout);
		getContentPane().add(panel);
		getContentPane().add(jlImagem1);

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
						.addComponent(panel, GroupLayout.PREFERRED_SIZE,
								AC_PREFERRED, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(jlImagem1, GroupLayout.PREFERRED_SIZE,
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
																jlImagem1,
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
		lbUsername.setBounds(LBLUBOUNDS_X, LBLUBOUNDS_Y, LBLUBOUNDS_WIDTH,
				LBLUBOUNDS_HEIGHT);
		panel.add(lbUsername);

		tfUsername = new JTextField(TFU_COLUMNS);
		tfUsername.setBounds(TFUBOUNDS_X, TFUBOUNDS_Y, TFUBOUNDS_WIDTH,
				TFUBOUNDS_HEIGHT);
		panel.add(tfUsername);

		lbPassword = new JLabel("Password: ");
		lbPassword.setBounds(LBPBOUNDS_X, LBPBOUNDS_Y, LBPBOUNDS_WIDTH,
				LBPBOUNDS_HEIGHT);
		panel.add(lbPassword);

		pfPassword = new JPasswordField(PF_COLUMNS);
		pfPassword.setBounds(PFBOUNDS_X, PFBOUNDS_Y, PFBOUNDS_WIDTH,
				PFBOUNDS_HEIGHT);
		panel.add(pfPassword);

		separator = new JSeparator();
		separator.setBounds(SEPBOUNDS_X, SEPBOUNDS_Y, SEPBOUNDS_WIDTH,
				SEPBOUNDS_HEIGHT);
		panel.add(separator);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(BTNLBOUNDS_X, BTNLBOUNDS_Y, BTNLBOUNDS_WIDTH,
				BTNLBOUNDS_HEIGHT);
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
			if (loginAuthentication(tfUsername.getText(), pfPassword.getText())) {

				JOptionPane.showMessageDialog(Login.this, "Wellcome "
						+ getUsername() + "! You have successfully logged in.",
						"Login", JOptionPane.INFORMATION_MESSAGE);

				succeeded = true;

				dispose();

			} else {
				JOptionPane.showMessageDialog(Login.this,
						"Invalid username or password", "Login",
						JOptionPane.ERROR_MESSAGE);
				tfUsername.setText("");
				pfPassword.setText("");
				succeeded = false;

			}

		}
	}

	public boolean loginAuthentication(String username, String password) {

		if (username.equals(this.username) && password.equals(this.password)) {
			return true;
		}
		return false;
	}

}