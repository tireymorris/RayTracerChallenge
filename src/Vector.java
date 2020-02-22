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

  @Override
  public Vector scale(double scalar) {
    return super.scale(scalar).asVector();
  }

  @Override
  public Vector mult(double scalar) {
    return super.scale(scalar).asVector();
  }

  @Override
  public Vector div(double scalar) {
    return super.mult(1 / scalar).asVector();
  }

  // gives us the opposite of a vector
  // for example, given a vector that points from a surface toward a light source,
  // what vector points from the light source back to the surface?
  public Vector negate() {
    return Tuple.vector(0, 0, 0).minus(this);
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

  // also called scalar product or inner product
  // shows up when intersecting rays with objects or computing surface shading
  // smaller dot product => larger angle between vectors
  // 1 == identical
  // -1 == opposite
  // (technically, cosine between the vectors)
  public double dot(Vector other) {
    return this.x * other.x + this.y * other.y + this.z * other.z;
  }

  // returns a new vector perpendicular to its operands
  // primariliy used with view transformations
  // order matters! otherwise sign will be negated
  public Vector cross(Vector other) {
    return Tuple.vector(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z,
        this.x * other.y - this.y * other.x);
  }
}