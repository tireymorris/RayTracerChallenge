import static org.junit.Assert.*;
import org.junit.Test;

import structures.*;
import util.Constants;

public class TransformTest {
  @Test
  public void translationMatrixMultiply() {
    Matrix t = Transformations.translation(5, -3, 2);
    Point p = new Point(-3, 4, 5);

    assertEquals(new Point(2, 1, 7), t.mult(p));
  }

  @Test
  public void inverseTranslationMatrixMultiply() {
    Matrix inv = Transformations.translation(5, -3, 2).inverse();
    Point p = new Point(-3, 4, 5);

    assertEquals(new Point(-8, 7, 3), inv.mult(p));
  }

  @Test
  public void vectorIsNotTranslated() {
    Matrix t = Transformations.translation(5, -3, 2);
    Vector v = new Vector(-3, 4, 5);

    assertEquals(v, t.mult(v));
  }

  @Test
  public void scalingPoint() {
    Matrix t = Transformations.scaling(2, 3, 4);
    Point p = new Point(-4, 6, 8);

    assertEquals(new Point(-8, 18, 32), t.mult(p));
  }

  @Test
  public void scalingVector() {
    Matrix t = Transformations.scaling(2, 3, 4);
    Vector v = new Vector(-4, 6, 8);

    assertEquals(new Vector(-8, 18, 32), t.mult(v));
  }

  @Test
  public void inverseScalingVector() {
    Matrix inv = Transformations.scaling(2, 3, 4).inverse();
    Vector v = new Vector(-4, 6, 8);

    assertEquals(new Vector(-2, 2, 2), inv.mult(v));
  }

  @Test
  public void reflectionion() {
    Matrix t = Transformations.scaling(-1, 1, 1);
    Point p = new Point(2, 3, 4);

    assertEquals(new Point(-2, 3, 4), t.mult(p));
    assertEquals(new Point(-2, 3, 4), Transformations.reflectionX().mult(p));
  }

  @Test
  public void rotationX() {
    Point p = new Point(0, 1, 0);
    Matrix halfQuarter = Transformations.rotationX(Constants.QUARTER_PI);
    Matrix fullQuarter = Transformations.rotationX(Constants.HALF_PI);

    assertEquals(new Point(0, Math.sqrt(2) / 2.0, Math.sqrt(2) / 2.0), halfQuarter.mult(p));
    assertEquals(new Point(0, 0, 1), fullQuarter.mult(p));
  }

  @Test
  public void rotationXInverse() {
    Point p = new Point(0, 1, 0);
    Matrix halfQuarter = Transformations.rotationX(Constants.QUARTER_PI);
    Matrix inv = halfQuarter.inverse();

    assertEquals(new Point(0, Math.sqrt(2) / 2.0, -Math.sqrt(2) / 2.0), inv.mult(p));
  }

  @Test
  public void rotationY() {
    Point p = new Point(0, 0, 1);
    Matrix halfQuarter = Transformations.rotationY(Constants.QUARTER_PI);
    Matrix fullQuarter = Transformations.rotationY(Constants.HALF_PI);

    assertEquals(new Point(Math.sqrt(2) / 2.0, 0, Math.sqrt(2) / 2.0), halfQuarter.mult(p));
    assertEquals(new Point(1, 0, 0), fullQuarter.mult(p));
  }

  @Test
  public void rotationZ() {
    Point p = new Point(0, 1, 0);
    Matrix halfQuarter = Transformations.rotationZ(Constants.QUARTER_PI);
    Matrix fullQuarter = Transformations.rotationZ(Constants.HALF_PI);

    assertEquals(new Point(-Math.sqrt(2) / 2.0, Math.sqrt(2) / 2.0, 0), halfQuarter.mult(p));
    assertEquals(new Point(-1, 0, 0), fullQuarter.mult(p));
  }

  @Test
  public void shearingXtoY() {
    Matrix t = Transformations.shearing(1, 0, 0, 0, 0, 0);
    Point p = new Point(2, 3, 4);

    assertEquals(new Point(5, 3, 4), t.mult(p));
  }

  @Test
  public void shearingXtoZ() {
    Matrix t = Transformations.shearing(0, 1, 0, 0, 0, 0);
    Point p = new Point(2, 3, 4);

    assertEquals(new Point(6, 3, 4), t.mult(p));
  }

  @Test
  public void shearingYtoX() {
    Matrix t = Transformations.shearing(0, 0, 1, 0, 0, 0);
    Point p = new Point(2, 3, 4);

    assertEquals(new Point(2, 5, 4), t.mult(p));
  }

  @Test
  public void shearingYtoZ() {
    Matrix t = Transformations.shearing(0, 0, 0, 1, 0, 0);
    Point p = new Point(2, 3, 4);

    assertEquals(new Point(2, 7, 4), t.mult(p));
  }

  @Test
  public void shearingZtoX() {
    Matrix t = Transformations.shearing(0, 0, 0, 0, 1, 0);
    Point p = new Point(2, 3, 4);

    assertEquals(new Point(2, 3, 6), t.mult(p));
  }

  @Test
  public void shearingZtoY() {
    Matrix t = Transformations.shearing(0, 0, 0, 0, 0, 1);
    Point p = new Point(2, 3, 4);

    assertEquals(new Point(2, 3, 7), t.mult(p));
  }

  @Test
  public void individualTransformationssInOrder() {
    Point p = new Point(1, 0, 1);
    Matrix a = Transformations.rotationX(Constants.HALF_PI);
    Matrix b = Transformations.scaling(5, 5, 5);
    Matrix c = Transformations.translation(10, 5, 7);

    Point p2 = a.mult(p).asPoint();
    assertEquals(new Point(1, -1, 0), p2);

    Point p3 = b.mult(p2).asPoint();
    assertEquals(new Point(5, -5, 0), p3);

    Point p4 = c.mult(p3).asPoint();
    assertEquals(new Point(15, 0, 7), p4);
  }

  @Test
  public void chainedTransformationsationsAppliedInReverse() {
    Point p = new Point(1, 0, 1);

    Matrix a = Transformations.rotationX(Constants.HALF_PI);
    Matrix b = Transformations.scaling(5, 5, 5);
    Matrix c = Transformations.translation(10, 5, 7);

    Matrix t = c.mult(b).mult(a);

    assertEquals(new Point(15, 0, 7), t.mult(p));
  }

  @Test
  public void chainedTransformsFluentAPI() {
    Point p = new Point(1, 0, 1);

    Matrix t = Transform.identity().rotateX(Constants.HALF_PI).scale(5, 5, 5).translate(10, 5, 7).build();

    assertEquals(new Point(15, 0, 7), t.mult(p));
  }

  @Test
  public void viewTransformDefaultOrientation() {
    Point from = new Point(0, 0, 0);
    Point to = new Point(0, 0, -1);
    Vector up = new Vector(0, 1, 0);

    Matrix t = Transformations.viewTransformation(from, to, up);

    assertEquals(Matrix.IDENTITY_MATRIX(), t);
  }

  @Test
  public void viewTransformFlipsXAndZ() {
    Point from = new Point(0, 0, 0);
    Point to = new Point(0, 0, 1);
    Vector up = new Vector(0, 1, 0);

    Matrix t = Transformations.viewTransformation(from, to, up);

    assertEquals(Transformations.scaling(-1, 1, -1), t);
  }

  @Test
  public void viewTransformMovesTheWorld() {
    Point from = new Point(0, 0, 8);
    Point to = new Point(0, 0, 0);
    Vector up = new Vector(0, 1, 0);

    Matrix t = Transformations.viewTransformation(from, to, up);

    assertEquals(Transformations.translation(0, 0, -8), t);
  }

  @Test
  public void viewTransformArbitrary() {
    Point from = new Point(1, 3, 2);
    Point to = new Point(4, -2, 8);
    Vector up = new Vector(1, 1, 0);

    Matrix t = Transformations.viewTransformation(from, to, up);

    double[][] rows = { { -0.50709, 0.50709, 0.67612, -2.36643 }, { 0.76772, 0.60609, 0.12122, -2.82843 },
        { -0.35857, 0.59761, -0.71714, 0 }, { 0, 0, 0, 1 } };

    assertEquals(Matrix.fromRows(rows), t);
  }

  @Test
  public void viewTransformMovesTheWorld2() {
    Point from = new Point(0, 0, 8);
    Point to = new Point(0, 0, 0);
    Vector up = new Vector(0, 1, 0);

    Matrix t = Transform.identity().viewTransform(from, to, up).build();

    assertEquals(Transformations.translation(0, 0, -8), t);
  }
}