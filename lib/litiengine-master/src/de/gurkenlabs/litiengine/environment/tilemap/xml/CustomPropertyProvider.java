package de.gurkenlabs.litiengine.environment.tilemap.xml;

import java.awt.Color;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.gurkenlabs.litiengine.environment.tilemap.ICustomProperty;
import de.gurkenlabs.litiengine.environment.tilemap.ICustomPropertyProvider;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomPropertyProvider implements ICustomPropertyProvider {
  @XmlElement
  @XmlJavaTypeAdapter(CustomPropertyAdapter.class)
  private Map<String, ICustomProperty> properties;

  public CustomPropertyProvider() {
    this.properties = new Hashtable<>(); // use Hashtable because it rejects null keys and null values
  }

  /**
   * Copy Constructor for copying instances of CustomPropertyProviders.
   *
   * @param propertyProviderToBeCopied
   *          the PropertyProvider we want to copy
   */
  public CustomPropertyProvider(CustomPropertyProvider propertyProviderToBeCopied) {
    this.properties = new Hashtable<>(propertyProviderToBeCopied.getProperties());
  }

  @Override
  public Map<String, ICustomProperty> getProperties() {
    return this.properties;
  }

  @Override
  public boolean hasCustomProperty(String propertyName) {
    return this.getProperties().containsKey(propertyName);
  }

  @Override
  public String getTypeOfProperty(String propertyName) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return null;
    }
    return property.getType();
  }

  @Override
  public void setTypeOfProperty(String propertyName, String type) {
    this.getProperty(propertyName).setType(type);
  }

  @Override
  public ICustomProperty getProperty(String propertyName) {
    return this.getProperties().get(propertyName);
  }

  @Override
  public void setValue(String propertyName, ICustomProperty value) {
    if (value != null) {
      this.getProperties().put(propertyName, value);
    }
  }

  @Override
  public String getStringValue(String propertyName) {
    return this.getStringValue(propertyName, null);
  }

  @Override
  public String getStringValue(String propertyName, String defaultValue) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return defaultValue;
    }
    return property.getAsString();
  }

  @Override
  public int getIntValue(String propertyName) {
    return this.getIntValue(propertyName, 0);
  }

  @Override
  public int getIntValue(String propertyName, int defaultValue) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return defaultValue;
    }
    return property.getAsInt();
  }

  @Override
  public long getLongValue(String propertyName, long defaultValue) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return defaultValue;
    }
    return property.getAsLong();
  }

  @Override
  public short getShortValue(String propertyName) {
    return this.getShortValue(propertyName, (short)0);
  }

  @Override
  public short getShortValue(String propertyName, short defaultValue) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return defaultValue;
    }
    return property.getAsShort();
  }

  @Override
  public byte getByteValue(String propertyName) {
    return this.getByteValue(propertyName, (byte)0);
  }

  @Override
  public byte getByteValue(String propertyName, byte defaultValue) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return defaultValue;
    }
    return property.getAsByte();
  }

  @Override
  public boolean getBoolValue(String propertyName) {
    return this.getBoolValue(propertyName, false);
  }

  @Override
  public boolean getBoolValue(String propertyName, boolean defaultValue) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return defaultValue;
    }
    return property.getAsBool();
  }

  @Override
  public float getFloatValue(String propertyName) {
    return this.getFloatValue(propertyName, 0f);
  }

  @Override
  public float getFloatValue(String propertyName, float defaultValue) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return defaultValue;
    }
    return property.getAsFloat();
  }

  @Override
  public double getDoubleValue(String propertyName) {
    return this.getDoubleValue(propertyName, 0.0);
  }

  @Override
  public double getDoubleValue(String propertyName, double defaultValue) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return defaultValue;
    }
    return property.getAsDouble();
  }

  @Override
  public Color getColorValue(String propertyName) {
    return this.getColorValue(propertyName, null);
  }

  @Override
  public Color getColorValue(String propertyName, Color defaultValue) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return defaultValue;
    }
    return property.getAsColor();
  }

  @Override
  public <T extends Enum<T>> T getEnumValue(String propertyName, Class<T> enumType) {
    return this.getEnumValue(propertyName, enumType, null);
  }

  @Override
  public <T extends Enum<T>> T getEnumValue(String propertyName, Class<T> enumType, T defaultValue) {
    ICustomProperty property = this.getProperty(propertyName);
    if (property == null) {
      return defaultValue;
    }
    return property.getAsEnum(enumType);
  }

  private ICustomProperty createPropertyIfAbsent(String propertyName) {
    return this.getProperties().computeIfAbsent(propertyName, n -> new CustomProperty());
  }

  @Override
  public void setValue(String propertyName, String value) {
    if (value != null) {
      ICustomProperty property = createPropertyIfAbsent(propertyName);
      property.setType("string");
      property.setValue(value);
    } else {
      this.getProperties().remove(propertyName);
    }
  }

  @Override
  public void setValue(String propertyName, boolean value) {
    ICustomProperty property = createPropertyIfAbsent(propertyName);
    property.setType("bool");
    property.setValue(value);
  }

  @Override
  public void setValue(String propertyName, byte value) {
    ICustomProperty property = createPropertyIfAbsent(propertyName);
    property.setType("int");
    property.setValue(value);
  }

  @Override
  public void setValue(String propertyName, short value) {
    ICustomProperty property = createPropertyIfAbsent(propertyName);
    property.setType("int");
    property.setValue(value);
  }

  @Override
  public void setValue(String propertyName, int value) {
    ICustomProperty property = createPropertyIfAbsent(propertyName);
    property.setType("int");
    property.setValue(value);
  }

  @Override
  public void setValue(String propertyName, long value) {
    ICustomProperty property = createPropertyIfAbsent(propertyName);
    property.setType("int");
    property.setValue(value);
  }

  @Override
  public void setValue(String propertyName, float value) {
    ICustomProperty property = createPropertyIfAbsent(propertyName);
    property.setType("float");
    property.setValue(value);
  }

  @Override
  public void setValue(String propertyName, double value) {
    ICustomProperty property = createPropertyIfAbsent(propertyName);
    property.setType("float");
    property.setValue(value);
  }

  @Override
  public void setValue(String propertyName, Color value) {
    ICustomProperty property = createPropertyIfAbsent(propertyName);
    property.setType("color");
    property.setValue(value);
  }

  @Override
  public void setValue(String propertyName, Enum<?> value) {
    ICustomProperty property = createPropertyIfAbsent(propertyName);
    property.setType("string");
    property.setValue(value);
  }

  @Override
  public void setProperties(Map<String, ICustomProperty> props) {
    this.getProperties().clear();
    if (props != null) {
      this.getProperties().putAll(props);
    }
  }

  @Override
  public void removeProperty(String propertyName) {
    this.getProperties().remove(propertyName);
  }

  @SuppressWarnings("unused")
  private void afterUnmarshal(Unmarshaller u, Object parent) {
    if (this.properties == null) {
      this.properties = new Hashtable<>();
    }
  }
}
