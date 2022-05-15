/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.harmony;

import io.github.musicdoc.music.tone.TonePitch;

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

  public boolean isFlat() {

    return (this == FLAT);
  }

  public boolean isSharp() {

    return (this == SHARP);
  }

  public boolean isNormal() {

    return (this == NORMAL);
  }

}
