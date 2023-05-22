package io.github.musicdoc.view.model;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.data.ViewPositionBean;

/**
 * {@link ViewItem} with individual {@link #getDrawStart() draw start} position.
 */
public abstract class ViewItemDrawStart extends ViewItem {

  private ViewPositionBean drawStart;

  /**
   * The constructor for absolute items that do not {@link #getStave() have a stave associated}.
   */
  public ViewItemDrawStart() {

    super();
  }

  /**
   * The constructor for items that {@link #getStave() have a stave associated} but no {@link #getVoice() voice}.
   *
   * @param stave the {@link #getStave() stave}.
   */
  public ViewItemDrawStart(Stave stave) {

    super(stave);
  }

  /**
   * The constructor for items that {@link #getVoice() have a voice associated} (and a {@link #getStave() stave}).
   *
   * @param voice the {@link #getVoice() voice}.
   */
  public ViewItemDrawStart(StaveVoice voice) {

    super(voice);
  }

  @Override
  public ViewPositionBean getDrawStart() {

    if (this.drawStart == null) {
      this.drawStart = new ViewPositionBean();
    }
    return this.drawStart;
  }

  /**
   * @param draw the new value of {@link #getDrawStart()}.
   */
  public void setDrawStart(ViewPositionBean draw) {

    this.drawStart = draw;
  }

  @Override
  public void add(double xOffset, double yOffset) {

    super.add(xOffset, yOffset);
    this.drawStart.add(xOffset, yOffset);
  }

}
