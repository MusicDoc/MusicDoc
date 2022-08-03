/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.interval;

import io.github.musicdoc.harmony.TonalSystem;

/**
 * Enum with the {@link #getDiatonicSteps() diatonic} {@link ToneInterval}s.
 */
public class DiatonicInterval implements ToneInterval {

  /** The "empty" interval called (perfect) <em>unison</em>. Also called <em>primum</em>. */
  public static DiatonicInterval UNISON = new DiatonicInterval(0);

  /** <em>secundum</em>. */
  public static DiatonicInterval SECOND = new DiatonicInterval(1);

  /** <em>tertium</em>. */
  public static DiatonicInterval THIRD = new DiatonicInterval(2);

  /** <em>quartum</em>. */
  public static DiatonicInterval FOURTH = new DiatonicInterval(3);

  /** <em>quintum</em>. */
  public static DiatonicInterval FIFTH = new DiatonicInterval(4);

  /** <em>sextum</em>. */
  public static DiatonicInterval SIXTH = new DiatonicInterval(5);

  /** <em>septum</em>. */
  public static DiatonicInterval SEVENTH = new DiatonicInterval(6);

  /** Eight or <em>octave</em>. */
  public static DiatonicInterval OCTAVE = new DiatonicInterval(7);

  /** <em>nonum</em>. */
  public static DiatonicInterval NINTH = new DiatonicInterval(8);

  /** <em>decum</em>. */
  public static DiatonicInterval TENTH = new DiatonicInterval(9);

  /** <em>eleventh</em>. */
  public static DiatonicInterval ELEVENTH = new DiatonicInterval(10);

  /** <em>twelfth</em> or <em>tritave</em>. */
  public static DiatonicInterval TWELFTH = new DiatonicInterval(11);

  /** <em>thirteenth</em> or <em>compound sixth</em>. */
  public static DiatonicInterval THIRTEENTH = new DiatonicInterval(12);

  private static final DiatonicInterval[] INTERVALS = new DiatonicInterval[] { UNISON, SECOND, THIRD, FOURTH, FIFTH,
  SIXTH, SEVENTH, OCTAVE, NINTH, TENTH, ELEVENTH, TWELFTH, THIRTEENTH };

  private final Integer diatonicSteps;

  /**
   * The constructor.
   *
   * @param diatonicSteps - see {@link #getDiatonicSteps()}.
   */
  private DiatonicInterval(int diatonicSteps) {

    this.diatonicSteps = Integer.valueOf(diatonicSteps);
  }

  @Override
  public int getChromaticSteps(TonalSystem system) {

    int steps = this.diatonicSteps.intValue();
    if (system == null) {
      if ((steps % 7) == 0) {
        return (steps / 7) * 12;
      }
      return Integer.MIN_VALUE;
    } else {
      return system.getChromaticSteps(steps);
    }
  }

  @Override
  public int getDiatonicSteps(TonalSystem system) {

    return this.diatonicSteps.intValue();
  }

  @Override
  public Integer getDiatonicSteps() {

    return this.diatonicSteps;
  }

  @Override
  public int getOctaves() {

    return this.diatonicSteps / 7;
  }

  @Override
  public boolean isEmpty() {

    return (this.diatonicSteps == 0);
  }

  @Override
  public ToneInterval invert() {

    return of(-this.diatonicSteps);
  }

  @Override
  public int hashCode() {

    return this.diatonicSteps;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    DiatonicInterval other = (DiatonicInterval) obj;
    if (this.diatonicSteps != other.diatonicSteps) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {

    String string = Integer.toString(this.diatonicSteps);
    if (this.diatonicSteps >= 0) {
      string = "+" + string;
    }
    return string;
  }

  /**
   * @param diatonicSteps the number of {@link #getDiatonicSteps() diatonic steps}.
   * @return the corresponding {@link DiatonicInterval} or <code>null</code> if no such {@link DiatonicInterval} exists
   *         (given value is negative or too high).
   */
  public static DiatonicInterval of(int diatonicSteps) {

    if ((diatonicSteps >= 0) && (diatonicSteps < INTERVALS.length)) {
      return INTERVALS[diatonicSteps];
    }
    return new DiatonicInterval(diatonicSteps);
  }

}
