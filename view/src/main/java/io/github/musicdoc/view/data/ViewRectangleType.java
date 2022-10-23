package io.github.musicdoc.view.data;

import java.util.Objects;

/**
 * Implementation of {@link ViewRectangle} as immutable type.
 */
public class ViewRectangleType implements ViewRectangle {

  private final ViewPositionType topLeft;

  private final ViewPositionType bottomRight;

  /**
   * The constructor.
   *
   * @param topLeft the {@link #getPositionTopLeft() top left position}.
   * @param bottomRight the {@link #getPositionBottomRight() bottom right position}.
   */
  public ViewRectangleType(ViewPositionType topLeft, ViewPositionType bottomRight) {

    super();
    this.topLeft = topLeft;
    this.bottomRight = bottomRight;
    assert (getWidth() >= 0);
    assert (getHeight() >= 0);
  }

  /**
   * The constructor.
   *
   * @param rectangle the {@link ViewRectangle} to copy.
   */
  public ViewRectangleType(ViewRectangle rectangle) {

    this(rectangle.getX0(), rectangle.getY1(), rectangle.getWidth(), rectangle.getHeight());
  }

  /**
   * The constructor.
   *
   * @param x0 the {@link #getX0() x0 coordinate}.
   * @param y0 the {@link #getY0() y0 coordinate}.
   * @param width the {@link #getWidth() width}.
   * @param height the {@link #getHeight() height}.
   */
  public ViewRectangleType(double x0, double y0, double width, double height) {

    super();
    assert (width >= 0);
    assert (height >= 0);
    this.topLeft = new ViewPositionType(x0, y0);
    this.bottomRight = new ViewPositionType(x0 + width, y0 + height);
  }

  @Override
  public ViewPosition getPositionTopLeft() {

    return this.topLeft;
  }

  @Override
  public ViewPosition getPositionBottomRight() {

    return this.bottomRight;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.topLeft, this.bottomRight);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if (!super.equals(obj)) {
      return false;
    }
    ViewRectangleType other = (ViewRectangleType) obj;
    return Objects.equals(this.topLeft, other.topLeft) && Objects.equals(this.bottomRight, other.bottomRight);
  }

  @Override
  public String toString() {

    return this.topLeft + "->" + this.bottomRight + "[" + getWidth() + "x" + getHeight() + "]";
  }

}
