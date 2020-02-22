public class Sphere extends Entity {
  public double radius;

  public Sphere() {
    super(new Point(0, 0, 0), new Material(), Transform.identity());
    this.radius = 1;
  }

  public Vector normalAt(Point worldPoint) {
    Point objectPoint = this.transform.build().inverse().mult(worldPoint);

    Vector objectNormal = objectPoint.minus(new Point(0, 0, 0));

    Vector worldNormal = this.transform.build().inverse().transpose().mult(objectNormal);

    worldNormal.w = 0;

    return worldNormal.normalize();
  }

  public Sphere withMaterial(Material material) {
    return (Sphere) super.withMaterial(material);
  }

  public Sphere withTransform(Transform transform) {
    return (Sphere) super.withTransform(transform);
  }

  // See "Line-sphere intersection on Wikipedia"
  // Ray-Sphere Intersection on Lighthouse3d
  // Ray-Sphere Intersection from Scratchapixel's "Minimal Ray Tracer"
  // assumes we're looking at a sphere centered at the origin with radius 1
  public Intersection[] intersections(Ray ray) {
    // We transform the ray by the inverse of the sphere to achieve the effect
    // of transforming the ray from world space to object space
    // In essence, applying transformations to the ray rather than the sphere
    Ray transformedRay = ray.transform(this.getTransform().build().inverse());
    Intersection[] intersections = new Intersection[2];

    // Vector from the sphere's center to the ray origin
    // idea is that sphere is centered at the world origin
    Vector sphereToRay = transformedRay.origin.minus(new Point(0, 0, 0));

    double a = transformedRay.direction.dot(transformedRay.direction);
    double b = 2.0 * transformedRay.direction.dot(sphereToRay);
    double c = sphereToRay.dot(sphereToRay) - 1;

    double discriminant = b * b - 4.0 * a * c;

    // if discriminant < 0, ray misses without any intersections
    if (discriminant < 0) {
      return intersections;
    }

    double t1_t = (-b - Math.sqrt(discriminant)) / (2.0 * a);
    double t2_t = (-b + Math.sqrt(discriminant)) / (2.0 * a);

    double t1 = Math.min(t1_t, t2_t);
    double t2 = Math.max(t1_t, t2_t);

    intersections[0] = (new Intersection(t1, this));
    intersections[1] = (new Intersection(t2, this));

    return intersections;
  }
}