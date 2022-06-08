/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.rythm.rest;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.format.FormatConstants;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsRest;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsRests;
import io.github.musicdoc.music.rythm.value.MusicalValue;
import io.github.musicdoc.music.rythm.value.ValuedItem;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link ValuedItem} for a rest (pause).
 */
public class Rest extends ValuedItem<Rest> {

  /** {@link Rest} lasting a {@link MusicalValue#_1_1 full bar}. */
  public static final Rest _1_1 = new Rest(MusicalValue._1_1);

  /** {@link Rest} lasting a {@link MusicalValue#_1_2 half note}. */
  public static final Rest _1_2 = new Rest(MusicalValue._1_2);

  /** {@link Rest} lasting a {@link MusicalValue#_1_4 quarter note}. */
  public static final Rest _1_4 = new Rest(MusicalValue._1_4);

  /** {@link Rest} lasting a {@link MusicalValue#_1_8 eighth note} (quaver). */
  public static final Rest _1_8 = new Rest(MusicalValue._1_8);

  /** {@link Rest} lasting a {@link MusicalValue#_1_16 sixteenth note} (semiquaver). */
  public static final Rest _1_16 = new Rest(MusicalValue._1_16);

  /** {@link Rest} lasting a {@link MusicalValue#_1_32 twenty-secondth note} (quaver). */
  public static final Rest _1_32 = new Rest(MusicalValue._1_32);

  private final boolean invisible;

  /**
   * The constructor.
   *
   * @param value - the {@link #getValue() value}.
   */
  public Rest(MusicalValue value) {

    this(value, false, new ArrayList<MusicalDecoration>());
  }

  /**
   * The constructor.
   *
   * @param value - the {@link #getValue() value}.
   * @param decorations - the {@link #getDecorations() decorations}.
   */
  public Rest(MusicalValue value, List<MusicalDecoration> decorations) {

    this(value, false, decorations);
  }

  /**
   * The constructor.
   *
   * @param value - the {@link #getValue() value}.
   * @param invisible - the invisible flag.
   * @param decorations - the {@link #getDecorations() decorations}.
   */
  public Rest(MusicalValue value, boolean invisible, List<MusicalDecoration> decorations) {

    super(value, decorations);
    this.invisible = invisible;
  }

  @Override
  public Rest transpose(int steps, boolean diatonic, TransposeContext context) {

    return this;
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    if (this.invisible) {
      return "";
    }
    String glyphs = null;
    if (context.isEnforceUnicode()) {
      glyphs = UnicodeGlyphsRests.get(this.value);
    } else {
      glyphs = SmuflGlyphsRest.get(this.value);
    }
    if (glyphs == null) {
      throw new IllegalStateException("Not implemented/supported");
    }
    String vGlyphs = this.value.getVariation().getGlyphs(context);
    if (vGlyphs == null) {
      throw new IllegalStateException("Not implemented/supported");
    } else if (!vGlyphs.isEmpty()) {
      glyphs = glyphs + vGlyphs;
    }
    return glyphs;
  }

  char getSymbol() {

    if (this.invisible) {
      return FormatConstants.REST_INVISIBLE;
    } else {
      return FormatConstants.REST_VISIBLE;
    }
  }

  @Override
  public String toString() {

    return "" + getSymbol() + getValue();
  }
}
