/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.rythm.value;

import java.util.Objects;

import io.github.musicdoc.music.rythm.Fraction;
import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.rythm.rest.Rest;
import io.github.musicdoc.music.tone.Tone;

/**
 * The value of a {@link Tone} or {@link Rest}.
 *
 * @see ValuedItem
 */
public class MusicalValue implements Fraction {

  /**
   * Whole (1/1). Unlike other predefined {@link MusicalValue}s this value does not have a fixed length in {@link #_1_4
   * quarters} but lasts a full bar whatever the {@link Beat} may be. This is used for a whole rest that lasts a full
   * bar (instead of {@link #_4_4}).
   */
  public static final MusicalValue _1_1 = new MusicalValue(1, 1);

  /**
   * Whole (4/4) tone also called <em>semibreve</em>.
   *
   * @see #_1_1
   */
  public static final MusicalValue _4_4 = new MusicalValue(4, 4);

  /**
   * Half (1/2) also called <em>minim</em>.
   */
  public static final MusicalValue _1_2 = new MusicalValue(1, 2);

  /**
   * Fourth (1/4) also called <em>quarter</em> or </em><em>crotchet</em>.
   */
  public static final MusicalValue _1_4 = new MusicalValue(1, 4);

  /**
   * Eighth (1/8) also called <em>quaver</em>.
   */
  public static final MusicalValue _1_8 = new MusicalValue(1, 8);

  /**
   * Sixteenth (1/16) also called <em>semiquaver</em>.
   */
  public static final MusicalValue _1_16 = new MusicalValue(1, 16);

  /**
   * Thirty-second fraction (1/32) also called <em>demi semi quaver</em>.
   */
  public static final MusicalValue _1_32 = new MusicalValue(1, 32);

  private final int beats;

  private final int fraction;

  private final MusicalValueVariation variation;

  /**
   * The constructor.
   *
   * @param beats - see {@link #getBeats()}.
   * @param fraction - see {@link #getFraction()}.
   */
  public MusicalValue(int beats, int fraction) {

    this(beats, fraction, MusicalValueVariation.NONE);
  }

  /**
   * The constructor.
   *
   * @param beats - see {@link #getBeats()}.
   * @param fraction - see {@link #getFraction()}.
   * @param variation - see {@link #getVariation()}.
   */
  public MusicalValue(int beats, int fraction, MusicalValueVariation variation) {

    super();
    Objects.requireNonNull(variation, "variation");
    if (beats < 1) {
      throw new IllegalArgumentException("Invalid beats value " + beats + " - must be positive!");
    }
    if (fraction < 1) {
      throw new IllegalArgumentException("Invalid fraction value " + fraction + " - must be positive!");
    }
    this.beats = beats;
    this.fraction = fraction;
    this.variation = variation;
  }

  @Override
  public int getBeats() {

    return this.beats;
  }

  @Override
  public int getFraction() {

    return this.fraction;
  }

  /**
   * @return the {@link MusicalValueVariation} of this {@link MusicalValue}.
   */
  public MusicalValueVariation getVariation() {

    return this.variation;
  }

  @Override
  public double getValue() {

    return getValue(true);
  }

  /**
   * @param withVariation {@code true} to include {@link #getVariation() variation}, {@code false} otherwise (to exclude
   *        it).
   * @return the value as {@link #getBeats() beats} / {@link #getFraction() fraction}.
   * @see #getValue()
   */
  public double getValue(boolean withVariation) {

    double value = Fraction.super.getValue();
    if (withVariation) {
      value = value * this.variation.getValue();
    }
    return value;
  }

  /**
   * Determines if this value is relative. E.g. a {@link #_4_4} (whole value, 1/1) is often considered to be equivalent
   * to 4/4 as 4 times a {@link #_1_4}, what is actually wrong.
   *
   * @return {@code true} if {@link #getFraction() fraction} is {@code 1} and the value is relative to the {@link Beat},
   *         {@code false} otherwise.
   */
  public boolean isRelative() {

    return (this.fraction == 1);
  }

  /**
   * @return {@code true} if NOT {@link #isRelative() relative} (absolute), <code>false</code> otherwise.
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
   */
  public MusicalValue toAbsoluteValue(Beat beat) {

    if (this.fraction == 1) {
      return new MusicalValue(beat.getBeats() * this.beats, beat.getFraction(), this.variation);
    }
    return this;
  }

  /**
   * @return the normalized {@link Fraction fraction} of this {@link MusicalValue} so that {@link #getVariation()
   *         variation} is {@link MusicalValueVariation#NONE}. Will return the current {@link MusicalValue}
   *         ({@code this}) if already {@link #isNormalized() normalized}.
   */
  public MusicalValue toNormalizedValue() {

    if (this.variation == MusicalValueVariation.NONE) {
      return this;
    }
    return new MusicalValue(this.variation.getBeats() * this.beats, this.variation.getFraction() * this.fraction);
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
    MusicalValue other = (MusicalValue) obj;
    if (this.beats != other.beats) {
      return false;
    }
    if (this.fraction != other.fraction) {
      return false;
    }
    if (!Objects.equals(this.variation, other.variation)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder(5);
    sb.append(this.beats);
    sb.append('/');
    sb.append(this.fraction);
    sb.append(this.variation);
    return sb.toString();
  }

}
