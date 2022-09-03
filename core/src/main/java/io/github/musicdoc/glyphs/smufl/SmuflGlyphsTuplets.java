package io.github.musicdoc.glyphs.smufl;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/string-techniques.html">string
 * techniques</a>.
 */
public interface SmuflGlyphsTuplets extends SmuflGlyphs {

  /** The tuplet 0: {@value}. */
  String TUPLET_0 = "\uE880";

  /** The tuplet 1: {@value}. */
  String TUPLET_1 = "\uE881";

  /** The tuplet 2 (duplet): {@value}. */
  String TUPLET_2 = "\uE882";

  /** The tuplet 3 (triplet): {@value}. */
  String TUPLET_3 = "\uE883";

  /** The tuplet 4 (quadruplet): {@value}. */
  String TUPLET_4 = "\uE884";

  /** The tuplet 5 (quintuplet): {@value}. */
  String TUPLET_5 = "\uE885";

  /** The tuplet 6 (sixtuplet): {@value}. */
  String TUPLET_6 = "\uE886";

  /** The tuplet 7 (sixtuplet): {@value}. */
  String TUPLET_7 = "\uE887";

  /** The tuplet 8 (eighttuplet): {@value}. */
  String TUPLET_8 = "\uE888";

  /** The tuplet 9 (nighttuplet): {@value}. */
  String TUPLET_9 = "\uE889";

  /** The tuplet colon(:): {@value}. */
  String TUPLET_COLON = "\uE88A";

  /**
   * @param digit the tuplet digit (e.g. 3 for triplet).
   * @return the glyph for the given tuplet digit or {@code null} if no such glyph exists.
   */
  static String getTuplet(int digit) {

    switch (digit) {
      case 0:
        return TUPLET_0;
      case 1:
        return TUPLET_1;
      case 2:
        return TUPLET_2;
      case 3:
        return TUPLET_3;
      case 4:
        return TUPLET_4;
      case 5:
        return TUPLET_5;
      case 6:
        return TUPLET_6;
      case 7:
        return TUPLET_7;
      case 8:
        return TUPLET_8;
      case 9:
        return TUPLET_9;
    }
    return null;
  }

}
