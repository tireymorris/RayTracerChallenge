import static org.junit.Assert.*;
import org.junit.Test;

public class TupleTest {

  @Test
  public void testConstructionAndIsPoint() {
    Tuple tuple = new Tuple(4.3, -4.2, 3.1, 1.0);

    assertEquals(4.3, tuple.x, Tuple.FLOATING_POINT_DELTA);
    assertEquals(-4.2, tuple.y, Tuple.FLOATING_POINT_DELTA);
    assertEquals(3.1, tuple.z, Tuple.FLOATING_POINT_DELTA);
    assertEquals(1.0, tuple.w, Tuple.FLOATING_POINT_DELTA);

    assertTrue(tuple.isPoint());
    assertFalse(tuple.isVector());
  }

  @Test
  public void testConstructionAndIsVector() {
    Tuple tuple = new Tuple(4.3, -4.2, 3.1, 0.0);

    assertEquals(4.3, tuple.x, Tuple.FLOATING_POINT_DELTA);
    assertEquals(-4.2, tuple.y, Tuple.FLOATING_POINT_DELTA);
    assertEquals(3.1, tuple.z, Tuple.FLOATING_POINT_DELTA);
    assertEquals(0.0, tuple.w, Tuple.FLOATING_POINT_DELTA);

    assertTrue(tuple.isVector());
    assertFalse(tuple.isPoint());
  }

  @Test
  public void tupleIsPoint() {
    Tuple tuple = Tuple.tuple(4.3, -4.2, 3.1, 1.0);

    assertEquals(4.3, tuple.x, Tuple.FLOATING_POINT_DELTA);
    assertEquals(-4.2, tuple.y, Tuple.FLOATING_POINT_DELTA);
    assertEquals(3.1, tuple.z, Tuple.FLOATING_POINT_DELTA);
    assertEquals(1.0, tuple.w, Tuple.FLOATING_POINT_DELTA);

    assertTrue(tuple.isPoint());
    assertFalse(tuple.isVector());
  }

  @Test
  public void tupleIsVector() {
    Tuple tuple = Tuple.tuple(4.3, -4.2, 3.1, 0.0);

    assertEquals(4.3, tuple.x, Tuple.FLOATING_POINT_DELTA);
    assertEquals(-4.2, tuple.y, Tuple.FLOATING_POINT_DELTA);
    assertEquals(3.1, tuple.z, Tuple.FLOATING_POINT_DELTA);
    assertEquals(0.0, tuple.w, Tuple.FLOATING_POINT_DELTA);

    assertTrue(tuple.isVector());
    assertFalse(tuple.isPoint());
  }

  @Test
  public void pointCreatesTuple() {
    Tuple point = Tuple.point(4.3, -4.2, 3.1);

    assertEquals(4.3, point.x, Tuple.FLOATING_POINT_DELTA);
    assertEquals(-4.2, point.y, Tuple.FLOATING_POINT_DELTA);
    assertEquals(3.1, point.z, Tuple.FLOATING_POINT_DELTA);
    assertEquals(1.0, point.w, Tuple.FLOATING_POINT_DELTA);

    assertTrue(point.isPoint());
    assertFalse(point.isVector());

    assertEquals(point, new Tuple(4.3, -4.2, 3.1, 1.0));
    assertNotEquals(point, new Tuple(4.3, -4.2, 3.1, 0.0));
  }

  @Test
  public void pointCreatesVector() {
    Tuple point = Tuple.vector(4.3, -4.2, 3.1);

    assertEquals(4.3, point.x, Tuple.FLOATING_POINT_DELTA);
    assertEquals(-4.2, point.y, Tuple.FLOATING_POINT_DELTA);
    assertEquals(3.1, point.z, Tuple.FLOATING_POINT_DELTA);
    assertEquals(0.0, point.w, Tuple.FLOATING_POINT_DELTA);

    assertFalse(point.isPoint());
    assertTrue(point.isVector());

    assertEquals(point, new Tuple(4.3, -4.2, 3.1, 0.0));
    assertNotEquals(point, new Tuple(4.3, -4.2, 3.1, 1.0));
  }
}