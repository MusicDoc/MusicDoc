package io.github.musicdoc.view.data;

/**
 * Interface for a position as {@link #getX() x} and {@link #getY() y} coordinate.
 */
public interface ViewPosition {

  /**
   * @return the x coordinate on the horizontal axis with {@code 0} as the left of the page or screen.
   */
  double getX();

  /**
   * @return the y coordinate of the vertical axis with {@code 0} as the top of the page or screen.
   */
  double getY();

  /**
   * @param x the {@link #getX() x coordinate}.
   * @param y the {@link #getY() y coordinate}.
   * @return the new {@link ViewPositionType} for the given values.
   */
  static ViewPositionType of(double x, double y) {

    return new ViewPositionType(x, y);
  }

}
