/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.rhythm.fraction;

import io.github.musicdoc.MusicalObject;

/**
 * This is the common interface for a fraction of {@link #getBeats() beats} / {@link #getUnit() unit} such as
 * {@link io.github.musicdoc.rhythm.metre.Metre} or {@link io.github.musicdoc.rhythm.value.MusicalValue}.
 * Implementations of {@link Fraction} can be simple like {@link PlainFraction} but also more complex such as
 * {@link io.github.musicdoc.rhythm.value.MusicalValue}. The idea of this {@link Fraction} interface is to hide such
 * complexity (see {@link io.github.musicdoc.rhythm.value.MusicalValue}). Therefore {@link #getBeats()},
 * {@link #getUnit()}, and {@link #getValue()} will always return the values with potential
 * {@link io.github.musicdoc.rhythm.value.MusicalValue#hasVariation() variations} applied (multiplied).
 *
 * @see io.github.musicdoc.rhythm.value.MusicalValue
 * @see io.github.musicdoc.rhythm.metre.Metre
 * @see PlainFraction
 */
public interface Fraction extends MusicalObject {

  /**
   * @return the beats
   */
  int getBeats();

  /**
   * @return the unit so e.g. 2 for halves, 4 for quarters.
   */
  int getUnit();

  /**
   * @return the value as {@link #getBeats() beats} / {@link #getUnit() unit}.
   */
  default double getValue() {

    double value = getBeats();
    value = value / getUnit();
    return value;
  }

  /**
   * @return the plain {@link Fraction} without any {@link io.github.musicdoc.rhythm.value.MusicalValue#hasVariation()
   *         variation}. May be {@code this} object itself.
   */
  SimpleFraction<?> getPlain();

  /**
   * @return an instance of {@link PlainFraction} with the {@link #getBeats() beats} and {@link #getUnit() unit} from
   *         this {@link Fraction}. It will not use the {@link #normalize() normalized} values of this {@link Fraction}.
   */
  default PlainFraction asPlain() {

    return new PlainFraction(getBeats(), getUnit());
  }

  /**
   * @return a normalized instance that is a reduction of this {@link Fraction} if possible. So e.g. {@code 2/4} will be
   *         normalized to {@code 1/2} or {@code 6/4} will be normalized to {@code 3/2}. Please note that this does not
   *         implement proper math and only works for cases relevant to music.
   */
  default Fraction normalize() {

    return this;
  }

}
