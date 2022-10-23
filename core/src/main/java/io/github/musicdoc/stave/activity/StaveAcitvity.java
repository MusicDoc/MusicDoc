package io.github.musicdoc.stave.activity;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * Interface to determine the activity of {@link Stave staves} and {@link StaveVoice voices}. Depending on the context
 * the activity can decide to enable or disable rendering or playing.
 */
public interface StaveAcitvity extends StavePlainActivity, StaveVoiceAcitvity {

  /**
   * {@link StaveVoiceAcitvity} considering all {@link Stave}s and {@link StaveVoice}s as {@link #isActive(StaveVoice)
   * active}.
   */
  StaveAcitvity ACTIVATE_ALL = new DefaultStaveActivity(ACTIVATE_PLAIN_ALL, ACTIVATE_VOICE_ALL);

}
