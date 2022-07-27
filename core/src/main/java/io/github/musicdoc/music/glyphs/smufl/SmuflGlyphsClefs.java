package io.github.musicdoc.music.glyphs.smufl;

import io.github.musicdoc.music.clef.ClefSymbol;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/clefs.html">clefs</a>.
 *
 * @see io.github.musicdoc.music.clef.Clef
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
        return PERCUSSION_CLEV_1;
      case PERCUSSION_2:
        return PERCUSSION_CLEV_2;
    }
    return null;
  }

}
