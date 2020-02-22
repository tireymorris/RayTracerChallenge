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

    Sphere ent1 = (Sphere) defaultWorld.entities.get(0);
    Sphere ent2 = (Sphere) defaultWorld.entities.get(1);

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
}