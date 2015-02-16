package fhj.shelf.imageui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public
class ImagePanel extends JPanel {

	private static final int DI_Y = 0;
	private static final int DI_X = 0;
	private Image img;

	public ImagePanel(String source) {
		this(new ImageIcon(source).getImage());
	}

	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, DI_X, DI_Y, null);
	}

}
