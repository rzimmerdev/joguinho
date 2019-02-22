package de.gurkenlabs.litiengine.graphics.animation;

import java.util.EventListener;

/**
 * This listener provides call-backs for when an <code>Animation</code> is played or the play back was finished.
 */
public interface AnimationListener extends EventListener {
  /**
   * Called when the specified animation has started playing.
   * 
   * @param animation
   *          The animation that is now played.
   */
  public void played(Animation animation);

  /**
   * Called when the specified animation has finished playing.
   * 
   * @param animation
   *          The animation that has just finished playing.
   */
  public void finished(Animation animation);
}
