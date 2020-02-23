package structures;

public class Transformations {
  public static Matrix translation(double x, double y, double z) {
    Matrix result = Matrix.IDENTITY_MATRIX();

    result.set(0, 3, x);
    result.set(1, 3, y);
    result.set(2, 3, z);

    return result;
  }

  // scaling by inverse will shrink by same factor
  public static Matrix scaling(double x, double y, double z) {
    Matrix result = Matrix.IDENTITY_MATRIX();

    result.set(0, 0, x);
    result.set(1, 1, y);
    result.set(2, 2, z);

    return result;
  }

  public static Matrix reflectionX() {
    return Transformations.scaling(-1, 1, 1);
  }

  public static Matrix reflectionY() {
    return Transformations.scaling(1, -1, 1);
  }

  public static Matrix reflectionZ() {
    return Transformations.scaling(-1, 1, -1);
  }

  public static Matrix rotationX(double radians) {
    Matrix result = Matrix.IDENTITY_MATRIX();

    result.set(1, 1, Math.cos(radians));
    result.set(1, 2, -Math.sin(radians));
    result.set(2, 1, Math.sin(radians));
    result.set(2, 2, Math.cos(radians));

    return result;
  }

  public static Matrix rotationY(double radians) {
    Matrix result = Matrix.IDENTITY_MATRIX();

    result.set(0, 0, Math.cos(radians));
    result.set(0, 2, Math.sin(radians));
    result.set(2, 0, -Math.sin(radians));
    result.set(2, 2, Math.cos(radians));

    return result;
  }

  public static Matrix rotationZ(double radians) {
    Matrix result = Matrix.IDENTITY_MATRIX();

    result.set(0, 0, Math.cos(radians));
    result.set(0, 1, -Math.sin(radians));
    result.set(1, 0, Math.sin(radians));
    result.set(1, 1, Math.cos(radians));

    return result;
  }

  // // also called skew
  public static Matrix shearing(double dxy, double dxz, double dyx, double dyz, double dzx, double dzy) {
    Matrix result = Matrix.IDENTITY_MATRIX();

    result.set(0, 1, dxy);
    result.set(0, 2, dxz);
    result.set(1, 0, dyx);
    result.set(1, 2, dyz);
    result.set(2, 0, dzx);
    result.set(2, 1, dzy);

    return result;
  }

  public static Matrix viewTransformation(Point from, Point to, Vector up) {
    Vector forward = to.minus(from).normalize();

    Vector left = forward.cross(up.normalize());

    // allows given Up vector to be an approximation
    Vector trueUp = left.cross(forward);

    double[][] orientationRows = { { left.x, left.y, left.z, 0 }, { trueUp.x, trueUp.y, trueUp.z, 0 },
        { -forward.x, -forward.y, -forward.z, 0 }, { 0, 0, 0, 1 } };

    Matrix orientation = Matrix.fromRows(orientationRows);
    Matrix translation = Transformations.translation(-from.x, -from.y, -from.z);

    return orientation.mult(translation);
  }
}