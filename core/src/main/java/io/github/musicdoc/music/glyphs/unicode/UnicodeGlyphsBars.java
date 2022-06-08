package io.github.musicdoc.music.glyphs.unicode;

import io.github.musicdoc.music.bar.BarType;

/**
 * {@link UnicodeGlyphs} for <a href="https://www.htmlsymbols.xyz/musical-symbols/bars">bars</a>-
 *
 * @see io.github.musicdoc.music.bar.Bar
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
      return UnicodeGlyphsCodas.LEFT_REPEAT;
    } else if (barType == BarType.REPEAT_END) {
      return UnicodeGlyphsCodas.RIGHT_REPEAT;
    } else if (barType == BarType.REPEAT_END_START) {
      return UnicodeGlyphsCodas.RIGHT_REPEAT + UnicodeGlyphsCodas.LEFT_REPEAT;
    }
    return null;
  }

}
