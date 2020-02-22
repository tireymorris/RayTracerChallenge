import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RayTest {
  @Test
  public void createAndQuery() {
    Point origin = Tuple.point(1, 2, 3);
    Vector direction = Tuple.vector(4, 5, 6);

    Ray r = new Ray(origin, direction);
    assertEquals(origin, r.origin);
    assertEquals(direction, r.direction);
  }

  @Test
  public void position() {
    Ray r = new Ray(Tuple.point(2, 3, 4), Tuple.vector(1, 0, 0));

    assertEquals(Tuple.point(2, 3, 4), r.position(0));
    assertEquals(Tuple.point(3, 3, 4), r.position(1));
    assertEquals(Tuple.point(1, 3, 4), r.position(-1));
    assertEquals(Tuple.point(4.5, 3, 4), r.position(2.5));
  }

  @Test
  public void intersectsSphereTwice() {
    Ray r = new Ray(Tuple.point(0, 0, -5), Tuple.vector(0, 0, 1));
    Sphere s = new Sphere();

    Intersection[] intersections = r.intersect(s);

    assertEquals(2, intersections.length);
    assertEquals(4.0, intersections[0].t, Constants.EPSILON);
    assertEquals(6.0, intersections[1].t, Constants.EPSILON);
  }

  @Test
  public void intersectsSphereOnce() {
    Ray r = new Ray(Tuple.point(0, 1, -5), Tuple.vector(0, 0, 1));
    Sphere s = new Sphere();

    Intersection[] intersections = r.intersect(s);

    assertEquals(2, intersections.length);
    assertEquals(5.0, intersections[0].t, Constants.EPSILON);
    assertEquals(5.0, intersections[1].t, Constants.EPSILON);
  }

  @Test
  public void intersectsTwiceInsideSphere() {
    Ray r = new Ray(Tuple.point(0, 0, 0), Tuple.vector(0, 0, 1));
    Sphere s = new Sphere();

    Intersection[] intersections = r.intersect(s);

    assertEquals(2, intersections.length);
    assertEquals(-1.0, intersections[0].t, Constants.EPSILON);
    assertEquals(1.0, intersections[1].t, Constants.EPSILON);
  }

  @Test
  public void intersectsTwiceSphereBehindRay() {
    Ray r = new Ray(Tuple.point(0, 0, 5), Tuple.vector(0, 0, 1));
    Sphere s = new Sphere();

    Intersection[] intersections = r.intersect(s);

    assertEquals(2, intersections.length);
    assertEquals(-6.0, intersections[0].t, Constants.EPSILON);
    assertEquals(-4.0, intersections[1].t, Constants.EPSILON);
  }

  @Test
  public void intersectionContainsEntity() {
    Ray r = new Ray(Tuple.point(0, 0, -5), Tuple.vector(0, 0, 1));
    Sphere s = new Sphere();

    Intersection[] intersections = r.intersect(s);

    assertEquals(2, intersections.length);
    assertEquals(s, intersections[0].entity);
    assertEquals(s, intersections[1].entity);
  }
}