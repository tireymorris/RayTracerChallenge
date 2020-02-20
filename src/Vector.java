public class Vector extends Tuple {
  public Vector(double x, double y, double z) {
    super(x, y, z, Constants.VECTOR_W_VALUE);
  }

  public Vector minus(Vector other) {
    return super.subtract(this, other).asVector();
  }

  public Vector plus(Vector other) {
    return super.add(this, other).asVector();
  }
}