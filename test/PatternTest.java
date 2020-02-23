import static org.junit.Assert.*;

import org.junit.Test;

import entities.*;
import structures.*;
import graphics.*;

public class PatternTest {
  @Test
  public void createStripePattern() {
    Pattern p = new StripePattern(Color.WHITE(), Color.BLACK());
  }

  @Test
  public void stripeConstantInY() {
    StripePattern p = new StripePattern(Color.WHITE(), Color.BLACK());

    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 0, 0)));
    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 1, 0)));
    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 2, 0)));
  }

  @Test
  public void stripeConstantInZ() {
    StripePattern p = new StripePattern(Color.WHITE(), Color.BLACK());

    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 0, 0)));
    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 0, 1)));
    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 0, 2)));
  }

  @Test
  public void stripeAlternatesInX() {
    StripePattern p = new StripePattern(Color.WHITE(), Color.BLACK());

    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 0, 0)));
    assertEquals(Color.WHITE(), p.patternAt(new Point(0.9, 0, 0)));
    assertEquals(Color.BLACK(), p.patternAt(new Point(1, 0, 0)));
    assertEquals(Color.BLACK(), p.patternAt(new Point(-0.1, 0, 0)));
    assertEquals(Color.BLACK(), p.patternAt(new Point(-1, 0, 0)));
    assertEquals(Color.WHITE(), p.patternAt(new Point(-1.1, 0, 0)));
  }

  @Test
  public void stripesWithObjectTransformation() {
    Entity o = new Sphere();
    o.setTransform(Transform.identity().scale(2, 2, 2));

    StripePattern p = new StripePattern(Color.WHITE(), Color.BLACK());

    assertEquals(Color.WHITE(), p.patternAtObject(o, new Point(1.5, 0, 0)));
  }

  @Test
  public void stripesWithPatternTransformation() {
    Entity o = new Sphere();
    StripePattern p = new StripePattern(Color.WHITE(), Color.BLACK());
    p.setTransform(Transform.identity().scale(2, 2, 2));

    assertEquals(Color.WHITE(), p.patternAtObject(o, new Point(1.5, 0, 0)));
  }

  @Test
  public void stripedWithBothTransforms() {
    Entity o = new Sphere();
    o.setTransform(Transform.identity().scale(2, 2, 2));

    StripePattern p = new StripePattern(Color.WHITE(), Color.BLACK());
    p.setTransform(Transform.identity().translate(0.5, 0, 0));

    assertEquals(Color.WHITE(), p.patternAtObject(o, new Point(2.5, 0, 0)));
  }
}