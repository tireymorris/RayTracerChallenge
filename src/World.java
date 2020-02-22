import java.util.ArrayList;
import java.util.Arrays;

public class World {
  ArrayList<Entity> entities;
  Light lightSource;

  private World() {
    entities = new ArrayList<>();
  }

  public static World createWorld() {
    return new World();
  }

  public static World defaultWorld() {
    Light light = Light.pointLight(new Point(-10, 10, -10), new Color(1, 1, 1));
    Sphere s1 = new Sphere().withMaterial(new Material(new Color(0.8, 1.0, 0.6), 0.1, 0.7, 0.2, 200.0));
    Sphere s2 = new Sphere().withTransform(Transform.identity().scale(0.5, 0.5, 0.5));

    return createWorld().withEntities(s1, s2).withLightSource(light);
  }

  public void setEntities(Entity... entities) {
    for (Entity ent : entities) {
      this.entities.add(ent);
    }
  }

  public World withEntities(Entity... entities) {
    for (Entity ent : entities) {
      this.entities.add(ent);
    }

    return this;
  }

  public void setLightSource(Light light) {
    this.lightSource = light;
  }

  public World withLightSource(Light light) {
    setLightSource(light);

    return this;
  }

  public Intersection[] intersections(Ray ray) {
    ArrayList<Intersection> intersections = new ArrayList<>();

    for (Entity e : this.entities) {
      intersections.addAll(Arrays.asList(e.intersections(ray)));
    }

    Intersection[] xs = new Intersection[intersections.size()];
    intersections.sort((i1, i2) -> i1.compareTo(i2));

    return intersections.toArray(xs);
  }

}