package io.github.musicdoc.view.data;

/**
 * Interface to {@link #getX() read} and {@link #setX(double) write} {@link #getX() x-coordinate}.
 */
public interface ViewAttributeWriteX extends ViewAttributeReadX, ViewAttributeAddX {

  /**
   * @param x the new value of {@link #getX()}.
   */
  void setX(double x);

  @Override
  default void addX(double xOffset) {

    setX(getX() + xOffset);
  }

}
