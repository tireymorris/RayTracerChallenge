import static org.junit.Assert.*;
import org.junit.Test;

import graphics.*;
import structures.*;
import entities.*;
import util.Constants;
import scene.*;

public class EntityTest {
  @Test
  public void defaultTransformation() {
    Entity s = new TestShape();
    assertEquals(Matrix.IDENTITY_MATRIX(), s.getTransform().build());
  }

  @Test
  public void otherTransformation() {
    Entity s = new TestShape();
    Transform t = Transform.identity().translate(2, 3, 4);

    s.setTransform(t);
    assertEquals(s.getTransform(), t);
  }

  @Test
  public void hasDefaultMaterial() {
    Entity s = new TestShape();
    Material m = s.material;
    assertEquals(new Material(), m);
  }

  @Test
  public void mayBeAssignedMaterial() {
    Entity s = new TestShape();
    Material m = new Material();

    m.ambient = 1;
    s.material = m;

    assertEquals(1, s.material.ambient, Constants.EPSILON);
  }

  @Test
  public void intersectScaledEntity() {
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
    TestShape s = new TestShape();

    s.setTransform(Transform.identity().scale(2, 2, 2));
    Intersection[] xs = s.intersections(r);

    assertEquals(new Point(0, 0, -2.5), s.savedRay.origin);
    assertEquals(new Vector(0, 0, 0.5), s.savedRay.direction);
  }

  @Test
  public void intersectTranslatedEntity() {
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
    TestShape s = new TestShape();

    s.setTransform(Transform.identity().translate(5, 0, 0));
    Intersection[] xs = s.intersections(r);

    assertEquals(new Point(-5, 0, -5), s.savedRay.origin);
    assertEquals(new Vector(0, 0, 1), s.savedRay.direction);
  }

  @Test
  public void translatedEntityNormal() {
    Entity s = new TestShape();
    s.setTransform(Transform.identity().translate(0, 1, 0));

    Vector n = s.normalAt(new Point(0, 1.70711, -0.70711));
    assertEquals(new Vector(0, 0.70711, -0.70711), n);
  }

  @Test
  public void transformedEntityNormal() {
    Entity s = new TestShape();
    s.setTransform(Transform.identity().rotateZ(Constants.PI / 5.0).scale(1, 0.5, 1));

    Vector n = s.normalAt(new Point(0, Math.sqrt(2) / 2.0, -Math.sqrt(2) / 2.0));
    assertEquals(new Vector(0, 0.97014, -0.24254), n);
  }
}