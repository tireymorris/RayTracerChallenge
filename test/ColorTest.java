import static org.junit.Assert.*;
import org.junit.Test;

public class ColorTest {
  @Test
  public void colorsAreRgbTuples() {
    Color c = Tuple.color(-0.5, 0.4, 1.7);

    assertEquals(-0.5, c.r, Constants.EPSILON);
    assertEquals(0.4, c.g, Constants.EPSILON);
    assertEquals(1.7, c.b, Constants.EPSILON);
  }

  @Test
  public void addColors() {
    Color c1 = Tuple.color(0.9, 0.6, 0.75);
    Color c2 = Tuple.color(0.7, 0.1, 0.25);

    assertEquals(Tuple.color(1.6, 0.7, 1.0), c1.plus(c2));
  }

  @Test
  public void subtractColors() {
    Color c1 = Tuple.color(0.9, 0.6, 0.75);
    Color c2 = Tuple.color(0.7, 0.1, 0.25);

    assertEquals(Tuple.color(0.2, 0.5, 0.5), c1.minus(c2));
  }

  @Test
  public void scaleColor() {
    Color color = Tuple.color(0.2, 0.3, 0.4);

    assertEquals(Tuple.color(0.4, 0.6, 0.8), color.scale(2));
  }

  @Test
  public void blendColors() {
    Color c1 = Tuple.color(1, 0.2, 0.4);
    Color c2 = Tuple.color(0.9, 1, 0.1);

    assertEquals(Tuple.color(0.9, 0.2, 0.04), c1.blend(c2));
  }

}