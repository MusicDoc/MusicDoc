package io.github.musicdoc.view.data;

import java.util.Objects;

/**
 * Implementation of {@link ViewSize} as immutable type.
 */
public class ViewSizeType implements ViewSize {

  private final double width;

  private final double height;

  /**
   * The constructor.
   *
   * @param width the {@link #getWidth() width}.
   * @param height the {@link #getHeight() height}.
   */
  public ViewSizeType(double width, double height) {

    super();
    this.width = width;
    this.height = height;
  }

  @Override
  public double getWidth() {

    return this.width;
  }

  @Override
  public double getHeight() {

    return this.height;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.height, this.width);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ViewSizeType other = (ViewSizeType) obj;
    return Double.doubleToLongBits(this.height) == Double.doubleToLongBits(other.height)
        && Double.doubleToLongBits(this.width) == Double.doubleToLongBits(other.width);
  }

  @Override
  public String toString() {

    return this.width + "x" + this.height;
  }

}
