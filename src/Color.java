public class Color extends Tuple {
  double r, g, b;

  public Color(double r, double g, double b) {
    super(r, g, b, Constants.POINT_W_VALUE);
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public Color plus(Color other) {
    return super.add(this, other).asColor();
  }

  public Color minus(Color other) {
    return super.subtract(this, other).asColor();
  }

  @Override
  public Color scale(double scalar) {
    return super.scale(scalar).asColor();
  }

  @Override
  public Color mult(double scalar) {
    return this.scale(scalar);
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