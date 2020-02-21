class Environment {
  Vector gravity = Tuple.vector(0, -0.1, 0);
  Vector wind = Tuple.vector(-0.01, 0, 0);
}

class Projectile {
  Point position;
  Vector velocity;
  Environment env;

  public Projectile() {
    position = Tuple.point(0, 1, 0);
    velocity = Tuple.vector(1, 1.8, 0).normalize().scale(11.25);
    env = new Environment();
  }

  public void tick() {
    position = position.plus(velocity);

    velocity = velocity.plus(env.gravity.plus(env.wind));
  }

  public static void main(String[] args) {
    Canvas canvas = new Canvas(900, 550);
    canvas.fill(Tuple.color(0.85, 0.85, 0.85));

    Projectile p = new Projectile();

    while (p.position.y > 0) {
      p.tick();

      double y = canvas.height - p.position.y;

      try {
        canvas.writePixel((int) Math.round(p.position.x), (int) Math.round(y), Constants.PURPLE);
        canvas.writePixel((int) Math.round(p.position.x + 1), (int) Math.round(y), Constants.PURPLE);
        canvas.writePixel((int) Math.round(p.position.x - 1), (int) Math.round(y), Constants.PURPLE);
        canvas.writePixel((int) Math.round(p.position.x), (int) Math.round(y + 1), Constants.PURPLE);
        canvas.writePixel((int) Math.round(p.position.x), (int) Math.round(y - 1), Constants.PURPLE);
      } catch (IndexOutOfBoundsException e) {

      }
    }

    canvas.exportToPPM("/home/tmorris/Desktop/proj.PPM");
  }
}