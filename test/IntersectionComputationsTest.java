import static org.junit.Assert.*;
import org.junit.Test;

import graphics.*;
import structures.*;
import entities.*;
import util.Constants;

public class IntersectionComputationsTest {
  @Test
  public void precomputeIntersectionState() {
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
    Entity s = new Sphere();
    Intersection i = new Intersection(4, s);

    IntersectionComputations comps = new IntersectionComputations(i, r, null);

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

    IntersectionComputations comps = new IntersectionComputations(i, r, null);

    assertFalse(comps.inside);
  }

  @Test
  public void setsInsideTrue() {
    Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
    Entity s = new Sphere();
    Intersection i = new Intersection(1, s);

    IntersectionComputations comps = new IntersectionComputations(i, r, null);

    assertEquals(new Point(0, 0, 1), comps.point);
    assertEquals(new Vector(0, 0, -1), comps.eyeVector);

    assertTrue(comps.inside);

    assertEquals(new Vector(0, 0, -1), comps.normalVector); // inverted because eye is inside!
  }

  @Test
  public void precomputeReflectionVector() {
    Entity s = new Plane();
    Ray r = new Ray(new Point(0, 1, -1), new Vector(0, -Math.sqrt(2) / 2.0, Math.sqrt(2) / 2.0));

    Intersection i = new Intersection(Math.sqrt(2), s);

    IntersectionComputations comps = new IntersectionComputations(i, r, null);

    assertEquals(new Vector(0, Math.sqrt(2) / 2.0, Math.sqrt(2) / 2.0), comps.reflectVector);
  }

  @Test
  public void reflectanceUnderTotalInternalReflection() {
    Entity s1 = new GlassSphere();
    Ray r = new Ray(new Point(0, 0, Math.sqrt(2) / 2), new Vector(0, 1, 0));

    Intersection[] xs = { new Intersection(-Math.sqrt(2) / 2, s1), new Intersection(Math.sqrt(2) / 2, s1) };

    IntersectionComputations comps = new IntersectionComputations(xs[1], r, xs);

    assertEquals(1.0, comps.schlick(), Constants.EPSILON);
  }

  @Test
  public void reflectanceOfPerpendicularRay() {
    Entity shape = new GlassSphere();
    shape.material.refractiveIndex = 1.5;
    Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 1, 0));

    Intersection[] xs = { new Intersection(-1, shape), new Intersection(1, shape) };

    IntersectionComputations comps = new IntersectionComputations(xs[1], ray, xs);

    assertEquals(0.04, comps.schlick(), Constants.EPSILON);
  }

  @Test
  public void reflectanceWhenN2Greater() {
    Entity shape = new GlassSphere();
    shape.material.refractiveIndex = 1.5;
    Ray ray = new Ray(new Point(0, 0.99, -2), new Vector(0, 0, 1));

    Intersection[] xs = { new Intersection(1.8589, shape) };

    IntersectionComputations comps = new IntersectionComputations(xs[0], ray, xs);

    assertEquals(0.48873, comps.schlick(), Constants.EPSILON);
  }
}