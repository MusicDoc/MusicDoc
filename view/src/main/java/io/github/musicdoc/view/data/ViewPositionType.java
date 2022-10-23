package io.github.musicdoc.view.data;

import java.util.Objects;

/**
 * Implementation of {@link ViewPosition} as immutable type.
 */
public class ViewPositionType implements ViewPosition {

  private final double x;

  private final double y;

  /**
   * The constructor.
   *
   * @param x the {@link #getX() x coordinate}.
   * @param y the {@link #getY() y coordinate}.
   */
  public ViewPositionType(double x, double y) {

    super();
    this.x = x;
    this.y = y;
  }

  /**
   * The copy-constructor.
   *
   * @param position the {@link ViewPosition} to copy.
   */
  public ViewPositionType(ViewPosition position) {

    this(position.getX(), position.getY());
  }

  @Override
  public double getX() {

    return this.x;
  }

  @Override
  public double getY() {

    return this.y;
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
    ViewPositionType other = (ViewPositionType) obj;
    return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(other.x)
        && Double.doubleToLongBits(this.y) == Double.doubleToLongBits(other.y);
  }

  @Override
  public String toString() {

    return this.x + "," + this.y;
  }

  /**
   * @param position the {@link ViewPosition} to cast or copy.
   * @return a {@link ViewPositionType} from the given {@link ViewPosition}.
   */
  public static ViewPositionType as(ViewPosition position) {

    if (position instanceof ViewPositionType) {
      return (ViewPositionType) position;
    }
    return new ViewPositionType(position);
  }

}
