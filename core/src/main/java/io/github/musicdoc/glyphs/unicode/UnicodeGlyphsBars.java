package io.github.musicdoc.glyphs.unicode;

import io.github.musicdoc.bar.BarLineType;

/**
 * {@link UnicodeGlyphs} for <a href="https://www.htmlsymbols.xyz/musical-symbols/bars">bars</a>-
 *
 * @see io.github.musicdoc.bar.BarLine
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
   * @param type the {@link BarLineType}.
   * @return the according glyph.
   */
  static String get(BarLineType type) {

    if (type == null) {
      return null;
    }
    switch (type) {
      case THIN:
      case THICK:
        return SINGLE_BARLINE;
      case THIN_THIN:
      case THICK_THICK:
        return DOUBLE_BARLINE;
      case THICK_THIN:
        return REVERSE_FINAL_BARLINE;
      case THIN_THICK:
        return FINAL_BARLINE;
      case REPEAT_START:
        return UnicodeGlyphsCodas.LEFT_REPEAT;
      case REPEAT_END:
        return UnicodeGlyphsCodas.RIGHT_REPEAT;
      case REPEAT_END_START_0:
      case REPEAT_END_START_1:
      case REPEAT_END_START_2:
        return UnicodeGlyphsCodas.RIGHT_REPEAT + UnicodeGlyphsCodas.LEFT_REPEAT;
    }
    return null;
  }

}
