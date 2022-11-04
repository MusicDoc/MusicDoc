package io.github.musicdoc.view.data;

/**
 * Interface to {@link #getWidth() read} and {@link #setWidth(double) write} {@link #getWidth() width}.
 */
public interface ViewAttributeWriteWidth extends ViewAttributeReadWidth {

  /**
   * @param width the new value of {@link #getWidth()}.
   */
  void setWidth(double width);

  /**
   * @param newWidthIfGreater new value of {@link #getWidth()} if greater than current {@link #getWidth() height}.
   */
  default void setWidthMax(double newWidthIfGreater) {

    if (newWidthIfGreater > getWidth()) {
      setWidth(newWidthIfGreater);
    }
  }

  /**
   * @param wOffset the offset to add to {@link #getWidth() width}.
   */
  default void addWidth(double wOffset) {

    setWidth(getWidth() + wOffset);
  }

}
