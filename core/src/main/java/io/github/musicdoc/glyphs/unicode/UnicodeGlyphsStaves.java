package io.github.musicdoc.glyphs.unicode;

/**
 * {@link UnicodeGlyphs} for <a href="https://www.htmlsymbols.xyz/musical-symbols/staves">staves</a>.
 *
 * @see io.github.musicdoc.stave.Stave
 */
public interface UnicodeGlyphsStaves extends UnicodeGlyphs {

  /**
   * The one-line staff symbol: {@value}.
   */
  String STAFF_1 = "\uD834\uDD16";

  /**
   * The two-line staff symbol: {@value}.
   */
  String STAFF_2 = "\uD834\uDD17";

  /**
   * The three-line staff symbol: {@value}.
   */
  String STAFF_3 = "\uD834\uDD18";

  /**
   * The four-line staff symbol: {@value}.
   */
  String STAFF_4 = "\uD834\uDD19";

  /**
   * The five-line staff symbol: {@value}.
   */
  String STAFF_5 = "\uD834\uDD1A";

  /**
   * The six-line staff symbol: {@value}.
   */
  String STAFF_6 = "\uD834\uDD1B";

  /**
   * @param lines the number of {@link io.github.musicdoc.stave.Stave#getLines() stave lines}.
   * @return the glyph for the given {@code lines} or {@code null} if no such glyph exists.
   */
  static String get(int lines) {

    switch (lines) {
      case 1:
        return STAFF_1;
      case 2:
        return STAFF_2;
      case 3:
        return STAFF_3;
      case 4:
        return STAFF_4;
      case 5:
        return STAFF_5;
      case 6:
        return STAFF_6;
    }
    return null;
  }
}
