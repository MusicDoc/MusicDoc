package io.github.musicdoc.view.model;

import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.layout.ViewPlacementType;
import io.github.musicdoc.volta.Volta;

/**
 * {@link ViewItem} for a {@link Volta}.
 */
public class ViewItemVolta extends ViewItem {

  private final Volta volta;

  /**
   * The constructor.
   *
   * @param voice the {@link #getVoice() voice}.
   * @param volta the {@link #getVolta() volta}.
   */
  public ViewItemVolta(StaveVoice voice, Volta volta) {

    super(voice);
    this.volta = volta;
  }

  /**
   * @return the {@link Volta}.
   */
  public Volta getVolta() {

    return this.volta;
  }

  @Override
  public ViewPlacementType getPlacementType() {

    return ViewPlacementType.VOLTA;
  }

}
