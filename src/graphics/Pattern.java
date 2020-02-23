package graphics;

import entities.Entity;
import structures.*;

public abstract class Pattern {
  public Transform transform;

  public Pattern() {
    this.transform = Transform.identity();
  }

  public abstract Color patternAt(Point patternSpacePoint);

  public Color patternAtObject(Entity object, Point worldPoint) {
    Point localPoint = object.transform.build().inverse().mult(worldPoint);
    Point patternSpacePoint = this.transform.build().inverse().mult(localPoint);

    return patternAt(patternSpacePoint);
  }

  public void setTransform(Transform transform) {
    this.transform = transform;
  }

  public Pattern withTransform(Transform transform) {
    setTransform(transform);

    return this;
  }
}