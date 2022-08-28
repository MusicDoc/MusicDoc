package io.github.musicdoc.rythm.beat;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.rythm.fraction.AbstractFraction;

/**
 * A {@link Beat} indicates the {@link io.github.musicdoc.rythm.value.MusicalValue} of a single bar in a
 * {@link io.github.musicdoc.stave.Stave}. E.g. a {@link #_4_4 4/4} beat allows to fit 4
 * {@link io.github.musicdoc.rythm.value.MusicalValue#_1_4 quarters} into one bar.<br/>
 * The {@link #getUnit() fraction} indicates the unit of a regular beat. Therefore a {@link #_4_4 4/4} beat is not the
 * same as a {@link #_2_2 2/2} beat even though they can cover the same
 * {@link io.github.musicdoc.rythm.value.MusicalValue}s per bar.
 */
public class Beat extends AbstractFraction<Beat> {

  /** A 3/4 beat. */
  public static final Beat _3_4 = create(3, 4);

  /** A 4/4 beat. */
  public static final Beat _4_4 = create(4, 4);

  /** A 2/2 beat. */
  public static final Beat _2_2 = create(2, 2);

  /** A 3/4 beat. */
  public static final Beat _6_8 = create(6, 8);

  /** A 4/4 beat as common time (C glyph). */
  public static final Beat COMMON_TIME = create(4, 4, "C");

  /** A 2/2 beat as cut time (C glyph with vertical bar through it). Also called "alla breve". */
  public static final Beat CUT_TIME = create(2, 2, "C|");

  /** No beat. */
  public static final Beat NONE = create(0, 1, "none");

  private Beat(int beats, int fraction) {

    this(beats, fraction, null);
  }

  private Beat(int beats, int fraction, String text) {

    super(beats, fraction, text);
  }

  private Beat(Beat beat, MutableObjecteCopier copier) {

    super(beat, copier);
  }

  @Override
  public Beat copy(MutableObjecteCopier copier) {

    return new Beat(this, copier);
  }

  @Override
  public String toString() {

    return getText();
  }

  private static Beat create(int beats, int unit) {

    return create(beats, unit, null);
  }

  private static Beat create(int beats, int unit, String text) {

    return new Beat(beats, unit, text).makeImmutable();
  }

  /**
   * @param beats the number of {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit}.
   * @return the according {@link Beat} instance.
   */
  public static Beat of(int beats, int unit) {

    return new Beat(beats, unit);
  }

}
