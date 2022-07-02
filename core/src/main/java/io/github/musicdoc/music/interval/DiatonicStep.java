package io.github.musicdoc.music.interval;

import io.github.musicdoc.music.harmony.TonalSystem;

/**
 * Implementation of {@link ToneInterval} as enum with the {@link #getDiatonicSteps() diatonic steps} within a single
 * octave.
 */
public enum DiatonicStep implements ToneInterval {

  /**
   * The empty {@link ToneInterval} representing the first tone of the
   * {@link io.github.musicdoc.music.harmony.MusicalKey#getDiatonicScale() diatonic scale}
   * ({@link io.github.musicdoc.music.harmony.MusicalKey#getTonika() tonika}).
   *
   * @see DiatonicInterval#UNISON
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#C
   */
  S0,

  /**
   * The second tone of the {@link io.github.musicdoc.music.harmony.MusicalKey#getDiatonicScale() diatonic scale} or one
   * diatonic step higher than {@link #S0}.
   *
   * @see DiatonicInterval#SECOND
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#D
   */
  S1,

  /**
   * The third tone of the {@link io.github.musicdoc.music.harmony.MusicalKey#getDiatonicScale() diatonic scale} or two
   * diatonic steps higher than {@link #S0}.
   *
   * @see DiatonicInterval#THIRD
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#E
   */
  S2,

  /**
   * The fourth tone of the {@link io.github.musicdoc.music.harmony.MusicalKey#getDiatonicScale() diatonic scale} or
   * three diatonic steps higher than {@link #S0}.
   *
   * @see DiatonicInterval#FOURTH
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#F
   */
  S3,

  /**
   * The fifth tone of the {@link io.github.musicdoc.music.harmony.MusicalKey#getDiatonicScale() diatonic scale} or four
   * diatonic steps higher than {@link #S0}.
   *
   * @see DiatonicInterval#FIFTH
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#G
   */
  S4,

  /**
   * The sixth tone of the {@link io.github.musicdoc.music.harmony.MusicalKey#getDiatonicScale() diatonic scale} or five
   * diatonic steps higher than {@link #S0}.
   *
   * @see DiatonicInterval#SIXTH
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#A
   */
  S5,

  /**
   * The seventh tone of the {@link io.github.musicdoc.music.harmony.MusicalKey#getDiatonicScale() diatonic scale} or
   * six diatonic steps higher than {@link #S0}.
   *
   * @see DiatonicInterval#SEVENTH
   * @see io.github.musicdoc.music.tone.TonePitchEnglish#B
   */
  S6;

  private static final DiatonicStep[] STEPS = values();

  /**
   * @return the number of diatonic steps.
   */
  public int get() {

    return ordinal();
  }

  @Override
  public Integer getDiatonicSteps() {

    return Integer.valueOf(get());
  }

  @Override
  public int getDiatonicSteps(TonalSystem system) {

    return get();
  }

  @Override
  public int getChromaticSteps(TonalSystem system) {

    if (system == null) {
      if (this == S0) {
        return 0;
      }
      return Integer.MIN_VALUE;
    }
    return system.getChromaticSteps(get());
  }

  /**
   * @return the next {@link DiatonicStep} increased by one diatonic step but wrapping from {@link #S6} to {@link #S0}.
   */
  public DiatonicStep next() {

    if (this == S6) {
      return S0;
    } else {
      return STEPS[get() + 1];
    }
  }

  /**
   * @return the previous {@link DiatonicStep} decreased by one diatonic step but wrapping from {@link #S0} to
   *         {@link #S6}.
   */
  public DiatonicStep previous() {

    if (this == S0) {
      return S6;
    } else {
      return STEPS[get() - 1];
    }
  }

  /**
   * @param diatonicSteps the number of {@link #getDiatonicSteps() diatonic steps} to add.
   * @return the {@link DiatonicStep} created by adding the given number of {@link #getDiatonicSteps() diatonic steps}
   *         but wrapping (modulo 7).
   */
  public DiatonicStep add(int diatonicSteps) {

    return of(get() + diatonicSteps);
  }

  /**
   * @param step the {@link DiatonicStep} to add.
   * @return the {@link DiatonicStep} created by adding the given {@link DiatonicStep} but wrapping (modulo 7).
   */
  public DiatonicStep add(DiatonicStep step) {

    return add(step.get());
  }

  /**
   * @param diatonicSteps the number of {@link #getDiatonicSteps() diatonic steps} to subtract.
   * @return the {@link DiatonicStep} created by subtract the given number of {@link #getDiatonicSteps() diatonic steps}
   *         but wrapping (modulo 7).
   */
  public DiatonicStep subtract(int diatonicSteps) {

    return add(-diatonicSteps);
  }

  /**
   * @param step the {@link DiatonicStep} to subtract.
   * @return the {@link DiatonicStep} created by subtract the given {@link DiatonicStep} but wrapping (modulo 7).
   */
  public DiatonicStep subtract(DiatonicStep step) {

    return subtract(step.get());
  }

  /**
   * @param diatonicStep the {@link #getDiatonicSteps() diatonic steps}. Values out of the range from {@code 0-6} will
   *        be wrapped (modulo 7).
   * @return the {@link DiatonicStep}.
   */
  public static DiatonicStep of(int diatonicStep) {

    int index = diatonicStep;
    if ((index < 0) || (index >= 7)) {
      index = index % 7;
      if (index < 0) {
        index = index + 7;
      }
    }
    return STEPS[index];
  }

}
