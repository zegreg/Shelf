package fhj.shelf.UI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class ImagePanel extends JPanel {

	  private Image img;

	  
	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    //setPreferredSize(new Dimension(422, 302));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  
	  
	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }
	  
	  
	  public static void main(String[] args) {
		    ImagePanel panel = new ImagePanel(new ImageIcon("C:/Users/José Oliveira/Pictures/url.png").getImage());

		    JFrame frame = new JFrame();
		    frame.getContentPane().add(panel);
		    frame.pack();
		    frame.setVisible(true);
		  }
		}
	

