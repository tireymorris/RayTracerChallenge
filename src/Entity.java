public abstract class Entity {
  public Point origin;
  public Material material;
  public Transform transform;

  public Entity(Point origin, Material material, Transform transform) {
    this.origin = origin;
    this.material = material;
    this.transform = transform;
  }

  public Entity withMaterial(Material material) {
    this.material = material;

    return this;
  }

  public Entity withTransform(Transform transform) {
    this.transform = transform;

    return this;
  }

  public Transform getTransform() {
    return this.transform;
  }

  public void setTransform(Transform transform) {
    this.transform = transform;
  }

  public Intersection[] intersections(Ray ray) {
    Intersection[] xs = {};
    return xs;
  }

  public Vector normalAt(Point worldPoint) {
    throw new NoSuchMethodError();
  }
}