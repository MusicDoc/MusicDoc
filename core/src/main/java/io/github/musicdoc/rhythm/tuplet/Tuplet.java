package io.github.musicdoc.rhythm.tuplet;

import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsTuplets;
import io.github.musicdoc.rhythm.fraction.FractionVariation;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.rhythm.value.MusicalValue;

/**
 * {@link FractionVariation} for a tuplet. A tuplet marks a group of notes that is played at a speed of a different
 * number of notes (irregular rhythm). The most famous variant is the {@link #TRIPLET triplet} where three notes of the
 * same value are played is long as if only two such notes would have been played. A tuplet is visualized by a bracket
 * above or below the grouped tones. The bracket is annotated with a centered digit indicating the number of regular
 * notes to play. If the grouped notes are all connected with a beam, the bracket can be omitted and the number can be
 * centered above or below that beam.
 */
public final class Tuplet extends FractionVariation {

  /**
   * Variation for the {@link MusicalValue value} such that two tones of that value actually last as long as three
   * regular ones. Visualized as a small {@code 2} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet DUPLET = new Tuplet(3, 2, "(2");

  /**
   * Variation for the {@link MusicalValue value} such that three tones of that value actually last as long as two
   * regular ones. Visualized as a small {@code 3} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet TRIPLET = new Tuplet(2, 3, "(3");

  /**
   * Variation for the {@link MusicalValue value} such that four tones of that value actually last as long as three
   * regular ones. Visualized as a small {@code 4} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet QUADRUPLET = new Tuplet(3, 4, "(4");

  /**
   * Variation for the {@link MusicalValue value} such that five tones of that value actually last as long as two
   * regular ones. Visualized as a small {@code 5} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet QUINTUPLET_2 = new Tuplet(2, 5, "(5:2");

  /**
   * Variation for the {@link MusicalValue value} such that five tones of that value actually last as long as three
   * regular ones. Visualized as a small {@code 5} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet QUINTUPLET_3 = new Tuplet(3, 5, "(5:3");

  /**
   * Variation for the {@link MusicalValue value} such that six tones of that value actually last as long as two regular
   * ones. Visualized as a small {@code 6} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet SIXTUPLET = new Tuplet(2, 6, "(6");

  /**
   * Variation for the {@link MusicalValue value} such that six tones of that value actually last as long as two regular
   * ones. Visualized as a small {@code 6} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet SEVENTUPLET_2 = new Tuplet(2, 7, "(7:2");

  /**
   * Variation for the {@link MusicalValue value} such that six tones of that value actually last as long as three
   * regular ones. Visualized as a small {@code 6} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet SEVENTUPLET_3 = new Tuplet(3, 7, "(7:3");

  /**
   * Variation for the {@link MusicalValue value} such that six tones of that value actually last as long as two regular
   * ones. Visualized as a small {@code 6} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet EIGHTTUPLET = new Tuplet(3, 8, "(8");

  /**
   * Variation for the {@link MusicalValue value} such that six tones of that value actually last as long as two regular
   * ones. Visualized as a small {@code 6} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet NINETUPLET_2 = new Tuplet(2, 9, "(9:2");

  /**
   * Variation for the {@link MusicalValue value} such that six tones of that value actually last as long as three
   * regular ones. Visualized as a small {@code 6} centered below or on top of the tones grouped by a bracket.
   */
  public static final Tuplet NINETUPLET_3 = new Tuplet(3, 9, "(9:3");

  private static final Tuplet[] VALUES = { DUPLET, TRIPLET, QUADRUPLET, QUINTUPLET_2, QUINTUPLET_3, SIXTUPLET,
  SEVENTUPLET_2, SEVENTUPLET_3, EIGHTTUPLET, NINETUPLET_2, NINETUPLET_3 };

  private Tuplet(int beats, int fraction, String text) {

    super(beats, fraction, text);
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    if (!context.isEnforceUnicode()) {
      String glyph = SmuflGlyphsTuplets.getTuplet(this.unit);
      if (glyph != null) {
        return glyph;
      }
    }
    return Integer.toString(this.unit);
  }

  /**
   * @param metere the {@link Metre}.
   * @return {@code true} if this {@link Tuplet} has the default number of {@link #getBeats() beats} for its
   *         {@link #getUnit() unit} and the given {@link Metre}.
   */
  public boolean hasDefaultBeats(Metre metere) {

    return (this.beats == getBeats(metere, this.unit));
  }

  /**
   * @param beats the {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit} what is also .
   * @return the {@link Tuplet} for the given values.
   */
  public static Tuplet of(int beats, int unit) {

    for (Tuplet t : VALUES) {
      if ((t.getBeats() == beats) && (t.getUnit() == unit)) {
        return t;
      }
    }
    return new Tuplet(beats, unit, "(" + unit + ":" + beats);
  }

  /**
   * @param metre the {@link Metre} potentially used to derive the default for {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit} what is also .
   * @return the {@link Tuplet} for the given values.
   */
  public static Tuplet of(Metre metre, int unit) {

    return of(getBeats(metre, unit), unit);
  }

  private static int getBeats(Metre metre, int unit) {

    switch (unit) {
      case 2:
      case 4:
      case 8:
        return 3;
      case 5:
      case 7:
      case 9:
        if (metre.isCompound()) {
          return 3;
        } else {
          return 2;
        }
      default:
        return 2;
    }
  }
}
