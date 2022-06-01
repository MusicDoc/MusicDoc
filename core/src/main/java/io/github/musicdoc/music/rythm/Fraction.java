/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.rythm;

/**
 * Common interface for Beat and {@link MusicalValue}.
 */
public interface Fraction {

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
