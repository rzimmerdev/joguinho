package de.gurkenlabs.litiengine.graphics.emitters;

import java.awt.Image;
import java.util.Random;

import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.emitters.particles.Particle;
import de.gurkenlabs.litiengine.graphics.emitters.particles.SpriteParticle;

public class SpritesheetEntityEmitter extends EntityEmitter {

  private final Spritesheet spriteSheet;

  public SpritesheetEntityEmitter(final Spritesheet spriteSheet, final IEntity entity) {
    super(entity);
    this.spriteSheet = spriteSheet;
    this.setSize(this.getSpritesheet().getSpriteWidth(), this.getSpritesheet().getSpriteHeight());
  }

  public Spritesheet getSpritesheet() {
    return this.spriteSheet;
  }

  protected Image getRandomSprite() {
    return this.getSpritesheet().getSprite(new Random().nextInt(this.getSpritesheet().getTotalNumberOfSprites()));
  }

  @Override
  protected Particle createNewParticle() {
    final int life = this.getRandomParticleTTL();

    return new SpriteParticle(this.getRandomSprite(), life);
  }
}