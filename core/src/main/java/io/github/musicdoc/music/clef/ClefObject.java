package io.github.musicdoc.music.clef;

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
   * @return the {@link Tone} between the bottom line and the second line of the stave or {@code null} for none (e.g.
   *         percussion clefs). Unlike the {@link #getReferenceTone() reference tone} this is more universal for
   *         computation of e.g. an {@link io.github.musicdoc.music.interval.ToneInterval}. So for {@link ClefSymbol#G G-clef}
   *         (treble clef) the low tone is {@link Tone#F4 F4} and for {@link ClefSymbol#F F-clef} (bass clef) the low tone
   *         is {@link Tone#A2 A2}.
   */
  Tone getLowTone();

}
