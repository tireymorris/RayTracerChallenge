package entities;

import util.Constants;

public class GlassSphere extends Sphere {
  public GlassSphere() {
    super();

    this.material.refractiveIndex = Constants.REFRACTION_INDEX_GLASS;
    this.material.transparency = 0.9;
    this.material.reflective = 0.9;
    this.material.ambient = 0.01;
    this.material.diffuse = 0.09;
  }
}