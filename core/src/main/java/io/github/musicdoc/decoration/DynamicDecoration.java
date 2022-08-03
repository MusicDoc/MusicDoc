package io.github.musicdoc.decoration;

import io.github.musicdoc.PeriodType;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsDynamics;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphs;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsArticulation;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsDynamics;
import io.github.musicdoc.rythm.value.ValuedItem;

/**
 * {@link MusicalDecoration} for the dynamics.
 */
public final class DynamicDecoration extends MusicalDecoration {

  /** Piano (p). */
  public static final DynamicDecoration PIANO_1 = create("p", UnicodeGlyphsDynamics.PIANO, SmuflGlyphsDynamics.PIANO);

  /** Pianissimo (pp). */
  public static final DynamicDecoration PIANO_2 = create("pp",
      UnicodeGlyphsDynamics.PIANO + UnicodeGlyphsDynamics.PIANO, SmuflGlyphsDynamics.PP);

  /** Pianississimo (ppp). */
  public static final DynamicDecoration PIANO_3 = create("ppp",
      UnicodeGlyphsDynamics.PIANO + UnicodeGlyphsDynamics.PIANO + UnicodeGlyphsDynamics.PIANO, SmuflGlyphsDynamics.PPP);

  /** Quadruple piano (pppp). */
  public static final DynamicDecoration PIANO_4 = create("pppp", UnicodeGlyphsDynamics.PIANO
      + UnicodeGlyphsDynamics.PIANO + UnicodeGlyphsDynamics.PIANO + UnicodeGlyphsDynamics.PIANO,
      SmuflGlyphsDynamics.PPPP);

  /** Quintuple piano (ppppp). */
  public static final DynamicDecoration PIANO_5 = create("ppppp",
      UnicodeGlyphsDynamics.PIANO + UnicodeGlyphsDynamics.PIANO + UnicodeGlyphsDynamics.PIANO
          + UnicodeGlyphsDynamics.PIANO + UnicodeGlyphsDynamics.PIANO,
      SmuflGlyphsDynamics.PPPPP);

  /** Mezzo-piano (mp). */
  public static final DynamicDecoration MEZZO_PIANO = create("mp",
      UnicodeGlyphsDynamics.MEZZO + UnicodeGlyphsDynamics.PIANO, SmuflGlyphsDynamics.MP);

  /** Mezzo-forte (mf). */
  public static final DynamicDecoration MEZZO_FORTE = create("mf",
      UnicodeGlyphsDynamics.MEZZO + UnicodeGlyphsDynamics.FORTE, SmuflGlyphsDynamics.MF);

  /** Forte (f). */
  public static final DynamicDecoration FORTE_1 = create("f", UnicodeGlyphsDynamics.FORTE, SmuflGlyphsDynamics.FORTE);

  /** Fortissimo (ff). */
  public static final DynamicDecoration FORTE_2 = create("ff",
      UnicodeGlyphsDynamics.FORTE + UnicodeGlyphsDynamics.FORTE, SmuflGlyphsDynamics.FF);

  /** Fortississimo (fff). */
  public static final DynamicDecoration FORTE_3 = create("fff",
      UnicodeGlyphsDynamics.FORTE + UnicodeGlyphsDynamics.FORTE + UnicodeGlyphsDynamics.FORTE, SmuflGlyphsDynamics.FFF);

  /** Quadruple forte (ffff). */
  public static final DynamicDecoration FORTE_4 = create("ffff", UnicodeGlyphsDynamics.FORTE
      + UnicodeGlyphsDynamics.FORTE + UnicodeGlyphsDynamics.FORTE + UnicodeGlyphsDynamics.FORTE,
      SmuflGlyphsDynamics.FFFF);

  /** Quintuple forte (fffff). */
  public static final DynamicDecoration FORTE_5 = create("fffff",
      UnicodeGlyphsDynamics.FORTE + UnicodeGlyphsDynamics.FORTE + UnicodeGlyphsDynamics.FORTE
          + UnicodeGlyphsDynamics.FORTE + UnicodeGlyphsDynamics.FORTE,
      SmuflGlyphsDynamics.FFFFF);

  /** Sforzando (sfz) - suddenly with force. */
  public static final DynamicDecoration SFORZANDO = create("sfz",
      UnicodeGlyphs.SUBITO + UnicodeGlyphsDynamics.FORTE + UnicodeGlyphsArticulation.Z, SmuflGlyphsDynamics.SFORZATO);

  /** Crescendo (starting and ending on the same {@link ValuedItem}). */
  public static final DynamicDecoration CRESCENDO = create("<()", UnicodeGlyphsDynamics.CRESCENDO,
      SmuflGlyphsDynamics.CRESCENDO, "crescendo");

  /** Start of {@link #CRESCENDO creshendo}. */
  public static final DynamicDecoration CRESCENDO_START = create("<(", PeriodType.START,
      UnicodeGlyphsDynamics.CRESCENDO, SmuflGlyphsDynamics.CRESCENDO, "crescendo(");

  /** End of {@link #CRESCENDO creshendo}. */
  public static final DynamicDecoration CRESCENDO_END = create("<)", PeriodType.END, UnicodeGlyphsDynamics.CRESCENDO,
      SmuflGlyphsDynamics.CRESCENDO, "crescendo)");

  /** Decrescendo (starting and ending on the same {@link ValuedItem}). */
  public static final DynamicDecoration DECRESCENDO = create(">()", UnicodeGlyphsDynamics.DECRESCENDO, "decrescendo",
      SmuflGlyphsDynamics.DIMINUENDO, "diminuendo");

  /** Start of {@link #DECRESCENDO decreshendo}. */
  public static final DynamicDecoration DECRESCENDO_START = create(">(", PeriodType.START,
      UnicodeGlyphsDynamics.DECRESCENDO, SmuflGlyphsDynamics.DIMINUENDO, "decrescendo(", "diminuendo(");

  /** End of {@link #DECRESCENDO decreshendo}. */
  public static final DynamicDecoration DECRESCENDO_END = create(">)", PeriodType.END,
      UnicodeGlyphsDynamics.DECRESCENDO, SmuflGlyphsDynamics.DIMINUENDO, "decrescendo)", "diminuendo)");

  private DynamicDecoration(String name, PeriodType period, String unicode, String smufl, MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    return MusicalDecorationPosition.BOTTOM;
  }

  @Override
  protected DynamicDecoration create(String newName) {

    return new DynamicDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.reference);
  }

  private static DynamicDecoration create(String name, String unicode, String smufl, String... altNames) {

    return create(name, null, unicode, smufl, altNames);
  }

  private static DynamicDecoration create(String name, PeriodType period, String unicode, String smufl,
      String... altNames) {

    DynamicDecoration decoration = new DynamicDecoration(name, period, unicode, smufl, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}