package io.github.musicdoc.view.data;

/**
 * Interface to {@link #getY() read} and {@link #setY(double) write} {@link #getY() y-coordinate}.
 */
public interface ViewAttributeWriteY extends ViewAttributeReadY, ViewAttributeAddY {

  /**
   * @param y the new value of {@link #getY()}.
   */
  void setY(double y);

  @Override
  default void addY(double yOffset) {

    setY(getY() + yOffset);
  }

}
