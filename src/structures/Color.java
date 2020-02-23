package structures;

import util.Constants;

public class Color extends Tuple {
  public double r, g, b;

  public static final Color WHITE() {
    return new Color(1, 1, 1);
  }

  public static final Color BLACK() {
    return new Color(0, 0, 0);
  }

  public static final Color PURPLE() {
    return new Color(0.4, 0.0627, 0.949);
  }

  public Color(double r, double g, double b) {
    super(r, g, b, Constants.POINT_W_VALUE);
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public Color plus(Color other) {
    return super.plus(other).asColor();
  }

  public Color minus(Color other) {
    return super.minus(other).asColor();
  }

  @Override
  public Color scale(double scalar) {
    return super.scale(scalar).asColor();
  }

  // Hadamard product
  // Multiply components together to form a new, blended color
  // Might use when (i.e.) finding out the visible color of a yellow-green surface
  // When illuminated by reddish-purple light
  public Color blend(Color other) {
    return super.color(this.r * other.r, this.g * other.g, this.b * other.b);
  }

  public Color toRGB() {
    Color intermediate = this.scale(255);
    return intermediate.constrain(0, 255).asColor();
  }
}