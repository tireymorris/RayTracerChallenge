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
  public void matrixMatrixMultiply() {
    double[][] rows1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 8, 7, 6 }, { 5, 4, 3, 2 } };
    double[][] rows2 = { { -2, 1, 2, 3 }, { 3, 2, 1, -1 }, { 4, 3, 6, 5 }, { 1, 2, 7, 8 } };

    Matrix m1 = Matrix.fromRows(rows1);
    Matrix m2 = Matrix.fromRows(rows2);

    double[][] expected = { { 20, 22, 50, 48 }, { 44, 54, 114, 108 }, { 40, 58, 110, 102 }, { 16, 26, 46, 42 } };

    assertEquals(Matrix.fromRows(expected), m1.mult(m2));
  }

  @Test
  public void matrixTupleMultiply() {
    double[][] rows = { { 1, 2, 3, 4 }, { 2, 4, 4, 2 }, { 8, 6, 4, 1 }, { 0, 0, 0, 1 } };

    Matrix a = Matrix.fromRows(rows);
    Tuple b = Tuple.tuple(1, 2, 3, 1);

    assertEquals(Tuple.tuple(18, 24, 33, 1), a.mult(b));
  }

  @Test
  public void multMatrixByIdentity() {
    double[][] rows = { { 0, 1, 2, 4 }, { 1, 2, 4, 8 }, { 2, 4, 8, 16 }, { 4, 8, 16, 32 } };

    Matrix a = Matrix.fromRows(rows);
    Matrix backup = Matrix.fromRows(rows);

    assertEquals(a, a.mult(Constants.IDENTITY_MATRIX));
    assertEquals(backup, a.mult(Constants.IDENTITY_MATRIX));
  }

  @Test
  public void multIdentityByTuple() {
    Tuple tuple = Tuple.tuple(1, 2, 3, 4);
    Tuple backup = Tuple.tuple(1, 2, 3, 4);

    assertEquals(tuple, Constants.IDENTITY_MATRIX.mult(tuple));
    assertEquals(backup, Constants.IDENTITY_MATRIX.mult(tuple));
  }

  @Test
  public void transposeMatrix() {
    double[][] rows = { { 0, 9, 3, 0 }, { 9, 8, 0, 8 }, { 1, 8, 5, 3 }, { 0, 0, 5, 8 } };
    Matrix a = Matrix.fromRows(rows);

    double[][] tRows = { { 0, 9, 1, 0 }, { 9, 8, 8, 0 }, { 3, 0, 5, 5 }, { 0, 8, 3, 8 } };
    Matrix b = Matrix.fromRows(tRows);

    assertEquals(b, a.transpose());
    assertEquals(a, b.transpose());
    assertEquals(a, a.transpose().transpose());
  }

  @Test
  public void transposeIdentityMatrix() {
    assertEquals(Constants.IDENTITY_MATRIX, Constants.IDENTITY_MATRIX.transpose());
  }

  @Test
  public void calculate2x2Determinant() {
    double[][] rows = { { 1, 5 }, { -3, 2 } };
    Matrix m = Matrix.fromRows(rows);

    double expected = 1 * 2 - 5 * -3;

    assertEquals(expected, m.determinant(), Constants.EPSILON);
  }
}