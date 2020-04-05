package graphics;

import structures.*;
import util.Constants;
import entities.*;
import static util.Constants.EPSILON;

import java.util.ArrayList;
import java.util.List;

public class IntersectionComputations {
  public boolean inside = false;
  public double t;

  public Entity entity;
  public Vector eyeVector;
  public Vector normalVector;
  public Vector reflectVector;

  public Point point;
  public Point overPoint;
  public Point underPoint;

  public double n1;
  public double n2;

  public IntersectionComputations(Intersection intersection, Ray ray, Intersection[] intersections) {
    List<Entity> containers = new ArrayList<>();

    if (intersections == null) {
      intersections = new Intersection[1];
      intersections[0] = intersection;
    }

    for (Intersection i : intersections) {
      boolean isHit = i == Intersection.hit(intersections);

      if (isHit) {
        if (containers.isEmpty()) {
          n1 = Constants.REFRACTION_INDEX_AIR;
        } else {
          n1 = containers.get(containers.size() - 1).material.refractiveIndex;
        }
      }

      if (containers.indexOf(i.entity) >= 0) {
        containers.remove(containers.indexOf(i.entity));
      } else {
        containers.add(i.entity);
      }

      if (isHit) {
        if (containers.isEmpty()) {
          n2 = Constants.REFRACTION_INDEX_AIR;
        } else {
          n2 = containers.get(containers.size() - 1).material.refractiveIndex;
        }
      }
    }

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
    underPoint = point.minus(normalVector.scale(EPSILON));
    reflectVector = ray.direction.reflect(normalVector);
  }

  public double schlick() {
    double cos = this.eyeVector.dot(this.normalVector);

    if (n1 > n2) {
      double n = n1 / n2;
      double sinSquaredT = Math.pow(n, 2) * (1.0 - cos * cos);
      if (sinSquaredT > 1.0) {
        return 1.0;
      }

      double cosT = Math.sqrt(1 - sinSquaredT);

      cos = cosT;
    }

    double r0 = Math.pow((n1 - n2) / (n1 + n2), 2);

    return r0 + (1.0 - r0) * Math.pow((1.0 - cos), 5);
  }

}