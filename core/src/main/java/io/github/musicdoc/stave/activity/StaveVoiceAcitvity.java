package io.github.musicdoc.stave.activity;

import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * Interface to determine the activity of {@link StaveVoice voices}. Depending on the context the activity can decide to
 * enable or disable rendering or playing.
 *
 * @see StaveAcitvity
 */
public interface StaveVoiceAcitvity {

  /** {@link StaveVoiceAcitvity} considering all {@link StaveVoice}s as {@link #isActive(StaveVoice) active}. */
  StaveVoiceAcitvity ACTIVATE_VOICE_ALL = (s) -> true;

  /**
   * Determines if the given {@link StaveVoice} is active.
   *
   * @param voice the {@link StaveVoice} to check.
   * @return {@code true} if the given {@link StaveVoice} should be active, {@code false} otherwise.
   */
  boolean isActive(StaveVoice voice);

}
