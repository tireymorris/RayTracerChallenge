public abstract class Entity {
  public Point origin;
  public Material material;

  public Entity(Point origin, Material material) {
    this.origin = origin;
    this.material = material;
  }
}