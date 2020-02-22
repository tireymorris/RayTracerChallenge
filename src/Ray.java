public class Ray {
  Point origin;
  Vector direction;

  public Ray(Point origin, Vector direction) {
    this.origin = origin;
    this.direction = direction;
  }

  // comes in handy when turning intersections into actual surface information
  // and computing shading
  public Point position(double t) {
    return this.origin.plus(this.direction.scale(t));
  }

  public Ray transform(Matrix tMatrix) {
    Ray result = new Ray(this.origin.clone().asPoint(), this.direction.clone().asVector());

    result.origin = tMatrix.mult(result.origin).asPoint();
    result.direction = tMatrix.mult(result.direction).asVector();

    return result;
  }
}