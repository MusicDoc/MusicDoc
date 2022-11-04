package io.github.musicdoc.view.data;

/**
 * Interface for a position as {@link #getX() x} and {@link #getY() y} coordinate.
 */
public interface ViewPosition extends ViewAttributeReadX, ViewAttributeReadY {

  /**
   * @param x the {@link #getX() x coordinate}.
   * @param y the {@link #getY() y coordinate}.
   * @return the new {@link ViewPositionType} for the given values.
   */
  static ViewPositionType of(double x, double y) {

    return new ViewPositionType(x, y);
  }

}
