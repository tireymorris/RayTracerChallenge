package graphics;

import structures.*;

public class StripePattern extends Pattern {
  public Color a;
  public Color b;

  public StripePattern(Color a, Color b) {
    super();

    this.a = a;
    this.b = b;
  }

  // only changes in the x dimension have an effect on the pattern
  @Override
  public Color patternAt(Point patternSpacePoint) {
    if (Math.floor(patternSpacePoint.x) % 2 == 0) {
      return a;
    }

    return b;
  }

}