package de.gurkenlabs.litiengine.environment.tilemap.xml;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import de.gurkenlabs.litiengine.environment.tilemap.ICustomProperty;
import de.gurkenlabs.litiengine.environment.tilemap.ITile;
import de.gurkenlabs.litiengine.environment.tilemap.ITilesetEntry;

@XmlAccessorType(XmlAccessType.FIELD)
public class Tile extends CustomPropertyProvider implements ITile, Serializable {
  public static final int NONE = 0;
  public static final Tile EMPTY = new Tile(NONE);
  protected static final int FLIPPED_HORIZONTALLY_FLAG = 0x80000000;
  protected static final int FLIPPED_VERTICALLY_FLAG = 0x40000000;
  protected static final int FLIPPED_DIAGONALLY_FLAG = 0x20000000;

  private static final long serialVersionUID = -7597673646108642906L;

  @XmlAttribute
  private Integer gid;

  private transient Point tileCoordinate;

  private transient ITilesetEntry tilesetEntry;

  private transient boolean flippedDiagonally;
  private transient boolean flippedHorizontally;
  private transient boolean flippedVertically;
  private transient boolean flipped;

  public Tile() {
  }

  public Tile(int gidBitmask) {
    // Clear the flags
    this.flippedDiagonally = (gidBitmask & FLIPPED_DIAGONALLY_FLAG) != 0;
    this.flippedHorizontally = (gidBitmask & FLIPPED_HORIZONTALLY_FLAG) != 0;
    this.flippedVertically = (gidBitmask & FLIPPED_VERTICALLY_FLAG) != 0;

    this.flipped = this.isFlippedDiagonally() || this.isFlippedHorizontally() || this.isFlippedVertically();
    this.gid = gidBitmask & ~(FLIPPED_HORIZONTALLY_FLAG | FLIPPED_VERTICALLY_FLAG | FLIPPED_DIAGONALLY_FLAG);
  }

  @Override
  public boolean hasCustomProperty(String name) {
    return this.getTilesetEntry() == null ? super.hasCustomProperty(name) : this.getTilesetEntry().hasCustomProperty(name);
  }

  @Override
  public java.util.Map<String, ICustomProperty> getProperties() {
    return this.getTilesetEntry() == null ? super.getProperties() : this.getTilesetEntry().getProperties();
  }

  @Override
  public void setProperties(java.util.Map<String, ICustomProperty> props) {
    if (this.getTilesetEntry() == null) {
      super.setProperties(props);
    } else {
      this.getTilesetEntry().setProperties(props);
    }
  }

  @Override
  public boolean isFlippedDiagonally() {
    return this.flippedDiagonally;
  }

  @Override
  public boolean isFlippedHorizontally() {
    return this.flippedHorizontally;
  }

  @Override
  public boolean isFlippedVertically() {
    return this.flippedVertically;
  }

  @Override
  public boolean isFlipped() {
    return this.flipped;
  }

  @Override
  public int getGridId() {
    if (this.gid == null) {
      return 0;
    }

    return this.gid;
  }

  @Override
  public Point getTileCoordinate() {
    return this.tileCoordinate;
  }

  /**
   * Sets the tile coordinate.
   *
   * @param tileCoordinate
   *          the new tile coordinate
   */
  public void setTileCoordinate(final Point tileCoordinate) {
    this.tileCoordinate = tileCoordinate;
  }

  @Override
  public boolean equals(Object anObject) {
    if (this == anObject) {
      return true;
    }
    if (!(anObject instanceof ITile)) {
      return false;
    }
    ITile other = (ITile) anObject;
    return this.getGridId() == other.getGridId() && this.isFlippedDiagonally() == other.isFlippedDiagonally()
        && this.isFlippedHorizontally() == other.isFlippedHorizontally() && this.isFlippedVertically() == other.isFlippedVertically();
  }

  @Override
  public int hashCode() {
    int hash = this.getGridId();
    if (this.isFlipped()) {
      if (this.isFlippedDiagonally()) {
        hash |= FLIPPED_DIAGONALLY_FLAG;
      }
      if (this.isFlippedHorizontally()) {
        hash |= FLIPPED_HORIZONTALLY_FLAG;
      }
      if (this.isFlippedVertically()) {
        hash |= FLIPPED_VERTICALLY_FLAG;
      }
    }
    return hash ^ Objects.hashCode(this.getTilesetEntry());
  }

  @Override
  public String toString() {
    return this.getGridId() + String.valueOf(this.getTilesetEntry());
  }

  protected void setTilesetEntry(ITilesetEntry entry) {
    this.tilesetEntry = entry;
  }

  @Override
  public ITilesetEntry getTilesetEntry() {
    return this.tilesetEntry;
  }

  @SuppressWarnings("unused")
  private void beforeMarshal(Marshaller m) {
    if (this.gid != null && this.gid == 0) {
      this.gid = null;
    }
  }
}
