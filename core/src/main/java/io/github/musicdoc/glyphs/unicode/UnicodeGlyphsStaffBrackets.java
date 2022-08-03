package io.github.musicdoc.glyphs.unicode;

import io.github.musicdoc.stave.StaveBracket;

/**
 * {@link UnicodeGlyphs} for <a href="https://www.htmlsymbols.xyz/musical-symbols/bars">bars</a>-
 *
 * @see io.github.musicdoc.bar.BarLine
 */
public interface UnicodeGlyphsStaffBrackets extends UnicodeGlyphs {

  /**
   * The brace symbol: {@value}.
   *
   * @see StaveBracket#CURLY
   */
  String BRACE = "\uD834\uDD14";

  /**
   * The bracket symbol: {@value}.
   *
   * @see StaveBracket#SQUARE
   */
  String BRACKET = "\uD834\uDD15";

}
