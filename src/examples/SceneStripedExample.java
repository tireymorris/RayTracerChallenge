package examples;

import graphics.*;
import structures.*;
import entities.*;
import util.Constants;
import scene.*;

import java.io.IOException;

public class SceneStripedExample {
  public static void main(String[] args) {
    Entity floor = new Sphere();
    floor.transform = Transform.identity().scale(10, 0.01, 10);
    floor.material.color = new Color(1, 0.9, 0.9);
    floor.material.specular = 0;
    floor.material.pattern = new StripePattern(Color.fromHex("#f07951"), Color.fromHex("#7952f0"))
        .withTransform(Transform.identity().scale(0.05, 0.05, 0.05));

    Entity leftWall = new Sphere();
    leftWall.transform = Transform.identity().scale(10, 0.01, 10).rotateX(Constants.HALF_PI)
        .rotateY(-Constants.QUARTER_PI).translate(0, 0, 5);
    leftWall.material = floor.material;

    Entity rightWall = new Sphere();
    rightWall.transform = Transform.identity().scale(10, 0.01, 10).rotateX(Constants.HALF_PI)
        .rotateY(Constants.QUARTER_PI).translate(0, 0, 5);
    rightWall.material = floor.material;

    Sphere middle = new Sphere();
    middle.transform = Transform.identity().translate(-0.5, 1, 0.5);
    middle.material.color = new Color(0.1, 1, 0.5);
    middle.material.diffuse = 0.7;
    middle.material.specular = 0.3;
    middle.material.pattern = new StripePattern(Color.fromHex("#c91900"), Color.fromHex("#00b0c7"))
        .withTransform(Transform.identity().scale(0.2, 0.2, 0.2).rotateY(-Constants.PI / 4.2));

    Sphere right = new Sphere();
    right.transform = Transform.identity().scale(0.5, 0.5, 0.5).translate(1.5, 0.5, -0.5);
    right.material.color = new Color(0.5, 1, 0.1);
    right.material.diffuse = 0.7;
    right.material.specular = 0.3;
    right.material.pattern = new StripePattern(Color.fromHex("#9f199a"), Color.fromHex("#199f1d"))
        .withTransform(Transform.identity().scale(0.05, 0.2, 0.2).rotateX(Constants.HALF_PI));

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