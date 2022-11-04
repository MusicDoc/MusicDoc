package io.github.musicdoc.view.model;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.view.layout.ViewPlacementType;

/**
 * {@link ViewItem} for the {@link Stave} itself (typically 5 horizontal lines).
 */
public class ViewItemStave extends ViewItem {

  /**
   * The constructor.
   *
   * @param stave the {@link #getStave() stave}.
   */
  public ViewItemStave(Stave stave) {

    super(stave);
  }

  @Override
  public ViewPlacementType getPlacementType() {

    return ViewPlacementType.STAVE;
  }

}
