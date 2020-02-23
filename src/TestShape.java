public class TestShape extends Entity {
  Ray savedRay;

  public TestShape() {
    super(new Point(0, 0, 0));
  }

  @Override
  public Intersection[] localIntersections(Ray localRay) {
    this.savedRay = localRay;

    return null;
  }

  @Override
  public Vector localNormalAt(Point localPoint) {
    return localPoint.minus(new Point(0, 0, 0));
  }
}