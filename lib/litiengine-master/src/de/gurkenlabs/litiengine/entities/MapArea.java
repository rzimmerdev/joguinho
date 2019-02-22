package de.gurkenlabs.litiengine.entities;

import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.graphics.RenderType;

@EntityInfo(renderType = RenderType.NONE)
public class MapArea extends Entity {
  public MapArea() {
  }
  
  public MapArea(final double x, final double y, final float width, final float height) {
    this(0, null, x, y, width, height);
  }

  public MapArea(final int id, final String name, final double x, final double y, final float width, final float height) {
    super(id, name);
    this.setLocation(x, y);
    this.setWidth(width);
    this.setHeight(height);
  }
}
