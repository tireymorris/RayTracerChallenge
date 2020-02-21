public class Canvas {
  Color[] pixels;

  int width;
  int height;

  public Canvas(int width, int height) {
    this.width = width;
    this.height = height;

    pixels = new Color[width * height];

    for (int idx = 0; idx < width * height; idx++) {
      pixels[idx] = Tuple.color(0, 0, 0);
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

  public void fill(Color color) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        this.writePixel(x, y, color);
      }
    }
  }

  public Color pixelAt(int x, int y) {
    checkCoordinates(x, y);

    return this.pixels[width * y + x];
  }

  public StringBuffer constructPPM() {
    // P3 is magic number for our version of PPM
    // third line is maximum pixel value
    // TODO: refactor
    StringBuffer PPM = new StringBuffer(String.format("P3\n%d %d\n255\n", this.width, this.height));

    int lineLength = 0;
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        if (col != 0) {
          PPM.append(" ");
          lineLength++;
        }

        Color pixel = this.pixelAt(col, row);
        pixel = pixel.toRGB();

        int r = (int) Math.round(pixel.r);
        int g = (int) Math.round(pixel.g);
        int b = (int) Math.round(pixel.b);

        if (lineLength + 3 > 70) {
          PPM.append("\n");
          lineLength = 0;
        }

        if (lineLength + 3 > 70) {
          PPM.append("\n");
          lineLength = 0;
        }
        PPM.append(r);
        lineLength++;

        if (lineLength + 3 > 70) {
          PPM.append("\n");
          lineLength = 0;
        }
        PPM.append(g);
        lineLength++;

        if (lineLength + 3 > 70) {
          PPM.append("\n");
          lineLength = 0;
        }
        PPM.append(b);
        lineLength++;
      }

      PPM.append("\n");

    }

    return PPM;
  }
}