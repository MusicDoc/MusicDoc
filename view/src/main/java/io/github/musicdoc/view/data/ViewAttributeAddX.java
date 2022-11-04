package io.github.musicdoc.view.data;

/**
 * Interface to move by {@link #addX(double) adding an x offset}.
 */
public interface ViewAttributeAddX {

  /**
   * @param xOffset the x-offset to add to {@link ViewAttributeReadX#getX() x coordinate(s)}.
   */
  void addX(double xOffset);

}
