package structures;

import java.util.ArrayList;
import java.util.List;

// TODO: Implement cloning
public class Transform {
  private List<Matrix> state;

  private Transform() {
    this.state = new ArrayList<Matrix>();
    this.state.add(Matrix.IDENTITY_MATRIX());
  }

  public static Transform identity() {
    return new Transform();
  }

  public Matrix build() {
    Matrix transform = Matrix.IDENTITY_MATRIX();

    // Applying backwards because they're in order A -> B -> C
    // but T = C * B * A
    for (int i = this.state.size() - 1; i >= 0; i--) {
      Matrix intermediate = this.state.get(i);
      transform = transform.mult(intermediate);
    }

    return transform;
  }

  public Transform translate(double x, double y, double z) {
    this.state.add(Transformations.translation(x, y, z));

    return this;
  }

  public Transform scale(double x, double y, double z) {
    this.state.add(Transformations.scaling(x, y, z));

    return this;
  }

  public Transform reflectX() {
    this.state.add(Transformations.reflectionX());

    return this;
  }

  public Transform reflectY() {
    this.state.add(Transformations.reflectionY());

    return this;
  }

  public Transform reflectZ() {
    this.state.add(Transformations.reflectionZ());

    return this;
  }

  public Transform rotateX(double radians) {
    this.state.add(Transformations.rotationX(radians));

    return this;
  }

  public Transform rotateY(double radians) {
    this.state.add(Transformations.rotationY(radians));

    return this;
  }

  public Transform rotateZ(double radians) {
    this.state.add(Transformations.rotationZ(radians));

    return this;
  }

  // // also called skew
  public Transform shear(double dxy, double dxz, double dyx, double dyz, double dzx, double dzy) {
    this.state.add(Transformations.shearing(dxy, dxz, dyx, dyz, dzx, dzy));

    return this;
  }

  public Transform viewTransform(Point from, Point to, Vector up) {
    this.state.add(Transformations.viewTransformation(from, to, up));

    return this;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Transform)) {
      return false;
    }

    Transform otherTransform = (Transform) other;

    return build().equals(otherTransform.build());
  }
}