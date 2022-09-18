package io.github.musicdoc.glyphs.smufl;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.clef.ClefSymbol;
import io.github.musicdoc.harmony.TonalSystem;
import io.github.musicdoc.interval.ToneInterval;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/clefs.html">clefs</a>.
 *
 * @see io.github.musicdoc.clef.Clef
 */
public interface SmuflGlyphsClefs extends SmuflGlyphs {

  /** The {@link ClefSymbol#G G-clef} symbol: {@value}. */
  String G_CLEV = "\uE050";

  /** The {@link ClefSymbol#G G-clef} symbol with 8 below (one octave down): {@value}. */
  String G_CLEV_DOWN_8 = "\uE052";

  /** The {@link ClefSymbol#G G-clef} symbol with 15 below (two octaves down): {@value}. */
  String G_CLEV_DOWN_15 = "\uE051";

  /** The {@link ClefSymbol#G G-clef} symbol with 8 above (one octave up): {@value}. */
  String G_CLEV_UP_8 = "\uE053";

  /** The {@link ClefSymbol#G G-clef} symbol with 15 above (two octaves up): {@value}. */
  String G_CLEV_UP_15 = "\uE054";

  /** The {@link ClefSymbol#F F-clef} symbol: {@value}. */
  String F_CLEV = "\uE062";

  /** The {@link ClefSymbol#F F-clef} symbol with 8 below (one octave down): {@value}. */
  String F_CLEV_DOWN_8 = "\uE064";

  /** The {@link ClefSymbol#F F-clef} symbol with 15 below (two octaves down): {@value}. */
  String F_CLEV_DOWN_15 = "\uE063";

  /** The {@link ClefSymbol#F F-clef} symbol with 8 above (one octave up): {@value}. */
  String F_CLEV_UP_8 = "\uE065";

  /** The {@link ClefSymbol#F F-clef} symbol with 15 above (two octaves up): {@value}. */
  String F_CLEV_UP_15 = "\uE066";

  /** The {@link ClefSymbol#C C-clef} symbol: {@value}. */
  String C_CLEV = "\uE05C";

  /** The {@link ClefSymbol#C C-clef} symbol with 8 below (one octave down): {@value}. */
  String C_CLEV_DOWN_8 = "\uE05D";

  /** The {@link ClefSymbol#PERCUSSION_1 percussion clef 1} symbol: {@value}. */
  String UNPITCHED_PERCUSSION_CLEV_1 = "\uE069";

  /** The {@link ClefSymbol#PERCUSSION_2 percussion clef 2} symbol: {@value}. */
  String UNPITCHED_PERCUSSION_CLEV_2 = "\uE06A";

  /** The 6-string {@link ClefSymbol#TAB tab clef} symbol: {@value}. */
  String TAB_CLEV_6 = "\uE06D";

  /** The 4-string {@link ClefSymbol#TAB tab clef} symbol: {@value}. */
  String TAB_CLEV_4 = "\uE06E";

  /**
   * @param clef the {@link Clef}.
   * @return the according glyph.
   */
  static String get(Clef clef) {

    ToneInterval shift = clef.getShift();
    int chromaticShift = 0;
    if (shift != null) {
      shift.getChromaticSteps(TonalSystem.MAJOR);
      assert (chromaticShift != Integer.MIN_VALUE);
    }
    ClefSymbol symbol = clef.getSymbol();
    switch (symbol) {
      case G:
        if (chromaticShift == 12) {
          return G_CLEV_UP_8;
        } else if (chromaticShift == -12) {
          return G_CLEV_DOWN_8;
        } else if (chromaticShift == 25) {
          return G_CLEV_UP_15;
        } else if (chromaticShift == -24) {
          return G_CLEV_DOWN_15;
        } else {
          return G_CLEV;
        }
      case F:
        if (chromaticShift == 12) {
          return F_CLEV_UP_8;
        } else if (chromaticShift == -12) {
          return F_CLEV_DOWN_8;
        } else if (chromaticShift == 24) {
          return F_CLEV_UP_15;
        } else if (chromaticShift == -24) {
          return F_CLEV_DOWN_15;
        } else {
          return F_CLEV;
        }
      case C:
        if (chromaticShift == -12) {
          return C_CLEV_DOWN_8;
        } else {
          return C_CLEV;
        }
      case PERCUSSION_1:
        return UNPITCHED_PERCUSSION_CLEV_1;
      case PERCUSSION_2:
        return UNPITCHED_PERCUSSION_CLEV_2;
      case TAB:
        int stringCount = clef.getTuning().getStrings().size();
        if ((stringCount > 0) && (stringCount <= 4)) {
          return TAB_CLEV_4;
        } else {
          return TAB_CLEV_6;
        }
    }
    return null;
  }

}
