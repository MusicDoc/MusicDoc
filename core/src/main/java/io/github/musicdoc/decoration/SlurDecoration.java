package io.github.musicdoc.decoration;

import io.github.musicdoc.PeriodType;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsBeams;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsBeams;

/**
 * {@link MusicalDecoration} for a curved line above or below a group on notes indicating to play them legato (without
 * separation).
 */
public final class SlurDecoration extends MusicalDecoration {

  /** Start of a slur. */
  public static final SlurDecoration SLUR_START = create("(", PeriodType.START, UnicodeGlyphsBeams.SLUR_START,
      SmuflGlyphsBeams.SLUR_START);

  /** End of a slur. */
  public static final SlurDecoration SLUR_END = create(")", PeriodType.END, UnicodeGlyphsBeams.SLUR_END,
      SmuflGlyphsBeams.SLUR_END);

  private SlurDecoration(String name, PeriodType period, String unicode, String smufl, MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
  }

  @Override
  public boolean isItemSuffix() {

    return (this == SLUR_END);
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    return MusicalDecorationPosition.NOTEHEAD;
  }

  @Override
  protected SlurDecoration create(String newName) {

    return new SlurDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.reference);
  }

  private static SlurDecoration create(String name, PeriodType period, String unicode, String smufl,
      String... altNames) {

    SlurDecoration decoration = new SlurDecoration(name, period, unicode, smufl, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}