package io.github.musicdoc.glyphs.smufl;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/staves.html">staves</a>.
 *
 * @see io.github.musicdoc.stave.Stave
 */
public interface SmuflGlyphsStaves extends SmuflGlyphs {

  /**
   * The one-line staff symbol: {@value}.
   */
  String STAFF_1 = "\uE010";

  /**
   * The two-line staff symbol: {@value}.
   */
  String STAFF_2 = "\uE011";

  /**
   * The three-line staff symbol: {@value}.
   */
  String STAFF_3 = "\uE012";

  /**
   * The four-line staff symbol: {@value}.
   */
  String STAFF_4 = "\uE013";

  /**
   * The five-line staff symbol: {@value}.
   */
  String STAFF_5 = "\uE014";

  /**
   * The six-line staff symbol: {@value}.
   */
  String STAFF_6 = "\uE015";

  /**
   * The one-line staff (wide) symbol: {@value}.
   */
  String STAFF_1_WIDE = "\uE016";

  /**
   * The two-line staff (wide) symbol: {@value}.
   */
  String STAFF_2_WIDE = "\uE017";

  /**
   * The three-line staff (wide) symbol: {@value}.
   */
  String STAFF_3_WIDE = "\uE018";

  /**
   * The four-line staff (wide) symbol: {@value}.
   */
  String STAFF_4_WIDE = "\uE019";

  /**
   * The five-line staff (wide) symbol: {@value}.
   */
  String STAFF_5_WIDE = "\uE01A";

  /**
   * The six-line staff (wide) symbol: {@value}.
   */
  String STAFF_6_WIDE = "\uE01B";

  /**
   * The one-line staff (narrow) symbol: {@value}.
   */
  String STAFF_1_NARROW = "\uE01C";

  /**
   * The two-line staff (narrow) symbol: {@value}.
   */
  String STAFF_2_NARROW = "\uE01D";

  /**
   * The three-line staff (narrow) symbol: {@value}.
   */
  String STAFF_3_NARROW = "\uE01E";

  /**
   * The four-line staff (narrow) symbol: {@value}.
   */
  String STAFF_4_NARROW = "\uE01F";

  /**
   * The five-line staff (narrow) symbol: {@value}.
   */
  String STAFF_5_NARROW = "\uE020";

  /**
   * The six-line staff (narrow) symbol: {@value}.
   */
  String STAFF_6_NARROW = "\uE021";

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
