package de.gurkenlabs.litiengine.environment;

import java.lang.reflect.Field;

import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.ICollisionEntity;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.environment.tilemap.IMapObject;
import de.gurkenlabs.litiengine.environment.tilemap.MapObjectProperty;
import de.gurkenlabs.litiengine.environment.tilemap.MapObjectType;
import de.gurkenlabs.litiengine.environment.tilemap.TmxProperty;
import de.gurkenlabs.litiengine.util.ReflectionUtilities;

public abstract class MapObjectLoader implements IMapObjectLoader {
  private final String mapObjectType;

  protected MapObjectLoader(String mapObjectType) {
    this.mapObjectType = mapObjectType;
  }

  protected MapObjectLoader(MapObjectType mapObjectType) {
    this.mapObjectType = mapObjectType.name();
  }

  @Override
  public String getMapObjectType() {
    return this.mapObjectType;
  }

  /**
   * Loads engine default properties to the specified <code>IEntity</code> instance:
   * <ul>
   * <li>width, height</li>
   * <li>mapId</li>
   * <li>name</li>
   * <li>location</li>
   * <li>tags</li>
   * </ul>
   * Also, this supports predefined <code>CustomMapObjectProperties</code>. It loads the specified custom properties via reflection.
   * 
   * @param entity
   *          The entity instance that will be initialized.
   * @param mapObject
   *          The mapObject that provides the static information for the new entity.
   * 
   * @see TmxProperty
   */
  public static void loadDefaultProperties(IEntity entity, IMapObject mapObject) {
    entity.setMapId(mapObject.getId());
    entity.setWidth(mapObject.getWidth());
    entity.setHeight(mapObject.getHeight());
    entity.setName(mapObject.getName());
    entity.setLocation(mapObject.getLocation());

    String tagsString = mapObject.getStringValue(MapObjectProperty.TAGS);
    if (tagsString != null && tagsString.trim().length() > 0) {
      String[] tags = tagsString.split(",");

      for (String rawTag : tags) {
        String tag = rawTag.trim().replaceAll("[^A-Za-z0-9\\-\\_]", "");
        if (tag == null || tag.isEmpty()) {
          continue;
        }

        entity.addTag(tag);
      }
    }

    loadCustomMapObjectProperties(entity, mapObject);
  }

  private static void loadCustomMapObjectProperties(IEntity entity, IMapObject mapObject) {
    for (final Field field : entity.getClass().getDeclaredFields()) {
      TmxProperty property = field.getAnnotation(TmxProperty.class);
      if (property == null) {
        continue;
      }

      String value = mapObject.getStringValue(property.name(), null);
      if (value == null) {
        continue;
      }

      ReflectionUtilities.setFieldValue(entity.getClass(), entity, field.getName(), value);
    }
  }

  public static void loadCollisionProperties(ICollisionEntity entity, IMapObject mapObject) {
    entity.setCollision(mapObject.getBoolValue(MapObjectProperty.COLLISION, true));
    entity.setCollisionBoxWidth(mapObject.getFloatValue(MapObjectProperty.COLLISIONBOX_WIDTH, mapObject.getWidth()));
    entity.setCollisionBoxHeight(mapObject.getFloatValue(MapObjectProperty.COLLISIONBOX_HEIGHT, mapObject.getHeight()));
    entity.setCollisionBoxAlign(mapObject.getEnumValue(MapObjectProperty.COLLISION_ALIGN, Align.class, Align.CENTER));
    entity.setCollisionBoxValign(mapObject.getEnumValue(MapObjectProperty.COLLISION_VALIGN, Valign.class, Valign.DOWN));
  }
}
