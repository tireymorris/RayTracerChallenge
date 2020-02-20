public class Tuple {
  public static final double FLOATING_POINT_DELTA = 0.0000001;
  public static final double VECTOR_W_VALUE = 0.0;
  public static final double POINT_W_VALUE = 1.0;

  public double x;
  public double y;
  public double z;
  public double w;

  public Tuple(double x, double y, double z, double w) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  // TODO: Move to dedicated Math module
  private boolean valuesAlmostEqual(double valueOne, double valueTwo) {
    return Math.abs(valueOne - valueTwo) < FLOATING_POINT_DELTA;
  }

  public boolean isVector() {
    return valuesAlmostEqual(this.w, VECTOR_W_VALUE);
  }

  public boolean isPoint() {
    return valuesAlmostEqual(this.w, POINT_W_VALUE);
  }

  public static Tuple add(Tuple t1, Tuple t2) {
    return tuple(t1.x + t2.x, t1.y + t2.y, t1.z + t2.z, t1.w + t2.w);
  }

  public static Tuple subtract(Tuple t1, Tuple t2) {
    return tuple(t1.x - t2.x, t1.y - t2.y, t1.z - t2.z, t1.w - t2.w);
  }

  public static Tuple tuple(double x, double y, double z, double w) {
    return new Tuple(x, y, z, w);
  }

  public static Tuple point(double x, double y, double z) {
    return tuple(x, y, z, POINT_W_VALUE);
  }

  public static Tuple vector(double x, double y, double z) {
    return tuple(x, y, z, VECTOR_W_VALUE);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Tuple)) {
      return false;
    }

    Tuple otherTuple = (Tuple) other;

    return valuesAlmostEqual(this.x, otherTuple.x) && valuesAlmostEqual(this.y, otherTuple.y)
        && valuesAlmostEqual(this.z, otherTuple.z) && valuesAlmostEqual(this.w, otherTuple.w);
  }
}