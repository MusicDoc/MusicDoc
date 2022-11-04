package io.github.musicdoc.view.data;

import java.util.Objects;

/**
 * Implementation of {@link ViewRectangle rectangle} as Java bean.
 */
public class ViewRectangleBean implements ViewRectangle, ViewPositionAdd {

  private ViewPositionBean topLeft;

  private ViewPositionBean bottomRight;

  /**
   * The constructor.
   */
  public ViewRectangleBean() {

    super();
  }

  /**
   * The constructor.
   *
   * @param topLeft the {@link #getTopLeft() top left position}.
   * @param bottomRight the {@link #getBottomRight() bottom right position}.
   */
  public ViewRectangleBean(ViewPositionBean topLeft, ViewPositionBean bottomRight) {

    super();
    this.topLeft = topLeft;
    this.bottomRight = bottomRight;
  }

  @Override
  public ViewPositionBean getTopLeft() {

    if (this.topLeft == null) {
      this.topLeft = new ViewPositionBean();
    }
    return this.topLeft;
  }

  /**
   * @param topLeft the new value of {@link #getTopLeft()}.
   */
  public void setTopLeft(ViewPositionBean topLeft) {

    this.topLeft = topLeft;
  }

  @Override
  public ViewPositionBean getBottomRight() {

    if (this.bottomRight == null) {
      this.bottomRight = new ViewPositionBean();
    }
    return this.bottomRight;
  }

  /**
   * @param bottomRight the new value of {@link #getBottomRight()}.
   */
  public void setBottomRight(ViewPositionBean bottomRight) {

    this.bottomRight = bottomRight;
  }

  @Override
  public void add(double xOffset, double yOffset) {

    this.topLeft.add(xOffset, yOffset);
    this.bottomRight.add(xOffset, yOffset);
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
    ViewRectangleBean other = (ViewRectangleBean) obj;
    return Objects.equals(this.topLeft, other.topLeft) && Objects.equals(this.bottomRight, other.bottomRight);
  }

  @Override
  public String toString() {

    return getTopLeft() + "->" + getBottomRight() + "[" + getWidth() + "x" + getHeight() + "]";
  }

}
