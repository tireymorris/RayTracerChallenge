import java.io.IOException;

public class RayCastingSphereExample {
  public static void main(String[] args) {
    int canvasPixels = 800;
    Canvas canvas = new Canvas(canvasPixels, canvasPixels);

    Entity shape = new Sphere();

    Point rayOrigin = Tuple.point(0, 0, -5);

    double wallZ = 10.0;
    double wallSize = 7.0;

    // in world space units
    double pixelSize = wallSize / canvasPixels;
    double half = wallSize / 2;

    for (int y = 0; y < canvasPixels; y++) {
      double worldY = half - pixelSize * y;

      for (int x = 0; x < canvasPixels; x++) {
        double worldX = -half + pixelSize * x;

        Point position = Tuple.point(worldX, worldY, wallZ);
        Ray r = new Ray(rayOrigin, position.minus(rayOrigin).normalize());

        Intersection[] xs = r.intersect((Sphere) shape);
        Intersection hit = Intersection.hit(xs);

        if (hit != null) {
          canvas.writePixel(x, y, Constants.PURPLE());
        }
      }
    }

    try {
      canvas.exportToPPM("/home/tmorris/Desktop/sphere.ppm");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}