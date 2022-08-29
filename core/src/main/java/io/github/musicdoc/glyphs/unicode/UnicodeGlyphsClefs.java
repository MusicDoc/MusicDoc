package io.github.musicdoc.glyphs.unicode;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.clef.ClefSymbol;
import io.github.musicdoc.harmony.TonalSystem;
import io.github.musicdoc.interval.ToneInterval;

/**
 * {@link UnicodeGlyphs} for <a href="https://www.htmlsymbols.xyz/musical-symbols/clefs">clefs</a>
 *
 * @see io.github.musicdoc.clef.Clef
 */
public interface UnicodeGlyphsClefs extends UnicodeGlyphs {

  /** The {@link ClefSymbol#G G-clef} symbol: {@value}. */
  String G_CLEV = "\uD834\uDD1E";

  /** The {@link ClefSymbol#G G-clef} symbol with 8 below (one octave down): {@value}. */
  String G_CLEV_DOWN_8 = "\uD834\uDD20";

  /** The {@link ClefSymbol#G G-clef} symbol with 8 above (one octave up): {@value}. */
  String G_CLEV_UP_8 = "\uD834\uDD1F";

  /** The {@link ClefSymbol#F F-clef} symbol: {@value}. */
  String F_CLEV = "\uD834\uDD22";

  /** The {@link ClefSymbol#F F-clef} symbol with 8 below (one octave down): {@value}. */
  String F_CLEV_DOWN_8 = "\uD834\uDD24";

  /** The {@link ClefSymbol#F F-clef} symbol with 8 above (one octave up): {@value}. */
  String F_CLEV_UP_8 = "\uD834\uDD23";

  /** The {@link ClefSymbol#C C-clef} symbol: {@value}. */
  String C_CLEV = "\uD834\uDD21";

  /** The {@link ClefSymbol#PERCUSSION_1 percussion clef 1} symbol: {@value}. */
  String PERCUSSION_CLEV_1 = "\uD834\uDD25";

  /** The {@link ClefSymbol#PERCUSSION_2 percussion clef 2} symbol: {@value}. */
  String PERCUSSION_CLEV_2 = "\uD834\uDD26";

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
        if (chromaticShift == 0) {
          return G_CLEV;
        } else if (chromaticShift == 12) {
          return G_CLEV_UP_8;
        } else if (chromaticShift == -12) {
          return G_CLEV_DOWN_8;
        }
        break;
      case F:
        if (chromaticShift == 0) {
          return F_CLEV;
        } else if (chromaticShift == 12) {
          return F_CLEV_UP_8;
        } else if (chromaticShift == -12) {
          return F_CLEV_DOWN_8;
        }
        break;
      case C:
        if (chromaticShift == 0) {
          return C_CLEV;
        }
        break;
      case PERCUSSION_1:
        return PERCUSSION_CLEV_1;
      case PERCUSSION_2:
        return PERCUSSION_CLEV_2;
      case TAB:
        return "T\nA\nB";
    }
    return null;
  }
}
