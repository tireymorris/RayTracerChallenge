import static org.junit.Assert.*;
import java.util.Arrays;
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
      assertEquals(Color.BLACK(), pixel);
    }
  }

  @Test
  public void canWritePixel() {
    Canvas canvas = new Canvas(10, 20);
    Color red = new Color(1, 0, 0);

    canvas.writePixel(2, 3, red);

    assertEquals(red, canvas.pixelAt(2, 3));
  }

  @Test
  public void constructPPMHeader() {
    Canvas canvas = new Canvas(5, 3);
    String PPM = canvas.constructPPM();

    String[] header = PPM.split("\n");
    header = Arrays.copyOfRange(header, 0, 3);
    String[] expected = { "P3", "5 3", "255" };

    assertEquals(3, header.length);
    assertArrayEquals(expected, header);
  }

  @Test
  public void constructPPM() {
    Canvas canvas = new Canvas(5, 3);

    Color c1 = new Color(1.5, 0, 0);
    Color c2 = new Color(0, 0.5, 0);
    Color c3 = new Color(-0.5, 0, 1);

    canvas.writePixel(0, 0, c1);
    canvas.writePixel(2, 1, c2);
    canvas.writePixel(4, 2, c3);

    String PPM = canvas.constructPPM();

    String[] lines = PPM.split("\n");
    lines = Arrays.copyOfRange(lines, 3, 6);
    String[] expected = { "255 0 0 0 0 0 0 0 0 0 0 0 0 0 0", "0 0 0 0 0 0 0 128 0 0 0 0 0 0 0",
        "0 0 0 0 0 0 0 0 0 0 0 0 0 0 255" };

    assertEquals(3, lines.length);
    assertArrayEquals(expected, lines);
  }

  @Test
  public void constructPPMSplitsLongLines() {
    Canvas canvas = new Canvas(10, 2);

    canvas.fill(new Color(1, 0.8, 0.6));

    String PPM = canvas.constructPPM();

    String[] lines = PPM.split("\n");
    lines = Arrays.copyOfRange(lines, 3, 7);
    String[] expected = { "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204",
        "153 255 204 153 255 204 153 255 204 153 255 204 153",
        "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204",
        "153 255 204 153 255 204 153 255 204 153 255 204 153" };

    assertEquals(4, lines.length);
    assertArrayEquals(expected, lines);
  }

  @Test
  public void constructedPPMMustEndInNewline() {
    Canvas canvas = new Canvas(5, 3);
    String PPM = canvas.constructPPM();

    assertEquals(PPM.charAt(PPM.length() - 1), '\n');
  }

  @Test
  public void toCanvasCoordinates() {
    Canvas canvas = new Canvas(100, 100);

    double x = 0, y = 0;
    assertEquals(50, canvas.toCanvasX(x));
    assertEquals(50, canvas.toCanvasY(y));

    x = 30;
    y = -45;
    assertEquals(80, canvas.toCanvasX(x));
    assertEquals(95, canvas.toCanvasY(y));

    canvas = new Canvas(80, 100);
    x = 40;
    y = -50;
    assertEquals(80, canvas.toCanvasX(x));
    assertEquals(100, canvas.toCanvasY(y));
  }

}