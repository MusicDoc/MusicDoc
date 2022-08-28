package io.github.musicdoc.rythm.value;

import io.github.musicdoc.glyphs.MusicalGlyphs;
import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsNote;
import io.github.musicdoc.rythm.fraction.Fraction;

/**
 * A {@link MusicalValueVariation} is a {@link Fraction} that modifies a {@link MusicalValue}. There are particular
 * symbols to visualize a {@link MusicalValueVariation} in a score.
 */
public enum MusicalValueVariation implements Fraction, MusicalGlyphs {

  /**
   * No variation.
   */
  NONE(1, 1, ""),

  /**
   * Increases the {@link MusicalValue} by adding half of its value. Visualized as a single dot right to the musical
   * symbol.
   */
  PUNCTURED(3, 2, "."),

  /**
   * Like {@link #PUNCTURED} but additionally adding a quarter of the value. Visualized as a double dot right to the
   * musical symbol.
   */
  DOUBLE_PUNCTURED(7, 4, ".."),

  /**
   * Like {@link #DOUBLE_PUNCTURED} but additionally adding an eight of the value. Visualized as a tripple dot right to
   * the musical symbol.<br>
   * ATTENTION: This is really uncommon in regular music and should be avoided. Please use with care as many musicians
   * do not even know the semantics of a {@link #DOUBLE_PUNCTURED double punctuation}.
   */
  TRIPPLE_PUNCTURED(15, 8, "..."),

  /**
   * Increases the {@link MusicalValue} such that three tones of that value actually last as long as two {@link #NONE
   * regular} ones. Visualized as a small three ({@code 3} centered below or on top of the tones. Typically used with
   * barred tones otherwise a bar-bracket is added to group the tones.
   */
  TRIPLET(2, 3, "(3");

  private final int beats;

  private final int unit;

  private final String text;

  private MusicalValueVariation(int beats, int fraction, String text) {

    this.beats = beats;
    this.unit = fraction;
    this.text = text;
  }

  @Override
  public int getBeats() {

    return this.beats;
  }

  @Override
  public int getUnit() {

    return this.unit;
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    if ((this == NONE) || (this == TRIPLET)) {
      return "";
    } else if (context.isEnforceUnicode()) {
      return this.text;
    } else if (this == PUNCTURED) {
      return SmuflGlyphsNote.AUGMENTATION_DOT;
    } else if (this == DOUBLE_PUNCTURED) {
      return SmuflGlyphsNote.AUGMENTATION_DOT + SmuflGlyphsNote.AUGMENTATION_DOT;
    }
    return null;
  }

  /**
   * @return the number of punctiations (dots behind the notehead). Will return {@code 1} for {@link #PUNCTURED},
   *         {@code 2} for {@link #DOUBLE_PUNCTURED}, {@code 3} for {@link #TRIPPLE_PUNCTURED}, and {@code 0} in any
   *         other case.
   */
  public int getPunctuationCount() {

    if (this == PUNCTURED) {
      return 1;
    } else if (this == DOUBLE_PUNCTURED) {
      return 2;
    } else if (this == TRIPPLE_PUNCTURED) {
      return 3;
    }
    return 0;
  }

  /**
   * @param other the {@link MusicalValueVariation} to compare with.
   * @return {@code true} if both {@code this} and the given {@link MusicalValueVariation} have the same
   *         {@link #getValue() value}.
   */
  public boolean isEqualTo(MusicalValueVariation other) {

    if (other == null) {
      return false;
    }
    return (this.beats == other.beats) && (this.unit == other.unit);
  }

  @Override
  public String toString() {

    return this.text;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append(this.text);
  }
}
