import static org.junit.Assert.*;
import org.junit.Test;

public class IntersectionTest {
  @Test
  public void storesData() {
    Sphere s = new Sphere();
    Intersection i = new Intersection(3.5, s);

    assertEquals(3.5, i.t, Constants.EPSILON);
    assertEquals(s, i.entity);
  }

  @Test
  public void aggregateIntersections() {
    Sphere s = new Sphere();
    Intersection i1 = new Intersection(1, s);
    Intersection i2 = new Intersection(2, s);

    Intersection[] intersections = Intersection.intersections(i1, i2);
    assertEquals(2, intersections.length);
    assertEquals(1, intersections[0].t, Constants.EPSILON);
    assertEquals(2, intersections[1].t, Constants.EPSILON);
  }

  @Test
  public void hitPositiveT() {
    Sphere s = new Sphere();
    Intersection i1 = new Intersection(1, s);
    Intersection i2 = new Intersection(2, s);

    Intersection[] intersections = Intersection.intersections(i1, i2);
    assertEquals(i1, Intersection.hit(intersections));
  }

  @Test
  public void hitPositiveAndNegativeT() {
    Sphere s = new Sphere();
    Intersection i1 = new Intersection(-1, s);
    Intersection i2 = new Intersection(1, s);

    Intersection[] intersections = Intersection.intersections(i1, i2);
    assertEquals(i2, Intersection.hit(intersections));
  }

  @Test
  public void hitAllNegativeT() {
    Sphere s = new Sphere();
    Intersection i1 = new Intersection(-2, s);
    Intersection i2 = new Intersection(-1, s);

    Intersection[] intersections = Intersection.intersections(i1, i2);
    assertNull(Intersection.hit(intersections));
  }

  @Test
  public void hitAlwaysLowestNonnegative() {
    Sphere s = new Sphere();
    Intersection i1 = new Intersection(5, s);
    Intersection i2 = new Intersection(7, s);
    Intersection i3 = new Intersection(-3, s);
    Intersection i4 = new Intersection(2, s);

    Intersection[] intersections = Intersection.intersections(i1, i2, i3, i4);
    assertEquals(i4, Intersection.hit(intersections));
  }
}