package io.github.musicdoc.music.glyphs;

import io.github.musicdoc.music.rythm.Fraction;

/**
 * {@link SmuflGlyphs} for {@link io.github.musicdoc.music.note.Note}s.
 */
public interface SmuflGlyphsNote extends SmuflGlyphs {

  /** The whole (4/4) note symbol: {@value}. */
  String NOTE_1 = "\uE1D2";

  /** The half (1/2) note symbol with stem up: {@value}. */
  String NOTE_1_2_UP = "\uE1D3";

  /** The half (1/2) note symbol with stem down: {@value}. */
  String NOTE_1_2_DOWN = "\uE1D4";

  /** The quarter (1/4) note symbol with stem up: {@value}. */
  String NOTE_1_4_UP = "\uE1D5";

  /** The quarter (1/4) note symbol with stem down: {@value}. */
  String NOTE_1_4_DOWN = "\uE1D6";

  /** The eighth (1/8) note symbol with stem up: {@value}. */
  String NOTE_1_8_UP = "\uE1D7";

  /** The eighth (1/8) note symbol with stem down: {@value}. */
  String NOTE_1_8_DOWN = "\uE1D8";

  /** The sixteenth (1/16) note symbol with stem up: {@value}. */
  String NOTE_1_16_UP = "\uE1D9";

  /** The sixteenth (1/16) note symbol with stem down: {@value}. */
  String NOTE_1_16_DOWN = "\uE1DA";

  /** The thirty-second (1/32) note symbol with stem up: {@value}. */
  String NOTE_1_32_UP = "\uE1DB";

  /** The thirty-second (1/32) note symbol with stem down: {@value}. */
  String NOTE_1_32_DOWN = "\uE1DC";

  /** The sixty-fourth (1/64) note symbol with stem up: {@value}. */
  String NOTE_1_64_UP = "\uE1DD";

  /** The sixty-fourth (1/64) note symbol with stem down: {@value}. */
  String NOTE_1_64_DOWN = "\uE1DE";

  /** The one-hundred-twenty-eighth (1/128) note symbol with stem up: {@value}. */
  String NOTE_1_128_UP = "\uE1DF";

  /** The one-hundred-twenty-eighth (1/128) note symbol with stem down: {@value}. */
  String NOTE_1_128_DOWN = "\uE1E0";

  /** The two-hundred-fifty-sixth (1/256) note symbol with stem up: {@value}. */
  String NOTE_1_256_UP = "\uE1E1";

  /** The two-hundred-fifty-sixth (1/256) note symbol with stem down: {@value}. */
  String NOTE_1_256_DOWN = "\uE1E2";

  /** The five-hundred-twelfth (1/512) note symbol with stem up: {@value}. */
  String NOTE_1_512_UP = "\uE1E3";

  /** The five-hundred-twelfth (1/512) note symbol with stem down: {@value}. */
  String NOTE_1_512_DOWN = "\uE1E4";

  /** The one-thousand-twenty-fourth (1/1024) note symbol with stem up: {@value}. */
  String NOTE_1_1024_UP = "\uE1E5";

  /** The one-thousand-twenty-fourth (1/1024) note symbol with stem up: {@value}. */
  String NOTE_1_1024_DOWN = "\uE1E6";

  /** The augmented dot: {@value}. */
  String AUGMENTATION_DOT = "\uE1E7";

  /**
   * @param value the {@link Fraction}.
   * @param down - {@code true} for stem down, {@code false} otherwise (stem up).
   * @return the according glyph.
   */
  static String get(Fraction value, boolean down) {

    int beats = value.getBeats();
    int fract = value.getFraction();
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
      } else if (fract == 512) {
        if (down) {
          return NOTE_1_512_DOWN;
        }
        return NOTE_1_512_UP;
      } else if (fract == 1024) {
        if (down) {
          return NOTE_1_1024_DOWN;
        }
        return NOTE_1_1024_UP;
      }
    }
    return null;
  }

}
