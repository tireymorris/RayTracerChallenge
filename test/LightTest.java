import static org.junit.Assert.*;
import org.junit.Test;

public class LightTest {
  @Test
  public void hasPositionAndIntensity() {
    Color intensity = Color.WHITE();
    Point position = new Point(0, 0, 0);

    Light light = Light.pointLight(position, intensity);
    assertEquals(intensity, light.intensity);
    assertEquals(position, light.position);
  }
}