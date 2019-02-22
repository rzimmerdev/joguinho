package de.gurkenlabs.litiengine.graphics.emitters;

import java.awt.Color;
import java.util.Random;

import de.gurkenlabs.litiengine.annotation.EmitterInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.graphics.emitters.particles.Particle;
import de.gurkenlabs.litiengine.graphics.emitters.particles.ShimmerParticle;

/**
 * The Class ShimmerEmitter.
 */
@EntityInfo(width = 64, height = 64)
@EmitterInfo(maxParticles = 10, spawnAmount = 10, spawnRate = 10, activateOnInit = true)
public class ShimmerEmitter extends Emitter {

  /**
   * Instantiates a new shimmer emitter.
   *
   * @param originX
   *          the origin x
   * @param originY
   *          the origin y
   */
  public ShimmerEmitter(final double originX, final double originY) {
    super(originX, originY);
  }

  @Override
  public Particle createNewParticle() {
    final Random rand = new Random();

    final float x = rand.nextInt((int) this.getWidth());
    final float y = rand.nextInt((int) this.getHeight());
    final float randX = rand.nextFloat();
    final float dx = Math.random() >= 0.5 ? -randX : randX;
    final float dy = Math.random() >= 0.5 ? -randX : randX;
    final byte size = (byte) (rand.nextInt(3) + 2);

    Color color = new Color(255, 255, 255, new Random().nextInt(155) + 100);
    if (rand.nextFloat() > 0.5) {
      color = new Color(170, 255, 255, new Random().nextInt(155) + 100);
    }

    return new ShimmerParticle(this.getBoundingBox(), size, size, color).setX(x).setY(y).setDeltaX(dx).setDeltaY(dy);
  }
}