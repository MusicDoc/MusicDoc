package io.github.musicdoc.view.model;

import io.github.musicdoc.view.data.ViewPositionBean;
import io.github.musicdoc.view.layout.ViewPlacement;

/**
 * {@link ViewItem} that has its own {@link #getPositionDraw() draw position}.
 */
public abstract class ViewItemDrawPosition extends ViewItem {

  private ViewPositionBean draw;

  /**
   * The constructor.
   *
   * @param placement the {@link #getPlacement() placement}.
   */
  public ViewItemDrawPosition(ViewPlacement placement) {

    super(placement);
  }

  /**
   * The constructor.
   *
   * @param placement the {@link #getPlacement() placement}.
   * @param topLeft the {@link #getPositionTopLeft() top left position}.
   * @param bottomRight the {@link #getPositionBottomRight() bottom right position}.
   * @param draw the {@link #getPositionDraw() draw position}.
   */
  public ViewItemDrawPosition(ViewPlacement placement, ViewPositionBean topLeft, ViewPositionBean bottomRight,
      ViewPositionBean draw) {

    super(placement, topLeft, bottomRight);
    this.draw = draw;
  }

  @Override
  public ViewPositionBean getPositionDraw() {

    if (this.draw == null) {
      this.draw = new ViewPositionBean();
    }
    return this.draw;
  }

  /**
   * @param draw the new value of {@link #getPositionDraw()}.
   */
  public void setPositionDraw(ViewPositionBean draw) {

    this.draw = draw;
  }

  @Override
  public void add(double xOffset, double yOffset) {

    super.add(xOffset, yOffset);
    this.draw.add(xOffset, yOffset);
  }

}
