package examples;

import graphics.*;
import structures.*;
import entities.*;
import util.Constants;
import scene.*;

import java.io.IOException;

public class RandomScene {
  public static double rand() {
    return Math.random();
  }

  public static int sign() {
    return rand() < 0.5 ? -1 : 1;
  }

  public static void main(String[] args) {
    Entity floor = new Plane();
    floor.material.color = new Color(1, 0.9, 0.9);
    floor.material.specular = 0;
    floor.material.pattern = new CheckerPattern(new Color(rand(), rand(), rand()), new Color(rand(), rand(), rand()));
    floor.material.reflective = 0.15;

    Entity leftWall = new Plane();
    leftWall.transform = Transform.identity().rotateX(Constants.HALF_PI).rotateY(-Constants.QUARTER_PI).translate(0, 0,
        5);
    leftWall.material = floor.material.clone();
    leftWall.material.pattern = new CheckerPattern(new Color(rand(), rand(), rand()),
        new Color(rand(), rand(), rand()));
    leftWall.material.reflective = 0.15;

    Entity rightWall = new Plane();
    rightWall.transform = Transform.identity().rotateX(Constants.HALF_PI).rotateY(Constants.QUARTER_PI).translate(0, 0,
        5);
    rightWall.material = floor.material.clone();
    rightWall.material.pattern = new CheckerPattern(new Color(rand(), rand(), rand()),
        new Color(rand(), rand(), rand()));
    rightWall.material.reflective = 0.15;

    Light light = Light.pointLight(new Point(-10, 10, -10), Color.WHITE());

    World world = World.createWorld().withEntities(floor, leftWall, rightWall).withLightSource(light);

    for (int i = 0; i < 20; i++) {
      Material m = new Material();
      m.diffuse = rand();
      m.ambient = rand();
      m.reflective = rand() + 0.25;
      m.shininess = Math.random() * 200;
      m.specular = rand();
      m.color = new Color(rand(), rand(), rand());

      double scale = rand() * 0.5;
      double tx = rand() * sign() * 2.5;
      double ty = scale;
      double tz = rand() * sign() * 2.5;
      Entity s = new Sphere().withMaterial(m)
          .withTransform(Transform.identity().scale(scale, scale, scale).translate(tx, ty, tz));
      world.addEntity(s);
    }

    Camera camera = new Camera(1920, 1080, Constants.PI / 3);
    camera.setTransform(
        Transform.identity().viewTransform(new Point(0, 1.5, -5), new Point(0, 1, 0), new Vector(0, 1, 0)));

    Canvas image = Canvas.render(camera, world);

    try {
      image.exportToPPM("/Users/tymorris/Desktop/world.ppm");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}