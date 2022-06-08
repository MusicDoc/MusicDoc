package io.github.musicdoc.music.interval;

import io.github.musicdoc.music.harmony.MusicalKey;
import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.tone.TonePitch;
import io.github.musicdoc.music.tone.TonePitchEnglish;

/**
 * The {@link ChromaticStep} represents the number of semitones a {@link TonePitch} is higher than
 * {@link TonePitchEnglish#C}. Or in other words the index of a {@link TonePitch} in the
 * {@link MusicalKey#getChromaticScale() chromatic scale} of {@link MusicalKey#C_MAJOR}.
 *
 * @see TonePitch#getStep()
 */
public enum ChromaticStep implements Interval {

  /**
   * The empty {@link Interval} with zero {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#PERFECT_UNISON
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#C
   */
  S0,

  /**
   * One {@link #getChromaticSteps() chromatic step}.
   *
   * @see ChromaticInterval#MINOR_SECOND
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#C_SHARP
   */
  S1,

  /**
   * Two {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#MAJOR_SECOND
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#D
   */
  S2,

  /**
   * Three {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#MINOR_THIRD
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#D_SHARP
   */
  S3,

  /**
   * Four {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#MAJOR_THIRD
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#E
   */
  S4,

  /**
   * Five {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#PERFECT_FOURTH
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#F
   */
  S5,

  /**
   * Six {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#DIMINISHED_FIFTH
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#F_SHARP
   */
  S6,

  /**
   * Seven {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#PERFECT_FIFTH
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#G
   */
  S7,

  /**
   * Eight {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#MINOR_SIXT
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#G_SHARP
   */
  S8,

  /**
   * Nine {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#MAJOR_SIXT
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#A
   */
  S9,

  /**
   * Ten {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#MINOR_SEVENTH
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#B_FLAT
   */
  S10,

  /**
   * Eleven {@link #getChromaticSteps() chromatic steps}.
   *
   * @see ChromaticInterval#MAJOR_SEVENTH
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#B
   */
  S11;

  private static final ChromaticStep[] STEPS = values();

  /**
   * @return the number of chromatic steps.
   */
  public int get() {

    return ordinal();
  }

  @Override
  public int getChromaticSteps(TonalSystem system) {

    return get();
  }

  @Override
  public int getDiatonicSteps(TonalSystem system) {

    if (system == null) {
      if (this == S0) {
        return 0;
      }
      return Integer.MIN_VALUE;
    }
    return system.getDiatonicSteps(get(), true);
  }

  /**
   * @return the next {@link ChromaticStep} increased by one semitone wrapping from {@link #S11} back to {@link #S0}.
   */
  public ChromaticStep next() {

    if (this == S11) {
      return S0;
    } else {
      return STEPS[get() + 1];
    }
  }

  /**
   * @return the previous {@link ChromaticStep} decreased by one semitone wrapping from {@link #S0} to {@link #S11}.
   */
  public ChromaticStep previous() {

    if (this == S0) {
      return S11;
    } else {
      return STEPS[get() - 1];
    }
  }

  /**
   * @param chromaticSteps the number of {@link #getChromaticSteps() chromatic steps} to add.
   * @return the {@link ChromaticStep} resulting from adding the given number of {@link #getChromaticSteps() chromatic
   *         steps}.
   */
  public ChromaticStep add(int chromaticSteps) {

    if (chromaticSteps == 0) {
      return this;
    }
    return of(get() + chromaticSteps);
  }

  /**
   * @param step the {@link ChromaticStep} to add.
   * @return the {@link ChromaticStep} resulting from adding the given {@link ChromaticStep}.
   */
  public ChromaticStep add(ChromaticStep step) {

    return add(step.get());
  }

  /**
   * @param chromaticSteps the number of {@link #getChromaticSteps() chromatic steps} to subtract.
   * @return the {@link ChromaticStep} resulting from subtracting the given number of {@link #getChromaticSteps()
   *         chromatic steps}.
   */
  public ChromaticStep subtract(int chromaticSteps) {

    return add(-chromaticSteps);
  }

  /**
   * @param step the {@link ChromaticStep} to subtract.
   * @return the {@link ChromaticStep} resulting from subtracting the given {@link ChromaticStep}.
   */
  public ChromaticStep subtract(ChromaticStep step) {

    return subtract(step.get());
  }

  @Override
  public String toString() {

    return Integer.toString(get());
  }

  /**
   * @param chromaticStep the number of {@link #getChromaticSteps() chromatic steps}.
   * @return the according {@link ChromaticStep}.
   */
  public static ChromaticStep of(int chromaticStep) {

    int index = chromaticStep;
    if ((index < 0) || (index >= 12)) {
      index = index % 12;
      if (index < 0) {
        index = index + 12;
      }
    }
    return STEPS[index];
  }

}
