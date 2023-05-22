package io.github.musicdoc.clef;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.tone.Tone;

/**
 * Interface for {@link Clef} and {@link ClefSymbol}.
 */
public interface ClefObject {

  /**
   * @return the reference {@link Tone} of this {@link ClefSymbol} or {@code null} for none (e.g. percussion clefs).
   */
  default Tone getReferenceTone() {

    return null;
  }

  /**
   * @return the {@link Tone} on the third line of the {@link Stave} (starting with the first line from the bottom). So
   *         for {@link ClefSymbol#G G-clef} this will be {@link Tone#B4 B4} and for {@link ClefSymbol#F F-clef} (bass
   *         clef) it will be {@link Tone#D3 D3}.
   */
  default Tone getMiddleTone() {

    return null;
  }

  /**
   * @return the {@link Tone} between the first and the second line of the {@link Stave} (starting with the first line
   *         from the bottom). So for {@link ClefSymbol#G G-clef} this will be {@link Tone#F4 F4} and for
   *         {@link ClefSymbol#F F-clef} (bass clef) it will be {@link Tone#A2 A2}.
   */
  default Tone getLowerTone() {

    return null;
  }

}
