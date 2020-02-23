package graphics;

import structures.*;

public class CheckerPattern extends Pattern {
  public Color a;
  public Color b;

  public CheckerPattern(Color a, Color b) {
    super();

    this.a = a;
    this.b = b;
  }

  // basic blending function
  @Override
  public Color patternAt(Point patternSpacePoint) {
    if (Math.floor((Math.abs(patternSpacePoint.x) + Math.abs(patternSpacePoint.y) + Math.abs(patternSpacePoint.z)))
        % 2 == 0) {
      return a;
    }

    return b;
  }

}