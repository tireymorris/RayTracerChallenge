public class IntersectionComputations {
  boolean inside = false;
  double t;

  Entity entity;
  Point point;
  Vector eyeVector;
  Vector normalVector;

  public IntersectionComputations(Intersection intersection, Ray ray) {
    t = intersection.t;
    entity = intersection.entity;

    point = ray.position(t);
    eyeVector = ray.direction.scale(-1);
    normalVector = this.entity.normalAt(point);

    if (normalVector.dot(eyeVector) < 0) {
      inside = true;
      normalVector = normalVector.scale(-1);
    }
  }
}