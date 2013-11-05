import org.apache.commons.math3.complex.Complex;

import java.awt.EventQueue;


public class Main {

	/**
	   * Private constructor to prevent instantiation.
	   */
	  private Main() {
	    throw new IllegalStateException();
	  }
	  
	  /**
	   * Creates a new instance, and starts it.
	   * 
	   * @param the_args Command line arguments.
	   */
	  public static void main(final String[] the_args) {
		  //System.out.println(new Complex(1, 1).abs());
		  
		  
		  
		  EventQueue.invokeLater(new Runnable() {
	      @Override
	      public void run() {
	        final MandelbrotGUI gui = new MandelbrotGUI();
	        gui.start();
	      }
	    });
	  }

}
