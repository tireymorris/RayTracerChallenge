import java.io.InvalidObjectException;
import java.util.Arrays;

public class Matrix {
  public int numRows;
  public int numCols;

  public double[] elements;

  public Matrix(int numRows, int numCols) {
    this.elements = new double[numRows * numCols];

    this.numRows = numRows;
    this.numCols = numCols;
  }

  public static Matrix fromRows(double[]... rows) {
    Matrix matrix = new Matrix(rows.length, rows[0].length);

    int numCols = -1;
    for (double[] row : rows) {
      if (numCols == -1) {
        numCols = row.length;
      } else if (row.length != numCols) {
        throw new IndexOutOfBoundsException("All matrix rows should have same length");
      }
    }

    matrix.elements = Arrays.asList(rows).stream().flatMapToDouble(Arrays::stream).toArray();

    return matrix;
  }

  private void checkCoordinates(int row, int col) {
    if (row < 0 || row > this.numRows || col < 0 || col > this.numCols) {
      throw new IndexOutOfBoundsException("Matrix index is out of bounds");
    }
  }

  public void set(int row, int col, double value) {
    this.checkCoordinates(row, col);
    this.elements[this.numCols * row + col] = value;
  }

  public double get(int row, int col) {
    this.checkCoordinates(row, col);
    return this.elements[this.numCols * row + col];
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Matrix)) {
      return false;
    }

    Matrix otherMatrix = (Matrix) other;

    if (this.numRows != otherMatrix.numRows || this.numCols != otherMatrix.numCols) {
      return false;
    }

    for (int idx = 0; idx < this.elements.length; idx++) {
      if (!Constants.valuesAlmostEqual(this.elements[idx], otherMatrix.elements[idx])) {
        return false;
      }
    }

    return true;
  }

  public static Matrix mult(Matrix a, Matrix b) {
    if (!(a.numRows == 4 && a.numCols == 4 && b.numRows == 4 && b.numCols == 4)) {
      throw new IndexOutOfBoundsException("Multiplication currently only supported for 4x4 matrices");
    }

    Matrix result = new Matrix(a.numRows, b.numCols);

    for (int row = 0; row < a.numRows; row++) {
      for (int col = 0; col < b.numCols; col++) {
        double product = a.get(row, 0) * b.get(0, col) + a.get(row, 1) * b.get(1, col) + a.get(row, 2) * b.get(2, col)
            + a.get(row, 3) * b.get(3, col);

        result.set(row, col, product);
      }
    }

    return result;
  }

  // will be useful for combining transformations
  public Matrix mult(Matrix other) {
    return Matrix.mult(this, other);
  }
}