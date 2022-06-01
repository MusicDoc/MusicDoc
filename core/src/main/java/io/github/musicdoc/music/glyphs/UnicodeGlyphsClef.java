package io.github.musicdoc.music.glyphs;

import io.github.musicdoc.music.clef.ClefSymbol;

/**
 * {@link UnicodeGlyphs} for {@link io.github.musicdoc.music.clef.Clef}s.
 */
public interface UnicodeGlyphsClef extends UnicodeGlyphs {

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
   * @param clefType the {@link ClefSymbol}.
   * @param chromaticShift the {@link io.github.musicdoc.music.clef.Clef#getShift() shift} as chromatic steps.
   * @return the according glyph.
   */
  static String get(ClefSymbol clefType, int chromaticShift) {

    switch (clefType) {
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
    }
    return null;
  }
}