public class Application {
  public static void main(String[] args) {
    Canvas canvas = new Canvas(1920, 1080);

    canvas.fill(Tuple.color(0, 1, 0.5));
    canvas.exportToPPM("/home/tmorris/Desktop/thing.ppm");
  }
}