package io.github.musicdoc.view.layout;

/**
 * Bean with the {@link #getMin() minimum} and {@link #getMax() maximum} y coordinate of a horizontal row.
 */
public class ViewMinMaxLayout {

  /** @see #getMin() */
  protected double min;

  /** @see #getMax() */
  protected double max;

  /**
   * @return the minimum coordinate.
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
   * @return the maximum coordinate.
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
   * @return the (current) length as {@link #getMax() max} - {@link #getMin() min}.
   */
  public double getLength() {

    return this.max - this.min;
  }

  /**
   * @param c0 the coordinate to consider as {@link #getMin() mimimum}.
   * @param c1 the coordinate to consider as {@link #getMax() maximum}.
   */
  public void visit(double c0, double c1) {

    if (c0 < this.min) {
      this.min = c0;
    }
    if (c1 > this.max) {
      this.max = c1;
    }
  }

}
