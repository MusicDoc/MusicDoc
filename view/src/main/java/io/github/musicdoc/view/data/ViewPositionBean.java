package io.github.musicdoc.view.data;

import java.util.Objects;

/**
 * Mutable implementation of {@link ViewPosition}.
 */
public class ViewPositionBean implements ViewPosition, ViewPositionAdd, ViewAttributeWriteX, ViewAttributeWriteY {

  private double x;

  private double y;

  /**
   * The constructor.
   */
  public ViewPositionBean() {

    super();
  }

  /**
   * The constructor.
   *
   * @param x the {@link #getX() x coordinate}.
   * @param y the {@link #getY() y coordinate}.
   */
  public ViewPositionBean(double x, double y) {

    super();
    this.x = x;
    this.y = y;
  }

  /**
   * The copy-constructor.
   *
   * @param position the existing {@link ViewPosition} to copy.
   */
  public ViewPositionBean(ViewPosition position) {

    super();
    if (position != null) {
      this.x = position.getX();
      this.y = position.getY();
    }
  }

  @Override
  public double getX() {

    return this.x;
  }

  @Override
  public void setX(double x) {

    this.x = x;
  }

  @Override
  public void addX(double xOffset) {

    this.x += xOffset;
  }

  @Override
  public double getY() {

    return this.y;
  }

  @Override
  public void setY(double y) {

    this.y = y;
  }

  @Override
  public void addY(double yOffset) {

    this.y += yOffset;
  }

  /**
   * @param x the {@link #getX() x coordinate}.
   * @param y the {@link #getY() y coordinate}.
   */
  public void set(double x, double y) {

    this.x = x;
    this.y = y;
  }

  @Override
  public void add(double xOffset, double yOffset) {

    this.x += xOffset;
    this.y += yOffset;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.x, this.y);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ViewPositionBean other = (ViewPositionBean) obj;
    return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(other.x)
        && Double.doubleToLongBits(this.y) == Double.doubleToLongBits(other.y);
  }

  @Override
  public String toString() {

    return this.x + "," + this.y;
  }

  /**
   * @param position the {@link ViewPosition} to cast or copy.
   * @return a {@link ViewPositionBean} from the given {@link ViewPosition}.
   */
  public static ViewPositionBean as(ViewPosition position) {

    if (position instanceof ViewPositionBean) {
      return (ViewPositionBean) position;
    }
    return new ViewPositionBean(position);
  }

}
