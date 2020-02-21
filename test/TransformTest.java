import static org.junit.Assert.*;
import org.junit.Test;

public class TransformTest {
  @Test
  public void translationMatrixMultiply() {
    Matrix t = Transform.translate(5, -3, 2);
    Point p = Tuple.point(-3, 4, 5);

    assertEquals(Tuple.point(2, 1, 7), t.mult(p));
  }

  @Test
  public void inverseTranslationMatrixMultiply() {
    Matrix inv = Transform.translate(5, -3, 2).inverse();
    Point p = Tuple.point(-3, 4, 5);

    assertEquals(Tuple.point(-8, 7, 3), inv.mult(p));
  }

  @Test
  public void vectorIsNotTranslated() {
    Matrix t = Transform.translate(5, -3, 2);
    Vector v = Vector.vector(-3, 4, 5);

    assertEquals(v, t.mult(v));
  }

  @Test
  public void scalingPoint() {
    Matrix t = Transform.scale(2, 3, 4);
    Point p = Tuple.point(-4, 6, 8);

    assertEquals(Tuple.point(-8, 18, 32), t.mult(p));
  }

  @Test
  public void scalingVector() {
    Matrix t = Transform.scale(2, 3, 4);
    Vector v = Tuple.vector(-4, 6, 8);

    assertEquals(Tuple.vector(-8, 18, 32), t.mult(v));
  }

  @Test
  public void inverseScalingVector() {
    Matrix inv = Transform.scale(2, 3, 4).inverse();
    Vector v = Tuple.vector(-4, 6, 8);

    assertEquals(Tuple.vector(-2, 2, 2), inv.mult(v));
  }

  @Test
  public void reflection() {
    Matrix t = Transform.scale(-1, 1, 1);
    Point p = Tuple.point(2, 3, 4);

    assertEquals(Tuple.point(-2, 3, 4), t.mult(p));
    assertEquals(Tuple.point(-2, 3, 4), Transform.reflectX().mult(p));
  }

  @Test
  public void rotateX() {
    Point p = Tuple.point(0, 1, 0);
    Matrix halfQuarter = Transform.rotateX(Constants.QUARTER_PI);
    Matrix fullQuarter = Transform.rotateX(Constants.HALF_PI);

    assertEquals(Tuple.point(0, Math.sqrt(2) / 2.0, Math.sqrt(2) / 2.0), halfQuarter.mult(p));
    assertEquals(Tuple.point(0, 0, 1), fullQuarter.mult(p));
  }

  @Test
  public void rotateXInverse() {
    Point p = Tuple.point(0, 1, 0);
    Matrix halfQuarter = Transform.rotateX(Constants.QUARTER_PI);
    Matrix inv = halfQuarter.inverse();

    assertEquals(Tuple.point(0, Math.sqrt(2) / 2.0, -Math.sqrt(2) / 2.0), inv.mult(p));
  }

  @Test
  public void rotateY() {
    Point p = Tuple.point(0, 0, 1);
    Matrix halfQuarter = Transform.rotateY(Constants.QUARTER_PI);
    Matrix fullQuarter = Transform.rotateY(Constants.HALF_PI);

    assertEquals(Tuple.point(Math.sqrt(2) / 2.0, 0, Math.sqrt(2) / 2.0), halfQuarter.mult(p));
    assertEquals(Tuple.point(1, 0, 0), fullQuarter.mult(p));
  }

  @Test
  public void rotateZ() {
    Point p = Tuple.point(0, 1, 0);
    Matrix halfQuarter = Transform.rotateZ(Constants.QUARTER_PI);
    Matrix fullQuarter = Transform.rotateZ(Constants.HALF_PI);

    assertEquals(Tuple.point(-Math.sqrt(2) / 2.0, Math.sqrt(2) / 2.0, 0), halfQuarter.mult(p));
    assertEquals(Tuple.point(-1, 0, 0), fullQuarter.mult(p));
  }

  @Test
  public void shearXtoY() {
    Matrix t = Transform.shear(1, 0, 0, 0, 0, 0);
    Point p = Tuple.point(2, 3, 4);

    assertEquals(Tuple.point(5, 3, 4), t.mult(p));
  }

  @Test
  public void shearXtoZ() {
    Matrix t = Transform.shear(0, 1, 0, 0, 0, 0);
    Point p = Tuple.point(2, 3, 4);

    assertEquals(Tuple.point(6, 3, 4), t.mult(p));
  }

  @Test
  public void shearYtoX() {
    Matrix t = Transform.shear(0, 0, 1, 0, 0, 0);
    Point p = Tuple.point(2, 3, 4);

    assertEquals(Tuple.point(2, 5, 4), t.mult(p));
  }

  @Test
  public void shearYtoZ() {
    Matrix t = Transform.shear(0, 0, 0, 1, 0, 0);
    Point p = Tuple.point(2, 3, 4);

    assertEquals(Tuple.point(2, 7, 4), t.mult(p));
  }

  @Test
  public void shearZtoX() {
    Matrix t = Transform.shear(0, 0, 0, 0, 1, 0);
    Point p = Tuple.point(2, 3, 4);

    assertEquals(Tuple.point(2, 3, 6), t.mult(p));
  }

  @Test
  public void shearZtoY() {
    Matrix t = Transform.shear(0, 0, 0, 0, 0, 1);
    Point p = Tuple.point(2, 3, 4);

    assertEquals(Tuple.point(2, 3, 7), t.mult(p));
  }

  @Test
  public void individualTransformsInOrder() {
    Point p = Tuple.point(1, 0, 1);
    Matrix a = Transform.rotateX(Constants.HALF_PI);
    Matrix b = Transform.scale(5, 5, 5);
    Matrix c = Transform.translate(10, 5, 7);

    Point p2 = a.mult(p).asPoint();
    assertEquals(Tuple.point(1, -1, 0), p2);

    Point p3 = b.mult(p2).asPoint();
    assertEquals(Tuple.point(5, -5, 0), p3);

    Point p4 = c.mult(p3).asPoint();
    assertEquals(Tuple.point(15, 0, 7), p4);
  }

  @Test
  public void chainedTransformationsAppliedInReverse() {
    Point p = Tuple.point(1, 0, 1);

    Matrix a = Transform.rotateX(Constants.HALF_PI);
    Matrix b = Transform.scale(5, 5, 5);
    Matrix c = Transform.translate(10, 5, 7);

    Matrix t = c.mult(b).mult(a);

    assertEquals(Tuple.point(15, 0, 7), t.mult(p));
  }
}