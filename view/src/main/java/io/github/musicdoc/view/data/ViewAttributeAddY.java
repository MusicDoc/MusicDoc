package io.github.musicdoc.view.data;

/**
 * Interface to move by {@link #addY(double) adding an y offset}.
 */
public interface ViewAttributeAddY {

  /**
   * @param yOffset the y-offset to add to {@link ViewAttributeReadY#getY() y coordinate(s)}.
   */
  void addY(double yOffset);

}
