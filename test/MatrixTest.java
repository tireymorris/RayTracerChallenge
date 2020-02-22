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
    Tuple b = new Tuple(1, 2, 3, 1);

    assertEquals(new Tuple(18, 24, 33, 1), a.mult(b));
  }

  @Test
  public void multMatrixByIdentity() {
    double[][] rows = { { 0, 1, 2, 4 }, { 1, 2, 4, 8 }, { 2, 4, 8, 16 }, { 4, 8, 16, 32 } };

    Matrix a = Matrix.fromRows(rows);
    Matrix backup = Matrix.fromRows(rows);

    assertEquals(a, a.mult(Matrix.IDENTITY_MATRIX()));
    assertEquals(backup, a.mult(Matrix.IDENTITY_MATRIX()));
  }

  @Test
  public void multIdentityByTuple() {
    Tuple tuple = new Tuple(1, 2, 3, 4);
    Tuple backup = new Tuple(1, 2, 3, 4);

    assertEquals(tuple, Matrix.IDENTITY_MATRIX().mult(tuple));
    assertEquals(backup, Matrix.IDENTITY_MATRIX().mult(tuple));
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
    assertEquals(Matrix.IDENTITY_MATRIX(), Matrix.IDENTITY_MATRIX().transpose());
  }

  @Test
  public void calculate2x2Determinant() {
    double[][] rows = { { 1, 5 }, { -3, 2 } };
    Matrix m = Matrix.fromRows(rows);

    double expected = 1 * 2 - 5 * -3;

    assertEquals(expected, m.determinant(), Constants.EPSILON);
  }

  @Test
  public void submatrix3x3() {
    double[][] rows = { { 1, 5, 0 }, { -3, 2, 7 }, { 0, 6, -3 } };
    Matrix original = Matrix.fromRows(rows);

    double[][] expectedRows = { { -3, 2 }, { 0, 6 } };
    Matrix expected = Matrix.fromRows(expectedRows);

    assertEquals(expected, original.submatrix(0, 2));
  }

  @Test
  public void submatrix4x4() {
    double[][] rows = { { -6, 1, 1, 6 }, { -8, 5, 8, 6 }, { -1, 0, 8, 2 }, { -7, 1, -1, 1 } };
    Matrix original = Matrix.fromRows(rows);

    double[][] expectedRows = { { -6, 1, 6 }, { -8, 8, 6 }, { -7, -1, 1 } };
    Matrix expected = Matrix.fromRows(expectedRows);

    assertEquals(expected, original.submatrix(2, 1));
  }

  @Test
  public void minor3x3() {
    double[][] rows = { { 3, 5, 0 }, { 2, -1, -7 }, { 6, -1, 5 } };
    Matrix a = Matrix.fromRows(rows);

    Matrix b = a.submatrix(1, 0);

    assertEquals(25, b.determinant(), Constants.EPSILON);
    assertEquals(25, a.minor(1, 0), Constants.EPSILON);
  }

  @Test
  public void cofactor3x3() {
    double[][] rows = { { 3, 5, 0 }, { 2, -1, -7 }, { 6, -1, 5 } };
    Matrix a = Matrix.fromRows(rows);

    assertEquals(-12, a.minor(0, 0), Constants.EPSILON);
    assertEquals(-12, a.cofactor(0, 0), Constants.EPSILON);
    assertEquals(25, a.minor(1, 0), Constants.EPSILON);
    assertEquals(-25, a.cofactor(1, 0), Constants.EPSILON);
  }

  @Test
  public void determinant3x3() {
    double[][] rows = { { 1, 2, 6 }, { -5, 8, -4 }, { 2, 6, 4 } };
    Matrix a = Matrix.fromRows(rows);

    assertEquals(56, a.cofactor(0, 0), Constants.EPSILON);
    assertEquals(12, a.cofactor(0, 1), Constants.EPSILON);
    assertEquals(-46, a.cofactor(0, 2), Constants.EPSILON);

    assertEquals(-196, a.determinant(), Constants.EPSILON);
  }

  @Test
  public void determinant4x4() {
    double[][] rows = { { -2, -8, 3, 5 }, { -3, 1, 7, 3 }, { 1, 2, -9, 6 }, { -6, 7, 7, -9 } };
    Matrix a = Matrix.fromRows(rows);

    assertEquals(690, a.cofactor(0, 0), Constants.EPSILON);
    assertEquals(447, a.cofactor(0, 1), Constants.EPSILON);
    assertEquals(210, a.cofactor(0, 2), Constants.EPSILON);
    assertEquals(51, a.cofactor(0, 3), Constants.EPSILON);

    assertEquals(-4071, a.determinant(), Constants.EPSILON);
  }

  @Test
  public void invertible1() {
    double[][] rows = { { 6, 4, 4, 4 }, { 5, 5, 7, 6 }, { 4, -9, 3, -7 }, { 9, 1, 7, -6 } };
    Matrix a = Matrix.fromRows(rows);

    assertEquals(-2120, a.determinant(), Constants.EPSILON);
    assertTrue(a.invertible());
  }

  @Test
  public void invertible2() {
    double[][] rows = { { 4, 2, -2, -3 }, { 9, 6, 2, 6 }, { 0, -5, 1, -5 }, { 0, 0, 0, 0 } };
    Matrix a = Matrix.fromRows(rows);

    assertEquals(0, a.determinant(), Constants.EPSILON);
    assertFalse(a.invertible());
  }

  @Test
  public void inverse4x4_1() {
    double[][] rows = { { -5, 2, 6, -8 }, { 1, -5, 1, 8 }, { 7, 7, -6, -7 }, { 1, -3, 7, 4 } };
    Matrix a = Matrix.fromRows(rows);

    Matrix b = a.inverse();

    double[][] invertedRows = { { 0.21805, 0.45113, 0.24060, -0.04511 }, { -0.80827, -1.45677, -0.44361, 0.52068 },
        { -0.07895, -0.22368, -0.05263, 0.19737 }, { -0.52256, -0.81391, -0.30075, 0.30639 } };
    Matrix inverse = Matrix.fromRows(invertedRows);

    assertEquals(532, a.determinant(), Constants.EPSILON);
    assertEquals(-160, a.cofactor(2, 3), Constants.EPSILON);
    assertEquals(105, a.cofactor(3, 2), Constants.EPSILON);

    assertEquals(-160.0 / 532, b.get(3, 2), Constants.EPSILON);
    assertEquals(105.0 / 532, b.get(2, 3), Constants.EPSILON);

    assertEquals(inverse, b);
  }

  @Test
  public void inverse4x4_2() {
    double[][] rows = { { 8, -5, 9, 2 }, { 7, 5, 6, 1 }, { -6, 0, 9, 6 }, { -3, 0, -9, -4 } };
    Matrix a = Matrix.fromRows(rows);

    Matrix b = a.inverse();

    double[][] invertedRows = { { -0.15385, -0.15385, -0.28205, -0.53846 }, { -0.07692, 0.12308, 0.02564, 0.03077 },
        { 0.35897, 0.35897, 0.43590, 0.92308 }, { -0.69231, -0.69231, -0.76923, -1.92308 } };
    Matrix inverse = Matrix.fromRows(invertedRows);

    assertEquals(inverse, b);
  }

  @Test
  public void inverse4x4_3() {
    double[][] rows = { { 9, 3, 0, 9 }, { -5, -2, -6, -3 }, { -4, 9, 6, 4 }, { -7, 6, 6, 2 } };
    Matrix a = Matrix.fromRows(rows);

    Matrix b = a.inverse();

    double[][] invertedRows = { { -0.04074, -0.07778, 0.14444, -0.22222 }, { -0.07778, 0.03333, 0.36667, -0.33333 },
        { -0.02901, -0.14630, -0.10926, 0.12963 }, { 0.17778, 0.06667, -0.26667, 0.33333 } };
    Matrix inverse = Matrix.fromRows(invertedRows);

    assertEquals(inverse, b);
  }

  @Test
  public void inverseMultTransitive() {
    double[][] aRows = { { 3, -9, 7, 3 }, { 3, -8, 2, -9 }, { -4, 4, 4, 1 }, { -6, 5, -1, 1 } };
    Matrix a = Matrix.fromRows(aRows);

    double[][] bRows = { { 8, 2, 2, 2 }, { 3, -1, 7, 0 }, { 7, 0, 5, 4 }, { 6, -2, 0, 5 } };
    Matrix b = Matrix.fromRows(bRows);

    Matrix c = a.mult(b);

    assertEquals(a, c.mult(b.inverse()));
  }

  @Test
  public void multByInverseGivesIdentity() {
    double[][] bRows = { { 8, 2, 2, 2 }, { 3, -1, 7, 0 }, { 7, 0, 5, 4 }, { 6, -2, 0, 5 } };
    Matrix b = Matrix.fromRows(bRows);

    assertEquals(Matrix.IDENTITY_MATRIX(), b.mult(b.inverse()));
  }

  @Test
  public void transposeAndInverseCommutative() {
    double[][] aRows = { { 3, -9, 7, 3 }, { 3, -8, 2, -9 }, { -4, 4, 4, 1 }, { -6, 5, -1, 1 } };
    Matrix a = Matrix.fromRows(aRows);

    assertEquals(a.transpose().inverse(), a.inverse().transpose());
  }
}