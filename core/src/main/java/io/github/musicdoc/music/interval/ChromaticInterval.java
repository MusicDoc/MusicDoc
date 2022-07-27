/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.interval;

import java.util.Objects;

import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.harmony.chord.Chord;
import io.github.musicdoc.music.harmony.chord.ChordExtension;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.tone.TonePitch;

/**
 * Enum with the {@link #getChromaticSteps() chromatic} {@link ToneInterval}s.
 */
public class ChromaticInterval implements ToneInterval {

  /**
   * The "empty" interval.
   */
  public static final ChromaticInterval PERFECT_UNISON = new ChromaticInterval(0, 0);

  /**
   * One semitone step (one diatonic step).
   */
  public static final ChromaticInterval MINOR_SECOND = new ChromaticInterval(1, 1);

  /**
   * Two semitone steps (one diatonic step).
   */
  public static final ChromaticInterval MAJOR_SECOND = new ChromaticInterval(2, 1);

  /**
   * Three semitone steps (two diatonic steps). The third of a {@link TonalSystem#MINOR minor} {@link Chord} or
   * {@link MusicalKey key}.
   */
  public static final ChromaticInterval MINOR_THIRD = new ChromaticInterval(3, 2);

  /**
   * Four semitone steps (two diatonic steps). The third of a {@link TonalSystem#MAJOR major} {@link Chord} or
   * {@link MusicalKey key}.
   */
  public static final ChromaticInterval MAJOR_THIRD = new ChromaticInterval(4, 2);

  /**
   * Five semitone steps (three diatonic steps). Same as {@link DiatonicInterval#FOURTH} also called <em>quartum</em>.
   */
  public static final ChromaticInterval PERFECT_FOURTH = new ChromaticInterval(5, 3);

  /**
   * Six semitone steps (three diatonic steps).
   */
  public static final ChromaticInterval AUGMENTED_FOURTH = new ChromaticInterval(6, 3);

  /**
   * Six semitone steps (four diatonic steps).
   */
  public static final ChromaticInterval DIMINISHED_FIFTH = new ChromaticInterval(6, 4);

  /**
   * Seven semitone steps (four diatonic steps). Same as {@link DiatonicInterval#FIFTH} also called <em>quintium</em>,
   * the total interval of a regular {@link TonalSystem#MAJOR major} or {@link TonalSystem#MINOR minor} {@link Chord}.
   */
  public static final ChromaticInterval PERFECT_FIFTH = new ChromaticInterval(7, 4);

  /**
   * Seven semitone steps (five diatonic steps).
   */
  public static final ChromaticInterval DIMINISHED_SIXT = new ChromaticInterval(7, 5);

  /**
   * Eight semitone steps (five diatonic steps).
   */
  public static final ChromaticInterval MINOR_SIXT = new ChromaticInterval(8, 5);

  /**
   * Nine semitone steps (five diatonic steps).
   */
  public static final ChromaticInterval MAJOR_SIXT = new ChromaticInterval(9, 5);

  /**
   * Nine semitone steps (six diatonic steps).
   */
  public static final ChromaticInterval DIMINISHED_SEVENTH = new ChromaticInterval(9, 6);

  /**
   * Ten semitone steps (five diatonic steps).
   */
  public static final ChromaticInterval AUGMENTED_SIXT = new ChromaticInterval(10, 5);

  /**
   * Ten semitone steps (six diatonic steps). The regular {@link DiatonicInterval#SEVENTH seventh} used e.g. for
   * {@link ChordExtension#_7}. Especially important for dominant {@link Chord}s.
   */
  public static final ChromaticInterval MINOR_SEVENTH = new ChromaticInterval(10, 6);

  /**
   * Eigth semitone steps.
   */
  public static final ChromaticInterval MAJOR_SEVENTH = new ChromaticInterval(11, 6);

  /**
   * Ten semitone steps (five diatonic steps).
   */
  public static final ChromaticInterval AUGMENTED_SEVENTH = new ChromaticInterval(12, 6);

  /**
   * Twelve semitone steps. Results in the logically same {@link TonePitch} on a higher octave.
   */
  public static final ChromaticInterval PERFECT_OCTAVE = new ChromaticInterval(12, 7);

  /**
   * Thirteen semitone steps.
   */
  public static final ChromaticInterval MINOR_NINTH = new ChromaticInterval(13, 8);

  /**
   * Fourteen semitone steps.
   */
  public static final ChromaticInterval MAJOR_NINTH = new ChromaticInterval(14, 8);

  /**
   * Fifteen semitone steps.
   */
  public static final ChromaticInterval MINOR_TENTH = new ChromaticInterval(15, 9);

  /**
   * Sixteen semitone steps.
   */
  public static final ChromaticInterval MAJOR_TENTH = new ChromaticInterval(16, 9);

  /**
   * Seventeen semitone steps.
   */
  public static final ChromaticInterval PERFECT_ELEVENTH = new ChromaticInterval(17, 10);

  /**
   * Eighteen semitone steps.
   */
  public static final ChromaticInterval DIMINISHED_TWELVE = new ChromaticInterval(18, 11);

  /**
   * Nineteen semitone steps.
   */
  public static final ChromaticInterval PERFECT_TWELVE = new ChromaticInterval(19, 11);

  /**
   * Twenty semitone steps.
   */
  public static final ChromaticInterval MINOR_THIRTEENTH = new ChromaticInterval(20, 12);

  /**
   * Twenty-one semitone steps.
   */
  public static final ChromaticInterval MAJOR_THIRTEENTH = new ChromaticInterval(21, 12);

  private static final ChromaticInterval[] INTERVALS = new ChromaticInterval[] { PERFECT_UNISON, MINOR_SECOND,
  MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SIXT, MAJOR_SIXT,
  MINOR_SEVENTH, MAJOR_SEVENTH, PERFECT_OCTAVE, MINOR_NINTH, MAJOR_NINTH, MINOR_TENTH, MAJOR_TENTH, PERFECT_ELEVENTH,
  DIMINISHED_TWELVE, PERFECT_TWELVE, MINOR_THIRTEENTH, MAJOR_THIRTEENTH };

  private final Integer chromaticSteps;

  private final Integer diatonicSteps;

  private ChromaticInterval(int chromatic, int diatonic) {

    this.chromaticSteps = Integer.valueOf(chromatic);
    this.diatonicSteps = Integer.valueOf(diatonic);
  }

  @Override
  public Integer getChromaticSteps() {

    return this.chromaticSteps;
  }

  @Override
  public Integer getDiatonicSteps() {

    return this.diatonicSteps;
  }

  @Override
  public int getChromaticSteps(TonalSystem system) {

    return this.chromaticSteps;
  }

  @Override
  public int getDiatonicSteps(TonalSystem system) {

    return this.diatonicSteps;
  }

  @Override
  public int getOctaves() {

    return this.chromaticSteps / 12;
  }

  @Override
  public boolean isEmpty() {

    return (this.chromaticSteps == 0);
  }

  @Override
  public ToneInterval invert() {

    int chromatic = -this.chromaticSteps;
    int diatonic = -this.diatonicSteps;
    if ((chromatic >= 0) && (chromatic < INTERVALS.length)) {
      ChromaticInterval inverted = INTERVALS[chromatic];
      if (inverted.diatonicSteps.intValue() == diatonic) {
        return inverted;
      }
    }
    return new ChromaticInterval(chromatic, diatonic);
  }

  @Override
  public int hashCode() {

    return this.chromaticSteps;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ChromaticInterval other = (ChromaticInterval) obj;
    if (!Objects.equals(this.chromaticSteps, other.chromaticSteps)
        || !Objects.equals(this.diatonicSteps, other.diatonicSteps)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {

    return "#" + this.chromaticSteps;
  }

  /**
   * @param chromaticSteps the number of {@link #getChromaticSteps() chromatic steps}.
   * @return the corresponding {@link ChromaticInterval}.
   */
  public static ChromaticInterval of(int chromaticSteps) {

    int diatonicSteps;
    if (chromaticSteps >= 0) {
      if (chromaticSteps < INTERVALS.length) {
        return INTERVALS[chromaticSteps];
      }
      int modulo = chromaticSteps % 12;
      ChromaticInterval mod = INTERVALS[modulo];
      int octaves = (chromaticSteps / 12);
      diatonicSteps = (octaves * 7) + mod.diatonicSteps;
    } else {
      int negation = -chromaticSteps;
      int modulo = negation % 12;
      ChromaticInterval mod = INTERVALS[modulo];
      int octaves = (negation / 12);
      diatonicSteps = -((octaves * 7) + mod.diatonicSteps);
    }
    return new ChromaticInterval(chromaticSteps, diatonicSteps);
  }

}
