package io.github.musicdoc.view.model;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.view.data.ViewPositionBean;
import io.github.musicdoc.view.layout.ViewPlacement;

/**
 * {@link ViewItem} for the {@link Stave} itself (typically 5 horizontal lines).
 */
public class ViewItemStave extends ViewItem {

  private Stave stave;

  /**
   * The constructor.
   *
   * @param placement the {@link #getPlacement() placement}.
   * @param stave the {@link #getStave() stave}.
   * @param topLeft the {@link #getPositionTopLeft() top left position}.
   * @param bottomRight the {@link #getPositionBottomRight() bottom right position}.
   */
  public ViewItemStave(ViewPlacement placement, Stave stave, ViewPositionBean topLeft, ViewPositionBean bottomRight) {

    super(placement, topLeft, bottomRight);
    this.stave = stave;
  }

  /**
   * The constructor.
   *
   * @param placement the {@link #getPlacement() placement}.
   * @param stave the {@link #getStave() stave}.
   */
  public ViewItemStave(ViewPlacement placement, Stave stave) {

    super(placement);
    this.stave = stave;
  }

  /**
   * @return stave the {@link Stave}.
   */
  public Stave getStave() {

    return this.stave;
  }

  /**
   * @param stave new value of {@link #getStave()}.
   */
  public void setStave(Stave stave) {

    this.stave = stave;
  }

}
