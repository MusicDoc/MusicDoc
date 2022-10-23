package io.github.musicdoc.stave.activity;

import java.util.Objects;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * Default implementation of {@link StaveAcitvity}.
 */
public class DefaultStaveActivity implements StaveAcitvity {

  private final StavePlainActivity plainActivity;

  private final StaveVoiceAcitvity voiceActivity;

  /**
   * The constructor.
   *
   * @param plainActivity the {@link StavePlainActivity}.
   * @param voiceActivity the {@link StaveVoiceAcitvity}.
   */
  public DefaultStaveActivity(StavePlainActivity plainActivity, StaveVoiceAcitvity voiceActivity) {

    super();
    Objects.requireNonNull(plainActivity);
    Objects.requireNonNull(voiceActivity);
    this.plainActivity = plainActivity;
    this.voiceActivity = voiceActivity;
  }

  @Override
  public boolean isActive(Stave stave) {

    return this.plainActivity.isActive(stave);
  }

  @Override
  public boolean isActive(StaveVoice voice) {

    return this.plainActivity.isActive(voice.getStave()) && this.voiceActivity.isActive(voice);
  }

}
