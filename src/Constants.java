public final class Constants {
  public static final double EPSILON = 0.00001;
  public static final double VECTOR_W_VALUE = 0.0;
  public static final double POINT_W_VALUE = 1.0;

  public static final Color BLACK() {
    return new Color(0, 0, 0);
  }

  public static final Color PURPLE() {
    return new Color(0.4, 0.0627, 0.949);
  }

  public static final Color WHITE() {
    return new Color(1, 1, 1);
  }

  public static final double QUARTER_PI = Math.PI / 4.0;
  public static final double HALF_PI = Math.PI / 2.0;
  public static final double PI = Math.PI;
  public static final double TAU = Math.PI * 2.0;

  public static Matrix IDENTITY_MATRIX() {
    double[][] identityValues = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
    return Matrix.fromRows(identityValues);
  }

  public static boolean valuesAlmostEqual(double valueOne, double valueTwo) {
    return Math.abs(valueOne - valueTwo) < EPSILON;
  }

  public static double constrain(double value, double min, double max) {
    return Math.min(Math.max(value, min), max);
  }

  public static double degrees(double radians) {
    return radians * 180.0 / PI;
  }

  public static double radians(double degrees) {
    return degrees = PI / 180.0;
  }
}