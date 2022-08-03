package io.github.musicdoc.music.rythm.beat;

import java.util.Objects;

import io.github.musicdoc.music.rythm.AbstractFraction;

/**
 * A {@link Beat} indicates the {@link io.github.musicdoc.music.rythm.value.MusicalValue} of a single bar in a
 * {@link io.github.musicdoc.music.stave.Stave}. E.g. a {@link #_4_4 4/4} beat allows to fit 4
 * {@link io.github.musicdoc.music.rythm.value.MusicalValue#_1_4 quarters} into one bar.<br/>
 * The {@link #getFraction() fraction} indicates the unit of a regular beat. Therefore a {@link #_4_4 4/4} beat is not
 * the same as a {@link #_2_2 2/2} beat even though they can cover the same
 * {@link io.github.musicdoc.music.rythm.value.MusicalValue}s per bar.
 */
public class Beat extends AbstractFraction {

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

  private final String text;

  private Beat(int beats, int fraction) {

    this(beats, fraction, beats + "/" + fraction);
  }

  private Beat(int beats, int fraction, String text) {

    super(beats, fraction);
    this.text = text;
  }

  @Override
  protected boolean isEqualTo(AbstractFraction other) {

    if (super.isEqualTo(other)) {
      return Objects.equals(this.text, ((Beat) other).text);
    }
    return false;
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
  public void toString(StringBuilder sb) {

    sb.append(this.text);
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
