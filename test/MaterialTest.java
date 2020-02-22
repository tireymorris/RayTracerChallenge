import static org.junit.Assert.*;
import org.junit.Test;

public class MaterialTest {
  Material m = new Material();
  Point position = Tuple.point(0, 0, 0);

  @Test
  public void defaultMaterial() {
    Material m = new Material();

    assertEquals(Constants.WHITE(), m.color);
    assertEquals(0.1, m.ambient, Constants.EPSILON);
    assertEquals(0.9, m.diffuse, Constants.EPSILON);
    assertEquals(0.9, m.specular, Constants.EPSILON);
    assertEquals(200.0, m.shininess, Constants.EPSILON);
  }

  @Test
  public void lighting1() {
    Vector eye = Tuple.vector(0, 0, -1);
    Vector normal = Tuple.vector(0, 0, -1);
    Light light = Light.pointLight(Tuple.point(0, 0, -10), Constants.WHITE());

    Color result = Light.lighting(m, light, position, eye, normal);
    assertEquals(Tuple.color(1.9, 1.9, 1.9), result);
  }

  @Test
  public void lighting2() {
    Vector eye = Tuple.vector(0, Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
    Vector normal = Tuple.vector(0, 0, -1);
    Light light = Light.pointLight(Tuple.point(0, 0, -10), Constants.WHITE());

    Color result = Light.lighting(m, light, position, eye, normal);
    assertEquals(Tuple.color(1.0, 1.0, 1.0), result);
  }

  @Test
  public void lighting3() {
    Vector eye = Tuple.vector(0, 0, -1);
    Vector normal = Tuple.vector(0, 0, -1);
    Light light = Light.pointLight(Tuple.point(0, 10, -10), Constants.WHITE());

    Color result = Light.lighting(m, light, position, eye, normal);
    assertEquals(Tuple.color(0.7364, 0.7364, 0.7364), result);
  }

  @Test
  public void lighting4() {
    Vector eye = Tuple.vector(0, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
    Vector normal = Tuple.vector(0, 0, -1);
    Light light = Light.pointLight(Tuple.point(0, 10, -10), Constants.WHITE());

    Color result = Light.lighting(m, light, position, eye, normal);
    assertEquals(Tuple.color(1.6364, 1.6364, 1.6364), result);
  }

  @Test
  public void lighting5() {
    Vector eye = Tuple.vector(0, 0, -1);
    Vector normal = Tuple.vector(0, 0, -1);
    Light light = Light.pointLight(Tuple.point(0, 0, 10), Constants.WHITE());

    Color result = Light.lighting(m, light, position, eye, normal);
    assertEquals(Tuple.color(0.1, 0.1, 0.1), result);
  }

}