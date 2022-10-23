/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.rhythm.rest;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.format.FormatConstants;
import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsRest;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsRests;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.rhythm.punctuation.Punctuation;
import io.github.musicdoc.rhythm.value.MusicalValue;
import io.github.musicdoc.transpose.TransposeContext;

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

  private boolean invisible;

  /**
   * The constructor.
   *
   * @param value - the {@link #getValue() value}.
   */
  public Rest(MusicalValue value) {

    this(value, false, new ArrayList<>());
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
   * @param invisible - the {@link #isInvisible() invisible} flag.
   * @param decorations - the {@link #getDecorations() decorations}.
   */
  public Rest(MusicalValue value, boolean invisible, List<MusicalDecoration> decorations) {

    super(value, decorations);
    this.invisible = invisible;
  }

  private Rest(Rest rest, MutableObjecteCopier copier) {

    super(rest, copier);
    this.invisible = rest.invisible;
  }

  @Override
  public Rest copy(MutableObjecteCopier copier) {

    return new Rest(this, copier);
  }

  /**
   * @return {@code true} if invisible, {@code false} otherwise.
   */
  public boolean isInvisible() {

    return this.invisible;
  }

  /**
   * @param invisible the new value of {@link #isInvisible()}.
   * @return a {@link Rest} with the given {@link #isInvisible() invisble flag} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public Rest setInvisible(boolean invisible) {

    if (this.invisible == invisible) {
      return this;
    }
    Rest rest = makeMutable();
    rest.invisible = invisible;
    return rest;
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
      glyphs = UnicodeGlyphsRests.get(this.value.getPlain());
    } else {
      glyphs = SmuflGlyphsRest.get(this.value.getPlain());
    }
    Punctuation punctuation = this.value.getPunctuation();
    if (punctuation != null) {
      String pGlyphs = punctuation.getGlyphs(context);
      if (pGlyphs == null) {
        glyphs = null;
      } else {
        glyphs = glyphs + pGlyphs;
      }
    }
    if (glyphs == null) {
      throw new IllegalStateException("Not implemented/supported");
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
  protected void toStringItem(StringBuilder sb) {

    sb.append(getSymbol());
    super.toStringItem(sb);
  }
}
