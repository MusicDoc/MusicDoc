package io.github.musicdoc.music.glyphs.unicode;

/**
 * {@link UnicodeGlyphs} for <a href="https://www.htmlsymbols.xyz/musical-symbols/accidentals">accidentals</a>.
 */
public interface UnicodeGlyphsAccidentals extends UnicodeGlyphs {

  /** The single flat symbol: {@value}. */
  String FLAT_1 = Character.toString(UnicodeGlyphsAccidentals.FLAT_1_CHAR);

  /** The neutral symbol: {@value}. */
  char NEUTRAL_CHAR = '\u266E';

  /** The neutral symbol: {@value}. */
  String NEUTRAL = Character.toString(NEUTRAL_CHAR);

  /** The single sharp symbol: {@value} . */
  char SHARP_1_CHAR = '\u266F';

  /** The single sharp symbol: {@value}. */
  String SHARP_1 = Character.toString(SHARP_1_CHAR);

  /** First surrogate for musical block. */
  char SIGN_2_CHAR1 = '\uD834';

  /** Second surrogate for {@link #SHARP_2}. */
  char SHARP_2_CHAR2 = '\uDD2A';

  /** Second surrogate for {@link #FLAT_2}. */
  char FLAT_2_CHAR2 = '\uDD2B';

  /** The double sharp symbol: {@value}. */
  String SHARP_2 = "" + SIGN_2_CHAR1 + SHARP_2_CHAR2;

  /** The double flat symbol: {@value}. */
  String FLAT_2 = "" + SIGN_2_CHAR1 + FLAT_2_CHAR2;

  /** The single flat symbol: {@value}. */
  char FLAT_1_CHAR = '\u266D';

  /**
   * @param semitones the number of semi-tones to increase. E.g. 1 for single sharp, 0 for neutral, -1 for single flat
   *        sign.
   * @return the according glyph.
   */
  static String get(int semitones) {

    if (semitones == 0) {
      return NEUTRAL;
    } else if (semitones == 1) {
      return SHARP_1;
    } else if (semitones == -1) {
      return FLAT_1;
    } else if (semitones == 2) {
      return SHARP_2;
    } else if (semitones == -2) {
      return FLAT_2;
    }
    return null;
  }

}
