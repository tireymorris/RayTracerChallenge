import static org.junit.Assert.*;
import org.junit.Test;

public class SphereTest {
  @Test
  public void defaultTransformation() {
    Sphere s = new Sphere();
    assertEquals(Constants.IDENTITY_MATRIX(), s.getTransform().build());
  }

  @Test
  public void otherTransformation() {
    Sphere s = new Sphere();
    Transform t = Transform.identity().translate(2, 3, 4);

    s.setTransform(t);
    assertEquals(s.getTransform(), t);
  }

  @Test
  public void intersectScaledSphere() {
    Ray r = new Ray(Tuple.point(0, 0, -5), Tuple.vector(0, 0, 1));
    Sphere s = new Sphere();

    s.setTransform(Transform.identity().scale(2, 2, 2));
    Intersection[] xs = r.intersect(s);

    assertEquals(2, xs.length);
    assertEquals(3, xs[0].t, Constants.EPSILON);
    assertEquals(7, xs[1].t, Constants.EPSILON);
  }
}