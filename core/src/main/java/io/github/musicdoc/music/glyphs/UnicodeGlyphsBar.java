package io.github.musicdoc.music.glyphs;

import io.github.musicdoc.music.bar.BarType;

/**
 * {@link UnicodeGlyphs} for {@link io.github.musicdoc.music.bar.Bar}s.
 */
public interface UnicodeGlyphsBar extends UnicodeGlyphs {

  /** The single barline symbol: {@value}. */
  String SINGLE_BARLINE = "\uD834\uDD00";

  /** The double barline symbol: {@value}. */
  String DOUBLE_BARLINE = "\uD834\uDD01";

  /** The finale barline symbol: {@value}. */
  String FINAL_BARLINE = "\uD834\uDD02";

  /** The reverse finale barline symbol: {@value}. */
  String REVERSE_FINAL_BARLINE = "\uD834\uDD03";

  /** The left repeat symbol: {@value}. */
  String LEFT_REPEAT = "\uD834\uDD06";

  /** The right repeat symbol: {@value}. */
  String RIGHT_REPEAT = "\uD834\uDD07";

  /** The {@link BarType#REPEAT_END_START end and start repeat} symbol: {@value}. */
  String RIGHT_LEFT_REPEAT = RIGHT_REPEAT + LEFT_REPEAT;

  /**
   * @param barType the {@link BarType}.
   * @return the according glyph.
   */
  static String get(BarType barType) {

    if (barType == BarType.SINGLE) {
      return SINGLE_BARLINE;
    } else if (barType == BarType.DOUBLE) {
      return DOUBLE_BARLINE;
    } else if (barType == BarType.THIN_THICK) {
      return FINAL_BARLINE;
    } else if (barType == BarType.THICK) {
      return SINGLE_BARLINE; // fallback
    } else if (barType == BarType.THICK_THIN) {
      return REVERSE_FINAL_BARLINE;
    } else if (barType == BarType.THICK_THICK) {
      return DOUBLE_BARLINE; // fallback
    } else if (barType == BarType.REPEAT_START) {
      return LEFT_REPEAT;
    } else if (barType == BarType.REPEAT_END) {
      return RIGHT_REPEAT;
    } else if (barType == BarType.REPEAT_END_START) {
      return RIGHT_LEFT_REPEAT;
    }
    return null;
  }

}
