/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.rythm;

import io.github.musicdoc.MusicalObject;

/**
 * Common interface for {@link io.github.musicdoc.music.rythm.beat.Beat} and
 * {@link io.github.musicdoc.music.rythm.value.MusicalValue}.
 */
public interface Fraction extends MusicalObject {

  /**
   * @return the beats
   */
  int getBeats();

  /**
   * @return the perUnit
   */
  int getFraction();

  /**
   * @return the value as {@link #getBeats() beats} / {@link #getFraction() fraction}.
   */
  default double getValue() {

    double value = getBeats();
    value = value / getFraction();
    return value;
  }

}
