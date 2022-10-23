package io.github.musicdoc.view.data;

/**
 * Interface for the size or dimensions of a rectangle as {@link #getWidth() width} and {@link #getHeight() height}.
 */
public interface ViewSize extends ViewAttributeHeight, ViewAttributeWidth {

  /**
   * @param width the {@link #getWidth() width}.
   * @param height the {@link #getHeight() height}.
   * @return the new {@link ViewSizeType} for the given values.
   */
  static ViewSizeType of(double width, double height) {

    return new ViewSizeType(width, height);
  }

}
