package entities;

import structures.*;
import graphics.*;

public abstract class Entity {
  public Point origin;
  public Material material;
  public Transform transform;

  public Entity(Point origin) {
    this.origin = origin;
    this.material = new Material();
    this.transform = Transform.identity();
  }

  public Entity withMaterial(Material material) {
    this.material = material;

    return this;
  }

  public Entity withTransform(Transform transform) {
    this.transform = transform;

    return this;
  }

  public abstract Intersection[] localIntersections(Ray localRay);

  public Intersection[] intersections(Ray ray) {
    // We transform the ray by the inverse transformation matrix to achieve the
    // effect of transforming the ray from world space to object space
    // In essence, applying transformations to the ray rather than the entity
    Ray transformedRay = ray.transform(this.getTransform().build().inverse());

    return localIntersections(transformedRay);
  }

  public abstract Vector localNormalAt(Point localPoint);

  public Vector normalAt(Point worldPoint) {
    Point localPoint = this.transform.build().inverse().mult(worldPoint);

    Vector localNormal = localNormalAt(localPoint);

    Vector worldNormal = this.transform.build().inverse().transpose().mult(localNormal);

    worldNormal.w = 0;

    return worldNormal.normalize();
  }

  public Transform getTransform() {
    return this.transform;
  }

  public void setTransform(Transform transform) {
    this.transform = transform;
  }
}