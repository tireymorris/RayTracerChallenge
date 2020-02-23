package graphics;

import structures.*;

public class RingPattern extends Pattern {
  public Color a;
  public Color b;

  public RingPattern(Color a, Color b) {
    super();

    this.a = a;
    this.b = b;
  }

  // basic blending function
  @Override
  public Color patternAt(Point patternSpacePoint) {
    if (Math.floor(Math.sqrt(patternSpacePoint.x * patternSpacePoint.x + patternSpacePoint.z * patternSpacePoint.z))
        % 2 == 0) {
      return a;
    }

    return b;
  }

}