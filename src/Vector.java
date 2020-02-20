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

  // gives us the opposite of a vector
  // for example, given a vector that points from a surface toward a light source,
  // what vector points from the light source back to the surface?
  public Vector negate() {
    return Constants.ZERO_VECTOR.minus(this);
  }
}