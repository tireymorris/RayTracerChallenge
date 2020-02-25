package graphics;

import structures.Color;
import util.Constants;

public class Material {
  public Color color;
  public Pattern pattern;

  public double ambient;
  public double diffuse;
  public double specular;
  public double shininess;

  public double reflective;
  public double transparency;
  public double refractiveIndex;

  public Material() {
    this.color = Color.WHITE();

    this.ambient = 0.1; // works best between 0 and 1
    this.diffuse = 0.9; // works best between 0 and 1
    this.specular = 0.9; // works best between 0 and 1
    this.shininess = 200.0; // works best between 10 and 200

    this.reflective = 0.0;
    this.transparency = 0.0;
    this.refractiveIndex = Constants.REFRACTION_INDEX_AIR;
  }

  public Material(Color color, double ambient, double diffuse, double specular, double shininess) {
    this.color = color;
    this.ambient = ambient;
    this.diffuse = diffuse;
    this.specular = specular;
    this.shininess = shininess;
  }

  public Material clone() {
    return new Material(this.color.clone().asColor(), this.ambient, this.diffuse, this.specular, this.shininess);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Material)) {
      return false;
    }

    Material other = (Material) obj;
    return this.color.equals(other.color) && this.ambient == other.ambient && this.diffuse == other.diffuse
        && this.specular == other.specular && this.shininess == other.shininess;
  }
}