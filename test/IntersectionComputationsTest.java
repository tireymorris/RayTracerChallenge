import static org.junit.Assert.*;
import org.junit.Test;

import graphics.*;
import structures.*;
import entities.*;
import util.Constants;
import scene.*;

public class IntersectionComputationsTest {
  @Test
  public void precomputeIntersectionState() {
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
    Entity s = new Sphere();
    Intersection i = new Intersection(4, s);

    IntersectionComputations comps = new IntersectionComputations(i, r);

    assertEquals(i.t, comps.t, Constants.EPSILON);
    assertEquals(i.entity, comps.entity);
    assertEquals(new Point(0, 0, -1), comps.point);
    assertEquals(new Vector(0, 0, -1), comps.eyeVector);
    assertEquals(new Vector(0, 0, -1), comps.normalVector);
  }

  @Test
  public void setsInsideFalse() {
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
    Entity s = new Sphere();
    Intersection i = new Intersection(4, s);

    IntersectionComputations comps = new IntersectionComputations(i, r);

    assertFalse(comps.inside);
  }

  @Test
  public void setsInsideTrue() {
    Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
    Entity s = new Sphere();
    Intersection i = new Intersection(1, s);

    IntersectionComputations comps = new IntersectionComputations(i, r);

    assertEquals(new Point(0, 0, 1), comps.point);
    assertEquals(new Vector(0, 0, -1), comps.eyeVector);

    assertTrue(comps.inside);

    assertEquals(new Vector(0, 0, -1), comps.normalVector); // inverted because eye is inside!
  }
}