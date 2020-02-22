import java.io.IOException;

public class ClockExample {
  public static void main(String[] args) {
    Canvas canvas = new Canvas(600, 600);

    int radius = Math.min(canvas.width, canvas.height) / 2 - 50;
    Point center = new Point(0, 0, 0);
    Vector hand = new Vector(0, 1, 0);

    for (int hour = 1; hour <= 12; hour++) {
      Point dot = center.plus(hand.scale(radius));

      int x = canvas.toCanvasX(dot.x);
      int y = canvas.toCanvasY(dot.y);

      canvas.writePixel(x, y, Constants.WHITE());
      canvas.writePixel(x, y + 1, Constants.WHITE());
      canvas.writePixel(x, y - 1, Constants.WHITE());
      canvas.writePixel(x + 1, y, Constants.WHITE());
      canvas.writePixel(x + 1, y - 1, Constants.WHITE());
      canvas.writePixel(x + 1, y + 1, Constants.WHITE());
      canvas.writePixel(x - 1, y, Constants.WHITE());
      canvas.writePixel(x - 1, y - 1, Constants.WHITE());
      canvas.writePixel(x - 1, y + 1, Constants.WHITE());

      hand = Transformations.rotationZ(Constants.HALF_PI / 3.0).mult(hand).asVector();
    }

    try {
      canvas.exportToPPM("/home/tmorris/Desktop/clock.ppm");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}