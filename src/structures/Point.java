package structures;

import util.Constants;

public class Point extends Tuple {
  public Point(double x, double y, double z) {
    super(x, y, z, Constants.POINT_W_VALUE);
  }

  // The vector pointing from p2 to p1
  public Vector minus(Point other) {
    return super.minus(other).asVector();
  }

  // represents moving backward by a vector
  public Point minus(Vector other) {
    return super.minus(other).asPoint();
  }

  // represents moving forward by a vector
  public Point plus(Vector other) {
    return super.plus(other).asPoint();
  }
}