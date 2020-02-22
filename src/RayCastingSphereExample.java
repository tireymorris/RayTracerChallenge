import java.io.IOException;

public class RayCastingSphereExample {
  public static void draw2d() {
    int canvasPixels = 800;
    Canvas canvas = new Canvas(canvasPixels, canvasPixels);

    Sphere shape = new Sphere();

    Point rayOrigin = new Point(0, 0, -5);

    double wallZ = 10.0;
    double wallSize = 7.0;

    // in world space units
    double pixelSize = wallSize / canvasPixels;
    double half = wallSize / 2;

    for (int y = 0; y < canvasPixels; y++) {
      double worldY = half - pixelSize * y;

      for (int x = 0; x < canvasPixels; x++) {
        double worldX = -half + pixelSize * x;

        Point position = new Point(worldX, worldY, wallZ);
        Ray r = new Ray(rayOrigin, position.minus(rayOrigin).normalize());

        Intersection[] xs = shape.intersections(r);
        Intersection hit = Intersection.hit(xs);

        if (hit != null) {
          canvas.writePixel(x, y, Constants.PURPLE());
        }
      }
    }

    try {
      canvas.exportToPPM("/home/tmorris/Desktop/sphere.ppm");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void draw3d() {
    int canvasPixels = 800;
    Canvas canvas = new Canvas(canvasPixels, canvasPixels);

    Sphere shape = new Sphere();
    shape.material.color = Constants.PURPLE();

    Point lightPosition = new Point(-10, 10, -10);
    Color lightColor = new Color(1, 1, 1);
    Light pointLight = Light.pointLight(lightPosition, lightColor);

    Point rayOrigin = new Point(0, 0, -5);

    double wallZ = 10.0;
    double wallSize = 7.0;

    // in world space units
    double pixelSize = wallSize / canvasPixels;
    double half = wallSize / 2;

    for (int y = 0; y < canvasPixels; y++) {
      double worldY = half - pixelSize * y;

      for (int x = 0; x < canvasPixels; x++) {
        double worldX = -half + pixelSize * x;

        Point position = new Point(worldX, worldY, wallZ);
        Ray r = new Ray(rayOrigin, position.minus(rayOrigin).normalize());

        Intersection[] xs = shape.intersections(r);
        Intersection hit = Intersection.hit(xs);

        if (hit != null) {
          Point point = r.position(hit.t);
          Vector normalVector = shape.normalAt(point);
          Vector eyeVector = r.direction.scale(-1);
          Color finalColor = Light.lighting(hit.entity.material, pointLight, point, eyeVector, normalVector);
          canvas.writePixel(x, y, finalColor);
        }
      }
    }

    try {
      canvas.exportToPPM("/home/tmorris/Desktop/sphere.ppm");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    draw3d();
  }
}