package io.github.musicdoc.view.model;

import io.github.musicdoc.view.layout.ViewPlacementType;

/**
 * {@link ViewItem} to draw an arc like a tie or slur.
 */
public class ViewItemArc extends ViewItemDrawStartEnd {

  private boolean up;

  @Override
  public ViewPlacementType getPlacementType() {

    return ViewPlacementType.STAVE;
  }

  /**
   * @return {@code true} if the arc is bent upwards (towards the top), {@code false} otherwise (if bent downwards
   *         towards the bottom).
   */
  public boolean isUp() {

    return this.up;
  }

  /**
   * @param down new value of {@link #isUp()}.
   */
  public void setUp(boolean down) {

    this.up = down;
  }

}
