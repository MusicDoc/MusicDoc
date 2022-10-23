package io.github.musicdoc.view.data;

/**
 * Interface for a {@link ViewPosition} based object that can be moved by adding relative x/y offset.
 */
public interface ViewPositionMovable {

  /**
   * @param xOffset the x-offset to add to {@link ViewPosition#getX() x coordinate(s)}.
   */
  default void addX(double xOffset) {

    add(xOffset, 0);
  }

  /**
   * @param yOffset the y-offset to add to {@link ViewPosition#getY() y coordinate(s)}.
   */
  default void addY(double yOffset) {

    add(0, yOffset);
  }

  /**
   * @param xOffset the x-offset to add to {@link ViewPosition#getX() x coordinate(s)}.
   * @param yOffset the y-offset to add to {@link ViewPosition#getY() y coordinate(s)}.
   */
  void add(double xOffset, double yOffset);

  /**
   * @param size the {@link ViewSize} to {@link #add(double, double) add}.
   */
  default void add(ViewSize size) {

    add(size.getWidth(), size.getHeight());
  }

}
