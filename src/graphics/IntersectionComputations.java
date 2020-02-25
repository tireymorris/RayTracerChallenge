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

  public IntersectionComputations(Intersection intersection, Ray ray) {
    this(intersection, ray, null);
  }

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
}