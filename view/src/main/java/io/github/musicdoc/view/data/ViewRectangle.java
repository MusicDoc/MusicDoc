package io.github.musicdoc.view.data;

/**
 * Interface for a rectangle. It is defined by two {@link ViewPosition positions}, the {@link #getTopLeft() top
 * left position} as {@link #getX0() x0} and {@link #getY0() y0} together with the {@link #getBottomRight()
 * bottom right position} as {@link #getX1() x1} and {@link #getY1() y1}. It also implements {@link ViewSize} providing
 * access to {@link #getWidth() width} and {@link #getHeight() height}.
 */
public interface ViewRectangle extends ViewSize {

  /**
   * @return the top left {@link ViewPosition} of the rectangle.
   */
  ViewPosition getTopLeft();

  /**
   * @return the bottom right {@link ViewPosition} of the rectangle.
   */
  ViewPosition getBottomRight();

  /**
   * @return the left x coordinate of the rectangle.
   */
  default double getX0() {

    return getTopLeft().getX();
  }

  /**
   * @return the top y coordinate of the rectangle.
   */
  default double getY0() {

    return getTopLeft().getY();
  }

  /**
   * @return the right x coordinate of the rectangle.
   */
  default double getX1() {

    return getBottomRight().getX();
  }

  /**
   * @return the bottom y coordinate the rectangle.
   */
  default double getY1() {

    return getBottomRight().getY();
  }

  @Override
  default double getWidth() {

    return getBottomRight().getX() - getTopLeft().getX();
  }

  @Override
  default double getHeight() {

    return getBottomRight().getY() - getTopLeft().getY();
  }

  /**
   * @param x the {@link #getX0() left x coordinate}.
   * @param y the {@link #getY0() top y coordinate}.
   * @param width the {@link #getWidth() width}.
   * @param height the {@link #getHeight() height}.
   * @return the {@link ViewRectangle} with the given values.
   */
  static ViewRectangleType of(double x, double y, double width, double height) {

    return new ViewRectangleType(x, y, width, height);
  }

  /**
   * @param topLeft the {@link #getTopLeft() top left position}.
   * @param bottomRight the {@link #getBottomRight() bottom right position}.
   * @return the {@link ViewRectangleType} from the given {@link ViewPosition}s.
   */
  static ViewRectangleType of(ViewPosition topLeft, ViewPosition bottomRight) {

    return new ViewRectangleType(ViewPositionType.as(topLeft), ViewPositionType.as(bottomRight));
  }

  /**
   * @param topLeft the {@link #getTopLeft() top left position}.
   * @param size the {@link ViewSize size} of the rectangle.
   * @return the {@link ViewRectangleType} fSrom the given {@link ViewPosition}s.
   */
  static ViewRectangleType of(ViewPosition topLeft, ViewSize size) {

    return new ViewRectangleType(ViewPositionType.as(topLeft),
        ViewPosition.of(topLeft.getX() + size.getWidth(), topLeft.getY() + size.getHeight()));
  }

}
