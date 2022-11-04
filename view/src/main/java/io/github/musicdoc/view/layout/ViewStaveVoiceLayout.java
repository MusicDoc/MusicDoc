package io.github.musicdoc.view.layout;

import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * {@link ViewStaveObjectLayout} for a {@link StaveVoice}.
 */
public class ViewStaveVoiceLayout extends ViewStaveObjectLayout {

  private final StaveVoice voice;

  /**
   * The constructor.
   *
   * @param voice the {@link StaveVoice}.
   */
  public ViewStaveVoiceLayout(StaveVoice voice) {

    super();
    this.voice = voice;
  }

  /**
   * @return the {@link StaveVoice} this layout is about.
   */
  public StaveVoice getVoice() {

    return this.voice;
  }

}
