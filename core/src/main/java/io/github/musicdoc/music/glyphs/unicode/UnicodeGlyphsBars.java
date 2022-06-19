package io.github.musicdoc.music.glyphs.unicode;

import io.github.musicdoc.music.bar.BarLineType;

/**
 * {@link UnicodeGlyphs} for <a href="https://www.htmlsymbols.xyz/musical-symbols/bars">bars</a>-
 *
 * @see io.github.musicdoc.music.bar.BarLine
 */
public interface UnicodeGlyphsBars extends UnicodeGlyphs {

  /** The single barline symbol: {@value}. */
  String SINGLE_BARLINE = "\uD834\uDD00";

  /** The double barline symbol: {@value}. */
  String DOUBLE_BARLINE = "\uD834\uDD01";

  /** The finale barline symbol: {@value}. */
  String FINAL_BARLINE = "\uD834\uDD02";

  /** The reverse finale barline symbol: {@value}. */
  String REVERSE_FINAL_BARLINE = "\uD834\uDD03";

  /**
   * @param barType the {@link BarLineType}.
   * @return the according glyph.
   */
  static String get(BarLineType barType) {

    if (barType == BarLineType.SINGLE) {
      return SINGLE_BARLINE;
    } else if (barType == BarLineType.DOUBLE) {
      return DOUBLE_BARLINE;
    } else if (barType == BarLineType.THIN_THICK) {
      return FINAL_BARLINE;
    } else if (barType == BarLineType.THICK) {
      return SINGLE_BARLINE; // fallback
    } else if (barType == BarLineType.THICK_THIN) {
      return REVERSE_FINAL_BARLINE;
    } else if (barType == BarLineType.THICK_THICK) {
      return DOUBLE_BARLINE; // fallback
    } else if (barType == BarLineType.REPEAT_START) {
      return UnicodeGlyphsCodas.LEFT_REPEAT;
    } else if (barType == BarLineType.REPEAT_END) {
      return UnicodeGlyphsCodas.RIGHT_REPEAT;
    } else if (barType == BarLineType.REPEAT_END_START) {
      return UnicodeGlyphsCodas.RIGHT_REPEAT + UnicodeGlyphsCodas.LEFT_REPEAT;
    }
    return null;
  }

}
