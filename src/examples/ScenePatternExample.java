package examples;

import graphics.*;
import structures.*;
import entities.*;
import util.Constants;
import scene.*;

import java.io.IOException;

public class ScenePatternExample {
  public static void main(String[] args) {
    Entity floor = new Plane();
    floor.material.color = new Color(1, 0.9, 0.9);
    floor.material.specular = 0;
    floor.material.pattern = new CheckerPattern(Color.fromHex("#7951f0"), Color.fromHex("#c8f051"));
    floor.material.reflective = 0.15;

    Entity leftWall = new Plane();
    leftWall.transform = Transform.identity().rotateX(Constants.HALF_PI).rotateY(-Constants.QUARTER_PI).translate(0, 0,
        5);
    leftWall.material = floor.material.clone();
    leftWall.material.pattern = new CheckerPattern(Color.fromHex("#51a1f0"), Color.fromHex("#f0a151"));
    leftWall.material.reflective = 0.15;

    Entity rightWall = new Plane();
    rightWall.transform = Transform.identity().rotateX(Constants.HALF_PI).rotateY(Constants.QUARTER_PI).translate(0, 0,
        5);
    rightWall.material = floor.material.clone();
    rightWall.material.pattern = new CheckerPattern(Color.fromHex("#51f0c8"), Color.fromHex("#f05179"));
    rightWall.material.reflective = 0.15;

    Sphere middle = new Sphere();
    middle.transform = Transform.identity().translate(-0.5, 1, 0.5);
    middle.material.color = new Color(0.1, 1, 0.5);
    middle.material.diffuse = 0.7;
    middle.material.specular = 0.3;
    middle.material.pattern = new GradientPattern(Color.fromHex("#c91900"), Color.fromHex("#00b0c7"))
        .withTransform(Transform.identity().rotateY(-Constants.HALF_PI));
    middle.material.reflective = 0.85;

    Sphere right = new Sphere();
    right.transform = Transform.identity().scale(0.5, 0.5, 0.5).translate(1.5, 0.5, -0.5);
    right.material.color = new Color(0.5, 1, 0.1);
    right.material.diffuse = 0.7;
    right.material.specular = 0.3;
    right.material.pattern = new CheckerPattern(Color.fromHex("#9f199a"), Color.fromHex("#199f1d"))
        .withTransform(Transform.identity().scale(0.25, 0.25, 0.25));

    Sphere left = new Sphere();
    left.transform = Transform.identity().scale(0.33, 0.33, 0.33).translate(-1.5, 0.33, -0.75);
    left.material.color = new Color(1, 0.8, 0.1);
    left.material.diffuse = 0.7;
    left.material.specular = 0.3;
    left.material.pattern = new StripePattern(new Color(1, 0.8, 0), new Color(0, 1, 0.8))
        .withTransform(Transform.identity().scale(0.1, 0.2, 0.2).rotateZ(Constants.PI / 6));

    Light light = Light.pointLight(new Point(-10, 10, -10), new Color(1, 1, 1));

    World world = World.createWorld().withEntities(floor, leftWall, rightWall, middle, right, left)
        .withLightSource(light);

    Camera camera = new Camera(1920, 1080, Constants.PI / 3);
    camera.setTransform(
        Transform.identity().viewTransform(new Point(0, 1.5, -5), new Point(0, 1, 0), new Vector(0, 1, 0)));

    Canvas image = Canvas.render(camera, world);

    try {
      image.exportToPPM("/home/tmorris/Desktop/world.ppm");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}