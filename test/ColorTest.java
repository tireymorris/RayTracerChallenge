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
}