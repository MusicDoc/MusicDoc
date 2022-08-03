/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.harmony;

import io.github.musicdoc.tone.pitch.TonePitch;

/**
 * The style of an enharmonic change.
 */
public enum EnharmonicStyle {

  /** The {@link TonePitch#isFlat() flattened} enharmonic form of a {@link TonePitch}. */
  FLAT,

  /** The {@link TonePitch#isSharp() sharpened} enharmonic form of a {@link TonePitch}. */
  SHARP,

  /** The {@link TonePitch#isNormal() normal} form of a {@link TonePitch}. */
  NORMAL;

  /**
   * @return {@code true} if {@link #FLAT}, {@code false} otherwise.
   */
  public boolean isFlat() {

    return (this == FLAT);
  }

  /**
   * @return {@code true} if {@link #SHARP}, {@code false} otherwise.
   */
  public boolean isSharp() {

    return (this == SHARP);
  }

  /**
   * @return {@code true} if {@link #NORMAL}, {@code false} otherwise.
   */
  public boolean isNormal() {

    return (this == NORMAL);
  }

}
