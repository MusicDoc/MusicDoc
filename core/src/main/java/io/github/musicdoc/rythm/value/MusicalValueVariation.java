package io.github.musicdoc.rythm.value;

import io.github.musicdoc.glyphs.MusicalGlyphs;
import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsNote;
import io.github.musicdoc.rythm.Fraction;

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
   * Increases the {@link MusicalValue} such that three tones of that value actually last as long as two {@link #NONE
   * regular} ones. Visualized as a small three ({@code 3} centered below or on top of the tones. Typically used with
   * barred tones otherwise a bar-bracket is added to group the tones.
   */
  TRIPLET(2, 3, "(3");

  private final int beats;

  private final int fraction;

  private final String text;

  private MusicalValueVariation(int beats, int fraction, String text) {

    this.beats = beats;
    this.fraction = fraction;
    this.text = text;
  }

  @Override
  public int getBeats() {

    return this.beats;
  }

  @Override
  public int getFraction() {

    return this.fraction;
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

  @Override
  public String toString() {

    return this.text;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append(this.text);
  }
}
