package io.github.musicdoc.glyphs.unicode;

import io.github.musicdoc.rhythm.fraction.Fraction;

/**
 * {@link UnicodeGlyphs} for {@link io.github.musicdoc.note.Note}s.
 */
public interface UnicodeGlyphsNotes extends UnicodeGlyphs {

  /** The whole (4/4) note symbol: {@value}. */
  String NOTE_1 = "\uD834\uDD5D";

  /** The half (1/2) note symbol with stem up: {@value}. */
  String NOTE_1_2_UP = "\uD834\uDD5E";

  /** The half (1/2) note symbol with stem down: {@value}. */
  String NOTE_1_2_DOWN = null;

  /** The quarter (1/4) note symbol with stem up: {@value}. */
  String NOTE_1_4_UP = "\uD834\uDD5F";

  /** The quarter (1/4) note symbol with stem down: {@value}. */
  String NOTE_1_4_DOWN = null;

  /** The eighth (1/8) note symbol with stem up: {@value}. */
  String NOTE_1_8_UP = "\uD834\uDD60";

  /** The eighth (1/8) note symbol with stem down: {@value}. */
  String NOTE_1_8_DOWN = null;

  /** The sixteenth (1/16) note symbol with stem up: {@value}. */
  String NOTE_1_16_UP = "\uD834\uDD61";

  /** The sixteenth (1/16) note symbol with stem down: {@value}. */
  String NOTE_1_16_DOWN = null;

  /** The thirty-second (1/32) note symbol with stem up: {@value}. */
  String NOTE_1_32_UP = "\uD834\uDD62";

  /** The thirty-second (1/32) note symbol with stem down: {@value}. */
  String NOTE_1_32_DOWN = null;

  /** The sixty-fourth (1/64) note symbol with stem up: {@value}. */
  String NOTE_1_64_UP = "\uD834\uDD63";

  /** The sixty-fourth (1/64) note symbol with stem down: {@value}. */
  String NOTE_1_64_DOWN = null;

  /** The one-hundred-twenty-eighth (1/128) note symbol with stem up: {@value}. */
  String NOTE_1_128_UP = "\uD834\uDD64";

  /** The one-hundred-twenty-eighth (1/128) note symbol with stem down: {@value}. */
  String NOTE_1_128_DOWN = null;

  /** The two-hundred-fifty-sixth (1/256) note symbol with stem up: {@value}. */
  String NOTE_1_256_UP = null;

  /** The two-hundred-fifty-sixth (1/256) note symbol with stem down: {@value}. */
  String NOTE_1_256_DOWN = null;

  /**
   * @param value the {@link Fraction}.
   * @param down - {@code true} for stem down, {@code false} otherwise (stem up).
   * @return the according glyph.
   */
  static String get(Fraction value, boolean down) {

    int beats = value.getBeats();
    int fract = value.getUnit();
    if (beats == fract) { // 4/4 case
      beats = 1;
      fract = 1;
    }
    if (beats == 1) {
      if (fract == 1) {
        return NOTE_1;
      } else if (fract == 2) {
        if (down) {
          return NOTE_1_2_DOWN;
        }
        return NOTE_1_2_UP;
      } else if (fract == 4) {
        if (down) {
          return NOTE_1_4_DOWN;
        }
        return NOTE_1_4_UP;
      } else if (fract == 8) {
        if (down) {
          return NOTE_1_8_DOWN;
        }
        return NOTE_1_8_UP;
      } else if (fract == 16) {
        if (down) {
          return NOTE_1_16_DOWN;
        }
        return NOTE_1_16_UP;
      } else if (fract == 32) {
        if (down) {
          return NOTE_1_32_DOWN;
        }
        return NOTE_1_32_UP;
      } else if (fract == 64) {
        if (down) {
          return NOTE_1_64_DOWN;
        }
        return NOTE_1_64_UP;
      } else if (fract == 128) {
        if (down) {
          return NOTE_1_128_DOWN;
        }
        return NOTE_1_128_UP;
      } else if (fract == 256) {
        if (down) {
          return NOTE_1_256_DOWN;
        }
        return NOTE_1_256_UP;
      }
    }
    return null;
  }

}
