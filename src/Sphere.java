public class Sphere extends Entity {
  public double radius;

  public Sphere() {
    super(Tuple.point(0, 0, 0), new Material(), Transform.identity());
    this.radius = 1;
  }

  public Transform getTransform() {
    return this.transform;
  }

  public void setTransform(Transform transform) {
    this.transform = transform;
  }

  public Vector normalAt(Point worldPoint) {
    Point objectPoint = this.transform.build().inverse().mult(worldPoint);

    Vector objectNormal = objectPoint.minus(Tuple.point(0, 0, 0));

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

}