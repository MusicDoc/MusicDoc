package io.github.musicdoc.glyphs.smufl;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/time-signatures.html">time signatures</a>.
 */
public interface SmuflGlyphsTimeSignatures extends SmuflGlyphs {

  /** Time signature 0: {@value}. */
  String TIME_SIG_0 = "\uE080";

  /** Time signature 1: {@value}. */
  String TIME_SIG_1 = "\uE081";

  /** Time signature 2: {@value}. */
  String TIME_SIG_2 = "\uE082";

  /** Time signature 3: {@value}. */
  String TIME_SIG_3 = "\uE083";

  /** Time signature 4: {@value}. */
  String TIME_SIG_4 = "\uE084";

  /** Time signature 5: {@value}. */
  String TIME_SIG_5 = "\uE085";

  /** Time signature 6: {@value}. */
  String TIME_SIG_6 = "\uE086";

  /** Time signature 7: {@value}. */
  String TIME_SIG_7 = "\uE087";

  /** Time signature 8: {@value}. */
  String TIME_SIG_8 = "\uE088";

  /** Time signature 9: {@value}. */
  String TIME_SIG_9 = "\uE089";

  /** Common time: {@value}. */
  String TIME_SIG_COMMON = "\uE08A";

  /** Cut time: {@value}. */
  String TIME_SIG_CUT_COMMON = "\uE08B";

  /**
   * @param digit the time signature digit ({@link io.github.musicdoc.rhythm.metre.Metre#getBeats() beats} or
   *        {@link io.github.musicdoc.rhythm.metre.Metre#getUnit() unit}).
   * @return the glyph for the given tuplet digit or {@code null} if no such glyph exists.
   */
  static String get(int digit) {

    switch (digit) {
      case 0:
        return TIME_SIG_0;
      case 1:
        return TIME_SIG_1;
      case 2:
        return TIME_SIG_2;
      case 3:
        return TIME_SIG_3;
      case 4:
        return TIME_SIG_4;
      case 5:
        return TIME_SIG_5;
      case 6:
        return TIME_SIG_6;
      case 7:
        return TIME_SIG_7;
      case 8:
        return TIME_SIG_8;
      case 9:
        return TIME_SIG_9;
    }
    return null;
  }

}
