package structures;

import util.Constants;

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

  public Tuple clone() {
    return new Tuple(this.x, this.y, this.z, this.w);
  }

  public Tuple plus(Tuple other) {
    return new Tuple(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w);
  }

  public Tuple minus(Tuple other) {
    return new Tuple(this.x - other.x, this.y - other.y, this.z - other.z, this.w - other.w);
  }

  public Tuple scale(double scalar) {
    return new Tuple(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
  }

  public Tuple div(double scalar) {
    return this.scale(1 / scalar);
  }

  public Point asPoint() {
    return new Point(this.x, this.y, this.z);
  }

  public Vector asVector() {
    return new Vector(this.x, this.y, this.z);
  }

  public static Color color(double r, double g, double b) {
    return new Color(r, g, b);
  }

  public Color asColor() {
    return new Color(this.x, this.y, this.z);
  }

  // ensure
  public Tuple constrain(double min, double max) {
    return new Tuple(Constants.constrain(this.x, min, max), Constants.constrain(this.y, min, max),
        Constants.constrain(this.z, min, max), Constants.constrain(this.z, min, max));
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

  @Override
  public String toString() {
    return String.format("{ %f, %f, %f, %f }", this.x, this.y, this.z, this.w);
  }
}