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

  // See "Line-sphere intersection on Wikipedia"
  // Ray-Sphere Intersection on Lighthouse3d
  // Ray-Sphere Intersection from Scratchapixel's "Minimal Ray Tracer"
  // assumes we're looking at a sphere centered at the origin with radius 1
  public Intersection[] intersect(Sphere sphere) {
    Intersection[] intersections = new Intersection[2];

    // Vector from the sphere's center to the ray origin
    // idea is that sphere is centered at the world origin
    Vector sphereToRay = this.origin.minus(Tuple.point(0, 0, 0));

    double a = this.direction.dot(this.direction);
    double b = 2 * this.direction.dot(sphereToRay);
    double c = sphereToRay.dot(sphereToRay) - 1;

    double discriminant = b * b - 4 * a * c;

    // if discriminant < 0, ray misses without any intersections
    if (discriminant < 0) {
      return intersections;
    }

    double t1_t = (-b - Math.sqrt(discriminant)) / (2 * a);
    double t2_t = (-b + Math.sqrt(discriminant)) / (2 * a);

    double t1 = Math.min(t1_t, t2_t);
    double t2 = Math.max(t1_t, t2_t);

    intersections[0] = (new Intersection(t1, sphere));
    intersections[1] = (new Intersection(t2, sphere));

    return intersections;
  }
}