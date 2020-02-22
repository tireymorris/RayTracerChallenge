import java.util.Arrays;

public class Intersection implements Comparable {
  double t;
  Entity entity;

  public Intersection(double t, Entity e) {
    this.t = t;
    this.entity = e;
  }

  public static Intersection[] intersections(Intersection... intersections) {
    Arrays.sort(intersections);
    return intersections;
  }

  // Identifies which intersection is actually visible from the rays origin
  // some may be behind the ray, and others may be occluded by other objects
  // hit will never be behind the ray's origin, since that's effectively behind
  // the camera - therefore, we can ignore negative values
  public static Intersection hit(Intersection[] intersections) {
    // Assumes intersections are already sorted!
    for (Intersection intersection : intersections) {
      if (intersection != null && intersection.t >= 0) {
        return intersection;
      }
    }

    return null;
  }

  @Override
  public int compareTo(Object other) {
    Intersection oi = (Intersection) other;
    return this.t < oi.t ? -1 : 1;
  }
}