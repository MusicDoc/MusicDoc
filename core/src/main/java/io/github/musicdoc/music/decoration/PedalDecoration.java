package io.github.musicdoc.music.decoration;

import io.github.musicdoc.music.PeriodType;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsPedal;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsPedals;

/**
 * {@link MusicalDecoration} for pedal usage. Typically used for pianos and keyboards indicating sustaining pedal (to
 * hold the tone).
 */
public final class PedalDecoration extends MusicalDecoration {

  /** Pedal (ped). */
  public static final PedalDecoration PEDAL_DOWN = create("ped,", PeriodType.START, UnicodeGlyphsPedals.PEDAL_MARK,
      SmuflGlyphsPedal.PEDAL_MARK, "pedal", "pedal(");

  /** Pedal up (*). */
  public static final PedalDecoration PEDAL_UP = create("*", PeriodType.END, UnicodeGlyphsPedals.PEDAL_UP_MARK,
      SmuflGlyphsPedal.PEDAL_UP_MARK, "pedal)");

  /** Pedal half down. */
  public static final PedalDecoration PEDAL_HALF_DOWN = create("pedalhalf", PeriodType.START,
      UnicodeGlyphsPedals.HALF_PEDAL_MARK, "pedalhalf)");

  private PedalDecoration(String name, PeriodType period, String unicode, String smufl, MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    return MusicalDecorationPosition.BOTTOM;
  }

  @Override
  protected PedalDecoration create(String newName) {

    return new PedalDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.reference);
  }

  private static PedalDecoration create(String name, PeriodType period, String unicode, String smufl,
      String... altNames) {

    PedalDecoration decoration = new PedalDecoration(name, period, unicode, smufl, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}