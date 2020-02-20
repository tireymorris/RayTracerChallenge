public class Point extends Tuple {
  public Point(double x, double y, double z) {
    super(x, y, z, Constants.POINT_W_VALUE);
  }

  // The vector pointing from p2 to p1
  public Vector minus(Point other) {
    return super.subtract(this, other).asVector();
  }

  // represents moving backward by a vector
  public Point minus(Vector other) {
    return super.subtract(this, other).asPoint();
  }

  // represents moving forward by a vector
  public Point plus(Vector other) {
    return super.add(this, other).asPoint();
  }
}