import static org.junit.Assert.*;
import org.junit.Test;

public class ColorTest {
  @Test
  public void colorsAreRgbTuples() {
    Color c = new Color(-0.5, 0.4, 1.7);

    assertEquals(-0.5, c.r, Constants.EPSILON);
    assertEquals(0.4, c.g, Constants.EPSILON);
    assertEquals(1.7, c.b, Constants.EPSILON);
  }

  @Test
  public void addColors() {
    Color c1 = new Color(0.9, 0.6, 0.75);
    Color c2 = new Color(0.7, 0.1, 0.25);

    assertEquals(new Color(1.6, 0.7, 1.0), c1.plus(c2));
  }

  @Test
  public void subtractColors() {
    Color c1 = new Color(0.9, 0.6, 0.75);
    Color c2 = new Color(0.7, 0.1, 0.25);

    assertEquals(new Color(0.2, 0.5, 0.5), c1.minus(c2));
  }

  @Test
  public void scaleColor() {
    Color color = new Color(0.2, 0.3, 0.4);

    assertEquals(new Color(0.4, 0.6, 0.8), color.scale(2));
  }

  @Test
  public void blendColors() {
    Color c1 = new Color(1, 0.2, 0.4);
    Color c2 = new Color(0.9, 1, 0.1);

    assertEquals(new Color(0.9, 0.2, 0.04), c1.blend(c2));
  }

}