package de.gurkenlabs.litiengine.graphics.emitters.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import de.gurkenlabs.litiengine.util.MathUtilities;

@XmlRootElement(name = "param")
public class ParticleParameter implements Serializable {
  public static final int MAX_VALUE_UNDEFINED = -1;
  private static final long serialVersionUID = 4893417265998349179L;

  @XmlAttribute
  private float maxValue;

  @XmlAttribute
  private float minValue;

  public ParticleParameter() {
  }

  public ParticleParameter(final float minValue, final float maxValue) {
    this.minValue = minValue;
    this.maxValue = maxValue;
  }

  /**
   * Gets either the actual value or a random value, depending on the random
   * number flag being set.
   *
   * @return The value of this parameter.
   */
  public float get() {
    if (maxValue != -1 && minValue < maxValue) {
      return this.getRandomNumber();
    } else {
      return this.getMinValue();
    }
  }

  @XmlTransient
  public float getMaxValue() {
    return this.maxValue;
  }

  @XmlTransient
  public float getMinValue() {
    return this.minValue;
  }

  @XmlTransient
  public float getRandomNumber() {
    return (float) MathUtilities.randomInRange(this.getMinValue(), this.getMaxValue());
  }

  public void setMaxValue(final float maxValue) {
    this.maxValue = maxValue;
  }

  public void setMinValue(final float minValue) {
    this.minValue = minValue;
  }
}