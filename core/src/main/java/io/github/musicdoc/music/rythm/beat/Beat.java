package io.github.musicdoc.music.rythm.beat;

import io.github.musicdoc.music.rythm.Fraction;
import io.github.musicdoc.music.rythm.value.MusicalValue;
import io.github.musicdoc.music.stave.Stave;

/**
 * A {@link Beat} indicates the {@link MusicalValue} of a single bar in a {@link Stave}. E.g. a {@link #_4_4 4/4} beat
 * allows to fit 4 {@link MusicalValue#_1_4 quarters} into one bar.<br/>
 * The {@link #getFraction() fraction} indicates the unit of a regular beat. Therefore a {@link #_4_4 4/4} beat is not
 * the same as a {@link #_2_2 2/2} beat even though they can cover the same {@link MusicalValue}s per bar.
 */
public class Beat implements Fraction {

  /** A 3/4 beat. */
  public static final Beat _3_4 = new Beat(3, 4);

  /** A 4/4 beat. */
  public static final Beat _4_4 = new Beat(4, 4);

  /** A 2/2 beat. */
  public static final Beat _2_2 = new Beat(2, 2);

  /** A 3/4 beat. */
  public static final Beat _6_8 = new Beat(6, 8);

  /** A 4/4 beat as common time (C glyph). */
  public static final Beat COMMON_TIME = new Beat(4, 4, "C");

  /** A 2/2 beat as cut time (C glyph with vertical bar through it). Also called "alla breve". */
  public static final Beat CUT_TIME = new Beat(2, 2, "C|");

  /** No beat. */
  public static final Beat NONE = new Beat(0, 1, "none");

  private final int beats;

  private final int fraction;

  private final String text;

  private Beat(int beats, int fraction) {

    this(beats, fraction, beats + "/" + fraction);
  }

  private Beat(int beats, int fraction, String text) {

    super();
    this.beats = beats;
    this.fraction = fraction;
    this.text = text;
  }

  @Override
  public int getBeats() {

    return this.beats;
  }

  @Override
  public int getFraction() {

    return this.fraction;
  }

  @Override
  public int hashCode() {

    return 31 * this.beats + this.fraction;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    Beat other = (Beat) obj;
    if (this.beats != other.beats) {
      return false;
    }
    if (this.fraction != other.fraction) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {

    return this.text;
  }

  /**
   * @param beats the number of {@link #getBeats() beats}.
   * @param fraction the {@link #getFraction() fraction}.
   * @return the according {@link Beat} instance.
   */
  public static Beat of(int beats, int fraction) {

    return new Beat(beats, fraction);
  }

}
