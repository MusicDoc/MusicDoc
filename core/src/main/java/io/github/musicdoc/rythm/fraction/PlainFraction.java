package io.github.musicdoc.rythm.fraction;

import io.github.musicdoc.MutableObjecteCopier;

/**
 * A plain implementation of {@link Fraction}. It represents nothing more as a {@link Fraction} consisting of
 * {@link #getBeats() beats} per {@link #getUnit() fraction}. In contrast e.g. a
 * {@link io.github.musicdoc.rythm.value.MusicalValue} may have a
 * {@link io.github.musicdoc.rythm.value.MusicalValue#getVariation() variation}.
 */
public class PlainFraction extends AbstractFraction<PlainFraction> {

  /** Whole (1/1) also called semi-breve. */
  public static final PlainFraction _1_1 = create(1, 1);

  /** Half (1/2) also called minim. */
  public static final PlainFraction _1_2 = create(1, 2);

  /** Fourth (1/4) also called quarter or crotchet . */
  public static final PlainFraction _1_4 = create(1, 4);

  /** Eighth (1/8) also called quaver. */
  public static final PlainFraction _1_8 = create(1, 8);

  /** Sixteenth (1/16) also called semi-quaver. */
  public static final PlainFraction _1_16 = create(1, 16);

  /**
   * The constructor.
   *
   * @param beats the {@link #getBeats() beats}.
   * @param fraction the {@link #getUnit() fraction}.
   */
  public PlainFraction(int beats, int fraction) {

    super(beats, fraction);
  }

  /**
   * The copy constructor.
   *
   * @param fraction the {@link Fraction} to copy.
   */
  public PlainFraction(Fraction fraction) {

    super(fraction);
  }

  private PlainFraction(PlainFraction fraction, MutableObjecteCopier copier) {

    super(fraction, copier);
  }

  @Override
  public PlainFraction copy(MutableObjecteCopier copier) {

    return new PlainFraction(this, copier);
  }

  @Override
  public PlainFraction asPlain() {

    return this;
  }

  private static PlainFraction create(int beats, int fraction) {

    return new PlainFraction(beats, fraction).makeImmutable();
  }

  /**
   * @param beats the {@link #getBeats() beats}.
   * @param fraction the {@link #getUnit() fraction}.
   * @return an instance of {@link PlainFraction} with the given values.
   */
  public static PlainFraction of(int beats, int fraction) {

    return new PlainFraction(beats, fraction);
  }

}
