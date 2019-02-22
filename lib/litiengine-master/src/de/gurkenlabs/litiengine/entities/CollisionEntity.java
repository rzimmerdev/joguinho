package de.gurkenlabs.litiengine.entities;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.annotation.CollisionInfo;
import de.gurkenlabs.litiengine.environment.tilemap.MapObjectProperty;
import de.gurkenlabs.litiengine.environment.tilemap.TmxProperty;

@CollisionInfo(collision = true)
public abstract class CollisionEntity extends Entity implements ICollisionEntity {
  private static final double HEIGHT_FACTOR = 0.4;

  private static final double WIDTH_FACTOR = 0.4;

  @TmxProperty(name = MapObjectProperty.COLLISION_ALIGN)
  private Align align = Align.CENTER;

  @TmxProperty(name = MapObjectProperty.COLLISION)
  private boolean collision;

  @TmxProperty(name = MapObjectProperty.COLLISIONBOX_HEIGHT)
  private float collisionBoxHeight;

  @TmxProperty(name = MapObjectProperty.COLLISIONBOX_WIDTH)
  private float collisionBoxWidth;

  @TmxProperty(name = MapObjectProperty.COLLISION_VALIGN)
  private Valign valign = Valign.DOWN;
  
  private Rectangle2D collisionBox;

  protected CollisionEntity() {
    super();
    final CollisionInfo info = this.getClass().getAnnotation(CollisionInfo.class);
    this.collisionBoxWidth = info.collisionBoxWidth();
    this.collisionBoxHeight = info.collisionBoxHeight();
    this.collision = info.collision();
    this.setCollisionBoxValign(info.valign());
    this.setCollisionBoxAlign(info.align());
    this.collisionBox = this.getCollisionBox(this.getLocation());
  }

  public static Rectangle2D getCollisionBox(final Point2D location, final double entityWidth, final double entityHeight, final double collisionBoxWidth, final double collisionBoxHeight, final Align align, final Valign valign) {
    double x = location.getX() + align.getLocation(entityWidth, collisionBoxWidth);
    double y = location.getY() + valign.getLocation(entityHeight, collisionBoxHeight);
    return new Rectangle2D.Double(x, y, collisionBoxWidth, collisionBoxHeight);
  }

  @Override
  public boolean canCollideWith(final ICollisionEntity otherEntity) {
    return true;
  }

  @Override
  public Align getCollisionBoxAlign() {
    return this.align;
  }

  /**
   * Gets the collision box.
   *
   * @return the collision box
   */
  @Override
  public Rectangle2D getCollisionBox() {
    return this.collisionBox;
  }

  /**
   * Gets the collision box.
   *
   * @param location
   *          the location
   * @return the collision box
   */
  @Override
  public Rectangle2D getCollisionBox(final Point2D location) {
    final double newCollisionBoxWidth = this.getCollisionBoxWidth() != -1 ? this.getCollisionBoxWidth() : this.getWidth() * WIDTH_FACTOR;
    final double newCollisionBoxHeight = this.getCollisionBoxHeight() != -1 ? this.getCollisionBoxHeight() : this.getHeight() * HEIGHT_FACTOR;

    return getCollisionBox(location, this.getWidth(), this.getHeight(), newCollisionBoxWidth, newCollisionBoxHeight, this.getCollisionBoxAlign(), this.getCollisionBoxValign());
  }

  @Override
  public float getCollisionBoxHeight() {
    return this.collisionBoxHeight;
  }

  @Override
  public float getCollisionBoxWidth() {
    return this.collisionBoxWidth;
  }

  @Override
  public Point2D getCollisionBoxCenter() {
    return new Point2D.Double(this.getCollisionBox().getCenterX(), this.getCollisionBox().getCenterY());
  }

  @Override
  public Valign getCollisionBoxValign() {
    return this.valign;
  }

  /**
   * Checks for collision.
   *
   * @return true, if successful
   */
  @Override
  public boolean hasCollision() {
    return this.collision;
  }

  /**
   * Sets the collision.
   *
   * @param collision
   *          the new collision
   */
  @Override
  public void setCollision(final boolean collision) {
    this.collision = collision;
  }

  @Override
  public void setCollisionBoxAlign(final Align align) {
    this.align = align;
    this.collisionBox = this.getCollisionBox(this.getLocation());
  }

  @Override
  public void setCollisionBoxHeight(final float collisionBoxHeight) {
    this.collisionBoxHeight = collisionBoxHeight;
    this.collisionBox = this.getCollisionBox(this.getLocation());
  }

  @Override
  public void setCollisionBoxValign(final Valign valign) {
    this.valign = valign;
    this.collisionBox = this.getCollisionBox(this.getLocation());
  }

  @Override
  public void setCollisionBoxWidth(final float collisionBoxWidth) {
    this.collisionBoxWidth = collisionBoxWidth;
    this.collisionBox = this.getCollisionBox(this.getLocation());
  }

  @Override
  public void setLocation(final Point2D location) {
    super.setLocation(location);
    this.collisionBox = this.getCollisionBox(this.getLocation());
  }

  @Override
  public void setSize(final float width, final float height) {
    super.setSize(width, height);
    this.collisionBox = this.getCollisionBox(this.getLocation());
  }

  @Override
  public void setHeight(final float height) {
    super.setHeight(height);
    this.collisionBox = this.getCollisionBox(this.getLocation());
  }

  @Override
  public void setWidth(final float width) {
    super.setWidth(width);
    this.collisionBox = this.getCollisionBox(this.getLocation());
  }
}
