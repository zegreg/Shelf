package fhj.shelf.imageui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class ImageBuilder extends JDialog {

	protected String source;
	protected int width;
	protected int height;

	public ImageBuilder(String source, int width, int height) {
		this.source = source;
		this.height = height;
		this.width = width;
	}

	protected abstract ImagePanel setBackGroundImage(JFrame frame)
			throws IOException;

	protected abstract ImagePanel setBackGroundImage();

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

}
