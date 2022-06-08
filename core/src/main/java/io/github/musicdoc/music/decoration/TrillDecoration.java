package io.github.musicdoc.music.decoration;

import io.github.musicdoc.music.PeriodType;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsOrnaments;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsOrnaments;

/**
 * {@link MusicalDecoration} for any kind of trill.
 */
public final class TrillDecoration extends MusicalDecoration {

  /** Trill (tr). */
  public static final TrillDecoration TRILL = create("T", null, UnicodeGlyphsOrnaments.TR, SmuflGlyphsOrnaments.TRILL,
      "trill", "tr");

  /** Start of extended {@link #TRILL}. */
  public static final TrillDecoration TRILL_START = create("tr(", PeriodType.START, "", "", "trill(");

  /** End of extended {@link #TRILL}. */
  public static final TrillDecoration TRILL_END = create("tr)", PeriodType.END, "", "", "trill)");

  /** (Lower) mordent. */
  public static final TrillDecoration LOWER_MORDENT = create("M", null, "", "mordent", "lowermordent");

  /** Upper mordent or pralltriller. */
  public static final TrillDecoration UPPER_MORDENT = create("P", null, "", "pralltriller", "uppermordent");

  /**
   * Turn. A turn or gruppetto is an ornament for a kind of trill indicating a group of notes formed by the following
   * diatonic steps relative to the annotated note: +1 0 -1 0.
   */
  public static final TrillDecoration TURN = create("~", null, UnicodeGlyphsOrnaments.TURN, SmuflGlyphsOrnaments.TURN,
      "turn");

  /**
   * Inverted {@link #TURN turn}. Group of notes formed by the following diatonic steps relative to the annotated note:
   * -1 0 +1 0.
   */
  public static final TrillDecoration INVERTED_TURN = create("invertedturn", null, UnicodeGlyphsOrnaments.INVERTED_TURN,
      SmuflGlyphsOrnaments.INVERTED_TURN);

  /**
   * {@link #TURN Turn} with a slash. It typically has the same semantic as the {@link #INVERTED_TURN inverted turn} but
   * this can vary depending on the context and time of the composition.
   */
  public static final TrillDecoration TURN_SLASH = create("turnx", null, UnicodeGlyphsOrnaments.TURN_SLASH,
      SmuflGlyphsOrnaments.TURN_SLASH);

  // /** Inverted {@link #TURN_SLASH turn slash}. An inverted {@link #TURN_SLASH}. */
  // public static final TrillDecoration INVERTED_TURN_SLASH = create("invertedturnx", null, "", "");

  private TrillDecoration(String name, PeriodType period, String unicode, String smufl, MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    return MusicalDecorationPosition.TOP;
  }

  @Override
  protected TrillDecoration create(String newName) {

    return new TrillDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.reference);
  }

  private static TrillDecoration create(String name, PeriodType period, String unicode, String smufl,
      String... altNames) {

    TrillDecoration decoration = new TrillDecoration(name, period, unicode, smufl, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}