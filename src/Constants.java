public final class Constants {
  public static final double EPSILON = 0.00001;
  public static final double VECTOR_W_VALUE = 0.0;
  public static final double POINT_W_VALUE = 1.0;

  public static final Vector ZERO_VECTOR = new Vector(0, 0, 0);
  public static final Vector I_VECTOR = new Vector(1, 0, 0);
  public static final Vector J_VECTOR = new Vector(0, 1, 0);
  public static final Vector K_VECTOR = new Vector(0, 0, 1);

  public static Color PURPLE = Tuple.color(0.4, 0.0627, 0.949);

  private static double[][] identityValues = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
  public static Matrix IDENTITY_MATRIX = Matrix.fromRows(identityValues);

  public static boolean valuesAlmostEqual(double valueOne, double valueTwo) {
    return Math.abs(valueOne - valueTwo) < EPSILON;
  }

  public static double constrain(double value, double min, double max) {
    return Math.min(Math.max(value, min), max);
  }
}