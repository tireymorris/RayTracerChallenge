package entities;

import util.Constants;

public class GlassSphere extends Sphere {
  public GlassSphere() {
    super();

    this.material.refractiveIndex = Constants.REFRACTION_INDEX_GLASS;
  }
}