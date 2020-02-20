public class Tuple {
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
    return Math.abs(valueOne - valueTwo) < Constants.EPSILON;
  }

  public boolean isVector() {
    return valuesAlmostEqual(this.w, Constants.VECTOR_W_VALUE);
  }

  public boolean isPoint() {
    return valuesAlmostEqual(this.w, Constants.POINT_W_VALUE);
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
    return tuple(x, y, z, Constants.POINT_W_VALUE);
  }

  public Point asPoint() {
    return new Point(this.x, this.y, this.z);
  }

  public static Tuple vector(double x, double y, double z) {
    return tuple(x, y, z, Constants.VECTOR_W_VALUE);
  }

  public Vector asVector() {
    return new Vector(this.x, this.y, this.z);
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