public final class Transform {
  public static Matrix translate(double x, double y, double z) {
    Matrix result = Constants.IDENTITY_MATRIX();

    result.set(0, 3, x);
    result.set(1, 3, y);
    result.set(2, 3, z);

    return result;
  }

  // scaling by inverse will shrink by same factor
  public static Matrix scale(double x, double y, double z) {
    Matrix result = Constants.IDENTITY_MATRIX();

    result.set(0, 0, x);
    result.set(1, 1, y);
    result.set(2, 2, z);

    return result;
  }

  public static Matrix reflectX() {
    return Transform.scale(-1, 1, 1);
  }

  public static Matrix reflectY() {
    return Transform.scale(1, -1, 1);
  }

  public static Matrix reflectZ() {
    return Transform.scale(-1, 1, -1);
  }

  public static Matrix rotateX(double radians) {
    Matrix result = Constants.IDENTITY_MATRIX();

    result.set(1, 1, Math.cos(radians));
    result.set(1, 2, -Math.sin(radians));
    result.set(2, 1, Math.sin(radians));
    result.set(2, 2, Math.cos(radians));

    return result;
  }

  public static Matrix rotateY(double radians) {
    Matrix result = Constants.IDENTITY_MATRIX();

    result.set(0, 0, Math.cos(radians));
    result.set(0, 2, Math.sin(radians));
    result.set(2, 0, -Math.sin(radians));
    result.set(2, 2, Math.cos(radians));

    return result;
  }

  public static Matrix rotateZ(double radians) {
    Matrix result = Constants.IDENTITY_MATRIX();

    result.set(0, 0, Math.cos(radians));
    result.set(0, 1, -Math.sin(radians));
    result.set(1, 0, Math.sin(radians));
    result.set(1, 1, Math.cos(radians));

    return result;
  }

  // // also called skew
  public static Matrix shear(double dxy, double dxz, double dyx, double dyz, double dzx, double dzy) {
    Matrix result = Constants.IDENTITY_MATRIX();

    result.set(0, 1, dxy);
    result.set(0, 2, dxz);
    result.set(1, 0, dyx);
    result.set(1, 2, dyz);
    result.set(2, 0, dzx);
    result.set(2, 1, dzy);

    return result;
  }
}