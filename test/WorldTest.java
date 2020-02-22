import static org.junit.Assert.*;
import org.junit.Test;

public class WorldTest {
  @Test
  public void createWorld() {
    World w = World.createWorld();

    assertEquals(0, w.entities.size());
    assertNull(w.lightSource);
  }

  @Test
  public void defaultWorld() {
    Light light = Light.pointLight(new Point(-10, 10, -10), new Color(1, 1, 1));
    Sphere s1 = new Sphere().withMaterial(new Material(new Color(0.8, 1.0, 0.6), 0.1, 0.7, 0.2, 200.0));
    Sphere s2 = new Sphere().withTransform(Transform.identity().scale(0.5, 0.5, 0.5));

    World defaultWorld = World.defaultWorld();
    assertEquals(light.intensity, defaultWorld.lightSource.intensity);
    assertEquals(light.position, defaultWorld.lightSource.position);

    assertFalse(defaultWorld.entities.contains(s1));
    assertFalse(defaultWorld.entities.contains(s2));

    Sphere ent1 = (Sphere) defaultWorld.getEntity(0);
    Sphere ent2 = (Sphere) defaultWorld.getEntity(1);

    assertEquals(s1.material, ent1.material);
    assertEquals(s1.origin, ent1.origin);
    assertEquals(s1.radius, ent1.radius, Constants.EPSILON);
    assertEquals(s1.transform.build(), ent1.transform.build());

    assertEquals(s2.material, ent2.material);
    assertEquals(s2.origin, ent2.origin);
    assertEquals(s2.radius, ent2.radius, Constants.EPSILON);
    assertEquals(s2.transform.build(), ent2.transform.build());
  }

  @Test
  public void rayIntersectsWithWorld() {
    World w = World.defaultWorld();
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

    Intersection[] xs = w.intersections(r);

    assertEquals(4, xs.length);
    assertEquals(4, xs[0].t, Constants.EPSILON);
    assertEquals(4.5, xs[1].t, Constants.EPSILON);
    assertEquals(5.5, xs[2].t, Constants.EPSILON);
    assertEquals(6, xs[3].t, Constants.EPSILON);
  }

  @Test
  public void shadeIntersection() {
    World w = World.defaultWorld();
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

    Entity s = w.getEntity(0);
    Intersection i = new Intersection(4, s);

    IntersectionComputations comps = new IntersectionComputations(i, r);
    Color c = w.shadeHit(comps);

    assertEquals(new Color(0.38066, 0.47583, 0.2855), c);
  }

  @Test
  public void shadeIntersectionFromInside() {
    World w = World.defaultWorld();
    w.lightSource = Light.pointLight(new Point(0, 0.25, 0), new Color(1, 1, 1));
    Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

    Entity s = w.getEntity(1);
    Intersection i = new Intersection(0.5, s);

    IntersectionComputations comps = new IntersectionComputations(i, r);
    Color c = w.shadeHit(comps);

    assertEquals(new Color(0.90498, 0.90498, 0.90498), c);
  }

  @Test
  public void colorAtMissIsBlack() {
    World w = World.defaultWorld();
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 1, 0));

    assertEquals(Color.BLACK(), w.colorAt(r));
  }

  @Test
  public void colorAtHit() {
    World w = World.defaultWorld();
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

    assertEquals(new Color(0.38066, 0.47583, 0.2855), w.colorAt(r));
  }

  @Test
  public void usesCorrectHit() {
    World w = World.defaultWorld();
    Entity outer = w.getEntity(0);
    Entity inner = w.getEntity(1);

    outer.material.ambient = 1;
    inner.material.ambient = 1;

    Ray r = new Ray(new Point(0, 0, 0.75), new Vector(0, 0, -1));

    assertEquals(inner.material.color, w.colorAt(r));
  }
}