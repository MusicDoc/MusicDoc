package io.github.musicdoc.instrument.string;

import java.util.List;

import io.github.musicdoc.tone.Tone;

/**
 * Interface for the tuning of a {@link StringInstrument}.
 */
public interface StringTuning {

  /**
   * @return the tuning of the strings as a {@link List} of {@link Tone}s from the highest along the instrument in
   *         linear direction. While a regular guitar or ukulele will have a tuning with the strings getting lower in
   *         strict monotonic order, this is not the case for special tunings or even for a 12-string guitar.
   */
  List<Tone> getStrings();

  /**
   * @param i the index of the requested string starting from {@code 0} as the first and highest string up to
   *        {@link #getStrings() strings} {@link List#size() size} - 1 as the last and lowest string.
   * @return the {@link Tone} of the {@link #getStrings() string} at the given index or {@code null} if no such string
   *         exits.
   */
  default Tone getString(int i) {

    List<Tone> strings = getStrings();
    if ((i < 0) || (i >= strings.size())) {
      return null;
    }
    return strings.get(i);
  }

  /**
   * @return the maximum fret number that can be played (comfortably) on this instrument or with this tuning. Will be
   *         {@code 0} for string instruments that do not have frets as typical orchestra strings (e.g. violin). For
   *         instruments with frets this may depend on aspects like electric vs. acoustic instruments as well as on
   *         features like <em>cutaway</em>. Every reasonable guitar like instrument should at least support 12 frets
   *         (one octave).
   */
  default int getMaxFret() {

    return 0;
  }
}
