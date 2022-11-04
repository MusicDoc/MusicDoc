package io.github.musicdoc.view.data;

/**
 * Interface to {@link #getHeight() read} and {@link #setHeight(double) write} {@link #getHeight() height}.
 */
public interface ViewAttributeWriteHeight extends ViewAttributeReadHeight {

  /**
   * @param height the new value of {@link #getHeight()}.
   */
  void setHeight(double height);

  /**
   * @param newHeightIfGreater new value of {@link #getHeight()} if greater than current {@link #getHeight() height}.
   */
  default void setHeightMax(double newHeightIfGreater) {

    if (newHeightIfGreater > getHeight()) {
      setHeight(newHeightIfGreater);
    }
  }

  /**
   * @param hOffset the offset to add to {@link #getHeight() height}.
   */
  default void addHeight(double hOffset) {

    setHeight(getHeight() + hOffset);
  }

}
