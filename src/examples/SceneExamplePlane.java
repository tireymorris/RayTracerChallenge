package examples;

import graphics.*;
import structures.*;
import entities.*;
import util.Constants;
import scene.*;

import java.io.IOException;

public class SceneExamplePlane {
  public static void main(String[] args) {
    Entity floor = new Plane();

    Sphere middle = new Sphere();
    middle.transform = Transform.identity().translate(-0.5, 1, 0.5);
    middle.material.color = new Color(0.1, 1, 0.5);
    middle.material.diffuse = 0.7;
    middle.material.specular = 0.3;

    Sphere right = new Sphere();
    right.transform = Transform.identity().scale(0.5, 0.5, 0.5).translate(1.5, 0.5, -0.5);
    right.material.color = new Color(0.5, 1, 0.1);
    right.material.diffuse = 0.7;
    right.material.specular = 0.3;

    Sphere left = new Sphere();
    left.transform = Transform.identity().scale(0.33, 0.33, 0.33).translate(-1.5, 0.33, -0.75);
    left.material.color = new Color(1, 0.8, 0.1);
    left.material.diffuse = 0.7;
    left.material.specular = 0.3;

    Light light = Light.pointLight(new Point(-10, 10, -10), new Color(1, 1, 1));

    World world = World.createWorld().withEntities(floor, middle, right, left).withLightSource(light);

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