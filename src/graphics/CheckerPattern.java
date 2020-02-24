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

  private int index(double coordinate) {
    return (int) Math.floor(Math.abs(coordinate));
  }

  // basic blending function
  @Override
  public Color patternAt(Point patternSpacePoint) {
    if ((index(patternSpacePoint.x) + index(patternSpacePoint.y) + index(patternSpacePoint.z)) % 2 == 0) {
      return a;
    }

    return b;
  }

}