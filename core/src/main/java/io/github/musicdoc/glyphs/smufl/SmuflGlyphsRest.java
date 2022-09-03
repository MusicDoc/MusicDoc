package io.github.musicdoc.glyphs.smufl;

import io.github.musicdoc.glyphs.unicode.UnicodeGlyphs;
import io.github.musicdoc.rhythm.fraction.Fraction;

/**
 * {@link UnicodeGlyphs} for {@link io.github.musicdoc.rhythm.rest.Rest}s.
 */
public interface SmuflGlyphsRest extends UnicodeGlyphs {

  /** The whole (1/1) rest symbol: {@value}. */
  String REST_1 = "\uE4E3";

  /** The half (1/2) rest symbol: {@value}. */
  String REST_1_2 = "\uE4E4";

  /** The quarter (1/4) rest symbol: {@value}. */
  String REST_1_4 = "\uE4E5";

  /** The eighth (1/8) rest symbol: {@value}. */
  String REST_1_8 = "\uE4E6";

  /** The sixteenth (1/16) rest symbol: {@value}. */
  String REST_1_16 = "\uE4E7";

  /** The thirty-second (1/32) rest symbol: {@value}. */
  String REST_1_32 = "\uE4E8";

  /** The sixty-fourth (1/64) rest symbol: {@value}. */
  String REST_1_64 = "\uE4E9";

  /** The one-hundred-twenty-eighth (1/128) rest symbol: {@value}. */
  String REST_1_128 = "\uE4EA";

  /** The two-hundred-fifty-sixth (1/256) rest symbol: {@value}. */
  String REST_1_256 = "\uE4EB";

  /** The five-hundred-twelfth (1/512) rest symbol: {@value}. */
  String REST_1_512 = "\uE4EC";

  /** The one-thousand-twenty-fourth (1/1024) rest symbol: {@value}. */
  String REST_1_1024 = "\uE4ED";

  /**
   * @param value the {@link Fraction} value.
   * @return the corresponding rest glyph.
   */
  static String get(Fraction value) {

    int beats = value.getBeats();
    int fract = value.getUnit();
    if (beats == fract) { // 4/4 case
      beats = 1;
      fract = 1;
    }
    if (beats == 1) {
      if (fract == 1) {
        return REST_1;
      } else if (fract == 2) {
        return REST_1_2;
      } else if (fract == 4) {
        return REST_1_4;
      } else if (fract == 8) {
        return REST_1_8;
      } else if (fract == 16) {
        return REST_1_16;
      } else if (fract == 32) {
        return REST_1_32;
      } else if (fract == 64) {
        return REST_1_64;
      } else if (fract == 128) {
        return REST_1_128;
      } else if (fract == 256) {
        return REST_1_256;
      } else if (fract == 512) {
        return REST_1_512;
      } else if (fract == 1024) {
        return REST_1_1024;
      }
    }
    return null;
  }

}
