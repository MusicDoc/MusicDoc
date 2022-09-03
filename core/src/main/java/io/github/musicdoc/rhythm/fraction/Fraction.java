/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.rhythm.fraction;

import io.github.musicdoc.MusicalObject;
import io.github.musicdoc.rhythm.value.MusicalValue;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariation;

/**
 * This is the common interface for a fraction of {@link #getBeats() beats} / {@link #getUnit() unit} such as
 * {@link io.github.musicdoc.rhythm.metre.Metre} or {@link io.github.musicdoc.rhythm.value.MusicalValue}.
 * Implementations of {@link Fraction} can be simple like {@link PlainFraction} but also more complex such as
 * {@link MusicalValue} that may have one or multiple {@link #getVariation() variations}. So e.g. a {@link MusicalValue}
 * may be simply {@code 1/2} (half note or minim) but it may also be {@code 1/2} with a
 * {@link io.github.musicdoc.rhythm.value.variation.Punctuation#P1 single punctuation} as {@link #getVariation()
 * variation} what results in the same {@link #getValue() value} as a {@link #getPlain() plain} {@link MusicalValue} of
 * {@code 3/4}. A {@link MusicalValueVariation} may even have multiple {@link #getVariation() variation}s so e.g. our
 * punctuated half note may additionally be part of a {@link io.github.musicdoc.rhythm.value.variation.Tuplet} (e.g. a
 * triplet) what further changes its {@link #getValue() value}.<br>
 * The idea of this {@link Fraction} interface is to hide the complexity described above. Therefore {@link #getBeats()},
 * {@link #getUnit()}, and {@link #getValue()} will always return the normalized values where {@link #getVariation()
 * variations} are applied (multiplied). However via {@link #getPlain()} you can get access to the plain fraction and
 * via {@link #getVariation() variation} you can get (and iterate) potential variations in order to distinguish between
 * {@code 1/2.} and {@code 3/4} or determine which sequence of {@link io.github.musicdoc.note.Note}s belong to a
 * {@link io.github.musicdoc.rhythm.value.variation.Tuplet} and have to be marked accordingly.
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
   * @return the plain {@link Fraction} {@link #hasVariation() without any} {@link #getVariation() variation}.
   */
  SimpleFraction<?> getPlain();

  /**
   * @return the potential next {@link MusicalValueVariation variation}. Will be {@link MusicalValueVariation#NONE} for
   *         no variation.
   */
  default FractionVariation getVariation() {

    return MusicalValueVariation.NONE;
  }

  /**
   * @return {@code true} if this {@link Fraction} has an actual {@link #getVariation() variation} is, {@code false}
   *         otherwise ({@link #getVariation()} == {@link MusicalValueVariation#NONE}).
   */
  default boolean hasVariation() {

    return getVariation() != MusicalValueVariation.NONE;
  }

  /**
   * @return a normalized instance that is a reduction of this {@link Fraction} if possible. So e.g. {@code 2/4} will be
   *         normalized to {@code 1/2} or {@code 6/4} will be normalized to {@code 3/2}. Please note that this does not
   *         implement proper math and only works for cases relevant to music.
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
