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

  @Test
  public void xAxisNormal() {
    Sphere s = new Sphere();
    Vector n = s.normalAt(Tuple.point(1, 0, 0));

    assertEquals(Tuple.vector(1, 0, 0), n);
  }

  @Test
  public void yAxisNormal() {
    Sphere s = new Sphere();
    Vector n = s.normalAt(Tuple.point(0, 1, 0));

    assertEquals(Tuple.vector(0, 1, 0), n);
  }

  @Test
  public void zAxisNormal() {
    Sphere s = new Sphere();
    Vector n = s.normalAt(Tuple.point(0, 0, 1));

    assertEquals(Tuple.vector(0, 0, 1), n);
  }

  @Test
  public void nonaxialNormal() {
    double magicNumber = Math.sqrt(3) / 3.0;

    Sphere s = new Sphere();
    Vector n = s.normalAt(Tuple.point(magicNumber, magicNumber, magicNumber));

    assertEquals(Tuple.vector(magicNumber, magicNumber, magicNumber), n);
  }

  @Test
  public void nonaxialNormalIsNormalized() {
    double magicNumber = Math.sqrt(3) / 3.0;

    Sphere s = new Sphere();
    Vector n = s.normalAt(Tuple.point(magicNumber, magicNumber, magicNumber));

    assertEquals(Tuple.vector(magicNumber, magicNumber, magicNumber), n);
    assertEquals(Tuple.vector(magicNumber, magicNumber, magicNumber), n.normalize());
  }

  @Test
  public void translatedSphereNormal() {
    Sphere s = new Sphere();
    s.setTransform(Transform.identity().translate(0, 1, 0));

    Vector n = s.normalAt(Tuple.point(0, 1.70711, -0.70711));
    assertEquals(Tuple.vector(0, 0.70711, -0.70711), n);
  }

  @Test
  public void transformedSphereNormal() {
    Sphere s = new Sphere();
    s.setTransform(Transform.identity().rotateZ(Constants.PI / 5.0).scale(1, 0.5, 1));

    Vector n = s.normalAt(Tuple.point(0, Math.sqrt(2) / 2.0, -Math.sqrt(2) / 2.0));
    assertEquals(Tuple.vector(0, 0.97014, -0.24254), n);
  }
}