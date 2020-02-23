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
    Pattern p = new StripePattern(Color.WHITE(), Color.BLACK());

    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 0, 0)));
    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 1, 0)));
    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 2, 0)));
  }

  @Test
  public void stripeConstantInZ() {
    Pattern p = new StripePattern(Color.WHITE(), Color.BLACK());

    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 0, 0)));
    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 0, 1)));
    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 0, 2)));
  }

  @Test
  public void stripeAlternatesInX() {
    Pattern p = new StripePattern(Color.WHITE(), Color.BLACK());

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

    Pattern p = new StripePattern(Color.WHITE(), Color.BLACK());

    assertEquals(Color.WHITE(), p.patternAtObject(o, new Point(1.5, 0, 0)));
  }

  @Test
  public void stripesWithPatternTransformation() {
    Entity o = new Sphere();
    Pattern p = new StripePattern(Color.WHITE(), Color.BLACK());
    p.setTransform(Transform.identity().scale(2, 2, 2));

    assertEquals(Color.WHITE(), p.patternAtObject(o, new Point(1.5, 0, 0)));
  }

  @Test
  public void stripedWithBothTransforms() {
    Entity o = new Sphere();
    o.setTransform(Transform.identity().scale(2, 2, 2));

    Pattern p = new StripePattern(Color.WHITE(), Color.BLACK());
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

    Pattern p = new TestPattern();

    assertEquals(new Color(1, 1.5, 2), p.patternAtObject(o, new Point(2, 3, 4)));
  }

  @Test
  public void testPatternWithPatternTransformation() {
    Entity o = new Sphere();
    Pattern p = new TestPattern();
    p.setTransform(Transform.identity().scale(2, 2, 2));

    assertEquals(new Color(1, 1.5, 2), p.patternAtObject(o, new Point(2, 3, 4)));
  }

  @Test
  public void testPatternWithBothTransforms() {
    Entity o = new Sphere();
    o.setTransform(Transform.identity().scale(2, 2, 2));

    Pattern p = new TestPattern();
    p.setTransform(Transform.identity().translate(0.5, 1, 1.5));

    assertEquals(new Color(0.75, 0.5, 0.25), p.patternAtObject(o, new Point(2.5, 3, 3.5)));
  }

  @Test
  public void createGradientPattern() {
    Pattern p = new GradientPattern(Color.WHITE(), Color.BLACK());

    assertEquals(Color.WHITE(), p.patternAt(new Point(0, 0, 0)));
    assertEquals(new Color(0.75, 0.75, 0.75), p.patternAt(new Point(0.25, 0, 0)));
    assertEquals(new Color(0.5, 0.5, 0.5), p.patternAt(new Point(0.5, 0, 0)));
    assertEquals(new Color(0.25, 0.25, 0.25), p.patternAt(new Point(0.75, 0, 0)));
  }

}