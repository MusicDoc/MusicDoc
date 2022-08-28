/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.rythm.fraction;

import io.github.musicdoc.MusicalObject;

/**
 * Common interface for {@link io.github.musicdoc.rythm.beat.Beat} and
 * {@link io.github.musicdoc.rythm.value.MusicalValue}.
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
   * @return a normalized instance that is a reduction of this {@link Fraction} if possible. So e.g. {@code 2/4} will be
   *         normalized to {@code 1/2} or {@code 6/4} will be normalized to {@code 3/2}. Please note that this does not
   *         implement proper math and only works for cases relevant to music. Further, specific types such as
   *         {@link io.github.musicdoc.rythm.value.MusicalValue} may even change their {@link #getValue() value} as
   *         their {@link io.github.musicdoc.rythm.value.MusicalValue#getVariation()} like a
   *         {@link io.github.musicdoc.rythm.value.MusicalValueVariation#PUNCTURED punctuation} gets resolved.
   */
  default Fraction normalize() {

    return this;
  }

  /**
   * @return an instance of {@link PlainFraction} with the {@link #getBeats() beats} and {@link #getUnit() unit} from
   *         this {@link Fraction}. It will not use the {@link #normalize() normalized} values of this {@link Fraction}.
   */
  default PlainFraction asPlain() {

    return new PlainFraction(getBeats(), getUnit());
  }

}
