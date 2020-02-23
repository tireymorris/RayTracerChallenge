import static org.junit.Assert.*;
import org.junit.Test;

import structures.*;
import util.Constants;

public class TupleTest {

  @Test
  public void testConstructionAndIsPoint() {
    Tuple tuple = new Tuple(4.3, -4.2, 3.1, 1.0);

    assertEquals(4.3, tuple.x, Constants.EPSILON);
    assertEquals(-4.2, tuple.y, Constants.EPSILON);
    assertEquals(3.1, tuple.z, Constants.EPSILON);
    assertEquals(1.0, tuple.w, Constants.EPSILON);

    assertTrue(tuple.isPoint());
    assertFalse(tuple.isVector());
  }

  @Test
  public void testConstructionAndIsVector() {
    Tuple tuple = new Tuple(4.3, -4.2, 3.1, 0.0);

    assertEquals(4.3, tuple.x, Constants.EPSILON);
    assertEquals(-4.2, tuple.y, Constants.EPSILON);
    assertEquals(3.1, tuple.z, Constants.EPSILON);
    assertEquals(0.0, tuple.w, Constants.EPSILON);

    assertTrue(tuple.isVector());
    assertFalse(tuple.isPoint());
  }

  @Test
  public void tupleIsPoint() {
    Tuple tuple = new Tuple(4.3, -4.2, 3.1, 1.0);

    assertEquals(4.3, tuple.x, Constants.EPSILON);
    assertEquals(-4.2, tuple.y, Constants.EPSILON);
    assertEquals(3.1, tuple.z, Constants.EPSILON);
    assertEquals(1.0, tuple.w, Constants.EPSILON);

    assertTrue(tuple.isPoint());
    assertFalse(tuple.isVector());
  }

  @Test
  public void tupleIsVector() {
    Tuple tuple = new Tuple(4.3, -4.2, 3.1, 0.0);

    assertEquals(4.3, tuple.x, Constants.EPSILON);
    assertEquals(-4.2, tuple.y, Constants.EPSILON);
    assertEquals(3.1, tuple.z, Constants.EPSILON);
    assertEquals(0.0, tuple.w, Constants.EPSILON);

    assertTrue(tuple.isVector());
    assertFalse(tuple.isPoint());
  }

  @Test
  public void tupleCreatesPoint() {
    Tuple point = new Point(4.3, -4.2, 3.1);

    assertEquals(4.3, point.x, Constants.EPSILON);
    assertEquals(-4.2, point.y, Constants.EPSILON);
    assertEquals(3.1, point.z, Constants.EPSILON);
    assertEquals(1.0, point.w, Constants.EPSILON);

    assertTrue(point.isPoint());
    assertFalse(point.isVector());

    assertEquals(new Tuple(4.3, -4.2, 3.1, 1.0), point);
    assertNotEquals(new Tuple(4.3, -4.2, 3.1, 0.0), point);
  }

  @Test
  public void tupleCreatesVector() {
    Tuple point = new Vector(4.3, -4.2, 3.1);

    assertEquals(4.3, point.x, Constants.EPSILON);
    assertEquals(-4.2, point.y, Constants.EPSILON);
    assertEquals(3.1, point.z, Constants.EPSILON);
    assertEquals(0.0, point.w, Constants.EPSILON);

    assertFalse(point.isPoint());
    assertTrue(point.isVector());

    assertEquals(new Tuple(4.3, -4.2, 3.1, 0.0), point);
    assertNotEquals(new Tuple(4.3, -4.2, 3.1, 1.0), point);
  }

  @Test
  public void addTuples() {
    Tuple t1 = new Tuple(3, -2, 5, 1);
    Tuple t2 = new Tuple(-2, 3, 1, 0);

    assertEquals(new Tuple(1, 1, 6, 1), t1.plus(t2));
  }

  @Test
  public void subtractTuples() {
    Tuple t1 = new Tuple(3, -2, 5, 1);
    Tuple t2 = new Tuple(-2, 3, 1, 0);

    assertEquals(new Tuple(5, -5, 4, 1), t1.minus(t2));
  }

  @Test
  public void subtractTwoPoints() {
    Point p1 = new Point(3, 2, 1);
    Point p2 = new Point(5, 6, 7);

    assertEquals(new Vector(-2, -4, -6), p1.minus(p2));
  }

  @Test
  public void subtractVectorFromPoint() {
    Point p = new Point(3, 2, 1);
    Vector v = new Vector(5, 6, 7);

    assertEquals(new Point(-2, -4, -6), p.minus(v));
  }

  @Test
  public void addTwoVectors() {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(6, 5, 4);

    assertEquals(new Vector(7, 7, 7), v1.plus(v2));
  }

  @Test
  public void subtractTwoVectors() {
    Vector v1 = new Vector(3, 2, 1);
    Vector v2 = new Vector(5, 6, 7);

    assertEquals(new Vector(-2, -4, -6), v1.minus(v2));
  }

  @Test
  public void subtractVectorFromZeroVector() {
    Vector zero = new Vector(0, 0, 0);
    Vector v = new Vector(1, -2, 3);

    assertEquals(new Vector(-1, 2, -3), zero.minus(v));
  }

  @Test
  public void negateVector() {
    Vector zero = new Vector(0, 0, 0);
    Vector v = new Vector(1, -2, 3);

    assertEquals(new Vector(-1, 2, -3), zero.minus(v));
    assertEquals(new Vector(-1, 2, -3), v.negate());
  }

  @Test
  public void scalarMultiplyTuple1() {
    Tuple t = new Tuple(1, -2, 3, -4);
    assertEquals(new Tuple(3.5, -7, 10.5, -14), t.scale(3.5));
  }

  @Test
  public void scalarMultiplyTuple2() {
    Tuple t = new Tuple(1, -2, 3, -4);
    assertEquals(new Tuple(0.5, -1, 1.5, -2), t.scale(0.5));
  }

  @Test
  public void divTuple() {
    Tuple t = new Tuple(1, -2, 3, -4);
    assertEquals(new Tuple(0.5, -1, 1.5, -2), t.div(2));
  }

  @Test
  public void computeVectorMagnitude1() {
    Vector v = new Vector(1, 0, 0);

    assertEquals(v.magnitude(), 1, Constants.EPSILON);
  }

  @Test
  public void computeVectorMagnitude2() {
    Vector v = new Vector(0, 1, 0);

    assertEquals(v.magnitude(), 1, Constants.EPSILON);
  }

  @Test
  public void computeVectorMagnitude3() {
    Vector v = new Vector(0, 0, 1);

    assertEquals(v.magnitude(), 1, Constants.EPSILON);
  }

  @Test
  public void computeVectorMagnitude4() {
    Vector v = new Vector(1, 2, 3);

    assertEquals(v.magnitude(), Math.sqrt(14), Constants.EPSILON);
  }

  @Test
  public void computeVectorMagnitude5() {
    Vector v = new Vector(-1, -2, -3);

    assertEquals(v.magnitude(), Math.sqrt(14), Constants.EPSILON);
  }

  @Test
  public void normalizeVector1() {
    Vector v = new Vector(4, 0, 0);

    assertEquals(new Vector(1, 0, 0), v.normalize());
  }

  @Test
  public void normalizeVector2() {
    Vector v = new Vector(1, 2, 3);

    Vector result1 = new Vector(0.26726, 0.53452, 0.80178);
    Vector result2 = new Vector(1 / Math.sqrt(14), 2 / Math.sqrt(14), 3 / Math.sqrt(14));

    assertEquals(result1, v.normalize());
    assertEquals(result2, v.normalize());
  }

  @Test
  public void normalizedVectorLengthIs1() {
    Vector v = new Vector(1, 2, 3);

    assertEquals(1, v.normalize().magnitude(), Constants.EPSILON);
  }

  @Test
  public void dotProduct() {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(2, 3, 4);

    assertEquals(20, v1.dot(v2), Constants.EPSILON);
  }

  @Test
  public void crossProduct() {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(2, 3, 4);

    assertEquals(new Vector(-1, 2, -1), v1.cross(v2));
    assertEquals(new Vector(1, -2, 1), v2.cross(v1));
  }

  @Test
  public void reflect45Degree() {
    Vector vel = new Vector(1, -1, 0);
    Vector n = new Vector(0, 1, 0);

    Vector r = vel.reflect(n);
    assertEquals(new Vector(1, 1, 0), r);
  }

  @Test
  public void reflectSlantedSurface() {
    Vector vel = new Vector(0, -1, 0);
    Vector n = new Vector(Math.sqrt(2) / 2.0, Math.sqrt(2) / 2.0, 0);

    Vector r = vel.reflect(n);
    assertEquals(new Vector(1, 0, 0), r);
  }
}