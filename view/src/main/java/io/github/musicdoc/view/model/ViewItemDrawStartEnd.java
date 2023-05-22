package io.github.musicdoc.view.model;

import io.github.musicdoc.view.data.ViewPositionBean;

/**
 * {@link ViewItem} with individual {@link #getDrawStart() draw start} and {@link #getDrawEnd() draw end} positions.
 *
 */
public abstract class ViewItemDrawStartEnd extends ViewItemDrawStart {

  private ViewPositionBean drawEnd;

  @Override
  public ViewPositionBean getDrawEnd() {

    return this.drawEnd;
  }

  @Override
  public void add(double xOffset, double yOffset) {

    super.add(xOffset, yOffset);
    this.drawEnd.add(xOffset, yOffset);
  }
}
