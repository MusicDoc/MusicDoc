package io.github.musicdoc.stave.activity;

import io.github.musicdoc.clef.ClefSymbol;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * Interface to determine the activity of {@link Stave staves}. Depending on the context the activity can decide to
 * enable or disable rendering or playing.
 *
 * @see StaveAcitvity
 */
@FunctionalInterface
public interface StavePlainActivity {

  /** {@link StavePlainActivity} considering all {@link Stave}s as {@link #isActive(Stave) active}. */
  StavePlainActivity ACTIVATE_PLAIN_ALL = (s) -> true;

  /**
   * Determines if an entire {@link Stave} should be active. Typically decided on the
   * {@link io.github.musicdoc.clef.Clef} so e.g. for rendering you can decide to hide
   * {@link io.github.musicdoc.clef.ClefSymbol#TAB tabs} or show only {@link io.github.musicdoc.clef.ClefSymbol#TAB
   * tabs}. Please note that if no {@link StaveVoice} of the {@link Stave}
   * {@link StaveVoiceAcitvity#isActive(StaveVoice) is active} this will also deactivate the entire {@link Stave} even
   * if this method returned {@code true}.
   *
   * @param stave the {@link Stave} to check.
   * @return {@code true} if the given {@link Stave} should be active, {@code false} otherwise.
   */
  boolean isActive(Stave stave);

  /**
   * @param symbols the {@link ClefSymbol}s considered to be {@link #isActive(Stave) active}.
   * @return the {@link StavePlainActivity} {@link #isActive(Stave) activating} all {@link Stave}s with the given
   *         {@link ClefSymbol}.
   */
  static StavePlainActivity ofActive(ClefSymbol... symbols) {

    return new StavePlainActivityClefSymbol(true, symbols);
  }

  /**
   * @param symbols the {@link ClefSymbol}s considered to be not {@link #isActive(Stave) active}.
   * @return the {@link StavePlainActivity} {@link #isActive(Stave) activating} all {@link Stave}s that do not have the
   *         given {@link ClefSymbol}.
   */
  static StavePlainActivity ofInactive(ClefSymbol... symbols) {

    return new StavePlainActivityClefSymbol(true, symbols);
  }

}
