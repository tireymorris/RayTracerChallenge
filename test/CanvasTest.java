import static org.junit.Assert.*;
import org.junit.Test;

public class CanvasTest {
  @Test
  public void canCreateCanvas() {
    Canvas canvas = new Canvas(1920, 1080);

    assertEquals(1920, canvas.width);
    assertEquals(1080, canvas.height);
  }

  @Test
  public void pixelsInitializedToBlack() {
    Canvas canvas = new Canvas(800, 600);

    for (Color pixel : canvas.pixels) {
      assertEquals(Tuple.color(0, 0, 0), pixel);
    }
  }

  @Test
  public void canWritePixel() {
    Canvas canvas = new Canvas(10, 20);
    Color red = Tuple.color(1, 0, 0);

    canvas.writePixel(2, 3, red);

    assertEquals(red, canvas.pixelAt(2, 3));
  }
}