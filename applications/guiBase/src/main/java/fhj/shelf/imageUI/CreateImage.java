package fhj.shelf.imageUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public abstract class CreateImage extends JFrame{
	
	
	static String source; 
	int width;
	int height;
	
	public CreateImage(String source, int width, int height) {
	this.source = source;
	this.height = height;
	this.width = width;
	}
	
	
	
	protected ImagePanel setBackGroundImage(JFrame frame) throws IOException {
		
		BufferedImage image = ImageIO.read(getClass().getResourceAsStream(
				source));
		
		BufferedImage resizedImage = resize(image, width, height);
		ImagePanel imagePanel = new ImagePanel(resizedImage);
		frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
	
		return imagePanel;
	}
	
	
	protected ImagePanel setBackGroundImage( ) {
		BufferedImage image;
		ImagePanel imagePanel = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(source));
			BufferedImage resizedImage = resize(image, width, height);
			imagePanel = new ImagePanel(resizedImage);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return imagePanel;
	}
	
	
	private static BufferedImage resize(BufferedImage image, int width,
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
