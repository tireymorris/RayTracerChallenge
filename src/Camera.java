public class Camera {
  private int hsize;
  private int vsize;
  private double fieldOfView;
  private double pixelSize;
  private double halfWidth;
  private double halfHeight;
  private Transform transform;

  public Camera(int hsize, int vsize, double fieldOfView) {
    this.hsize = hsize;
    this.vsize = vsize;

    this.fieldOfView = fieldOfView;

    this.transform = Transform.identity();

    calculatePixelSize();
  }

  // returns a new ray that passes through the point X, Y on the canvas
  // must compute world coordinates at the center of the given pixel
  // constructs a ray that passes through that point
  public Ray rayForPixel(int x, int y) {
    // offset from edge of the canvas to pixel's center
    double xOffset = (x + 0.5) * pixelSize;
    double yOffset = (y + 0.5) * pixelSize;

    // untransformed coordinates of the pixel in world space
    // (camera looks toward -z, so +x is to the left!)
    double worldX = halfWidth - xOffset;
    double worldY = halfHeight - yOffset;

    // use camera matrix to transform canvas point and the origin
    // then compute ray's direction vector
    // (note that canvas is at z = -1)
    Point pixel = transform.build().inverse().mult(new Point(worldX, worldY, -1));
    Point origin = transform.build().inverse().mult(new Point(0, 0, 0));
    Vector direction = pixel.minus(origin).normalize();

    return new Ray(origin, direction);
  }

  private void calculatePixelSize() {
    // width of half of the canvas
    double halfView = Math.tan(fieldOfView / 2.0);

    // ratio of horizontal to vertical size
    double aspectRatio = hsize / (double) vsize;

    // "horizontal" canvas
    double halfWidth;
    double halfHeight;

    if (aspectRatio >= 1) {
      halfWidth = halfView;
      halfHeight = halfWidth / aspectRatio;
    } else {
      halfWidth = halfView * aspectRatio;
      halfHeight = halfView;
    }

    this.pixelSize = (halfWidth * 2) / hsize;

    this.halfWidth = halfWidth;
    this.halfHeight = halfHeight;
  }

  public int getHsize() {
    return hsize;
  }

  public int getVsize() {
    return vsize;
  }

  public double getFieldOfView() {
    return fieldOfView;
  }

  public Matrix getTransform() {
    return transform.build().clone();
  }

  public void setHsize(int hsize) {
    this.hsize = hsize;
    calculatePixelSize();
  }

  public void setVsize(int vsize) {
    this.vsize = vsize;
    calculatePixelSize();
  }

  public void setFieldOfView(double fieldOfView) {
    this.fieldOfView = fieldOfView;
    calculatePixelSize();
  }

  public void setTransform(Transform transform) {
    this.transform = transform;
    calculatePixelSize();
  }

  public double getPixelSize() {
    return pixelSize;
  }

  public double getHalfHeight() {
    return halfHeight;
  }

  public double getHalfWidth() {
    return halfWidth;
  }

}