import static org.junit.Assert.*;
import org.junit.Test;

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
    Tuple tuple = Tuple.tuple(4.3, -4.2, 3.1, 1.0);

    assertEquals(4.3, tuple.x, Constants.EPSILON);
    assertEquals(-4.2, tuple.y, Constants.EPSILON);
    assertEquals(3.1, tuple.z, Constants.EPSILON);
    assertEquals(1.0, tuple.w, Constants.EPSILON);

    assertTrue(tuple.isPoint());
    assertFalse(tuple.isVector());
  }

  @Test
  public void tupleIsVector() {
    Tuple tuple = Tuple.tuple(4.3, -4.2, 3.1, 0.0);

    assertEquals(4.3, tuple.x, Constants.EPSILON);
    assertEquals(-4.2, tuple.y, Constants.EPSILON);
    assertEquals(3.1, tuple.z, Constants.EPSILON);
    assertEquals(0.0, tuple.w, Constants.EPSILON);

    assertTrue(tuple.isVector());
    assertFalse(tuple.isPoint());
  }

  @Test
  public void tupleCreatesPoint() {
    Tuple point = Tuple.point(4.3, -4.2, 3.1);

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
    Tuple point = Tuple.vector(4.3, -4.2, 3.1);

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
    Tuple t1 = Tuple.tuple(3, -2, 5, 1);
    Tuple t2 = Tuple.tuple(-2, 3, 1, 0);

    assertEquals(new Tuple(1, 1, 6, 1), Tuple.add(t1, t2));
  }

  @Test
  public void subtractTuples() {
    Tuple t1 = Tuple.tuple(3, -2, 5, 1);
    Tuple t2 = Tuple.tuple(-2, 3, 1, 0);

    assertEquals(new Tuple(5, -5, 4, 1), Tuple.subtract(t1, t2));
  }

  @Test
  public void subtractTwoPoints() {
    Point p1 = Tuple.point(3, 2, 1);
    Point p2 = Tuple.point(5, 6, 7);

    assertEquals(Tuple.vector(-2, -4, -6), p1.minus(p2));
  }

  @Test
  public void subtractVectorFromPoint() {
    Point p = Tuple.point(3, 2, 1);
    Vector v = Tuple.vector(5, 6, 7);

    assertEquals(Tuple.point(-2, -4, -6), p.minus(v));
  }

  @Test
  public void addTwoVectors() {
    Vector v1 = Tuple.vector(1, 2, 3);
    Vector v2 = Tuple.vector(6, 5, 4);

    assertEquals(Tuple.vector(7, 7, 7), v1.plus(v2));
  }

  @Test
  public void subtractTwoVectors() {
    Vector v1 = Tuple.vector(3, 2, 1);
    Vector v2 = Tuple.vector(5, 6, 7);

    assertEquals(Tuple.vector(-2, -4, -6), v1.minus(v2));
  }

  @Test
  public void subtractVectorFromZeroVector() {
    Vector zero = Tuple.vector(0, 0, 0);
    Vector v = Tuple.vector(1, -2, 3);

    assertEquals(Tuple.vector(-1, 2, -3), zero.minus(v));
  }

  @Test
  public void negateVector() {
    Vector zero = Tuple.vector(0, 0, 0);
    Vector v = Tuple.vector(1, -2, 3);

    assertEquals(Tuple.vector(-1, 2, -3), zero.minus(v));
    assertEquals(Tuple.vector(-1, 2, -3), v.negate());
  }

  @Test
  public void scalarMultiplyTuple1() {
    Tuple t = Tuple.tuple(1, -2, 3, -4);
    assertEquals(Tuple.tuple(3.5, -7, 10.5, -14), t.scale(3.5));
  }

  @Test
  public void scalarMultiplyTuple2() {
    Tuple t = Tuple.tuple(1, -2, 3, -4);
    assertEquals(Tuple.tuple(0.5, -1, 1.5, -2), t.mult(0.5));
  }

  @Test
  public void divTuple() {
    Tuple t = Tuple.tuple(1, -2, 3, -4);
    assertEquals(Tuple.tuple(0.5, -1, 1.5, -2), t.div(2));
  }
}