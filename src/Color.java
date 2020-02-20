public class Color extends Tuple {
  double r, g, b;

  public Color(double r, double g, double b) {
    super(r, g, b, Constants.POINT_W_VALUE);
    this.r = r;
    this.g = g;
    this.b = b;
  }
}