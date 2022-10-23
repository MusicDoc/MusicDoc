package io.github.musicdoc.view.model;

import java.util.Objects;

import io.github.musicdoc.view.data.ViewPositionBean;
import io.github.musicdoc.view.data.ViewPositionType;
import io.github.musicdoc.view.data.ViewRectangle;
import io.github.musicdoc.view.data.ViewRectangleBean;
import io.github.musicdoc.view.layout.ViewPlacement;

/**
 * A single item that can be drawn. Defines a {@link ViewRectangle rectangle} with the bounding box of its content on
 * the page or screen.
 */
public class ViewItem extends ViewRectangleBean {

  private final ViewPlacement placement;

  /**
   * The constructor.
   *
   * @param placement the {@link #getPlacement() placement}.
   */
  public ViewItem(ViewPlacement placement) {

    super();
    Objects.requireNonNull(placement);
    this.placement = placement;
  }

  /**
   * The constructor.
   *
   * @param placement the {@link #getPlacement() placement}.
   * @param topLeft the {@link #getPositionTopLeft() top left position}.
   * @param bottomRight the {@link #getPositionBottomRight() bottom right position}.
   */
  public ViewItem(ViewPlacement placement, ViewPositionBean topLeft, ViewPositionBean bottomRight) {

    super(topLeft, bottomRight);
    this.placement = placement;
  }

  /**
   * @return the {@link ViewPositionType position} where to start drawing. Typically {@link #getPositionTopLeft() top
   *         left position} but may also be a position within the rectangle, e.g. to text that is baseline aligned and
   *         may have a tilt or in case of an ellipse the center of the rectangle.
   */
  public ViewPositionBean getPositionDraw() {

    return getPositionTopLeft();
  }

  /**
   * @return the {@link ViewPlacement} used for vertical placement and alignment of horizontal rows.
   */
  public ViewPlacement getPlacement() {

    return this.placement;
  }

}
