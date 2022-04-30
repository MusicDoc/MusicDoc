/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.rythm;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.format.FormatConstants;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link ValuedItem} for a rest (pause).
 */
public class Rest extends ValuedItem<Rest> {

  public static final Rest _1_1 = new Rest(MusicalValue._1_1);

  public static final Rest _1_2 = new Rest(MusicalValue._1_2);

  public static final Rest _1_4 = new Rest(MusicalValue._1_4);

  public static final Rest _1_8 = new Rest(MusicalValue._1_8);

  public static final Rest _1_16 = new Rest(MusicalValue._1_16);

  public static final Rest _1_32 = new Rest(MusicalValue._1_32);

  private final boolean invisible;

  /**
   * The constructor.
   *
   * @param value - the {@link #getValue() value}.
   */
  public Rest(MusicalValue value) {

    this(value, false, new ArrayList<ValuedItemDecoration>());
  }

  /**
   * The constructor.
   *
   * @param value - the {@link #getValue() value}.
   * @param decorations - the {@link #getDecorations() decorations}.
   */
  public Rest(MusicalValue value, List<ValuedItemDecoration> decorations) {

    this(value, false, decorations);
  }

  /**
   * The constructor.
   *
   * @param value - the {@link #getValue() value}.
   * @param invisible - the invisible flag.
   * @param decorations - the {@link #getDecorations() decorations}.
   */
  public Rest(MusicalValue value, boolean invisible, List<ValuedItemDecoration> decorations) {

    super(value, decorations);
    this.invisible = invisible;
  }

  @Override
  public Rest transpose(int steps, boolean diatonic, TransposeContext context) {
    return this;
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
