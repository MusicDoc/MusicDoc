package io.github.musicdoc.view.layout;

import io.github.musicdoc.view.data.ViewAttributeReadY;

/**
 * Bean with the {@link #getMin() minimum} and {@link #getMax() maximum} y coordinate of the horizontal row for the
 * according {@link ViewPlacement}. Allows to compute the required height and transform from relative to absolute
 * coordinates. First items get rendered relative to the font baseline with negative y coordinate if above the baseline
 * and positive y coordinate if below the baseline. After the relative rendering of the entire row is completed, the
 * {@link #getMin() minimum} and {@link #getMax() maximum} y coordinates are used to move all items vertically such that
 * the {@link #getMin() minimum} y position is at the current y rendering position and the {@link #getLength() height}
 * can be added to advance the rendering position for the next row.
 */
public class ViewPlacementHeight extends ViewMinMaxLayout implements ViewAttributeReadY {

  private double y;

  @Override
  public double getY() {

    return this.y;
  }

  void setYAbsolute(double y) {

    // we want to move to y so that min becomes 0
    this.y = y - this.min;
  }

}
