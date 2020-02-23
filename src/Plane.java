public class Plane extends Entity {
  public Plane() {
    super(new Point(0, 0, 0));
  }

  @Override
  public Intersection[] localIntersections(Ray localRay) {
    if (Constants.valuesAlmostEqual(localRay.direction.y, 0)) {
      Intersection[] empty = {};
      return empty;
    }

    double t = -localRay.origin.y / localRay.direction.y;
    Intersection intersection = new Intersection(t, this);

    Intersection[] intersections = new Intersection[1];
    intersections[0] = intersection;

    return intersections;
  }

  @Override
  public Vector localNormalAt(Point localPoint) {
    // all our planes are xz planes
    return new Vector(0, 1, 0);
  }

}