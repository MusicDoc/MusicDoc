package io.github.musicdoc.decoration;

import io.github.musicdoc.PeriodType;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsPlucked;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsArticulation;

/**
 * {@link MusicalDecoration} for plucked techniques.
 */
public class PluckedDecoration extends MusicalDecoration {

  /**
   * A regular arpeggio (arpeggiato). Chrod tones are played one after another. Direction is unspecified but by default
   * up (from lower to upper tones / pitches).
   */
  public static final PluckedDecoration ARPEGGIO = create("arpeggio", UnicodeGlyphsArticulation.ARPEGGIATO_UP,
      SmuflGlyphsPlucked.ARPEGGIATO);

  /** {@link #ARPEGGIO Arpeggio} up. */
  public static final PluckedDecoration ARPEGGIO_UP = create("arpeggioup", UnicodeGlyphsArticulation.ARPEGGIATO_UP,
      SmuflGlyphsPlucked.ARPEGGIATO_UP);

  /** {@link #ARPEGGIO Arpeggio} down. */
  public static final PluckedDecoration ARPEGGIO_DOWN = create("arpeggiodown",
      UnicodeGlyphsArticulation.ARPEGGIATO_DOWN, SmuflGlyphsPlucked.ARPEGGIATO_DOWN);

  private PluckedDecoration(String name, PeriodType period, String unicode, String smufl, MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    return MusicalDecorationPosition.TOP;
  }

  @Override
  protected PluckedDecoration create(String newName) {

    return new PluckedDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.reference);
  }

  private static PluckedDecoration create(String name, String unicode, String smufl, String... altNames) {

    return create(name, MusicalDecorationPosition.TOP, unicode, smufl, altNames);
  }

  private static PluckedDecoration create(String name, MusicalDecorationPosition position, String unicode, String smufl,
      String... altNames) {

    PluckedDecoration decoration = new PluckedDecoration(name, null, unicode, smufl, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}