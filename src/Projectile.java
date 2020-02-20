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
    velocity = Tuple.vector(1, 1, 0).normalize();
    env = new Environment();
  }

  public void tick() {
    position = position.plus(velocity);

    velocity = velocity.plus(env.gravity.plus(env.wind));
  }

  public static void main(String[] args) {
    Projectile p = new Projectile();

    while (p.position.y > 0) {
      p.tick();
      System.out.println(p.position);
    }
  }
}