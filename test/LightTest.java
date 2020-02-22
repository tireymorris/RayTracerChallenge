import static org.junit.Assert.*;
import org.junit.Test;

public class LightTest {
  @Test
  public void hasPositionAndIntensity() {
    Color intensity = Constants.WHITE();
    Point position = Tuple.point(0, 0, 0);

    Light light = Light.pointLight(position, intensity);
    assertEquals(intensity, light.intensity);
    assertEquals(position, light.position);
  }
}