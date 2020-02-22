import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class World {
  public List<Entity> entities;
  public Light lightSource;

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

  public Entity getEntity(int index) {
    return entities.get(index);
  }

  public void setEntity(int index, Entity ent) {
    entities.set(index, ent);
  }

  public void addEntity(Entity ent) {
    entities.add(ent);
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
    lightSource = light;
  }

  public World withLightSource(Light light) {
    setLightSource(light);

    return this;
  }

  public Intersection[] intersections(Ray ray) {
    List<Intersection> intersections = new ArrayList<>();

    for (Entity e : entities) {
      intersections.addAll(Arrays.asList(e.intersections(ray)));
    }
    intersections = intersections.stream().filter(x -> x != null).collect(Collectors.toList());

    Intersection[] xs = new Intersection[intersections.size()];
    intersections.sort((i1, i2) -> i1.compareTo(i2));

    return intersections.toArray(xs);
  }

  public Color shadeHit(IntersectionComputations comps) {
    return Light.lighting(comps.entity.material, lightSource, comps.point, comps.eyeVector, comps.normalVector);
  }

  public Color colorAt(Ray ray) {
    Intersection[] intersections = intersections(ray);

    if (intersections.length == 0) {
      return Color.BLACK();
    }

    Intersection hit = Intersection.hit(intersections);

    if (hit == null) {
      return Color.BLACK();
    }

    return shadeHit(new IntersectionComputations(hit, ray));
  }
}