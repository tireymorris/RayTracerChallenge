import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Application extends JPanel {
  private BufferedImage canvas;

  public Application(int width, int height) {
    canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    fillCanvas(Color.BLUE);
  }

  public Dimension getPreferredSize() {
    return new Dimension(canvas.getWidth(), canvas.getHeight());
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.drawImage(canvas, null, null);
  }

  public void fillCanvas(Color c) {
    int color = c.getRGB();
    for (int x = 0; x < canvas.getWidth(); x++) {
      for (int y = 0; y < canvas.getHeight(); y++) {
        canvas.setRGB(x, y, color);
      }
    }
    repaint();
  }

  public static void main(String[] args) {
    int width = 1280;
    int height = 720;
    JFrame frame = new JFrame("Ray tracer");

    Application panel = new Application(width, height);

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}