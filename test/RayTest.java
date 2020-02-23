import static org.junit.Assert.*;
import org.junit.Test;

import graphics.*;
import structures.*;
import entities.*;
import util.Constants;

public class RayTest {
  @Test
  public void createAndQuery() {
    Point origin = new Point(1, 2, 3);
    Vector direction = new Vector(4, 5, 6);

    Ray r = new Ray(origin, direction);
    assertEquals(origin, r.origin);
    assertEquals(direction, r.direction);
  }

  @Test
  public void position() {
    Ray r = new Ray(new Point(2, 3, 4), new Vector(1, 0, 0));

    assertEquals(new Point(2, 3, 4), r.position(0));
    assertEquals(new Point(3, 3, 4), r.position(1));
    assertEquals(new Point(1, 3, 4), r.position(-1));
    assertEquals(new Point(4.5, 3, 4), r.position(2.5));
  }

  @Test
  public void intersectsSphereTwice() {
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
    Sphere s = new Sphere();

    Intersection[] xs = s.intersections(r);

    assertEquals(2, xs.length);
    assertEquals(4.0, xs[0].t, Constants.EPSILON);
    assertEquals(6.0, xs[1].t, Constants.EPSILON);
  }

  @Test
  public void intersectsSphereOnce() {
    Ray r = new Ray(new Point(0, 1, -5), new Vector(0, 0, 1));
    Sphere s = new Sphere();

    Intersection[] xs = s.intersections(r);

    assertEquals(2, xs.length);
    assertEquals(5.0, xs[0].t, Constants.EPSILON);
    assertEquals(5.0, xs[1].t, Constants.EPSILON);
  }

  @Test
  public void intersectsTwiceInsideSphere() {
    Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
    Sphere s = new Sphere();

    Intersection[] xs = s.intersections(r);

    assertEquals(2, xs.length);
    assertEquals(-1.0, xs[0].t, Constants.EPSILON);
    assertEquals(1.0, xs[1].t, Constants.EPSILON);
  }

  @Test
  public void intersectsTwiceSphereBehindRay() {
    Ray r = new Ray(new Point(0, 0, 5), new Vector(0, 0, 1));
    Sphere s = new Sphere();

    Intersection[] xs = s.intersections(r);

    assertEquals(2, xs.length);
    assertEquals(-6.0, xs[0].t, Constants.EPSILON);
    assertEquals(-4.0, xs[1].t, Constants.EPSILON);
  }

  @Test
  public void intersectionContainsEntity() {
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
    Sphere s = new Sphere();

    Intersection[] xs = s.intersections(r);

    assertEquals(2, xs.length);
    assertEquals(s, xs[0].entity);
    assertEquals(s, xs[1].entity);
  }

  @Test
  public void translateRay() {
    Ray r = new Ray(new Point(1, 2, 3), new Vector(0, 1, 0));
    Matrix m = Transformations.translation(3, 4, 5);

    Ray r2 = r.transform(m);

    assertEquals(new Point(4, 6, 8), r2.origin);
    assertEquals(new Vector(0, 1, 0), r2.direction);

    assertEquals(new Point(1, 2, 3), r.origin);
    assertEquals(new Vector(0, 1, 0), r.direction);
  }

  @Test
  public void scaleRay() {
    Ray r = new Ray(new Point(1, 2, 3), new Vector(0, 1, 0));
    Matrix m = Transformations.scaling(2, 3, 4);

    Ray r2 = r.transform(m);

    assertEquals(new Point(2, 6, 12), r2.origin);
    assertEquals(new Vector(0, 3, 0), r2.direction);
  }
}