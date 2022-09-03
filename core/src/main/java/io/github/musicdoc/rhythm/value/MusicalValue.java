/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.rhythm.value;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.rhythm.fraction.AdvancedFraction;
import io.github.musicdoc.rhythm.fraction.Fraction;
import io.github.musicdoc.rhythm.fraction.SimpleFraction;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.rhythm.value.variation.AbstractFractionVariation;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariation;
import io.github.musicdoc.rhythm.value.variation.Punctuation;

/**
 * The value of a {@link ValuedItem} like a {@link io.github.musicdoc.tone.Tone} or
 * {@link io.github.musicdoc.rhythm.rest.Rest}.
 *
 * @see ValuedItem
 */
public class MusicalValue extends AdvancedFraction<MusicalValue> {

  /**
   * Whole (1/1). Unlike other predefined {@link MusicalValue}s this value does not have a fixed length in {@link #_1_4
   * quarters} but lasts a full bar whatever the {@link Metre} may be. This is used for a whole rest that lasts a full
   * bar (instead of {@link #_4_4}).
   */
  public static final MusicalValue _1_1 = create(1, 1);

  /**
   * Whole (4/4) tone also called <em>semi-breve</em>.
   *
   * @see #_1_1
   */
  public static final MusicalValue _4_4 = create(4, 4);

  /**
   * Half (1/2) also called <em>minim</em>.
   */
  public static final MusicalValue _1_2 = create(1, 2);

  /**
   * {@link Punctuation#P1 Punctuated} {@link #_1_2 half} (1/2) so actually 3/4.
   */
  public static final MusicalValue _1_2p = create(1, 2, Punctuation.P1);

  /**
   * Fourth (1/4) also called <em>quarter</em> or </em><em>crotchet</em>.
   */
  public static final MusicalValue _1_4 = create(1, 4);

  /**
   * Three fourth (3/4) what is as long as a {@link #_1_2p punctuated half}.
   */
  public static final MusicalValue _3_4 = create(3, 4);

  /**
   * {@link Punctuation#P1 Punctuated} {@link #_1_4 fourth} (1/4) so actually 3/8.
   */
  public static final MusicalValue _1_4p = create(1, 4, Punctuation.P1);

  /**
   * Eighth (1/8) also called <em>quaver</em>.
   */
  public static final MusicalValue _1_8 = create(1, 8);

  /**
   * {@link Punctuation#P1 Punctuated} {@link #_1_8 eighth} (1/8) so actually 3/16.
   */
  public static final MusicalValue _1_8p = create(1, 8, Punctuation.P1);

  /**
   * Sixteenth (1/16) also called <em>semi-quaver</em>.
   */
  public static final MusicalValue _1_16 = create(1, 16);

  /**
   * Thirty-second fraction (1/32) also called <em>demi-semi-quaver</em>.
   */
  public static final MusicalValue _1_32 = create(1, 32);

  /**
   * The constructor.
   *
   * @param beats - see {@link #getBeats()}.
   * @param fraction - see {@link #getUnit()}.
   */
  public MusicalValue(int beats, int fraction) {

    this(beats, fraction, null);
  }

  /**
   * The constructor.
   *
   * @param beats - see {@link #getBeats()}.
   * @param fraction - see {@link #getUnit()}.
   * @param variation - see {@link #getVariation()}.
   */
  public MusicalValue(int beats, int fraction, AbstractFractionVariation<?> variation) {

    super(beats, fraction, variation);
  }

  /**
   * The copy constructor for normalization.
   *
   * @param fraction the {@link Fraction} to copy.
   */
  public MusicalValue(Fraction fraction) {

    this(fraction.getBeats(), fraction.getUnit());
  }

  private MusicalValue(MusicalValue value, MutableObjecteCopier copier) {

    super(value, copier);
  }

  @Override
  public MusicalValue copy(MutableObjecteCopier copier) {

    return new MusicalValue(this, copier);
  }

  /**
   * Determines if this value is relative. E.g. a {@link #_1_1 1/1} (whole value) is often considered to be equivalent
   * to {@link #_4_4 4/4} what is 4 times a {@link #_1_4}, what is actually wrong. Instead {@link #_1_1 1/1} is relative
   * to a given {@link Metre} so a whole rest lasts for one bar whatever the {@link Metre} is (so in case of 3/4 beat,
   * its {@link #toAbsoluteValue(Metre) absolute value} will be 3/4).
   *
   * @return {@code true} if {@link #getUnit() fraction} is {@code 1} and the value is relative to the {@link Metre},
   *         {@code false} otherwise.
   * @see #toAbsoluteValue(Metre)
   */
  public boolean isRelative() {

    return (this.plain.getBeats() == 1) && (this.plain.getUnit() == 1);
  }

  /**
   * @return {@code true} if NOT {@link #isRelative() relative} (absolute), <code>false</code> otherwise.
   * @see #isRelative()
   */
  public boolean isAbsolute() {

    return !isRelative();
  }

  /**
   * @param beat the {@link Metre} used as base to make this {@link MusicalValue} absolute.
   * @return the {@link #isAbsolute() absolute} {@link MusicalValue} according to the given {@link Metre}. Will return
   *         the current {@link MusicalValue} ({@code this}) if already {@link #isAbsolute() absolute}.
   * @see #isRelative()
   */
  public MusicalValue toAbsoluteValue(Metre beat) {

    if (isRelative()) {
      return new MusicalValue(beat.getBeats() * this.plain.getBeats(), this.plain.getUnit(), this.variation);
    }
    return this;
  }

  @Override
  public MusicalValue normalize() {

    SimpleFraction<?> plainNormalized = this.plain.normalize();
    if (plainNormalized == this.plain) {
      if (this.variation == MusicalValueVariation.NONE) {
        return this;
      }
    }
    return new MusicalValue(this);
  }

  private static MusicalValue create(int beats, int unit) {

    return new MusicalValue(beats, unit, MusicalValueVariation.NONE).makeImmutable();
  }

  private static MusicalValue create(int beats, int unit, MusicalValueVariation variation) {

    return new MusicalValue(beats, unit, variation).makeImmutable();
  }

}
