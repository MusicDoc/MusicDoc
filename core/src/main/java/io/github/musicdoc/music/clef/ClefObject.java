package io.github.musicdoc.music.clef;

import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.tone.Tone;

/**
 * Interface for {@link Clef} and {@link ClefSymbol}.
 */
public interface ClefObject {

  /**
   * @return the reference {@link Tone} of this {@link ClefSymbol} or {@code null} for none (e.g. percussion clefs).
   */
  Tone getReferenceTone();

  /**
   * @return the {@link Tone} on the third line of the {@link Stave} (starting with the first line from the bottom). So
   *         for {@link ClefSymbol#G G-clef} this will be {@link Tone#B4 B4} and for {@link ClefSymbol#F F-clef} (bass
   *         clef) it will be {@link Tone#D3 D3}.
   */
  Tone getMiddleTone();

}
