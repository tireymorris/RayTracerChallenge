import static org.junit.Assert.*;
import org.junit.Test;

import structures.*;
import entities.*;

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
}