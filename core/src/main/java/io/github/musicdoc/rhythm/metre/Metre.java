package io.github.musicdoc.rhythm.metre;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.rhythm.fraction.SimpleFraction;

/**
 * A {@link Metre} (meter in American) indicates the {@link io.github.musicdoc.rhythm.value.MusicalValue} of a regular
 * bar in a {@link io.github.musicdoc.stave.Stave}. E.g. a {@link #_4_4 4/4} metre allows to fit 4
 * {@link io.github.musicdoc.rhythm.value.MusicalValue#_1_4 quarters} into one bar.<br/>
 * The {@link #getUnit() unit} indicates the unit of a regular beat. Therefore a {@link #_4_4 4/4} metre is not the same
 * as a {@link #_2_2 2/2} metre even though they can cover the same
 * {@link io.github.musicdoc.rhythm.value.MusicalValue}s per bar. For details see
 * <a href="https://en.wikipedia.org/wiki/Metre_(music)">metre@wikipedia</a>.
 */
public class Metre extends SimpleFraction<Metre> {

  private String text;

  /** A 3/4 beat. */
  public static final Metre _3_4 = create(3, 4);

  /** A 4/4 beat. */
  public static final Metre _4_4 = create(4, 4);

  /** A 2/2 beat. */
  public static final Metre _2_2 = create(2, 2);

  /** A 3/4 beat. */
  public static final Metre _6_8 = create(6, 8);

  /** A 4/4 beat as common time (C glyph). */
  public static final Metre COMMON_TIME = create(4, 4, "C");

  /** A 2/2 beat as cut time (C glyph with vertical bar through it). Also called "alla breve". */
  public static final Metre CUT_TIME = create(2, 2, "C|");

  /** No beat. */
  public static final Metre NONE = create(0, 1, "none");

  private Metre(int beats, int fraction) {

    this(beats, fraction, null);
  }

  private Metre(int beats, int fraction, String text) {

    super(beats, fraction);
    this.text = text;
  }

  private Metre(Metre beat, MutableObjecteCopier copier) {

    // do not copy text on purpose
    super(beat, copier);
  }

  @Override
  public Metre copy(MutableObjecteCopier copier) {

    return new Metre(this, copier);
  }

  /**
   * @return {@code true} if this {@link Metre} is
   *         <a href="https://en.wikipedia.org/wiki/Metre_(music)#Compound_metre">compound</a>.
   */
  public boolean isCompound() {

    if (this.unit == 8) {
      if ((this.beats == 6) || (this.beats == 9) || (this.beats == 12)) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected String getText() {

    return this.text;
  }

  private static Metre create(int beats, int unit) {

    return create(beats, unit, null);
  }

  private static Metre create(int beats, int unit, String text) {

    return new Metre(beats, unit, text).makeImmutable();
  }

  /**
   * @param beats the number of {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit}.
   * @return the according {@link Metre} instance.
   */
  public static Metre of(int beats, int unit) {

    return new Metre(beats, unit);
  }

}
