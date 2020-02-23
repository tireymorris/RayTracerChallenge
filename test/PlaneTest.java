import static org.junit.Assert.*;
import org.junit.Test;

import graphics.*;
import structures.*;
import entities.*;
import util.Constants;

public class PlaneTest {
  @Test
  public void normalIsConstantEverywhere() {
    Plane p = new Plane();
    Vector n1 = p.localNormalAt(new Point(0, 0, 0));
    Vector n2 = p.localNormalAt(new Point(10, 0, -10));
    Vector n3 = p.localNormalAt(new Point(-5, 0, 150));

    assertEquals(new Vector(0, 1, 0), n1);
    assertEquals(new Vector(0, 1, 0), n2);
    assertEquals(new Vector(0, 1, 0), n3);
  }

  @Test
  public void intersectWithParallelRay() {
    Plane p = new Plane();
    Ray r = new Ray(new Point(0, 10, 0), new Vector(0, 0, 1));

    Intersection[] xs = p.intersections(r);

    assertEquals(0, xs.length);
  }

  @Test
  public void intersectWithCoplanarRay() {
    Plane p = new Plane();
    Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

    Intersection[] xs = p.intersections(r);

    assertEquals(0, xs.length);
  }

  @Test
  public void rayIntersectsFromAbove() {
    Plane p = new Plane();
    Ray r = new Ray(new Point(0, 1, 0), new Vector(0, -1, 0));

    Intersection[] xs = p.intersections(r);

    assertEquals(1, xs.length);
    assertEquals(p, xs[0].entity);
    assertEquals(1, xs[0].t, Constants.EPSILON);
  }

  @Test
  public void rayIntersectsFromBelow() {
    Plane p = new Plane();
    Ray r = new Ray(new Point(0, -1, 0), new Vector(0, 1, 0));

    Intersection[] xs = p.intersections(r);

    assertEquals(1, xs.length);
    assertEquals(p, xs[0].entity);
    assertEquals(1, xs[0].t, Constants.EPSILON);
  }

}