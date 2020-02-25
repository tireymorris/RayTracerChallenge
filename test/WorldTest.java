import static org.junit.Assert.*;
import org.junit.Test;

import graphics.*;
import structures.*;
import entities.*;
import util.Constants;
import scene.*;

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
    Color c = w.shadeHit(comps, Constants.INITIAL_REFLECTIONS);

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
    Color c = w.shadeHit(comps, Constants.INITIAL_REFLECTIONS);

    assertEquals(new Color(0.90498, 0.90498, 0.90498), c);
  }

  @Test
  public void colorAtMissIsBlack() {
    World w = World.defaultWorld();
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 1, 0));

    assertEquals(Color.BLACK(), w.colorAt(r, Constants.INITIAL_REFLECTIONS));
  }

  @Test
  public void colorAtHit() {
    World w = World.defaultWorld();
    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

    assertEquals(new Color(0.38066, 0.47583, 0.2855), w.colorAt(r, Constants.INITIAL_REFLECTIONS));
  }

  @Test
  public void usesCorrectHit() {
    World w = World.defaultWorld();
    Entity outer = w.getEntity(0);
    Entity inner = w.getEntity(1);

    outer.material.ambient = 1;
    inner.material.ambient = 1;

    Ray r = new Ray(new Point(0, 0, 0.75), new Vector(0, 0, -1));

    assertEquals(inner.material.color, w.colorAt(r, Constants.INITIAL_REFLECTIONS));
  }

  @Test
  public void noShadowWhenColinear() {
    World w = World.defaultWorld();
    Point p = new Point(0, 10, 0);

    assertFalse(w.isShadowed(p));
  }

  @Test
  public void sphereBetweenPointAndLight() {
    World w = World.defaultWorld();
    Point p = new Point(10, -10, 10);

    assertTrue(w.isShadowed(p));
  }

  @Test
  public void lightBetweenPointAndSphere() {
    World w = World.defaultWorld();
    Point p = new Point(-20, 20, -20);

    assertFalse(w.isShadowed(p));
  }

  @Test
  public void pointBetweenLightAndSphere() {
    World w = World.defaultWorld();
    Point p = new Point(-2, 2, -2);

    assertFalse(w.isShadowed(p));
  }

  @Test
  public void shadeHitInIntersection() {
    World w = World.defaultWorld();
    w.lightSource = Light.pointLight(new Point(0, 0, -10), Color.WHITE());

    Entity s1 = new Sphere();
    w.addEntity(s1);

    Entity s2 = new Sphere().withTransform(Transform.identity().translate(0, 0, 10));
    w.addEntity(s2);

    Ray r = new Ray(new Point(0, 0, 5), new Vector(0, 0, 1));
    Intersection i = new Intersection(4, s2);

    IntersectionComputations comps = new IntersectionComputations(i, r);

    Color c = w.shadeHit(comps, Constants.INITIAL_REFLECTIONS);

    assertEquals(new Color(0.1, 0.1, 0.1), c);
  }

  @Test
  public void rayStrikesNonReflectiveSurface() {
    World w = World.defaultWorld();
    Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
    Entity s = w.getEntity(1);
    s.material.ambient = 1;

    Intersection i = new Intersection(1, s);
    IntersectionComputations comps = new IntersectionComputations(i, r);

    assertEquals(Color.BLACK(), w.reflectedColor(comps, Constants.INITIAL_REFLECTIONS));
  }

  @Test
  public void rayStrikesReflectiveSurface() {
    World w = World.defaultWorld();

    Material reflective = new Material();
    reflective.reflective = 0.5;

    Entity s = new Plane().withMaterial(reflective).withTransform(Transform.identity().translate(0, -1, 0));

    w.addEntity(s);

    Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -Math.sqrt(2) / 2.0, Math.sqrt(2) / 2.0));

    Intersection i = new Intersection(Math.sqrt(2), s);
    IntersectionComputations comps = new IntersectionComputations(i, r);

    assertEquals(new Color(0.19032, 0.2379, 0.14274), w.reflectedColor(comps, Constants.INITIAL_REFLECTIONS));
  }

  @Test
  public void shadeHitReflective() {
    World w = World.defaultWorld();

    Material reflective = new Material();
    reflective.reflective = 0.5;

    Entity s = new Plane().withMaterial(reflective).withTransform(Transform.identity().translate(0, -1, 0));

    w.addEntity(s);

    Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -Math.sqrt(2) / 2.0, Math.sqrt(2) / 2.0));

    Intersection i = new Intersection(Math.sqrt(2), s);
    IntersectionComputations comps = new IntersectionComputations(i, r);

    assertEquals(new Color(0.87677, 0.92436, 0.82918), w.shadeHit(comps, Constants.INITIAL_REFLECTIONS));
  }

  @Test
  public void colorAtMirrors() {
    World w = World.createWorld();

    w.lightSource = Light.pointLight(new Point(0, 0, 0), Color.WHITE());

    Material reflective = new Material();
    reflective.reflective = 1;

    Entity lower = new Plane().withMaterial(reflective).withTransform(Transform.identity().translate(0, -1, 0));

    w.addEntity(lower);

    Entity upper = new Plane().withMaterial(reflective).withTransform(Transform.identity().translate(0, 1, 0));

    w.addEntity(upper);

    Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 1, 0));

    assertNotNull(w.colorAt(r, Constants.INITIAL_REFLECTIONS));
  }

  @Test
  public void limitRecursion() {
    World w = World.defaultWorld();

    Material reflective = new Material();
    reflective.reflective = 0.5;

    Entity s = new Plane().withMaterial(reflective).withTransform(Transform.identity().translate(0, -1, 0));

    w.addEntity(s);

    Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -Math.sqrt(2) / 2.0, Math.sqrt(2) / 2.0));

    Intersection i = new Intersection(Math.sqrt(2), s);
    IntersectionComputations comps = new IntersectionComputations(i, r);

    assertEquals(Color.BLACK(), w.reflectedColor(comps, 0));
  }

  @Test
  public void refractedColorOpaqueObject() {
    World w = World.defaultWorld();

    Entity s = w.getEntity(0);

    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

    Intersection[] xs = { new Intersection(4, s), new Intersection(6, s) };

    IntersectionComputations comps = new IntersectionComputations(xs[0], r, xs);

    Color c = w.refractedColor(comps, 5);

    assertEquals(Color.BLACK(), c);
  }

  @Test
  public void refractedColorAtMaximumDepth() {
    World w = World.defaultWorld();

    Material m = new Material();
    m.transparency = 1;
    m.refractiveIndex = 1.5;
    Entity s = w.getEntity(0).withMaterial(m);

    Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

    Intersection[] xs = { new Intersection(4, s), new Intersection(6, s) };

    IntersectionComputations comps = new IntersectionComputations(xs[0], r, xs);

    Color c = w.refractedColor(comps, 0);

    assertEquals(Color.BLACK(), c);
  }

  @Test
  public void refractedColorAtTIR() {
    World w = World.defaultWorld();

    Material m = new Material();
    m.transparency = 1;
    m.refractiveIndex = 1.5;
    Entity s = w.getEntity(0).withMaterial(m);

    Ray r = new Ray(new Point(0, 0, Math.sqrt(2) / 2), new Vector(0, 1, 0));

    Intersection[] xs = { new Intersection(-Math.sqrt(2) / 2, s), new Intersection(Math.sqrt(2) / 2, s) };

    IntersectionComputations comps = new IntersectionComputations(xs[1], r, xs);

    Color c = w.refractedColor(comps, 5);

    assertEquals(Color.BLACK(), c);
  }

  @Test
  public void findingRefractedColor() {
    World w = World.defaultWorld();

    Material m1 = new Material();
    m1.ambient = 1;
    m1.pattern = new TestPattern();
    Entity s1 = w.getEntity(0).withMaterial(m1);

    Material m2 = new Material();
    m2.transparency = 1;
    m2.refractiveIndex = 1.5;
    Entity s2 = w.getEntity(1).withMaterial(m2);

    Ray r = new Ray(new Point(0, 0, 0.1), new Vector(0, 1, 0));

    Intersection[] xs = { new Intersection(-0.9899, s1), new Intersection(-0.4899, s2), new Intersection(0.4899, s2),
        new Intersection(0.9899, s1) };

    IntersectionComputations comps = new IntersectionComputations(xs[2], r, xs);

    Color c = w.refractedColor(comps, 5);

    assertEquals(new Color(0, 0.99888, 0.04725), c);
  }

  @Test
  public void refractionInShadeHit() {
    World w = World.defaultWorld();

    Material fm = new Material();
    fm.transparency = 0.5;
    fm.refractiveIndex = 1.5;

    Entity floor = new Plane().withMaterial(fm).withTransform(Transform.identity().translate(0, -1, 0));
    w.addEntity(floor);

    Material bm = new Material();
    bm.color = new Color(1, 0, 0);
    bm.ambient = 0.5;
    Entity ball = new Sphere().withMaterial(bm).withTransform(Transform.identity().translate(0, -3.5, -0.5));
    w.addEntity(ball);

    Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -Math.sqrt(2) / 2, Math.sqrt(2) / 2));

    Intersection[] xs = { new Intersection(Math.sqrt(2), floor) };

    IntersectionComputations comps = new IntersectionComputations(xs[0], r, xs);

    Color c = w.shadeHit(comps, 5);

    assertEquals(new Color(0.93642, 0.68642, 0.68642), c);
  }

}