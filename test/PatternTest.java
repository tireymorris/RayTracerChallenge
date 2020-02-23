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

  @Test
  public void defaultPatternTransformation() {
    Pattern pattern = new TestPattern();
    assertEquals(Transform.identity(), pattern.transform);
  }

  @Test
  public void assignPatternTransformation() {
    Pattern pattern = new TestPattern();
    pattern.setTransform(Transform.identity().translate(1, 2, 3));
    assertEquals(Transform.identity().translate(1, 2, 3), pattern.transform);
  }

  @Test
  public void testPatternWithObjectTransformation() {
    Entity o = new Sphere();
    o.setTransform(Transform.identity().scale(2, 2, 2));

    TestPattern p = new TestPattern();

    assertEquals(new Color(1, 1.5, 2), p.patternAtObject(o, new Point(2, 3, 4)));
  }

  @Test
  public void testPatternWithPatternTransformation() {
    Entity o = new Sphere();
    TestPattern p = new TestPattern();
    p.setTransform(Transform.identity().scale(2, 2, 2));

    assertEquals(new Color(1, 1.5, 2), p.patternAtObject(o, new Point(2, 3, 4)));
  }

  @Test
  public void testPatternWithBothTransforms() {
    Entity o = new Sphere();
    o.setTransform(Transform.identity().scale(2, 2, 2));

    TestPattern p = new TestPattern();
    p.setTransform(Transform.identity().translate(0.5, 1, 1.5));

    assertEquals(new Color(0.75, 0.5, 0.25), p.patternAtObject(o, new Point(2.5, 3, 3.5)));
  }

}