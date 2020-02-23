package graphics;

import structures.*;

public class GradientPattern extends Pattern {
  public Color a;
  public Color b;

  public GradientPattern(Color a, Color b) {
    super();

    this.a = a;
    this.b = b;
  }

  // basic blending function
  @Override
  public Color patternAt(Point patternSpacePoint) {
    Color distance = b.minus(a);
    double fraction = patternSpacePoint.x - Math.floor(patternSpacePoint.x);

    return a.plus(distance.scale(fraction));
  }

}