package io.github.musicdoc.glyphs.unicode;

import io.github.musicdoc.rythm.Fraction;

/**
 * {@link UnicodeGlyphs} for {@link io.github.musicdoc.rythm.rest.Rest}s.
 */
public interface UnicodeGlyphsRests extends UnicodeGlyphs {

  /** The whole (1/1) rest symbol: {@value}. */
  String REST_1 = "\uD834\uDD3B";

  /** The half (1/2) rest symbol: {@value}. */
  String REST_1_2 = "\uD834\uDD3C";

  /** The quarter (1/4) rest symbol: {@value}. */
  String REST_1_4 = "\uD834\uDD3D";

  /** The eighth (1/8) rest symbol: {@value}. */
  String REST_1_8 = "\uD834\uDD3E";

  /** The sixteenth (1/16) rest symbol: {@value}. */
  String REST_1_16 = "\uD834\uDD3F";

  /** The thirty-second (1/32) rest symbol: {@value}. */
  String REST_1_32 = "\uD834\uDD40";

  /** The sixty-fourth (1/64) rest symbol: {@value}. */
  String REST_1_64 = "\uD834\uDD41";

  /** The one-hundred-twenty-eighth (1/128) rest symbol: {@value}. */
  String REST_1_128 = "\uD834\uDD42";

  /**
   * @param value the {@link Fraction} value.
   * @return the corresponding rest glyph.
   */
  static String get(Fraction value) {

    int beats = value.getBeats();
    int fract = value.getFraction();
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
      }
    }
    return null;
  }
}
