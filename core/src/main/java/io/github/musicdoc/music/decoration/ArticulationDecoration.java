package io.github.musicdoc.music.decoration;

import io.github.musicdoc.music.PeriodType;
import io.github.musicdoc.music.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsArticulation;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsBaroqueOrnaments;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsHolds;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsPlucked;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsString;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsArticulation;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsHolds;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsInstrumentation;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsOrnaments;
import io.github.musicdoc.music.note.StemDirection;

/**
 * {@link MusicalDecoration} for articulation variants.
 */
public final class ArticulationDecoration extends MusicalDecoration {

  /** Wege. */
  public static final ArticulationDecoration WEDGE = create("wedge",
      UnicodeGlyphsOrnaments.ORNAMENT_STROKE_5, "todo");

  /** Left-hand pizzicato, or rasp for French horns. */
  public static final ArticulationDecoration PLUS = create("+", "+",
      SmuflGlyphsBaroqueOrnaments.SHAKE, "plus");

  /** cello thumb symbol. */
  public static final ArticulationDecoration THUMB = create("thumb", "TODO",
      SmuflGlyphsString.THUMB_POSITION);

  /** Snap. */
  public static final ArticulationDecoration SNAP = create("snap",
      UnicodeGlyphsInstrumentation.COMBINING_SNAP_PIZZICATO, SmuflGlyphsPlucked.SNAP_PIZZICATO_BELOW);

  /** Open string or harmonic. */
  public static final ArticulationDecoration OPEN = create("open",
      UnicodeGlyphsInstrumentation.COMBINING_HARMONIC, SmuflGlyphsString.HARMONIC);

  /** Breathing mark. */
  public static final ArticulationDecoration BREATH = create("'", UnicodeGlyphsHolds.BREATH_MARK,
      SmuflGlyphsHolds.BREATH_MARK, "breath");

  /**
   * The Irish roll. For whatever reason ABC has adopted this and even used '~' what we use for turn (gruppetto)
   * instead.
   */
  public static final ArticulationDecoration ROLL = create("roll",
      UnicodeGlyphsOrnaments.ORNAMENT_STROKE_9, SmuflGlyphsBaroqueOrnaments.CURVE_ABOVE);

  /** Accent. */
  public static final ArticulationDecoration ACCENT = create(">",
      UnicodeGlyphsArticulation.COMBINING_ACCENT,
      SmuflGlyphsArticulation.ACCENT_ABOVE + SmuflGlyphsArticulation.ACCENT_BELOW, "accent", "emphasis", "L");

  /** Fermata. */
  public static final ArticulationDecoration FERMATA = create("H", UnicodeGlyphsHolds.FERMATA,
      SmuflGlyphsHolds.FERMATA_ABOVE, "fermata");

  /** Fermata below. */
  public static final ArticulationDecoration INVERTED_FERMATA = create("invertedfermata",
      UnicodeGlyphsHolds.FERMATA_BELOW, SmuflGlyphsHolds.FERMATA_BELOW);

  /** Tenuto. */
  public static final ArticulationDecoration TENUTO = create("tenuto",
      UnicodeGlyphsArticulation.COMBINING_TENUTO, SmuflGlyphsArticulation.TENUTO_ABOVE); // SmuflGlyphsArticulation.TENUTO_BELOW

  /** Staccato. */
  public static final ArticulationDecoration STACCATO = create(".",
      UnicodeGlyphsArticulation.COMBINING_STACCATO, SmuflGlyphsArticulation.STACCATO_ABOVE, "staccato"); // SmuflGlyphsArticulation.STACCATO_BELOW

  private ArticulationDecoration(String name, PeriodType period, String unicode, String smufl,
      MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    if (getReference() == INVERTED_FERMATA) {
      return MusicalDecorationPosition.BOTTOM;
    }
    return MusicalDecorationPosition.TOP;
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    if (!context.isEnforceUnicode() && this.glyphsSmufl.length() == 2) {
      int index = 0;
      if (context.getCurrentStemDirection() == StemDirection.DOWN) {
        index = 1;
      }
      return this.glyphsSmufl.substring(index, index + 1);
    }
    return super.getGlyphs(context);
  }

  @Override
  protected ArticulationDecoration create(String newName) {

    return new ArticulationDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.reference);
  }

  private static ArticulationDecoration create(String name, String unicode, String smufl, String... altNames) {

    return create(name, MusicalDecorationPosition.TOP, unicode, smufl, altNames);
  }

  private static ArticulationDecoration create(String name, MusicalDecorationPosition position, String unicode,
      String smufl, String... altNames) {

    ArticulationDecoration decoration = new ArticulationDecoration(name, null, unicode, smufl, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}