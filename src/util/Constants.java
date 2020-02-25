package util;

public final class Constants {
  public static final double EPSILON = 0.0001;
  public static final double VECTOR_W_VALUE = 0.0;
  public static final double POINT_W_VALUE = 1.0;

  public static final double QUARTER_PI = Math.PI / 4.0;
  public static final double HALF_PI = Math.PI / 2.0;
  public static final double PI = Math.PI;
  public static final double TAU = Math.PI * 2.0;

  public static final double REFRACTION_INDEX_VACUUM = 1;
  public static final double REFRACTION_INDEX_AIR = 1.00029;
  public static final double REFRACTION_INDEX_WATER = 1.333;
  public static final double REFRACTION_INDEX_GLASS = 1.52;
  public static final double REFRACTION_INDEX_DIAMOND = 2.417;

  public static final int INITIAL_REFLECTIONS = 5;

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