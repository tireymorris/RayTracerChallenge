// TODO: consider different structure
public class Light {
  public Point position;
  public Color intensity;

  public Light(Point position, Color intensity) {
    this.position = position;
    this.intensity = intensity;
  }

  public static Light pointLight(Point position, Color intensity) {
    return new Light(position, intensity);
  }

  static Color lighting(Material material, Light light, Point point, Vector eyeVector, Vector normal,
      boolean inShadow) {
    // combine surface color and light's color/intensity
    Color effectiveColor = material.color.blend(light.intensity);

    // find direction to the light source
    Vector lightVector = light.position.minus(point).normalize();

    // compute ambient contribution
    Color ambient = effectiveColor.scale(material.ambient);

    // lightDotNormal -> cosine of angle between light vector and normal vector
    // a negative number means the light is on the other side of the surface
    double lightDotNormal = lightVector.dot(normal);

    Color diffuse, specular;

    if (lightDotNormal < 0) {
      diffuse = Color.BLACK();
      specular = Color.BLACK();
    } else {
      // compute diffuse contribution
      diffuse = effectiveColor.scale(material.diffuse).scale(lightDotNormal);

      // reflectDotEye -> cosine of angle between reflection vector and eye vector
      // negative number means light reflects away from the eye
      Vector reflectVector = lightVector.scale(-1).reflect(normal);
      double reflectDotEye = reflectVector.dot(eyeVector);

      if (reflectDotEye <= 0) {
        specular = Color.BLACK();
      } else {
        // compute specular contribution
        double factor = Math.pow(reflectDotEye, material.shininess);
        specular = light.intensity.scale(material.specular).scale(factor);
      }
    }

    if (inShadow) {
      return ambient;
    }

    return ambient.plus(diffuse).plus(specular);
  }
}
