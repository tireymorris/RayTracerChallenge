import java.io.IOException;

class Environment {
  Vector gravity = new Vector(0, -0.1, 0);
  Vector wind = new Vector(-0.01, 0, 0);
}

class ProjectileExample {
  Point position;
  Vector velocity;
  Environment env;

  public ProjectileExample() {
    position = new Point(0, 1, 0);
    velocity = new Vector(1, 1.8, 0).normalize().scale(11.25);
    env = new Environment();
  }

  public void tick() {
    position = position.plus(velocity);
    velocity = velocity.plus(env.gravity).plus(env.wind);
  }

  public static void main(String[] args) {
    Canvas canvas = new Canvas(900, 550);
    canvas.fill(new Color(0.85, 0.85, 0.85));

    ProjectileExample p = new ProjectileExample();

    while (p.position.y > 0) {
      p.tick();

      int x = (int) Math.round(p.position.x);
      int y = (int) Math.round(canvas.height - p.position.y);

      try {
        canvas.writePixel(x, y, Color.PURPLE());
        canvas.writePixel(x + 1, y, Color.PURPLE());
        canvas.writePixel(x - 1, y, Color.PURPLE());
        canvas.writePixel(x, y + 1, Color.PURPLE());
        canvas.writePixel(x, y - 1, Color.PURPLE());
      } catch (IndexOutOfBoundsException e) {
      }
    }

    try {
      canvas.exportToPPM("/home/tmorris/Desktop/proj.PPM");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}