package io.github.musicdoc.view.data;

import java.util.Objects;

/**
 * Implementation of {@link ViewSize} as immutable type.
 */
public class ViewSizeBean implements ViewSize {

  /** @see #getWidth() */
  protected double width;

  /** @see #getHeight() */
  protected double height;

  /**
   * The constructor.
   */
  public ViewSizeBean() {

    super();
  }

  /**
   * The constructor.
   *
   * @param width the {@link #getWidth() width}.
   * @param height the {@link #getHeight() height}.
   */
  public ViewSizeBean(double width, double height) {

    super();
    this.width = width;
    this.height = height;
  }

  @Override
  public double getWidth() {

    return this.width;
  }

  /**
   * @param width new value of {@link #getWidth()}.
   */
  public void setWidth(double width) {

    this.width = width;
  }

  /**
   * @param newWidthIfGreater new value of {@link #getWidth()} if greater than current {@link #getWidth() width}.
   */
  public void setWidthMax(double newWidthIfGreater) {

    if (newWidthIfGreater > this.width) {
      this.width = newWidthIfGreater;
    }
  }

  /**
   * @param wOffset the offset to add to {@link #getWidth() width}.
   */
  public void addWidth(double wOffset) {

    this.width += wOffset;
  }

  @Override
  public double getHeight() {

    return this.height;
  }

  /**
   * @param height new value of {@link #getHeight()}.
   */
  public void setHeight(double height) {

    this.height = height;
  }

  /**
   * @param newHeightIfGreater new value of {@link #getHeight()} if greater than current {@link #getHeight() height}.
   */
  public void setHeightMax(double newHeightIfGreater) {

    if (newHeightIfGreater > this.height) {
      this.height = newHeightIfGreater;
    }
  }

  /**
   * @param hOffset the offset to add to {@link #getHeight() height}.
   */
  public void addHeight(double hOffset) {

    this.height += hOffset;
  }

  /**
   * @param wOffset the offset to add to {@link #getWidth() width}.
   * @param hOffset the offset to add to {@link #getHeight() height}.
   */
  public void add(double wOffset, double hOffset) {

    this.height += hOffset;
    this.height += hOffset;
  }

  /**
   * @param size the {@link ViewSize} to {@link #add(double, double) add}.
   */
  public void add(ViewSize size) {

    add(size.getWidth(), size.getHeight());
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
    ViewSizeBean other = (ViewSizeBean) obj;
    return Double.doubleToLongBits(this.height) == Double.doubleToLongBits(other.height)
        && Double.doubleToLongBits(this.width) == Double.doubleToLongBits(other.width);
  }

  @Override
  public String toString() {

    return this.width + "x" + this.height;
  }

}
