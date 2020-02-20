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

  // represents length of vector
  public double magnitude() {
    return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
  }

  // take arbitrary vector and convert to unit vector
  // keeps calculations relative to common scale (the unit vector)
  // without this, calculations would be scaled differently for every ray cast
  public Vector normalize() {
    double magnitude = this.magnitude();

    return super.vector(this.x / magnitude, this.y / magnitude, this.z / magnitude);
  }
}