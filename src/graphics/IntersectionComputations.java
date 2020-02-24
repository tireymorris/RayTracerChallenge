package graphics;

import structures.*;
import entities.*;
import static util.Constants.EPSILON;

public class IntersectionComputations {
  public boolean inside = false;
  public double t;

  public Entity entity;
  public Vector eyeVector;
  public Vector normalVector;
  public Vector reflectVector;

  public Point point;
  public Point overPoint;

  public IntersectionComputations(Intersection intersection, Ray ray) {
    t = intersection.t;
    entity = intersection.entity;

    point = ray.position(t);
    eyeVector = ray.direction.scale(-1);
    normalVector = this.entity.normalAt(point);

    if (normalVector.dot(eyeVector) < 0) {
      inside = true;
      normalVector = normalVector.scale(-1);
    }

    overPoint = point.plus(normalVector.scale(EPSILON));
    reflectVector = ray.direction.reflect(normalVector);
  }
}