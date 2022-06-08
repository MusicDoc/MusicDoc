package io.github.musicdoc.music.decoration;

import io.github.musicdoc.music.PeriodType;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsRepeats;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsCodas;

/**
 * {@link MusicalDecoration} for a jump to a {@link MarkerDecoration}.
 *
 * @see MarkerDecoration
 */
public final class JumpDecoration extends MusicalDecoration {

  /**
   * Da capo (D.C.). It is a mark indicating a jump from the current position to the beginning of the
   * {@link io.github.musicdoc.music.partiture.Partiture}.
   */
  public static final JumpDecoration DA_CAPO = create("D.C.", UnicodeGlyphsCodas.DA_CAPO, SmuflGlyphsRepeats.DA_CAPO,
      MarkerDecoration.CAPO, "dacapo");

  /**
   * Dal {@link MarkerDecoration#SEGNO segno} (D.S.). It is indicating a jump from the current position to the previous
   * {@link MarkerDecoration#SEGNO} sign.
   */
  public static final JumpDecoration DAL_SEGNO = create("D.S.", UnicodeGlyphsCodas.DAL_SEGNO,
      SmuflGlyphsRepeats.DAL_SEGNO, MarkerDecoration.SEGNO, "dalsegno");

  /**
   * Da {@link MarkerDecoration#CODA}. It is indicating a jump from the current position to the
   * {@link MarkerDecoration#CODA} sign.
   */
  public static final JumpDecoration DA_CODA = create("dacoda", "Da " + UnicodeGlyphsCodas.CODA,
      "Da " + SmuflGlyphsRepeats.CODA, MarkerDecoration.CODA);

  /**
   * Da {@link MarkerDecoration#CODA}. It is indicating a jump from the current position to the
   * {@link MarkerDecoration#CODA} sign.
   */
  public static final JumpDecoration TO_CODA = create("tocoda", "To " + UnicodeGlyphsCodas.CODA,
      "To " + SmuflGlyphsRepeats.CODA, MarkerDecoration.CODA);

  private final MarkerDecoration target;

  // public static final JumpDecoration TO_CODA = create("T.C.", "To Coda " + UnicodeGlyphs.CODA);

  private JumpDecoration(String name, PeriodType period, String unicode, String smufl, MarkerDecoration target,
      MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
    this.target = target;
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    return MusicalDecorationPosition.TOP;
  }

  /**
   * @return target the {@link MarkerDecoration} to jump to.
   */
  public MarkerDecoration getTarget() {

    return this.target;
  }

  @Override
  protected JumpDecoration create(String newName) {

    return new JumpDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.target, this.reference);
  }

  private static JumpDecoration create(String name, String unicode, String smufl, MarkerDecoration target,
      String... altNames) {

    JumpDecoration decoration = new JumpDecoration(name, null, unicode, smufl, target, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}