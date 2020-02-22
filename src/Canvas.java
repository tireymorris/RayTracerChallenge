import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Canvas {
  protected Color[] pixels;

  public int width;
  public int height;

  public Canvas(int width, int height) {
    this.width = width;
    this.height = height;

    pixels = new Color[width * height];

    for (int idx = 0; idx < width * height; idx++) {
      pixels[idx] = Color.BLACK();
    }
  }

  private void checkCoordinates(int x, int y) {
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

  public String constructPPM() {
    // P3 is magic number for our version of PPM
    // third line is maximum pixel value
    StringBuffer PPM = new StringBuffer(String.format("P3\n%d %d\n255\n", this.width, this.height));

    for (int row = 0; row < this.height; row++) {
      StringBuffer line = new StringBuffer();

      for (int col = 0; col < this.width; col++) {
        if (col > 0) {
          line.append(" ");
        }

        Color pixel = this.pixelAt(col, row);
        pixel = pixel.toRGB();

        int r = (int) Math.round(pixel.r);
        int g = (int) Math.round(pixel.g);
        int b = (int) Math.round(pixel.b);

        line.append(String.format("%d %d %d", r, g, b));
      }

      if (line.length() > 70) {
        int indexToInsertSpace = line.substring(0, 69).lastIndexOf(" ");
        line.replace(indexToInsertSpace, indexToInsertSpace + 1, "\n");
      }

      PPM.append(line);
      PPM.append("\n");
    }
    return PPM.toString();
  }

  public void exportToPPM(String path) throws IOException {
    File file = new File(path);

    String PPM = this.constructPPM();

    FileWriter writer = new FileWriter(file);
    writer.append(PPM);
    writer.close();
  }

  public int toCanvasX(double x) {
    return (int) Math.round(this.width / 2 + x);
  }

  public int toCanvasY(double y) {
    return (int) Math.round(this.height / 2 - y);
  }
}