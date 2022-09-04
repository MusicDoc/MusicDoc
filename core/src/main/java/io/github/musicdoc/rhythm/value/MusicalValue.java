/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.rhythm.value;

import java.util.Objects;

import io.github.musicdoc.AbstractMusicalObject;
import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.rhythm.fraction.Fraction;
import io.github.musicdoc.rhythm.fraction.FractionVariation;
import io.github.musicdoc.rhythm.fraction.PlainFraction;
import io.github.musicdoc.rhythm.fraction.SimpleFraction;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.rhythm.punctuation.Punctuation;
import io.github.musicdoc.rhythm.tuplet.Tuplet;

/**
 * The value of a {@link io.github.musicdoc.rhythm.item.ValuedItem} like a {@link io.github.musicdoc.tone.Tone} or
 * {@link io.github.musicdoc.rhythm.rest.Rest} that defines its duration.<br>
 * A {@link MusicalValue} may {@link #hasVariation() have variations}:
 * <ul>
 * <li>{@link Punctuation} - see {@link #getPunctuation()}</li>
 * <li>{@link Tuplet} - see {@link #getTuplet() tuplet}</li>
 * </ul>
 *
 * If present such variation will modify the actual value as described in their according JavaDoc.
 *
 *
 * @see io.github.musicdoc.rhythm.item.ValuedItem
 */
public final class MusicalValue extends AbstractMusicalObject implements Fraction, MutableObject<MusicalValue> {

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

  private static final MusicalValue[] VALUES = { _1_1, _4_4, _1_2, _1_2p, _3_4, _1_4, _1_4p, _1_8, _1_8p, _1_16,
  _1_32 };

  private boolean immutable;

  private final PlainFraction plain;

  private Punctuation punctuation;

  private Tuplet tuplet;

  /**
   * The constructor.
   *
   * @param beats - see {@link #getBeats()}.
   * @param fraction - see {@link #getUnit()}.
   */
  public MusicalValue(int beats, int fraction) {

    this(beats, fraction, null, null);
  }

  /**
   * The constructor.
   *
   * @param beats the {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit}.
   * @param punctuation the {@link #getPunctuation() punctuation}.
   */
  public MusicalValue(int beats, int unit, Punctuation punctuation) {

    this(beats, unit, punctuation, null);
  }

  /**
   * The constructor.
   *
   * @param beats the {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit}.
   * @param punctuation the {@link #getPunctuation() punctuation}.
   * @param tuplet the {@link #getTuplet() tuplet}.
   */
  public MusicalValue(int beats, int unit, Punctuation punctuation, Tuplet tuplet) {

    super();
    this.plain = new PlainFraction(beats, unit);
    this.punctuation = punctuation;
    this.tuplet = tuplet;
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

    super();
    // by intention do not use copier.copy here
    this.plain = copy(value.plain);
    this.punctuation = value.punctuation;
    this.tuplet = value.tuplet;
  }

  @SuppressWarnings("unchecked")
  private static <T extends Fraction & MutableObject<?>> T copy(T fraction) {

    if (fraction instanceof FractionVariation) {
      return fraction;
    }
    return (T) fraction.copy();
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

    if (!this.immutable) {
      this.plain.makeImmutable();
      this.immutable = true;
    }
    return this;
  }

  /**
   * @return the optional {@link Punctuation} or {@code null} for none.
   */
  public Punctuation getPunctuation() {

    return this.punctuation;
  }

  /**
   * @param punctuation the new value of {@link #getPunctuation() punctuation}. May be {@code null}.
   * @return a {@link MusicalValue} with the given {@link #getPunctuation() punctuation} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public MusicalValue setPunctuation(Punctuation punctuation) {

    if (punctuation == this.punctuation) {
      return this;
    }
    MusicalValue value = makeMutable();
    value.punctuation = punctuation;
    return value;
  }

  /**
   * @return the number of {@link Punctuation} dots or {@code 0} for none.
   */
  public int getPunctuationCount() {

    if (this.punctuation != null) {
      return this.punctuation.getPunctuationCount();
    }
    return 0;
  }

  /**
   * @param punctuationCount the new value of {@link #getPunctuationCount() punctuationCount}.
   * @return a {@link MusicalValue} with the given {@link #getPunctuationCount() punctuationCount} and all other
   *         properties like {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public MusicalValue setPunctuationCount(int punctuationCount) {

    if ((punctuationCount < 0) || (punctuationCount > 3)) {
      throw new IllegalArgumentException(String.valueOf(punctuationCount));
    }
    return setPunctuation(Punctuation.of(punctuationCount));
  }

  /**
   * @return the optional {@link Tuplet} or {@code null} for none.
   */
  public Tuplet getTuplet() {

    return this.tuplet;
  }

  /**
   * @param tuplet the new value of {@link #getTuplet() tuplet}. May be {@code null}.
   * @return a {@link MusicalValue} with the given {@link #getPunctuation() punctuation} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public MusicalValue setTuplet(Tuplet tuplet) {

    if (tuplet == this.tuplet) {
      return this;
    }
    MusicalValue value = makeMutable();
    value.tuplet = tuplet;
    return value;
  }

  /**
   * @return {@code true} if this {@link Fraction} has a variation ({@link #getPunctuation() punctuation} or
   *         {@link #getTuplet() tuplet}), {@code false} otherwise.
   */
  public boolean hasVariation() {

    return (this.punctuation == null) && (this.tuplet == null);
  }

  @Override
  public int getBeats() {

    int beats = this.plain.getBeats();
    if (this.punctuation != null) {
      beats = beats * this.punctuation.getBeats();
    }
    if (this.tuplet != null) {
      beats = beats * this.tuplet.getBeats();
    }
    return beats;
  }

  @Override
  public int getUnit() {

    int unit = this.plain.getUnit();
    if (this.punctuation != null) {
      unit = unit * this.punctuation.getUnit();
    }
    if (this.tuplet != null) {
      unit = unit * this.tuplet.getUnit();
    }
    return unit;
  }

  /**
   * @return the plain {@link Fraction} {@link #hasVariation() without any} {@link #hasVariation() variation}.
   */
  @Override
  public PlainFraction getPlain() {

    return this.plain;
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
      return new MusicalValue(beat.getBeats(), beat.getUnit(), this.punctuation, this.tuplet);
    }
    return this;
  }

  @Override
  public MusicalValue normalize() {

    SimpleFraction<?> plainNormalized = this.plain.normalize();
    if (plainNormalized == this.plain) {
      if ((this.punctuation == null) && (this.tuplet == null)) {
        return this;
      }
    }
    return new MusicalValue(this);
  }

  @Override
  public int hashCode() {

    return 31 * getBeats() + getUnit();
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (obj.getClass() != MusicalValue.class)) {
      return false;
    }
    MusicalValue other = (MusicalValue) obj;
    if (!Objects.equals(this.plain, other.plain)) {
      return false;
    } else if (this.punctuation != other.punctuation) {
      return false;
    } else if (!Objects.equals(this.tuplet, other.tuplet)) {
      return false;
    }
    return true;
  }

  @Override
  public void toString(StringBuilder sb) {

    this.plain.toString(sb);
    if (this.punctuation != null) {
      this.punctuation.toString(sb);
    }
    if (this.tuplet != null) {
      this.tuplet.toString(sb);
    }
  }

  private static MusicalValue create(int beats, int unit) {

    return new MusicalValue(beats, unit, null).makeImmutable();
  }

  private static MusicalValue create(int beats, int unit, Punctuation punctuation) {

    return new MusicalValue(beats, unit, punctuation).makeImmutable();
  }

  /**
   * @param beats the {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit}.
   * @param punctuation the {@link #getPunctuation() punctuation}.
   * @param tuplet the {@link #getTuplet() tuplet}.
   * @return the {@link MusicalValue} with the given values. May return one of the predefined {@link #isImmutable()
   *         immutable} value types if matching the given values.
   */
  public static MusicalValue of(int beats, int unit, Punctuation punctuation, Tuplet tuplet) {

    if (tuplet == null) {
      for (MusicalValue value : VALUES) {
        if ((value.getPlain().getBeats() == beats) && (value.getPlain().getUnit() == unit)
            && (value.getPunctuation() == punctuation)) {
          return value;
        }
      }
    }
    return new MusicalValue(beats, unit, punctuation, tuplet);
  }

}
