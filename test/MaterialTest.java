import static org.junit.Assert.*;
import org.junit.Test;

import entities.Sphere;
import graphics.*;
import structures.*;
import util.Constants;

public class MaterialTest {
  Material m = new Material();
  Point position = new Point(0, 0, 0);

  @Test
  public void defaultMaterial() {
    Material m = new Material();

    assertEquals(Color.WHITE(), m.color);
    assertEquals(0.1, m.ambient, Constants.EPSILON);
    assertEquals(0.9, m.diffuse, Constants.EPSILON);
    assertEquals(0.9, m.specular, Constants.EPSILON);
    assertEquals(200.0, m.shininess, Constants.EPSILON);
  }

  @Test
  public void lighting1() {
    Vector eye = new Vector(0, 0, -1);
    Vector normal = new Vector(0, 0, -1);
    Light light = Light.pointLight(new Point(0, 0, -10), Color.WHITE());

    Color result = Light.lighting(m, new Sphere(), light, position, eye, normal, false);
    assertEquals(new Color(1.9, 1.9, 1.9), result);
  }

  @Test
  public void lighting2() {
    Vector eye = new Vector(0, Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
    Vector normal = new Vector(0, 0, -1);
    Light light = Light.pointLight(new Point(0, 0, -10), Color.WHITE());

    Color result = Light.lighting(m, new Sphere(), light, position, eye, normal, false);
    assertEquals(new Color(1.0, 1.0, 1.0), result);
  }

  @Test
  public void lighting3() {
    Vector eye = new Vector(0, 0, -1);
    Vector normal = new Vector(0, 0, -1);
    Light light = Light.pointLight(new Point(0, 10, -10), Color.WHITE());

    Color result = Light.lighting(m, new Sphere(), light, position, eye, normal, false);
    assertEquals(new Color(0.7364, 0.7364, 0.7364), result);
  }

  @Test
  public void lighting4() {
    Vector eye = new Vector(0, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
    Vector normal = new Vector(0, 0, -1);
    Light light = Light.pointLight(new Point(0, 10, -10), Color.WHITE());

    Color result = Light.lighting(m, new Sphere(), light, position, eye, normal, false);
    assertEquals(new Color(1.6364, 1.6364, 1.6364), result);
  }

  @Test
  public void lighting5() {
    Vector eye = new Vector(0, 0, -1);
    Vector normal = new Vector(0, 0, -1);
    Light light = Light.pointLight(new Point(0, 0, 10), Color.WHITE());

    Color result = Light.lighting(m, new Sphere(), light, position, eye, normal, false);
    assertEquals(new Color(0.1, 0.1, 0.1), result);
  }

  @Test
  public void lightingPointInShadow() {
    Vector eye = new Vector(0, 0, -1);
    Vector normal = new Vector(0, 0, -1);
    Light light = Light.pointLight(new Point(0, 0, -10), Color.WHITE());

    boolean inShadow = true;

    Color result = Light.lighting(m, new Sphere(), light, position, eye, normal, inShadow);
    assertEquals(new Color(0.1, 0.1, 0.1), result);
  }

  @Test
  public void lightingWithPatternApplied() {
    Material m2 = m.clone();
    m2.pattern = new StripePattern(Color.WHITE(), Color.BLACK());
    m2.ambient = 1;
    m2.diffuse = 0;
    m2.specular = 0;

    Vector eyeVector = new Vector(0, 0, -1);
    Vector normalVector = new Vector(0, 0, -1);

    Light light = Light.pointLight(new Point(0, 0, -10), Color.WHITE());

    Color c1 = Light.lighting(m2, new Sphere(), light, new Point(0.9, 0, 0), eyeVector, normalVector, false);
    Color c2 = Light.lighting(m2, new Sphere(), light, new Point(1.1, 0, 0), eyeVector, normalVector, false);

    assertEquals(Color.WHITE(), c1);
    assertEquals(Color.BLACK(), c2);
  }

  @Test
  public void materialContainsReflective() {
    Material m = new Material();

    assertEquals(0, m.reflective, Constants.EPSILON);
  }

  @Test
  public void transparencyRefractiveDefaults() {
    Material m = new Material();
    assertEquals(0, m.transparency, Constants.EPSILON);
    assertEquals(Constants.REFRACTION_INDEX_AIR, m.refractiveIndex, Constants.EPSILON);
  }

}