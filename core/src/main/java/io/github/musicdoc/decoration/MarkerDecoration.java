package io.github.musicdoc.decoration;

import io.github.musicdoc.PeriodType;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsRepeats;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsCodas;

/**
 * {@link MusicalDecoration} for a marker like {@link #CODA} or {@link #SEGNO}. Such marker acts like a label marking a
 * position in the {@link io.github.musicdoc.score.Score} to {@link JumpDecoration jump to from a different
 * location}.
 *
 * @see JumpDecoration
 */
public final class MarkerDecoration extends MusicalDecoration {

  /**
   * Capo. The capo is a virtual marker for the beginning of the score.
   *
   * @see JumpDecoration#DA_CAPO
   */
  public static final MarkerDecoration CAPO = create("", "", "");

  /**
   * Coda. A coda is a mark looking like a circle with an overlapping plus sign (o+). It marks the start of an appended
   * ending of a score.
   */
  public static final MarkerDecoration CODA = create("O", UnicodeGlyphsCodas.CODA, SmuflGlyphsRepeats.CODA, "coda");

  /**
   * Segno. A segno is a mark looking like a slightly left rotated "S" with a Slash ("/") through and dots in the lower
   * left and upper right space. It marks the start of a section that will be repeated for a
   * {@link JumpDecoration#DAL_SEGNO} mark.
   */
  public static final MarkerDecoration SEGNO = create("S", UnicodeGlyphsCodas.SEGNO, SmuflGlyphsRepeats.SEGNO, "segno");

  /** Fine. Marks the end of the score. Incredible but true neither Unicode nor SMuFL supports a glyph for it. */
  public static final MarkerDecoration FINE = create("fine", "FINE", "FINE");

  private MarkerDecoration(String name, PeriodType period, String unicode, String smufl, MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    return MusicalDecorationPosition.TOP;
  }

  @Override
  protected MarkerDecoration create(String newName) {

    return new MarkerDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.reference);
  }

  private static MarkerDecoration create(String name, String unicode, String smufl, String... altNames) {

    MarkerDecoration decoration = new MarkerDecoration(name, null, unicode, smufl, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}