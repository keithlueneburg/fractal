import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.apache.commons.math3.complex.Complex;


public class MandelbrotPanel extends JPanel {
	/**
	 * Width to initialize panel with.
	 */
	private static final int PREFERRED_WIDTH = 1200;

	/**
	 * Height to initialize panel with.
	 */
	private static final int PREFERRED_HEIGHT = 900;

	/**
	 * Panel's background color.
	 */
	private static final Color BACKGROUND_COLOR = Color.WHITE;

	private static final double DEFAULT_LEFT_BOUND = -1.5;

	private static final double DEFAULT_RIGHT_BOUND = 0.5;

	private static final double DEFAULT_LOWER_BOUND = -1.0;

	private static final double DEFAULT_UPPER_BOUND = 1.0;
	/**
	 * Color of shape currently being drawn.
	 */
	private Color current_color;

	private Color[] colors;
	
	protected MandelbrotPanel() {
		super();
		setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		setBackground(BACKGROUND_COLOR);

		current_color = Color.BLACK;
		colors = new Color[100];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = new Color( (int)(Math.random()*256), (int)(Math.random()*256),(int)(Math.random()*256));
		}
		

	}
	@Override
	public void paintComponent(Graphics g_temp) {
		super.paintComponent(g_temp);
		Graphics2D g = (Graphics2D) g_temp;
		
		Dimension d = new Dimension(this.getWidth(), this.getHeight());
		
		BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int width = this.getWidth();
		int height = this.getHeight();
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				//Color c = (isMandelbrot(x, y, d) ? Color.BLACK : Color.WHITE);
				isMandelbrot(x, y, d);
				//isMandelbrot(x, y, d, 0.0, 0.0, 0.0, 0.0);
				int rgb = current_color.getRGB();
				img.setRGB(x, y, rgb);
				//Color c = Color.BLACK;
//				int rgb = c.getRGB();
//				img.setRGB(x, y, rgb);
			}
		}
		
		g.drawImage(img, 0, 0, null);
	}
	private boolean isMandelbrot(int x, int y, Dimension d , double im_lo, double im_hi, double re_lo, double re_hi) {

		//get Complex for x,y
		Complex n = getComplex(x, y, d, im_lo, im_hi, re_lo, re_hi);
		
		//iterate on Complex
		Complex result = checkSet(n, 100);
		
		//return based on value
			
		return result.abs() > .02;
	}
	
	private Complex checkSet(Complex n, int iterations) {
		Complex result = new Complex(n.getReal(),n.getImaginary());
		int lastIteration = 0;
		for (int i = 0; i < iterations; i++) {
			result = result.multiply(result).add(n);
			if(result.abs() > 4) {
				lastIteration = i;
				break;
			}
		}
		
//		if (lastIteration > 66) {
//			current_color = Color.RED;
//		} else if (lastIteration > 10) {
//			current_color = Color.GREEN;
//		} else if (lastIteration > 0) {
//			current_color = Color.BLUE;
//		} else {
//			current_color = Color.BLACK;
//		}
		if (lastIteration == 0) {
			current_color = Color.BLACK;
		} else {
			current_color = colors[lastIteration];
		}
		return result;
	}
	private boolean isMandelbrot(int x, int y, Dimension d) {

		return isMandelbrot(x, y, d, DEFAULT_LOWER_BOUND, DEFAULT_UPPER_BOUND, DEFAULT_LEFT_BOUND, DEFAULT_RIGHT_BOUND);
		
			
		//return false;
	}
	
	private Complex getComplex(int x, int y, Dimension d , double im_lo, double im_hi, double re_lo, double re_hi) {
		
		double re = ((((double)x)/d.width) * Math.abs(re_hi - re_lo)) + re_lo;
		double im = im_hi - ((((double)y)/d.height) * Math.abs(im_hi - im_lo));
		
		
		return new Complex(re,im);
	}
}
