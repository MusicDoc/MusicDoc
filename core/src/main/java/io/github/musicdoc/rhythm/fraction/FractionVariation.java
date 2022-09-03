package io.github.musicdoc.rhythm.fraction;

import io.github.musicdoc.glyphs.MusicalGlyphs;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariation;
import io.github.musicdoc.rhythm.value.variation.Punctuation;
import io.github.musicdoc.rhythm.value.variation.Tuplet;

/**
 * Interface for a {@link #getVariation() variation} that modifies the {@link #getPlain() plain fraction} of a
 * {@link Fraction} like a {@link io.github.musicdoc.rhythm.value.MusicalValue}.
 *
 * @see #getVariation()
 * @see MusicalValueVariation
 */
public interface FractionVariation extends Fraction, MusicalGlyphs {

  @Override
  MusicalValueVariation getPlain();

  /**
   * @return the number of {@link Punctuation} dots or {@code 0} for none.
   */
  default int getPunctuationCount() {

    return 0;
  }

  /**
   * @return the {@link Tuplet} variation or {@code null} for none.
   */
  default Tuplet getTuplet() {

    Tuplet tuplet = getPlain().getTuplet();
    if (tuplet == null) {
      tuplet = getVariation().getTuplet();
    }
    return tuplet;
  }

}
