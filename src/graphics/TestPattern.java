package graphics;

import structures.*;

public class TestPattern extends Pattern {
  public TestPattern() {
    super();
  }

  @Override
  public Color patternAt(Point patternSpacePoint) {
    return new Color(patternSpacePoint.x, patternSpacePoint.y, patternSpacePoint.z);
  }

}