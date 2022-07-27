/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.clef;

import io.github.musicdoc.music.tone.Tone;

/**
 * The {@link ClefSymbol} identifies the {@link Clef#getSymbol() type} of a {@link Clef}.
 */
public enum ClefSymbol implements ClefObject {

  /**
   * The G-clef which is also called treble or violin clef. This is the most common clef used in modern music. If you
   * have proper unicode support you can see it here: &#119070;
   */
  G,

  /**
   * The F-clef which is also called bass clef. Besides the G-clef this is also commonly used in modern music. If you
   * have proper unicode support you can see it here: &#119074;
   */
  F,

  /**
   * The C-clef which is also called alto clef (or tenor clef according to placement). If you have proper unicode
   * support you can see it here: &#119073;
   */
  C,

  /**
   * The percussion clef 1 is used for regular percussion notation. As percussion scores can get rather complex and
   * denote many different percussion instruments different clefs can be used to distinguish the instruments (e.g. clef
   * 1 for snare and bass drum and clef 2 for more specific instruments). If you have proper unicode support you can see
   * it here: &#119077;
   */
  PERCUSSION_1,

  /**
   * The percussion clef 2 is used for additional percussion instruments. If you have proper unicode support you can see
   * it here: &#119078;
   */
  PERCUSSION_2;

  /**
   * @return the number of the {@link io.github.musicdoc.music.stave.Stave} line (starting with the first line from the
   *         bottom) where this clef is written by default.
   */
  public int getLineNumber() {

    if (this == G) {
      return 2;
    } else if (this == F) {
      return 4;
    } else if (this == C) {
      return 3;
    }
    return -1;
  }

  @Override
  public Tone getReferenceTone() {

    if (this == G) {
      return Tone.G4;
    } else if (this == F) {
      return Tone.F3;
    } else if (this == C) {
      return Tone.C4;
    }
    return null;
  }

  @Override
  public Tone getMiddleTone() {

    if (this == G) {
      return Tone.B4;
    } else if (this == F) {
      return Tone.D3;
    } else if (this == C) {
      return Tone.C4;
    }
    return null;
  }

}
