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
}