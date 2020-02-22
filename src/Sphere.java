public class Sphere extends Entity {
  Point origin;
  double radius;

  public Sphere(Point origin, double radius) {
    super(origin);
    this.radius = radius;
  }

  public Sphere() {
    this(Tuple.point(0, 0, 0), 1.0);
  }
}