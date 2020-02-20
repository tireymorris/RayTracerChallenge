public class Canvas {
  Color[] pixels;

  int width;
  int height;

  public Canvas(int width, int height) {
    this.width = width;
    this.height = height;

    pixels = new Color[width * height];

    for (int i = 0; i < width * height; i++) {
      pixels[i] = Tuple.color(0, 0, 0);
    }
  }

  public void checkCoordinates(int x, int y) {
    if ((width * y + x) >= width * height || x < 0 || y < 0 || x >= width || y >= height) {
      throw new IndexOutOfBoundsException("Pixel coordinates are invalid");
    }
  }

  public void writePixel(int x, int y, Color color) {
    checkCoordinates(x, y);

    this.pixels[this.width * y + x] = color;
  }

  public Color pixelAt(int x, int y) {
    checkCoordinates(x, y);

    return this.pixels[width * y + x];
  }

}