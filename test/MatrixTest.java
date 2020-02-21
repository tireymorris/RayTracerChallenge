import static org.junit.Assert.*;
import org.junit.Test;

public class MatrixTest {
  @Test(expected = IndexOutOfBoundsException.class)
  public void invalidMatrixThrows() {
    double[][] rows = { { -3, 5, 5 }, { 1 } };

    Matrix.fromRows(rows);
  }

  @Test
  public void construct4x4Matrix() {
    double[][] rows = { { 1, 2, 3, 4 }, { 5.5, 6.5, 7.5, 8.5 }, { 9, 10, 11, 12 }, { 13.5, 14.5, 15.5, 16.5 } };

    Matrix m = Matrix.fromRows(rows);

    assertEquals(1, m.get(0, 0), Constants.EPSILON);
    assertEquals(4, m.get(0, 3), Constants.EPSILON);
    assertEquals(5.5, m.get(1, 0), Constants.EPSILON);
    assertEquals(7.5, m.get(1, 2), Constants.EPSILON);
    assertEquals(11, m.get(2, 2), Constants.EPSILON);
    assertEquals(13.5, m.get(3, 0), Constants.EPSILON);
    assertEquals(15.5, m.get(3, 2), Constants.EPSILON);
  }

  @Test
  public void construct2x2Matrix() {
    double[][] rows = { { -3, 5 }, { 1, -2 } };

    Matrix m = Matrix.fromRows(rows);

    assertEquals(-3, m.get(0, 0), Constants.EPSILON);
    assertEquals(5, m.get(0, 1), Constants.EPSILON);
    assertEquals(1, m.get(1, 0), Constants.EPSILON);
    assertEquals(-2, m.get(1, 1), Constants.EPSILON);
  }

  @Test
  public void construct3x3Matrix() {
    double[][] rows = { { -3, 5, 0 }, { 1, -2, -7 }, { 0, 1, 1 } };
    Matrix m = Matrix.fromRows(rows);

    assertEquals(-3, m.get(0, 0), Constants.EPSILON);
    assertEquals(-2, m.get(1, 1), Constants.EPSILON);
    assertEquals(1, m.get(2, 2), Constants.EPSILON);
  }

  @Test
  public void matrixEquality() {
    double[][] rows1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 8, 7, 6 }, { 5, 4, 3, 2 } };
    double[][] rows2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 8, 7, 6 }, { 5, 4, 3, 2 } };

    Matrix m1 = Matrix.fromRows(rows1);
    Matrix m2 = Matrix.fromRows(rows2);

    assertEquals(m1, m2);
  }

  @Test
  public void matrixInequality() {
    double[][] rows1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 8, 7, 6 }, { 5, 4, 3, 2 } };
    double[][] rows2 = { { 2, 3, 4, 5 }, { 6, 7, 8, 9 }, { 9, 8, 7, 6 }, { 5, 4, 3, 2 } };

    Matrix m1 = Matrix.fromRows(rows1);
    Matrix m2 = Matrix.fromRows(rows2);

    assertNotEquals(m1, m2);
  }

  @Test
  public void matrixMultiply() {
    double[][] rows1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 8, 7, 6 }, { 5, 4, 3, 2 } };
    double[][] rows2 = { { -2, 1, 2, 3 }, { 3, 2, 1, -1 }, { 4, 3, 6, 5 }, { 1, 2, 7, 8 } };

    Matrix m1 = Matrix.fromRows(rows1);
    Matrix m2 = Matrix.fromRows(rows2);

    double[][] expected = { { 20, 22, 50, 48 }, { 44, 54, 114, 108 }, { 40, 58, 110, 102 }, { 16, 26, 46, 42 } };

    assertEquals(Matrix.fromRows(expected), m1.mult(m2));
  }
}