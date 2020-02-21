import java.io.InvalidObjectException;
import java.util.Arrays;

// TODO: decide on policy for static methods (some are, some aren't)
public class Matrix {
  public int numRows;
  public int numCols;

  public double[] elements;

  public Matrix(int numRows, int numCols) {
    if (numRows < 1 || numCols < 1) {
      throw new IndexOutOfBoundsException("Need matrix with at least one row and column");
    }

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

  // computes the dot product of every row-column combination in the two matrices
  // will be useful for combining transformations
  public static Matrix mult(Matrix a, Matrix b) {
    if (a.numCols != b.numRows) {
      throw new IndexOutOfBoundsException("Multiplication attempted for non-matching matrices");
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

  public Matrix mult(Matrix other) {
    return Matrix.mult(this, other);
  }

  public static Tuple mult(Matrix a, Tuple b) {
    // TODO: Assumes Tuple is always a quadruple
    Matrix bMatrix = new Matrix(4, 1);

    bMatrix.set(0, 0, b.x);
    bMatrix.set(1, 0, b.y);
    bMatrix.set(2, 0, b.z);
    bMatrix.set(3, 0, b.w);

    Matrix product = Matrix.mult(a, bMatrix);
    Tuple result = Tuple.tuple(product.get(0, 0), product.get(1, 0), product.get(2, 0), product.get(3, 0));

    return result;
  }

  public Tuple mult(Tuple other) {
    return Matrix.mult(this, other);
  }

  public Matrix transpose() {
    Matrix result = new Matrix(this.numRows, this.numCols);
    for (int row = 0; row < this.numRows; row++) {
      for (int col = 0; col < this.numCols; col++) {
        result.set(col, row, this.get(row, col));
      }
    }

    return result;
  }

  // in theory, tells you if system has a solution (nonzero)
  public double determinant() {
    if (this.numRows == 2 && this.numCols == 2) {
      return this.get(0, 0) * this.get(1, 1) - this.get(0, 1) * this.get(1, 0);
    }

    double det = 0;
    // can be computed with row-wise or column-wise
    for (int col = 0; col < this.numCols; col++) {
      det += this.get(0, col) * this.cofactor(0, col);
    }

    return det;
  }

  public Matrix submatrix(int deadRow, int deadCol) {
    // TODO: Better algorithm
    Matrix result = new Matrix(this.numRows - 1, this.numCols - 1);

    int resultRow = 0;
    int resultCol = 0;

    for (int row = 0; row < this.numRows; row++) {
      boolean appended = false;

      for (int col = 0; col < this.numCols; col++) {
        if (row == deadRow || col == deadCol) {
          continue;
        }

        result.set(resultRow, resultCol, this.get(row, col));
        resultCol++;

        appended = true;
      }

      if (appended) {
        resultRow++;
      }
      resultCol = 0;
    }

    return result;
  }

  public double minor(int row, int col) {
    return this.submatrix(row, col).determinant();
  }

  public double cofactor(int row, int col) {
    int sign = (row + col) % 2 == 0 ? 1 : -1;

    return sign * this.minor(row, col);
  }

  // also implies this matrix corresponds to a system with a solution
  public boolean invertible() {
    return this.determinant() != 0;
  }

  // allows reversing the effects of a transformation / matrix multiplication
  public Matrix inverse() {
    if (!this.invertible()) {
      // TODO: Handle exception
      // throw new InvalidObjectException("Matrix is not invertible");
      System.err.println("Matrix is not invertible");
      return this;
    }

    Matrix result = new Matrix(this.numRows, this.numCols);
    double det = this.determinant();

    for (int row = 0; row < this.numRows; row++) {
      for (int col = 0; col < this.numCols; col++) {
        result.set(col, row, this.cofactor(row, col) / det);
      }
    }

    return result;
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();

    for (int row = 0; row < this.numRows; row++) {
      buffer.append("[ ");
      for (int col = 0; col < this.numCols; col++) {
        buffer.append(this.get(row, col));

        if (col < this.numCols - 1) {
          buffer.append(", ");
        }
      }
      buffer.append("]\n");
    }

    return buffer.toString();
  }
}