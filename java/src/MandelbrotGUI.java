
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Main window
 * @author Keith Lueneburg
 * 
 */
public final class MandelbrotGUI extends JFrame {
  
  /**
   * Window title text.
   */
  private static final String WINDOW_TITLE = "Mandelbrot Set Visualizer";

  private final MandelbrotPanel my_drawing_panel;
  
  /**
   * Menu bar for the program. 
   */
  private final MandelbrotMenuBar my_menu_bar;
  
  /**
   * Instantiate a  window.
   */
  public MandelbrotGUI() {
    super(WINDOW_TITLE);
   
    my_drawing_panel = new MandelbrotPanel();
    my_menu_bar = new MandelbrotMenuBar();
  }
  
  /**
   * Set up window and make visible.
   */
  public void start() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationByPlatform(true);
         
    //setJMenuBar(my_menu_bar);
    add(my_drawing_panel, BorderLayout.CENTER);
    
    
    pack();
    setVisible(true);
  }
  
  
}
