public class Sphere extends Entity {
  Point origin;
  double radius;

  private Transform transform;

  public Sphere(Point origin, double radius) {
    super(origin);
    this.radius = radius;

    this.transform = Transform.identity();
  }

  public Sphere() {
    this(Tuple.point(0, 0, 0), 1.0);
  }

  public Transform getTransform() {
    return this.transform;
  }

  public void setTransform(Transform transform) {
    this.transform = transform;
  }
}