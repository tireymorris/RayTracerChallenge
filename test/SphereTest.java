import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import structures.*;
import util.Constants;
import entities.*;
import graphics.Intersection;
import graphics.IntersectionComputations;
import graphics.Material;
import graphics.Ray;

public class SphereTest {
  @Test
  public void xAxisNormal() {
    Sphere s = new Sphere();
    Vector n = s.normalAt(new Point(1, 0, 0));

    assertEquals(new Vector(1, 0, 0), n);
  }

  @Test
  public void yAxisNormal() {
    Sphere s = new Sphere();
    Vector n = s.normalAt(new Point(0, 1, 0));

    assertEquals(new Vector(0, 1, 0), n);
  }

  @Test
  public void zAxisNormal() {
    Sphere s = new Sphere();
    Vector n = s.normalAt(new Point(0, 0, 1));

    assertEquals(new Vector(0, 0, 1), n);
  }

  @Test
  public void nonaxialNormal() {
    double magicNumber = Math.sqrt(3) / 3.0;

    Sphere s = new Sphere();
    Vector n = s.normalAt(new Point(magicNumber, magicNumber, magicNumber));

    assertEquals(new Vector(magicNumber, magicNumber, magicNumber), n);
  }

  @Test
  public void nonaxialNormalIsNormalized() {
    double magicNumber = Math.sqrt(3) / 3.0;

    Sphere s = new Sphere();
    Vector n = s.normalAt(new Point(magicNumber, magicNumber, magicNumber));

    assertEquals(new Vector(magicNumber, magicNumber, magicNumber), n);
    assertEquals(new Vector(magicNumber, magicNumber, magicNumber), n.normalize());
  }

  @Test
  public void createGlassySphere() {
    GlassSphere s = new GlassSphere();

    assertEquals(Constants.REFRACTION_INDEX_GLASS, s.material.refractiveIndex, Constants.EPSILON);
  }

  @Test
  public void refractionIndicesMultipleIntersections() {
    Material m1 = new Material();
    m1.refractiveIndex = 1.5;
    Sphere a = new GlassSphere().withTransform(Transform.identity().scale(2, 2, 2)).withMaterial(m1);

    Material m2 = new Material();
    m2.refractiveIndex = 2.0;
    Sphere b = new GlassSphere().withTransform(Transform.identity().translate(0, 0, -0.25)).withMaterial(m2);

    Material m3 = new Material();
    m3.refractiveIndex = 2.5;
    Sphere c = new GlassSphere().withTransform(Transform.identity().translate(0, 0, 0.25)).withMaterial(m3);

    Ray r = new Ray(new Point(0, 0, -4), new Vector(0, 0, 1));

    Intersection[] xs = { new Intersection(2, a), new Intersection(2.75, b), new Intersection(3.25, c),
        new Intersection(4.75, b), new Intersection(5.25, c), new Intersection(6, a) };

    HashMap<Integer, Double[]> testData = new HashMap<>();
    Double[] td1 = { Constants.REFRACTION_INDEX_AIR, 1.5 };
    Double[] td2 = { 1.5, 2.0 };
    Double[] td3 = { 2.0, 2.5 };
    Double[] td4 = { 2.5, 2.5 };
    Double[] td5 = { 2.5, 1.5 };
    Double[] td6 = { 1.5, Constants.REFRACTION_INDEX_AIR };

    testData.put(0, td1);
    testData.put(1, td2);
    testData.put(2, td3);
    testData.put(3, td4);
    testData.put(4, td5);
    testData.put(5, td6);

    for (int i = 0; i < 6; i++) {
      IntersectionComputations comps = new IntersectionComputations(xs[i], r, xs);
      assertEquals((double) td1[0], comps.n1, Constants.EPSILON);
      assertEquals((double) td1[1], comps.n2, Constants.EPSILON);
    }

  }
}