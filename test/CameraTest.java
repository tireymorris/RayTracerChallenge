import static org.junit.Assert.*;
import org.junit.Test;

public class CameraTest {
  @Test
  public void cameraConstruction() {
    int hsize = 160;
    int vsize = 120;
    double FOV = Constants.HALF_PI;

    Camera c = new Camera(hsize, vsize, FOV);

    assertEquals(hsize, c.getHsize());
    assertEquals(vsize, c.getVsize());
    assertEquals(FOV, c.getFieldOfView(), Constants.EPSILON);
    assertEquals(Matrix.IDENTITY_MATRIX(), c.getTransform());
  }

  @Test
  public void calcPixelSizeHorizontal() {
    Camera c = new Camera(200, 125, Constants.PI / 2);

    assertEquals(0.01, c.getPixelSize(), Constants.EPSILON);
  }

  @Test
  public void calcPixelSizeVertical() {
    Camera c = new Camera(125, 200, Constants.PI / 2);

    assertEquals(0.01, c.getPixelSize(), Constants.EPSILON);
  }

  @Test
  public void rayThroughCanvasCenter() {
    Camera c = new Camera(201, 101, Constants.HALF_PI);
    Ray r = c.rayForPixel(100, 50);

    assertEquals(new Point(0, 0, 0), r.origin);
    assertEquals(new Vector(0, 0, -1), r.direction);
  }

  @Test
  public void rayThroughCanvasCorner() {
    Camera c = new Camera(201, 101, Constants.HALF_PI);
    Ray r = c.rayForPixel(0, 0);

    assertEquals(new Point(0, 0, 0), r.origin);
    assertEquals(new Vector(0.66519, 0.33259, -0.66851), r.direction);
  }

  @Test
  public void rayWhenCameraTransformed() {
    Camera c = new Camera(201, 101, Constants.HALF_PI);
    c.setTransform(Transform.identity().translate(0, -2, 5).rotateY(Constants.QUARTER_PI).build());
    Ray r = c.rayForPixel(100, 50);

    assertEquals(new Point(0, 2, -5), r.origin);
    assertEquals(new Vector(Math.sqrt(2) / 2, 0, -Math.sqrt(2) / 2), r.direction);
  }

  @Test
  public void renderWorld() {
    World w = World.defaultWorld();
    Camera c = new Camera(11, 11, Constants.HALF_PI);

    Point from = new Point(0, 0, -5);
    Point to = new Point(0, 0, 0);
    Vector up = new Vector(0, 1, 0);

    c.setTransform(Transformations.viewTransformation(from, to, up));

    Canvas image = Canvas.render(c, w);
    assertEquals(new Color(0.38066, 0.47583, 0.2855), image.pixelAt(5, 5));
  }
}