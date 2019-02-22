package de.gurkenlabs.litiengine.entities;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.Valign;

public interface ICollisionEntity extends IEntity {
  public boolean canCollideWith(ICollisionEntity otherEntity);

  /**
   * Gets the collision box.
   *
   * @return the collision box
   */
  public Rectangle2D getCollisionBox();

  /**
   * Gets the collision box.
   *
   * @param location
   *          the location
   * @return the collision box
   */
  public Rectangle2D getCollisionBox(Point2D location);

  /**
   * Gets the center {@link Point2D} of the entities collision box.
   * 
   * @return The center {@link Point2D} of the entities collision box
   */
  public Point2D getCollisionBoxCenter();

  public Valign getCollisionBoxValign();

  public Align getCollisionBoxAlign();

  public float getCollisionBoxHeight();

  public float getCollisionBoxWidth();

  /**
   * Checks for collision.
   *
   * @return true, if successful
   */
  public boolean hasCollision();

  /**
   * Sets the collision.
   *
   * @param collision
   *          the new collision
   */
  public void setCollision(boolean collision);

  public void setCollisionBoxHeight(final float collisionBoxHeight);

  public void setCollisionBoxWidth(final float collisionBoxWidth);

  public void setCollisionBoxAlign(final Align align);

  public void setCollisionBoxValign(final Valign valign);
}
