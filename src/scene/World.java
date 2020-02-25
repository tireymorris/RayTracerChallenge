package scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import entities.*;
import structures.*;
import graphics.*;

public class World {
  public List<Entity> entities;
  public Light lightSource;

  private World() {
    entities = new ArrayList<>();
  }

  public static World createWorld() {
    return new World();
  }

  public static World defaultWorld() {
    Light light = Light.pointLight(new Point(-10, 10, -10), new Color(1, 1, 1));
    Sphere s1 = new Sphere().withMaterial(new Material(new Color(0.8, 1.0, 0.6), 0.1, 0.7, 0.2, 200.0));
    Sphere s2 = new Sphere().withTransform(Transform.identity().scale(0.5, 0.5, 0.5));

    return createWorld().withEntities(s1, s2).withLightSource(light);
  }

  public Entity getEntity(int index) {
    return entities.get(index);
  }

  public void setEntity(int index, Entity ent) {
    entities.set(index, ent);
  }

  public void addEntity(Entity ent) {
    entities.add(ent);
  }

  public void setEntities(Entity... entities) {
    for (Entity ent : entities) {
      this.entities.add(ent);
    }
  }

  public World withEntities(Entity... entities) {
    for (Entity ent : entities) {
      this.entities.add(ent);
    }

    return this;
  }

  public void setLightSource(Light light) {
    lightSource = light;
  }

  public World withLightSource(Light light) {
    setLightSource(light);

    return this;
  }

  public Intersection[] intersections(Ray ray) {
    List<Intersection> intersections = new ArrayList<>();

    for (Entity e : entities) {
      intersections.addAll(Arrays.asList(e.intersections(ray)));
    }
    intersections = intersections.stream().filter(x -> x != null).collect(Collectors.toList());

    Intersection[] xs = new Intersection[intersections.size()];
    intersections.sort((i1, i2) -> i1.compareTo(i2));

    return intersections.toArray(xs);
  }

  public Color shadeHit(IntersectionComputations comps, int remainingCalls) {
    boolean isShadowed = isShadowed(comps.overPoint);

    Color surface = Light.lighting(comps.entity.material, comps.entity, lightSource, comps.point, comps.eyeVector,
        comps.normalVector, isShadowed);

    Color reflected = reflectedColor(comps, remainingCalls);

    Color refracted = refractedColor(comps, remainingCalls);

    return surface.plus(reflected).plus(refracted);
  }

  public boolean isShadowed(Point point) {
    // distance between from the point to the light source
    Vector pointToLightVector = lightSource.position.minus(point);
    double distance = pointToLightVector.magnitude();

    Ray pointToLightRay = new Ray(point, pointToLightVector.normalize());

    Intersection[] intersections = intersections(pointToLightRay);

    Intersection hit = Intersection.hit(intersections);

    return hit != null && hit.t < distance;
  }

  public Color colorAt(Ray ray, int remainingCalls) {
    Intersection[] intersections = intersections(ray);

    if (intersections.length == 0) {
      return Color.BLACK();
    }

    Intersection hit = Intersection.hit(intersections);

    if (hit == null) {
      return Color.BLACK();
    }

    return shadeHit(new IntersectionComputations(hit, ray, intersections), remainingCalls);
  }

  public Color reflectedColor(IntersectionComputations comps, int remainingCalls) {
    if (remainingCalls <= 0) {
      return Color.BLACK();
    }

    if (comps.entity.material.reflective == 0) {
      return Color.BLACK();
    }

    Ray reflectRay = new Ray(comps.overPoint, comps.reflectVector);

    Color reflectColor = colorAt(reflectRay, remainingCalls - 1);

    return reflectColor.scale(comps.entity.material.reflective);
  }

  public Color refractedColor(IntersectionComputations comps, int remainingCalls) {
    if (comps.entity.material.transparency == 0 || remainingCalls == 0) {
      return Color.BLACK();
    }

    double nRatio = comps.n1 / comps.n2;
    double cosI = comps.eyeVector.dot(comps.normalVector);
    double sin2Theta = nRatio * nRatio * (1 - cosI * cosI);

    if (sin2Theta > 1) {
      return Color.BLACK();
    }

    double cosT = Math.sqrt(1.0 - sin2Theta);

    Vector refractDirection = comps.normalVector.scale(nRatio * cosI - cosT).minus(comps.eyeVector.scale(nRatio));

    Ray refractRay = new Ray(comps.underPoint, refractDirection);

    Color refractColor = colorAt(refractRay, remainingCalls - 1).scale(comps.entity.material.transparency);

    return refractColor;
  }
}