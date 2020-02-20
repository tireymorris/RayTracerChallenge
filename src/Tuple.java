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

  public boolean isVector() {
    return Constants.valuesAlmostEqual(this.w, Constants.VECTOR_W_VALUE);
  }

  public boolean isPoint() {
    return Constants.valuesAlmostEqual(this.w, Constants.POINT_W_VALUE);
  }

  protected static Tuple add(Tuple t1, Tuple t2) {
    return tuple(t1.x + t2.x, t1.y + t2.y, t1.z + t2.z, t1.w + t2.w);
  }

  protected static Tuple subtract(Tuple t1, Tuple t2) {
    return tuple(t1.x - t2.x, t1.y - t2.y, t1.z - t2.z, t1.w - t2.w);
  }

  public Tuple scale(double scalar) {
    return tuple(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
  }

  public Tuple mult(double scalar) {
    return this.scale(scalar);
  }

  public Tuple div(double scalar) {
    return this.mult(1 / scalar);
  }

  public static Tuple tuple(double x, double y, double z, double w) {
    return new Tuple(x, y, z, w);
  }

  public static Point point(double x, double y, double z) {
    return tuple(x, y, z, Constants.POINT_W_VALUE).asPoint();
  }

  public Point asPoint() {
    return new Point(this.x, this.y, this.z);
  }

  public static Vector vector(double x, double y, double z) {
    return tuple(x, y, z, Constants.VECTOR_W_VALUE).asVector();
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

    return Constants.valuesAlmostEqual(this.x, otherTuple.x) && Constants.valuesAlmostEqual(this.y, otherTuple.y)
        && Constants.valuesAlmostEqual(this.z, otherTuple.z) && Constants.valuesAlmostEqual(this.w, otherTuple.w);
  }
}