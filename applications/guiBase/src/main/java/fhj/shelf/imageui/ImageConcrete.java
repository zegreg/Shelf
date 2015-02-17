package fhj.shelf.imageui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import fhj.shelf.ui.Book;

@SuppressWarnings("serial")
public class ImageConcrete extends ImageBuilder {

	public ImageConcrete(String source, int width, int height) {
		super(source, width, height);

	}

	public ImagePanel setBackGroundImage(JFrame frame) throws IOException {

		BufferedImage image = ImageIO.read(getClass().getResourceAsStream(
				source));

		BufferedImage resizedImage = resize(image, width, height);
		ImagePanel imagePanel = new ImagePanel(resizedImage);
		frame.getContentPane().add(imagePanel, BorderLayout.CENTER);

		return imagePanel;
	}

	public ImagePanel setBackGroundImage() {
		BufferedImage image;
		ImagePanel imagePanel = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(source));
			BufferedImage resizedImage = resize(image, width, height);
			imagePanel = new ImagePanel(resizedImage);
		} catch (Exception e) {

			Logger.getLogger(ImageConcrete.class.getName()).log(Level.WARNING, " IOException Occured : setBackGroundImage() method ", 
					e.getClass().getName());
		}
		return imagePanel;
	}

}
