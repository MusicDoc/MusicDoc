package io.github.musicdoc.view.layout;

/**
 * Bean with the {@link #getMin() minimum} and {@link #getMax() maximum} y coordinate of the horizontal row for the
 * according {@link ViewPlacement}. Allows to compute the required height and transform from relative to absolute
 * coordinates. First items get rendered relative to the font baseline with negative y coordinate if above the baseline
 * and positive y coordinate if below the baseline. After the relative rendering of the entire row is completed, the
 * {@link #getMin() minimum} and {@link #getMax() maximum} y coordinates are used to move all items vertically such that
 * the {@link #getMin() minimum} y position is at the current y rendering position and the {@link #getHeight() height}
 * can be added to advance the rendering position for the next row.
 */
public class ViewPlacementHeight {

  private double min;

  private double max;

  /**
   * @return the minimum y coordinate of the horizontal row for the according {@link ViewPlacement}.
   */
  public double getMin() {

    return this.min;
  }

  /**
   * @param min new value of {@link #getMin()}.
   */
  public void setMin(double min) {

    this.min = min;
  }

  /**
   * @return the maximum y coordinate of the horizontal row for the according {@link ViewPlacement}.
   */
  public double getMax() {

    return this.max;
  }

  /**
   * @param max new value of {@link #getMax()}.
   */
  public void setMax(double max) {

    this.max = max;
  }

  /**
   * @return the (current) height as {@link #getMax() max} - {@link #getMin() min}.
   */
  public double getHeight() {

    return this.max - this.min;
  }

  /**
   * @param y0 the top y coordinate to consider as {@link #getMin() mimimum}.
   * @param y1 the bottom y coordinate to consider as {@link #getMax() maximum}.
   */
  public void visit(double y0, double y1) {

    if (y0 < this.min) {
      this.min = y0;
    }
    if (y1 > this.max) {
      this.max = y1;
    }
  }

}
