package io.github.musicdoc.music.glyphs.smufl;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/standard-accidentals-12-edo.html">standard
 * accidentals</a>.
 */
public interface SmuflGlyphsAccidentals extends SmuflGlyphs {

  /** The single flat symbol: {@value}. */
  String FLAT_1 = "\uE260";

  /** The double flat symbol: {@value}. */
  String FLAT_2 = "\uE264";

  /** The double flat symbol: {@value}. */
  String FLAT_3 = "\uE266";

  /** The neutral symbol: {@value}. */
  String NEUTRAL = "\uE261";

  /** The single sharp symbol: {@value}. */
  String SHARP_1 = "\uE262";

  /** The double sharp symbol: {@value}. */
  String SHARP_2 = "\uE263";

  /** The double sharp symbol: {@value}. */
  String SHARP_3 = "\uE265";

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
    } else if (semitones == 3) {
      return SHARP_3;
    } else if (semitones == -3) {
      return FLAT_3;
    }
    throw new IllegalArgumentException("" + semitones);
  }

}
