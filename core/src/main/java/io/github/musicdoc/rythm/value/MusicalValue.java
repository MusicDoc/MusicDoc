/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.rythm.value;

import java.util.Objects;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.rythm.beat.Beat;
import io.github.musicdoc.rythm.fraction.AbstractFraction;
import io.github.musicdoc.rythm.fraction.Fraction;

/**
 * The value of a {@link ValuedItem} like a {@link io.github.musicdoc.tone.Tone} or
 * {@link io.github.musicdoc.rythm.rest.Rest}.
 *
 * @see ValuedItem
 */
public class MusicalValue extends AbstractFraction<MusicalValue> {

  /**
   * Whole (1/1). Unlike other predefined {@link MusicalValue}s this value does not have a fixed length in {@link #_1_4
   * quarters} but lasts a full bar whatever the {@link Beat} may be. This is used for a whole rest that lasts a full
   * bar (instead of {@link #_4_4}).
   */
  public static final MusicalValue _1_1 = create(1, 1);

  /**
   * Whole (4/4) tone also called <em>semibreve</em>.
   *
   * @see #_1_1
   */
  public static final MusicalValue _4_4 = create(4, 4);

  /**
   * Half (1/2) also called <em>minim</em>.
   */
  public static final MusicalValue _1_2 = create(1, 2);

  /**
   * {@link MusicalValueVariation#PUNCTURED Punctuated} {@link #_1_2 half} (1/2) so actually 3/4.
   */
  public static final MusicalValue _1_2p = create(1, 2, MusicalValueVariation.PUNCTURED);

  /**
   * Fourth (1/4) also called <em>quarter</em> or </em><em>crotchet</em>.
   */
  public static final MusicalValue _1_4 = create(1, 4);

  /**
   * Fourth (3/4) what is as long as a {@link #_1_2p punctuated half}.
   */
  public static final MusicalValue _3_4 = create(3, 4);

  /**
   * {@link MusicalValueVariation#PUNCTURED Punctuated} {@link #_1_4 fourth} (1/4) so actually 3/8.
   */
  public static final MusicalValue _1_4p = create(1, 4, MusicalValueVariation.PUNCTURED);

  /**
   * Eighth (1/8) also called <em>quaver</em>.
   */
  public static final MusicalValue _1_8 = create(1, 8);

  /**
   * {@link MusicalValueVariation#PUNCTURED Punctuated} {@link #_1_8 eighth} (1/8) so actually 3/16.
   */
  public static final MusicalValue _1_8p = create(1, 8, MusicalValueVariation.PUNCTURED);

  /**
   * Sixteenth (1/16) also called <em>semiquaver</em>.
   */
  public static final MusicalValue _1_16 = create(1, 16);

  /**
   * Thirty-second fraction (1/32) also called <em>demi semi quaver</em>.
   */
  public static final MusicalValue _1_32 = create(1, 32);

  private MusicalValueVariation variation;

  /**
   * The constructor.
   *
   * @param beats - see {@link #getBeats()}.
   * @param fraction - see {@link #getUnit()}.
   */
  public MusicalValue(int beats, int fraction) {

    this(beats, fraction, MusicalValueVariation.NONE);
  }

  /**
   * The constructor.
   *
   * @param beats - see {@link #getBeats()}.
   * @param fraction - see {@link #getUnit()}.
   * @param variation - see {@link #getVariation()}.
   */
  public MusicalValue(int beats, int fraction, MusicalValueVariation variation) {

    super(beats, fraction);
    Objects.requireNonNull(variation, "variation");
    this.variation = variation;
  }

  /**
   * The copy constructor.
   *
   * @param fraction the {@link Fraction} to copy.
   */
  public MusicalValue(Fraction fraction) {

    super(fraction);
    this.variation = MusicalValueVariation.NONE;
  }

  private MusicalValue(MusicalValue value, MutableObjecteCopier copier) {

    super(value.beats, value.unit);
    this.variation = value.variation;
  }

  @Override
  public MusicalValue copy(MutableObjecteCopier copier) {

    return new MusicalValue(this, copier);
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public MusicalValue makeImmutable() {

    this.immutable = true;
    return this;
  }

  /**
   * @return the {@link MusicalValueVariation} of this {@link MusicalValue}.
   */
  public MusicalValueVariation getVariation() {

    return this.variation;
  }

  /**
   * @param variation the new value of {@link #getVariation() variation}.
   * @return a {@link MusicalValue} with the given {@link #getVariation() variation} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public MusicalValue setVariation(MusicalValueVariation variation) {

    if (variation == null) {
      variation = MusicalValueVariation.NONE;
    }
    if (this.variation == variation) {
      return this;
    }
    MusicalValue value = makeMutable();
    value.variation = variation;
    return value;
  }

  @Override
  public double getValue() {

    return getValue(true);
  }

  /**
   * @param withVariation {@code true} to include {@link #getVariation() variation}, {@code false} otherwise (to exclude
   *        it).
   * @return the value as {@link #getBeats() beats} / {@link #getUnit() fraction}.
   * @see #getValue()
   */
  public double getValue(boolean withVariation) {

    double value = super.getValue();
    if (withVariation) {
      value = value * this.variation.getValue();
    }
    return value;
  }

  /**
   * Determines if this value is relative. E.g. a {@link #_1_1 1/1} (whole value) is often considered to be equivalent
   * to {@link #_4_4 4/4} what is 4 times a {@link #_1_4}, what is actually wrong. Instead {@link #_1_1 1/1} is relative
   * to a given {@link Beat} so a whole rest last for one bar whatever the {@link Beat} is (so in case of 3/4 beat, its
   * {@link #toAbsoluteValue(Beat) absolute value} will be 3/4).
   *
   * @return {@code true} if {@link #getUnit() fraction} is {@code 1} and the value is relative to the {@link Beat},
   *         {@code false} otherwise.
   * @see #toAbsoluteValue(Beat)
   */
  public boolean isRelative() {

    return (this.unit == 1);
  }

  /**
   * @return {@code true} if NOT {@link #isRelative() relative} (absolute), <code>false</code> otherwise.
   * @see #isRelative()
   */
  public boolean isAbsolute() {

    return !isRelative();
  }

  /**
   * @return {@code true} if {@link #getVariation() variation} is {@link MusicalValueVariation#NONE}, {@code false}
   *         otherwise.
   */
  public boolean isNormalized() {

    return this.variation == MusicalValueVariation.NONE;
  }

  /**
   * @param beat the {@link Beat} used as base to make this {@link MusicalValue} absolute.
   * @return the {@link #isAbsolute() absolute} {@link MusicalValue} according to the given {@link Beat}. Will return
   *         the current {@link MusicalValue} ({@code this}) if already {@link #isAbsolute() absolute}.
   * @see #isRelative()
   */
  public MusicalValue toAbsoluteValue(Beat beat) {

    if (this.unit == 1) {
      return new MusicalValue(beat.getBeats() * this.beats, beat.getUnit(), this.variation);
    }
    return this;
  }

  @Override
  public MusicalValue normalize() {

    if (this.variation == MusicalValueVariation.NONE) {
      return super.normalize();
    }
    MusicalValue normalized = makeMutable();
    normalized.multiply(this.variation);
    normalized.variation = MusicalValueVariation.NONE;
    return normalized.normalize();
  }

  @Override
  protected boolean isEqualTo(AbstractFraction<?> other) {

    if (super.isEqualTo(other)) {
      return Objects.equals(this.variation, ((MusicalValue) other).variation);
    }
    return false;
  }

  @Override
  public void toString(StringBuilder sb) {

    super.toString(sb);
    sb.append(this.variation);
  }

  private static MusicalValue create(int beats, int unit) {

    return new MusicalValue(beats, unit, MusicalValueVariation.NONE).makeImmutable();
  }

  private static MusicalValue create(int beats, int unit, MusicalValueVariation variation) {

    return new MusicalValue(beats, unit, variation).makeImmutable();
  }

}
